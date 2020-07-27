package com.finartz.flightmanagement.domain;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AirportNotFoundExceptionTest {

    @Test
    public void controlThatCreateMethodShouldReturnCorrectException(){
        final AirportNotFoundException exception = AirportNotFoundException.create("SAW");

        assertThat(exception.getMessage(),is(equalTo("Airport not found for airport code: SAW")));
    }
}