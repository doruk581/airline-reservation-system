package com.finartz.flightmanagement.interfaces.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AddAirlineRequest {
    @NotBlank
    private String airlineName;
}
