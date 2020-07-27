package com.finartz.flightmanagement.application;

import com.finartz.flightmanagement.domain.AirlineNotFoundException;
import com.finartz.flightmanagement.domain.AirportNotFoundException;
import com.finartz.flightmanagement.interfaces.common.ApiError;
import com.finartz.flightmanagement.interfaces.common.ErrorCode;
import com.finartz.flightmanagement.interfaces.request.AirportRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/airport")
@Slf4j
public class AirportController {

    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }


    @PostMapping
    public ResponseEntity addAirport(@RequestBody AirportRequest airportRequest) {
        airportService.addAirport(airportRequest);

        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity updateAirport(@RequestBody AirportRequest request) {
        airportService.updateAirport(request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteAirport(@PathVariable String id) {
        airportService.deleteAirport(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity getAllAirports() {
        return ResponseEntity.ok(airportService.getAllAirports());
    }


    @ExceptionHandler(AirportNotFoundException.class)
    public final ResponseEntity<ApiError> handlePolicyApprovalException(Exception ex, WebRequest request) {
        log.error("AirportNotFoundException exception occurred!", ex);
        return new ResponseEntity<>(ApiError.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), ErrorCode.AIRPORTNOTFOUND.code()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ApiError> handleInternalServerError(Exception ex, WebRequest request) {
        log.error("Unhandled exception occurred!", ex);
        return new ResponseEntity<>(ApiError.internalServerError(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
