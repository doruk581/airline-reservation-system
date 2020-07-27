package com.finartz.flightmanagement.domain;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AirlineNotFoundExceptionTest {

    @Test
    public void controlThatCreateMethodShouldReturnCorrectException(){
        final String airlineId = "SAW";
        final AirlineNotFoundException exception = AirlineNotFoundException.create(airlineId);

        assertThat(exception.getMessage(),is(equalTo("Airline not found for airline id: SAW")));
    }

}