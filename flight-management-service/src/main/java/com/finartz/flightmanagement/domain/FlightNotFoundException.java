package com.finartz.flightmanagement.domain;

import java.util.NoSuchElementException;

public class FlightNotFoundException extends NoSuchElementException {

    private final Long id;

    private FlightNotFoundException(Long id) {
        this.id = id;
    }

    public static FlightNotFoundException create(Long id) {
        return new FlightNotFoundException(id);
    }

    @Override
    public String getMessage() {
        return "Flight not found for flight id: " + id;
    }
}
