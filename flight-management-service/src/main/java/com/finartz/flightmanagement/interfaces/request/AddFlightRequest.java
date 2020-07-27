package com.finartz.flightmanagement.interfaces.request;

import com.finartz.flightmanagement.interfaces.request.dto.DateDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class AddFlightRequest {
    private String airlineId;
    private Long routeId;
    private Integer seatCount;
    private Long departureAirportId;
    private Long arrivalAirportId;
    private BigDecimal ticketPrice;
    private DateDto departureDate;
}
