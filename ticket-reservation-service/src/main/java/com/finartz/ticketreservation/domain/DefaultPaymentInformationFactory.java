package com.finartz.ticketreservation.domain;

import com.finartz.ticketreservation.infrastructure.flightmanagement.FlightReservationResponse;
import com.finartz.ticketreservation.interfaces.PaymentRequest;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.UUID;

public class DefaultPaymentInformationFactory implements PaymentInformationFactory {

    @Override
    public PaymentInformation createFrom(PaymentRequest request, final FlightReservationResponse response) {
        final CustomerInformation customerInformation = prepareCustomerInformation(request);
        final FlightInformation flightInformation = prepareFlightInformation(response);
        final Ticket ticket = prepareTicket(response.getTicketPrice());

        final String prepareCreditCardNumber = prepareCreditCardNumber(request.getCreditCardNumber());

        return PaymentInformation
                .builder()
                .ccvNumber(request.getCcvNumber())
                .creditCardExpireDate(request.getCreditCardExpireDate())
                .creditCardNumber(prepareCreditCardNumber)
                .customerInformation(customerInformation)
                .flightInformation(flightInformation)
                .ticket(ticket)
                .build();
    }

    protected CustomerInformation prepareCustomerInformation(final PaymentRequest paymentRequest) {
        return CustomerInformation
                .builder()
                .flightId(paymentRequest.getFlightId())
                .identityNumber(paymentRequest.getIdentityNumber())
                .name(paymentRequest.getName())
                .surname(paymentRequest.getSurname())
                .build();
    }

    protected FlightInformation prepareFlightInformation(final FlightReservationResponse response) {
        return FlightInformation.builder()
                .airlineCode(response.getAirlineCode())
                .departureTime(response.getDepartureTime())
                .build();
    }

    protected Ticket prepareTicket(final BigDecimal ticketPrice) {
        final String ticketId = UUID.randomUUID().toString().replaceAll("-", "");

        return Ticket.builder().ticketId(ticketId).ticketPrice(ticketPrice).build();
    }

    protected String prepareCreditCardNumber(final String creditCard) {
        final String value = creditCard.replaceAll("[^0-9]", "");

        return StringUtils.overlay(value, StringUtils.repeat("*", value.length() - 12), 8, value.length() - 4);
    }
}
