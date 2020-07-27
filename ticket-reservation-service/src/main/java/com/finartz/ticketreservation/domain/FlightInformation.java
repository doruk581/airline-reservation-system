package com.finartz.ticketreservation.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightInformation {
    private String airlineCode;
    private LocalDateTime departureTime;
}
