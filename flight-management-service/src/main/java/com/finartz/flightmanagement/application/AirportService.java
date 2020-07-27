package com.finartz.flightmanagement.application;

import com.finartz.flightmanagement.domain.Airport;
import com.finartz.flightmanagement.interfaces.request.AirportRequest;

import java.util.List;

public interface AirportService {
    void addAirport(final AirportRequest request);

    void updateAirport(final AirportRequest request);

    void deleteAirport(final String code);

    List<Airport> getAllAirports();
}
