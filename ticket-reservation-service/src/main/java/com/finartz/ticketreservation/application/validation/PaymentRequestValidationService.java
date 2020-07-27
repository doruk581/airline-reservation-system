package com.finartz.ticketreservation.application.validation;

import com.finartz.ticketreservation.interfaces.PaymentRequest;
import com.finartz.ticketreservation.interfaces.common.ErrorCode;

public class PaymentRequestValidationService implements ValidationService<PaymentRequest> {

    @Override
    public ValidationResult validate(PaymentRequest request) {
        if (request.getFlightId() == null || request.getFlightId() <= 0)
            return ValidationResult.error("Flight id cannot be null and lower than zero", ErrorCode.FLIGHTIDNOTVALID);

        if (request.getCcvNumber() == null || request.getCcvNumber() <= 0)
            return ValidationResult.error("Cvv number cannot be null and lower than zero", ErrorCode.CVVNUMBERNOTVALID);

        if (request.getCreditCardExpireDate().isBlank())
            return ValidationResult.error("Credit card expire date cannot be null or empty", ErrorCode.CREDITCARDEXPIREDATENOTVALID);

        if (request.getCreditCardNumber().isBlank())
            return ValidationResult.error("Credit card number cannot be null or empty", ErrorCode.CREDITCARDNUMBERNOTVALID);

        if (request.getIdentityNumber().isBlank())
            return ValidationResult.error("Identity number cannot be null or empty", ErrorCode.IDENTITYNUMBERNOTVALID);

        if (request.getName().isBlank())
            return ValidationResult.error("Name cannot be null or empty", ErrorCode.NAMENOTVALID);

        if (request.getSurname().isBlank())
            return ValidationResult.error("Surname cannot be null or empty", ErrorCode.SURNAMENOTVALID);

        return ValidationResult.success();
    }
}
