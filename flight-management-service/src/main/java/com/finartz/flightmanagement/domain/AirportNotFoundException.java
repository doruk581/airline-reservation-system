package com.finartz.flightmanagement.domain;

import java.util.NoSuchElementException;

public class AirportNotFoundException extends NoSuchElementException {

    private final String airportCode;

    private AirportNotFoundException(String airportCode) {
        this.airportCode = airportCode;
    }

    public static AirportNotFoundException create(String airportCode) {
        return new AirportNotFoundException(airportCode);
    }

    @Override
    public String getMessage() {
        return "Airport not found for airport code: " + airportCode;
    }
}
