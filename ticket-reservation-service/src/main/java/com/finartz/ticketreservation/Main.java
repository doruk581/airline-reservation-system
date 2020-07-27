package com.finartz.ticketreservation;

import com.finartz.ticketreservation.application.TicketReservationService;
import com.finartz.ticketreservation.application.validation.PaymentRequestValidationService;
import com.finartz.ticketreservation.application.validation.ValidationService;
import com.finartz.ticketreservation.domain.*;
import com.finartz.ticketreservation.infrastructure.flightmanagement.FlightManagementServiceGateway;
import com.finartz.ticketreservation.infrastructure.flightmanagement.FlightManagementServiceGatewayConfiguration;
import com.finartz.ticketreservation.infrastructure.repository.DefaultMongoRepository;
import com.finartz.ticketreservation.infrastructure.repository.MongoConfiguration;
import com.finartz.ticketreservation.interfaces.PaymentRequest;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@Configuration
@Slf4j
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        log.info("Main started..");
    }


    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    FlightManagementService flightManagementService(RestTemplate restTemplate, FlightManagementServiceGatewayConfiguration configuration) {
        return new FlightManagementServiceGateway(restTemplate, configuration);
    }

    @Bean
    ValidationService<PaymentRequest> paymentRequestValidationService() {
        return new PaymentRequestValidationService();
    }

    @Bean
    PaymentInformationFactory paymentInformationFactory() {
        return new DefaultPaymentInformationFactory();
    }

    @Bean
    TicketReservationService ticketReservationService(FlightManagementService flightManagementService, PaymentInformationFactory factory, PaymentInformationRepository repository) {
        return new DefaultTicketReservationService(flightManagementService, factory, repository);
    }

    @Bean
    public MongoClient getMongoClient(MongoConfiguration configuration) {
        final String mongoConnectionString = configuration.getMongoDbConnectionString();
        return MongoClients.create(mongoConnectionString);
    }

    @Bean
    PaymentInformationRepository paymentInformationRepository(MongoConfiguration mongoConfiguration, MongoClient mongoClient) {
        return new DefaultMongoRepository(mongoConfiguration, mongoClient);
    }
}
