package com.finartz.flightmanagement.domain;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class FlightTest {

    @Test
    public void controlThatDecrementAvailableSeatCountWorksCorrectly(){
        final Flight flight = Flight.builder()
                .id(1L)
                .ticketPrice(BigDecimal.valueOf(1000))
                .availableSeatCount(50)
                .build();

        flight.decrementAvailableSeatCount();

        assertThat(flight.getAvailableSeatCount(),is(equalTo(49)));
    }

    @Test
    public void controlThatIncrementAvailableSeatCountWorksCorrectly(){
        final Flight flight = Flight.builder()
                .id(1L)
                .ticketPrice(BigDecimal.valueOf(1000))
                .availableSeatCount(50)
                .build();

        flight.incrementAvailableSeatCount();

        assertThat(flight.getAvailableSeatCount(),is(equalTo(51)));
    }

    @Test
    public void controlThatIncrementTicketPriceBy10PercentWorksCorrectly(){
        final Flight flight = Flight.builder()
                .id(1L)
                .ticketPrice(BigDecimal.valueOf(1000))
                .availableSeatCount(50)
                .build();

        flight.incrementTicketPriceBy10Percent();

        assertThat(flight.getTicketPrice(),is(equalTo(BigDecimal.valueOf(1100))));
    }

}