package com.finartz.flightmanagement.domain;

import com.finartz.flightmanagement.infrastructure.repository.AirlineRepository;
import com.finartz.flightmanagement.interfaces.request.AddAirlineRequest;
import com.finartz.flightmanagement.interfaces.request.UpdateAirlineRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultAirlineServiceTest {

    @Mock
    private AirlineRepository airlineRepository;

    @InjectMocks
    private DefaultAirlineService airlineService;

    @Test
    public void verifyThatAddAirlineMethodCallRepositoryOnce(){
        final AddAirlineRequest addAirlineRequest = new AddAirlineRequest();
        addAirlineRequest.setAirlineName("THY");

        airlineService.addAirline(addAirlineRequest);

        verify(airlineRepository,times(1)).save(any());
    }

    @Test(expected = AirlineNotFoundException.class)
    public void controlThatUpdateAirlineShouldThrowAirlineNotFoundExceptionWhenAirlineNotExist(){
        final UpdateAirlineRequest request = new UpdateAirlineRequest();
        request.setId("1");

        when(airlineRepository.findById(request.getId())).thenReturn(Optional.empty());

        airlineService.updateAirline(request);
    }

    @Test
    public void controlThatUpdateAirlineMethodShouldCallRepositoryOnce(){
        final UpdateAirlineRequest request = new UpdateAirlineRequest();
        request.setId("1");
        final Airline airline = new Airline();

        when(airlineRepository.findById(request.getId())).thenReturn((Optional.of(airline)));

        airlineService.updateAirline(request);

        verify(airlineRepository,times(1)).save(airline);
    }

    @Test(expected = AirlineNotFoundException.class)
    public void controlThatDeleteAirlineShouldThrowAirlineNotFoundExceptionWhenAirlineNotExist(){

        when(airlineRepository.findById(any())).thenReturn(Optional.empty());

        airlineService.deleteAirline(any());
    }

    @Test
    public void controlThatDeleteAirlineMethodShouldCallRepositoryOnce(){

        final Airline airline = new Airline();

        when(airlineRepository.findById(any())).thenReturn((Optional.of(airline)));

        airlineService.deleteAirline(any());

        verify(airlineRepository,times(1)).delete(any());
    }
}