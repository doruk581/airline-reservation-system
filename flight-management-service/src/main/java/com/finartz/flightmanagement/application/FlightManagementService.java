package com.finartz.flightmanagement.application;

import com.finartz.flightmanagement.domain.Flight;
import com.finartz.flightmanagement.interfaces.request.AddFlightRequest;
import com.finartz.flightmanagement.interfaces.request.FlightReservationRequest;
import com.finartz.flightmanagement.interfaces.response.FlightReservationResponse;

import java.util.List;

public interface FlightManagementService {

    void addFlight(final AddFlightRequest request);
    List<Flight> searchByAirlineId(final String id);
    FlightReservationResponse makeReservation(final FlightReservationRequest reservationRequest);
    void cancelReservation(final FlightReservationRequest reservationRequest);
}
