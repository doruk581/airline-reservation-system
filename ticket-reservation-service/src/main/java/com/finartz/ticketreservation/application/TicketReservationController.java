package com.finartz.ticketreservation.application;

import com.finartz.ticketreservation.application.validation.ValidationResult;
import com.finartz.ticketreservation.application.validation.ValidationService;
import com.finartz.ticketreservation.interfaces.PaymentRequest;
import com.finartz.ticketreservation.interfaces.common.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
@Slf4j
public class TicketReservationController {

    private final TicketReservationService ticketReservationService;
    private final ValidationService<PaymentRequest> paymentRequestValidationService;

    public TicketReservationController(TicketReservationService ticketReservationService, ValidationService<PaymentRequest> paymentRequestValidationService) {
        this.ticketReservationService = ticketReservationService;
        this.paymentRequestValidationService = paymentRequestValidationService;
    }

    @PostMapping(path = "/pay")
    public ResponseEntity payTicket(@RequestBody PaymentRequest request) {
        final ValidationResult validationResult = paymentRequestValidationService.validate(request);

        if (!validationResult.getIsValid()) {
            log.warn(validationResult.getMessage());
            return new ResponseEntity<>(ApiError.create(HttpStatus.BAD_REQUEST.value(), validationResult.getMessage(), validationResult.getErrorCode().code()), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(ticketReservationService.doPayment(request));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getTicket(@PathVariable String id){
        return ResponseEntity.ok(ticketReservationService.getPaymentInformation(id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity cancelTicket(@PathVariable String id){
        ticketReservationService.cancelTicket(id);

        return ResponseEntity.noContent().build();
    }
}
