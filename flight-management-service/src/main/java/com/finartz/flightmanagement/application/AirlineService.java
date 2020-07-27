package com.finartz.flightmanagement.application;

import com.finartz.flightmanagement.domain.Airline;
import com.finartz.flightmanagement.interfaces.request.AddAirlineRequest;
import com.finartz.flightmanagement.interfaces.request.UpdateAirlineRequest;

import java.util.List;

public interface AirlineService {
    void addAirline(final AddAirlineRequest request);

    void updateAirline(final UpdateAirlineRequest request);

    void deleteAirline(final String id);

    List<Airline> getAllAirlines();
}
