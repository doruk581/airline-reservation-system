package com.finartz.ticketreservation.infrastructure.flightmanagement;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("flight-management-service")
@Getter
@Setter
public class FlightManagementServiceGatewayConfiguration {
    @Value("url")
    public String URL;
}
