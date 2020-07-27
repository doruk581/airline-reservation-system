package com.finartz.flightmanagement.domain;

import java.util.NoSuchElementException;

public class AirlineNotFoundException extends NoSuchElementException {

    private final String airlineId;


    private AirlineNotFoundException(String airlineId) {
        this.airlineId = airlineId;
    }

    public static AirlineNotFoundException create(String airlineId) {
        return new AirlineNotFoundException(airlineId);
    }

    @Override
    public String getMessage() {
        return "Airline not found for airline id: " + airlineId;
    }
}
