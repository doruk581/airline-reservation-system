package com.finartz.flightmanagement.domain;

import java.util.NoSuchElementException;

public class RouteNotFoundException extends NoSuchElementException {

    private final Long routeId;

    private RouteNotFoundException(Long routeId) {
        this.routeId = routeId;
    }

    public static RouteNotFoundException create(Long routeId) {
        return new RouteNotFoundException(routeId);
    }

    @Override
    public String getMessage() {
        return "Route not found for route id: " + routeId;
    }
}
