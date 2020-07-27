package com.finartz.flightmanagement.domain;

import com.finartz.flightmanagement.application.AirlineService;
import com.finartz.flightmanagement.infrastructure.repository.AirlineRepository;
import com.finartz.flightmanagement.interfaces.request.AddAirlineRequest;
import com.finartz.flightmanagement.interfaces.request.UpdateAirlineRequest;

import java.util.List;

public class DefaultAirlineService implements AirlineService {

    private final AirlineRepository repository;

    public DefaultAirlineService(AirlineRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addAirline(AddAirlineRequest request) {
        final Airline airline = Airline.builder().airline(request.getAirlineName()).build();
        repository.save(airline);
    }

    @Override
    public void updateAirline(UpdateAirlineRequest request) {
        final Airline airline = repository.findById(request.getId()).orElseThrow(() -> AirlineNotFoundException.create(request.getId()));

        airline.setAirline(request.getAirlineName());

        repository.save(airline);
    }

    @Override
    public void deleteAirline(String id) {
        final Airline airline = repository.findById(id).orElseThrow(() -> AirlineNotFoundException.create(id));

        repository.delete(airline);
    }

    @Override
    public List<Airline> getAllAirlines() {
        return repository.findAll();
    }
}
