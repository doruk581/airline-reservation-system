package com.finartz.ticketreservation.domain;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerInformation {

    private String name;
    private String surname;
    private String identityNumber;
    private Long flightId;
    private PaymentInformation paymentInformation;
    private Ticket ticket;
}
