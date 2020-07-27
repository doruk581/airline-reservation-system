package com.finartz.flightmanagement.application.validation;

import com.finartz.flightmanagement.interfaces.common.ErrorCode;
import com.finartz.flightmanagement.interfaces.request.FlightReservationRequest;

public class FlightReservationValidationService implements ValidationService<FlightReservationRequest> {

    @Override
    public ValidationResult validate(FlightReservationRequest request) {
        if (request.getId() == null ||request.getId() <= 0)
            return ValidationResult.error("Flight id cannot be empty or null", ErrorCode.FLIGHTIDNOTVALID);

        return ValidationResult.success();
    }
}
