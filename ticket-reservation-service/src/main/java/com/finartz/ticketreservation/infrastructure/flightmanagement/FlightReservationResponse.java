package com.finartz.ticketreservation.infrastructure.flightmanagement;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class FlightReservationResponse {
    private Boolean isSuccessful;
    private String message;
    private String airlineCode;
    private LocalDateTime departureTime;
    private BigDecimal ticketPrice;
}
