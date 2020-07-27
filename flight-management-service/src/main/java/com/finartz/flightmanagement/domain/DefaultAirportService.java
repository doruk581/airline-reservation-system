package com.finartz.flightmanagement.domain;

import com.finartz.flightmanagement.application.AirportService;
import com.finartz.flightmanagement.infrastructure.repository.AirportRepository;
import com.finartz.flightmanagement.interfaces.request.AirportRequest;

import java.util.List;

public class DefaultAirportService implements AirportService {

    private final AirportRepository airportRepository;

    public DefaultAirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @Override
    public void addAirport(AirportRequest request) {
        final Airport airport = Airport
                .builder()
                .airportName(request.getAirportName())
                .code(request.getAirportCode())
                .country(request.getCountry())
                .build();

        airportRepository.save(airport);
    }

    @Override
    public void updateAirport(AirportRequest request) {
        final Airport airport = airportRepository.findById(request.getAirportCode())
                .orElseThrow(() -> AirportNotFoundException.create(request.getAirportCode()));

        airport.setAirportName(request.getAirportName());
        airport.setCountry(request.getCountry());

        airportRepository.save(airport);
    }

    @Override
    public void deleteAirport(String code) {
        final Airport airport = airportRepository.findById(code)
                .orElseThrow(() -> AirportNotFoundException.create(code));

        airportRepository.delete(airport);
    }

    @Override
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }
}
