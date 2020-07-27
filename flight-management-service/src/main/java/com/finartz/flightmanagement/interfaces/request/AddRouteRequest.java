package com.finartz.flightmanagement.interfaces.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddRouteRequest {
    private String departureAirportCode;
    private String arrivalAirportCode;
}
