package com.finartz.flightmanagement.interfaces.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AirportRequest {

    @NotBlank
    private String airportCode;
    @NotBlank
    private String airportName;
    @NotBlank
    private String country;
}
