package com.finartz.ticketreservation.application;

import com.finartz.ticketreservation.domain.PaymentInformation;
import com.finartz.ticketreservation.interfaces.PaymentRequest;
import com.finartz.ticketreservation.interfaces.PaymentResponse;

public interface TicketReservationService {

    PaymentResponse doPayment(final PaymentRequest paymentRequest);

    PaymentInformation getPaymentInformation(final String id);

    void cancelTicket(final String id);
}
