package com.finartz.ticketreservation.domain;

import com.finartz.ticketreservation.infrastructure.flightmanagement.FlightReservationResponse;
import com.finartz.ticketreservation.interfaces.PaymentRequest;

public interface PaymentInformationFactory {
    PaymentInformation createFrom(final PaymentRequest request, final FlightReservationResponse reservationResponse);
}
