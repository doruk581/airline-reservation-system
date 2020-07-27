package com.finartz.flightmanagement.domain;

import com.finartz.flightmanagement.infrastructure.repository.AirportRepository;
import com.finartz.flightmanagement.interfaces.request.AirportRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultAirportServiceTest {

    @Mock
    private AirportRepository repository;

    @InjectMocks
    private DefaultAirportService service;

    @Test
    public void controlThatAddAirportMethodShouldCallRepositorySaveOnce(){
        final AirportRequest request = new AirportRequest();

        service.addAirport(request);

        verify(repository,times(1)).save(any());
    }

    @Test(expected = AirportNotFoundException.class)
    public void controlThatUpdateAirportShouldThrowExceptionWhenEntityNotFound(){
        final AirportRequest request = new AirportRequest();

        when(repository.findById(any())).thenReturn(Optional.empty());

        service.updateAirport(request);
    }

    @Test
    public void controlThatUpdateAirportShouldCallRepositoryOnce(){
        final AirportRequest request = new AirportRequest();
        final Airport airport = new Airport();
        when(repository.findById(any())).thenReturn(Optional.of(airport));

        service.updateAirport(request);

        verify(repository,times(1)).save(airport);
    }

    @Test(expected = AirportNotFoundException.class)
    public void controlThatDeleteAirportShouldThrowExceptionWhenEntityNotFound(){

        when(repository.findById(any())).thenReturn(Optional.empty());

        service.deleteAirport(anyString());
    }

    @Test
    public void controlThatDeleteAirportMethodShouldCallRepositoryOnce(){
        final Airport airport = new Airport();
        when(repository.findById(any())).thenReturn(Optional.of(airport));

        service.deleteAirport(anyString());

        verify(repository,times(1)).delete(any());
    }

}