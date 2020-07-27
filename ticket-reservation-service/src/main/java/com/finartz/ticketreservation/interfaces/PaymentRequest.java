package com.finartz.ticketreservation.interfaces;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {
    private Long flightId;
    private String creditCardNumber;
    private String creditCardExpireDate;
    private Integer ccvNumber;
    private String name;
    private String surname;
    private String identityNumber;
}
