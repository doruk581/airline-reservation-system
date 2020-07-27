package com.finartz.ticketreservation.domain;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentInformation {
    private String creditCardNumber;
    private String creditCardExpireDate;
    private Integer ccvNumber;
    private CustomerInformation customerInformation;
    private FlightInformation flightInformation;
    private Ticket ticket;
}
