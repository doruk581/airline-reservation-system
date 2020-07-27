package com.finartz.flightmanagement.application;

import com.finartz.flightmanagement.domain.AirlineNotFoundException;
import com.finartz.flightmanagement.interfaces.common.ApiError;
import com.finartz.flightmanagement.interfaces.common.ErrorCode;
import com.finartz.flightmanagement.interfaces.request.AddAirlineRequest;
import com.finartz.flightmanagement.interfaces.request.UpdateAirlineRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/airline")
@Slf4j
public class AirlineController {

    private final AirlineService airlineService;

    public AirlineController(AirlineService airlineService) {
        this.airlineService = airlineService;
    }

    @PostMapping
    public ResponseEntity addAirline(@RequestBody AddAirlineRequest airlineRequest) {
        airlineService.addAirline(airlineRequest);

        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity updateAirline(@RequestBody UpdateAirlineRequest request) {
        airlineService.updateAirline(request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteAirline(@PathVariable String id) {
        airlineService.deleteAirline(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity getAllAirlines() {
        return ResponseEntity.ok(airlineService.getAllAirlines());
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ApiError> handleInternalServerError(Exception ex, WebRequest request) {
        log.error("Unhandled exception occurred!", ex);
        return new ResponseEntity<>(ApiError.internalServerError(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(AirlineNotFoundException.class)
    public final ResponseEntity<ApiError> handlePolicyApprovalException(Exception ex, WebRequest request) {
        log.error("AirlineNotFoundException exception occurred!", ex);
        return new ResponseEntity<>(ApiError.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), ErrorCode.AIRLINENOTFOUND.code()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
