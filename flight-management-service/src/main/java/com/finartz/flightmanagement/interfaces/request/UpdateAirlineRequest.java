package com.finartz.flightmanagement.interfaces.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UpdateAirlineRequest {

    @NotBlank
    private String id;
    @NotBlank
    private String airlineName;
}
