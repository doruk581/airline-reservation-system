package com.finartz.flightmanagement.application.validation;

import com.finartz.flightmanagement.interfaces.common.ErrorCode;
import com.finartz.flightmanagement.interfaces.request.AddFlightRequest;

public class AddFlightRequestValidationService implements ValidationService<AddFlightRequest> {

    @Override
    public ValidationResult validate(AddFlightRequest request) {
        if (request.getAirlineId() == null)
            return ValidationResult.error("Airline id is not acceptable", ErrorCode.AIRLINEIDNOTVALID);

        if (request.getArrivalAirportId() == null)
            return ValidationResult.error("Arrival airport id is not acceptable", ErrorCode.ARRIVALAIRPORTIDNOTVALID);

        if (request.getDepartureAirportId() == null)
            return ValidationResult.error("Departure airport id is not acceptable", ErrorCode.DEPARTUREAIRPORTIDNOTVALID);

        if (request.getSeatCount() == null)
            return ValidationResult.error("Seat count is not acceptable", ErrorCode.SEATCOUNDNOTVALID);

        return ValidationResult.success();
    }
}
