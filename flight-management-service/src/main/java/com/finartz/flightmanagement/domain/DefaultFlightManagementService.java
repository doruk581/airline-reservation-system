package com.finartz.flightmanagement.domain;

import com.finartz.flightmanagement.application.FlightManagementService;
import com.finartz.flightmanagement.interfaces.request.AddFlightRequest;
import com.finartz.flightmanagement.interfaces.request.FlightReservationRequest;
import com.finartz.flightmanagement.interfaces.response.FlightReservationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
public class DefaultFlightManagementService implements FlightManagementService {

    private final FlightRepository repository;
    private final FlightFactory flightFactory;

    public DefaultFlightManagementService(FlightRepository repository, FlightFactory flightFactory) {
        this.repository = repository;
        this.flightFactory = flightFactory;
    }

    @Override
    public void addFlight(AddFlightRequest request) {
        final Flight flight = flightFactory.createFrom(request);
        repository.save(flight);
    }

    @Override
    public List<Flight> searchByAirlineId(String id) {
        return repository.findAllByAirline(id);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public FlightReservationResponse makeReservation(FlightReservationRequest reservationRequest) {
        final Flight flight = repository.findById(reservationRequest.getId()).
                orElseThrow(() -> FlightNotFoundException.create(reservationRequest.getId()));

        if (!flight.isAvailableForReservation())
            return FlightReservationResponse.builder().isSuccessful(Boolean.FALSE).message(Constants.CAPACITY_EXCEED).build();

        flight.decrementAvailableSeatCount();

        if (isAvailableSeatAtBarrageOfPercent(flight))
            flight.incrementTicketPriceBy10Percent();

        repository.update(flight);

        return FlightReservationResponse.builder()
                .isSuccessful(Boolean.TRUE)
                .airlineCode(flight.getAirline().getAirline())
                .departureTime(flight.getDepartureTime())
                .ticketPrice(flight.getTicketPrice())
                .build();
    }

    //Normally I use specification pattern since there are only two specifications,so it is not necessary in case of code crowd.
    public Boolean isAvailableSeatAtBarrageOfPercent(final Flight flight){
        final Integer currentCount = flight.getCapacity()/10;

        return (flight.getAvailableSeatCount() - currentCount) %2 == 0;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void cancelReservation(FlightReservationRequest reservationRequest) {
        final Flight flight = repository.findById(reservationRequest.getId()).
                orElseThrow(() -> FlightNotFoundException.create(reservationRequest.getId()));

        flight.incrementAvailableSeatCount();

        repository.update(flight);
    }
}
