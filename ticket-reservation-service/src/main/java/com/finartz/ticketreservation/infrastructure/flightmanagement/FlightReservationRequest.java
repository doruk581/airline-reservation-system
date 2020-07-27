package com.finartz.ticketreservation.infrastructure.flightmanagement;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FlightReservationRequest {
    private Long id;
}
