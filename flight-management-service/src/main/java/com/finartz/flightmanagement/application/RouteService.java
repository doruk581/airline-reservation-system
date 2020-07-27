package com.finartz.flightmanagement.application;

import com.finartz.flightmanagement.domain.Route;
import com.finartz.flightmanagement.interfaces.request.AddRouteRequest;

import java.util.List;

public interface RouteService {
    Long addRoute(final AddRouteRequest addRouteRequest);

    void deleteRoute(final Long id);

    List<Route> getAllRoutes();
}
