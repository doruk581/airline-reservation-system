package com.finartz.ticketreservation.domain;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    private String ticketId;
    private BigDecimal ticketPrice;
}
