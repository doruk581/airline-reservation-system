package com.finartz.flightmanagement.domain;

import com.finartz.flightmanagement.application.RouteService;
import com.finartz.flightmanagement.infrastructure.repository.AirportRepository;
import com.finartz.flightmanagement.infrastructure.repository.RouteRepository;
import com.finartz.flightmanagement.interfaces.request.AddRouteRequest;

import java.util.List;

public class DefaultRouteService implements RouteService {

    private final RouteRepository routeRepository;
    private final AirportRepository airportRepository;

    public DefaultRouteService(RouteRepository routeRepository, AirportRepository airportRepository) {
        this.routeRepository = routeRepository;
        this.airportRepository = airportRepository;
    }

    @Override
    public Long addRoute(final AddRouteRequest addRouteRequest) {
        final Airport departureAirport = findAirport(addRouteRequest.getDepartureAirportCode());

        final Airport arrivalAirport = findAirport(addRouteRequest.getArrivalAirportCode());

        final Route route = Route.builder().arrivalAirport(arrivalAirport).departureAirport(departureAirport).build();

        return routeRepository.save(route).getId();
    }

    @Override
    public void deleteRoute(Long id) {
        routeRepository.deleteById(id);
    }

    @Override
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public Airport findAirport(final String airportCode) {
        return airportRepository
                .findById(airportCode)
                .orElseThrow(() -> AirportNotFoundException.create(airportCode));
    }
}
