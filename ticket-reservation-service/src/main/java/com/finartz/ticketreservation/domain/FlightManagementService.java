package com.finartz.ticketreservation.domain;

import com.finartz.ticketreservation.infrastructure.flightmanagement.FlightReservationRequest;
import com.finartz.ticketreservation.infrastructure.flightmanagement.FlightReservationResponse;
import com.finartz.ticketreservation.interfaces.common.GatewayException;
import io.vavr.control.Either;

import java.util.Optional;

public interface FlightManagementService {
    Either<GatewayException, FlightReservationResponse> makeReservation(final FlightReservationRequest request);

    Optional<GatewayException> cancelReservation(final FlightReservationRequest request);
}
