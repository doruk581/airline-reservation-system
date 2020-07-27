package com.finartz.flightmanagement.domain;

import com.finartz.flightmanagement.infrastructure.repository.AirlineRepository;
import com.finartz.flightmanagement.infrastructure.repository.RouteRepository;
import com.finartz.flightmanagement.interfaces.request.AddFlightRequest;
import com.finartz.flightmanagement.interfaces.request.dto.DateDto;

import java.time.LocalDateTime;

public class DefaultFlightFactory implements FlightFactory {

    private final AirlineRepository airlineRepository;
    private final RouteRepository routeRepository;

    public DefaultFlightFactory(AirlineRepository airlineRepository, RouteRepository routeRepository) {
        this.airlineRepository = airlineRepository;
        this.routeRepository = routeRepository;
    }

    @Override
    public Flight createFrom(AddFlightRequest request) {
        final Airline airline = airlineRepository.findById(request.getAirlineId()).orElseThrow(() -> AirlineNotFoundException.create(request.getAirlineId()));
        final Route route = routeRepository.findById(request.getRouteId()).orElseThrow(() -> RouteNotFoundException.create(request.getArrivalAirportId()));

        final DateDto dateDto = request.getDepartureDate();
        final LocalDateTime departureDate = LocalDateTime.of(dateDto.getYear(),dateDto.getMonth(),dateDto.getDay(),dateDto.getHour(),dateDto.getMinute());

        return Flight.builder()
                .airline(airline)
                .route(route)
                .departureTime(departureDate)
                .availableSeatCount(request.getSeatCount())
                .capacity(request.getSeatCount())
                .ticketPrice(request.getTicketPrice())
                .build();
    }
}
