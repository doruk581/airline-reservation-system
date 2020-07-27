package com.finartz.flightmanagement;

import com.finartz.flightmanagement.application.AirlineService;
import com.finartz.flightmanagement.application.AirportService;
import com.finartz.flightmanagement.application.FlightManagementService;
import com.finartz.flightmanagement.application.RouteService;
import com.finartz.flightmanagement.application.validation.AddFlightRequestValidationService;
import com.finartz.flightmanagement.application.validation.FlightReservationValidationService;
import com.finartz.flightmanagement.application.validation.ValidationService;
import com.finartz.flightmanagement.domain.*;
import com.finartz.flightmanagement.infrastructure.repository.AirlineRepository;
import com.finartz.flightmanagement.infrastructure.repository.AirportRepository;
import com.finartz.flightmanagement.infrastructure.repository.RouteRepository;
import com.finartz.flightmanagement.interfaces.request.AddFlightRequest;
import com.finartz.flightmanagement.interfaces.request.FlightReservationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@Slf4j
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

        log.info("Main started..");
    }

    @Bean
    FlightFactory flightFactory(AirlineRepository airlineRepository, RouteRepository routeRepository) {
        return new DefaultFlightFactory(airlineRepository, routeRepository);
    }

    @Bean
    FlightManagementService flightManagementService(FlightRepository flightRepository, FlightFactory flightFactory) {
        return new DefaultFlightManagementService(flightRepository, flightFactory);
    }

    @Bean
    ValidationService<AddFlightRequest> flightRequestValidationService() {
        return new AddFlightRequestValidationService();
    }

    @Bean
    AirlineService airlineService(AirlineRepository airlineRepository) {
        return new DefaultAirlineService(airlineRepository);
    }

    @Bean
    AirportService airportService(AirportRepository airportRepository) {
        return new DefaultAirportService(airportRepository);
    }

    @Bean
    RouteService routeService(RouteRepository routeRepository, AirportRepository airportRepository) {
        return new DefaultRouteService(routeRepository, airportRepository);
    }

    @Bean
    ValidationService<FlightReservationRequest> flightReservationRequestValidationService(){
        return new FlightReservationValidationService();
    }

}
