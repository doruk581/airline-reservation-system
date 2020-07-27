package com.finartz.flightmanagement.domain;

import com.finartz.flightmanagement.application.FlightManagementService;
import com.finartz.flightmanagement.interfaces.request.FlightReservationRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultFlightManagementServiceTest {

    @Mock
    private FlightRepository repository;

    @Mock
    private FlightFactory flightFactory;

    @InjectMocks
    private DefaultFlightManagementService service;

    @Test
    public void controlThatWhenEntityExistThenCancelReservationShouldIncreaseAvailableSeatCount(){
        final FlightReservationRequest reservationRequest = new FlightReservationRequest();
        reservationRequest.setId(1L);
        final Flight flight = new Flight();
        flight.setAvailableSeatCount(10);

        when(repository.findById(any())).thenReturn(Optional.of(flight));

        service.cancelReservation(reservationRequest);

        assertThat(flight.getAvailableSeatCount(),is(equalTo(11)));
        verify(repository,times(1)).update(flight);
    }
}