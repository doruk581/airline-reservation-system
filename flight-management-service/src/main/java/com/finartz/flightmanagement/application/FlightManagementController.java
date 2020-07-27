package com.finartz.flightmanagement.application;

import com.finartz.flightmanagement.application.validation.ValidationResult;
import com.finartz.flightmanagement.application.validation.ValidationService;
import com.finartz.flightmanagement.domain.AirportNotFoundException;
import com.finartz.flightmanagement.domain.FlightNotFoundException;
import com.finartz.flightmanagement.interfaces.common.ApiError;
import com.finartz.flightmanagement.interfaces.common.ErrorCode;
import com.finartz.flightmanagement.interfaces.request.AddFlightRequest;
import com.finartz.flightmanagement.interfaces.request.FlightReservationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@Slf4j
@RequestMapping("/flight")
public class FlightManagementController {

    private final ValidationService<AddFlightRequest> addFlightRequestValidationService;
    private final ValidationService<FlightReservationRequest> flightReservationRequestValidationService;
    private final FlightManagementService flightManagementService;

    public FlightManagementController(ValidationService<AddFlightRequest> addFlightRequestValidationService,
                                      ValidationService<FlightReservationRequest> flightReservationRequestValidationService,
                                      FlightManagementService flightManagementService) {
        this.addFlightRequestValidationService = addFlightRequestValidationService;
        this.flightReservationRequestValidationService = flightReservationRequestValidationService;
        this.flightManagementService = flightManagementService;
    }

    @PostMapping
    public ResponseEntity addFlight(@RequestBody AddFlightRequest request) {
        final ValidationResult validationResult = addFlightRequestValidationService.validate(request);

        if (!validationResult.getIsValid()) {
            log.warn(validationResult.getMessage());
            return new ResponseEntity<>(ApiError.create(HttpStatus.BAD_REQUEST.value(), validationResult.getMessage(), validationResult.getErrorCode().code()), HttpStatus.BAD_REQUEST);
        }

        flightManagementService.addFlight(request);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/airline/{id}")
    public ResponseEntity searchByAirlineId(@PathVariable String id) {
        return ResponseEntity.ok(flightManagementService.searchByAirlineId(id));
    }

    @PostMapping(path = "/reservation")
    public ResponseEntity makeReservation(@RequestBody FlightReservationRequest reservationRequest){
        return ResponseEntity.ok(flightManagementService.makeReservation(reservationRequest));
    }

    @PostMapping(path = "/reservation/cancel")
    public ResponseEntity cancelReservation(@RequestBody FlightReservationRequest reservationRequest){
        flightManagementService.cancelReservation(reservationRequest);

        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(FlightNotFoundException.class)
    public final ResponseEntity<ApiError> handlePolicyApprovalException(Exception ex, WebRequest request) {
        log.error("FlightNotFoundException exception occurred!", ex);
        return new ResponseEntity<>(ApiError.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), ErrorCode.FLIGHTNOTFOUND.code()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ApiError> handleInternalServerError(Exception ex, WebRequest request) {
        log.error("Unhandled exception occurred!", ex);
        return new ResponseEntity<>(ApiError.internalServerError(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
