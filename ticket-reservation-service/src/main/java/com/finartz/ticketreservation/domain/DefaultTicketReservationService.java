package com.finartz.ticketreservation.domain;

import com.finartz.ticketreservation.application.TicketReservationService;
import com.finartz.ticketreservation.infrastructure.flightmanagement.FlightReservationRequest;
import com.finartz.ticketreservation.infrastructure.flightmanagement.FlightReservationResponse;
import com.finartz.ticketreservation.interfaces.PaymentRequest;
import com.finartz.ticketreservation.interfaces.PaymentResponse;
import com.finartz.ticketreservation.interfaces.common.GatewayException;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class DefaultTicketReservationService implements TicketReservationService {

    private final FlightManagementService flightManagementService;
    private final PaymentInformationFactory factory;
    private final PaymentInformationRepository repository;

    public DefaultTicketReservationService(FlightManagementService flightManagementService, PaymentInformationFactory factory, PaymentInformationRepository paymentInformationRepository) {
        this.flightManagementService = flightManagementService;
        this.factory = factory;
        this.repository = paymentInformationRepository;
    }


    @Override
    public PaymentResponse doPayment(PaymentRequest paymentRequest) {
        final Either<GatewayException, FlightReservationResponse> maybeResponse
                = flightManagementService.makeReservation(FlightReservationRequest.builder().id(paymentRequest.getFlightId()).build());

        if (maybeResponse.isLeft())
            throw PurchaseTicketException.create(paymentRequest.getFlightId(), Constants.PAYMENT_ERROR);

        final FlightReservationResponse reservationResponse = maybeResponse.get();

        if (!reservationResponse.getIsSuccessful())
            throw PurchaseTicketException.create(paymentRequest.getFlightId(), reservationResponse.getMessage());

        final PaymentInformation paymentInformation = factory.createFrom(paymentRequest, reservationResponse);

        try {
            //Assume it is also payment action..
            repository.save(paymentInformation);
        } catch (Exception e) {
            //log it,qeueue it, send notification event, cancel reservation vs.I just log it now.
            log.info("Payment is unsuccessful!");
            return PaymentResponse.builder().isSuccessful(Boolean.FALSE).build();
        }

        return PaymentResponse.builder()
                .ticketId(paymentInformation.getTicket().getTicketId())
                .totalCharge(paymentInformation.getTicket().getTicketPrice())
                .isSuccessful(Boolean.TRUE)
                .build();
    }

    @Override
    public PaymentInformation getPaymentInformation(final String id) {
        return repository.findByTicketId(id).orElseThrow(() -> TicketNotFoundException.create(id));
    }

    @Override
    public void cancelTicket(final String id) {
        final PaymentInformation paymentInformation = repository.findByTicketId(id).orElseThrow(() -> TicketNotFoundException.create(id));

        final Optional<GatewayException> maybeSuccess = flightManagementService.cancelReservation(FlightReservationRequest.builder().id(paymentInformation.getCustomerInformation().getFlightId()).build());

        if (maybeSuccess.isPresent())
            throw maybeSuccess.get();

        repository.delete(paymentInformation);
    }
}
