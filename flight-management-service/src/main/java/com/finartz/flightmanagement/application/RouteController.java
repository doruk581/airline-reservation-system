package com.finartz.flightmanagement.application;

import com.finartz.flightmanagement.domain.FlightNotFoundException;
import com.finartz.flightmanagement.domain.RouteNotFoundException;
import com.finartz.flightmanagement.interfaces.common.ApiError;
import com.finartz.flightmanagement.interfaces.common.ErrorCode;
import com.finartz.flightmanagement.interfaces.request.AddRouteRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;


@RestController
@RequestMapping("/route")
@Slf4j
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }


    @PostMapping
    public ResponseEntity addRoute(@RequestBody AddRouteRequest addRouteRequest) {
        return ResponseEntity.ok(routeService.addRoute(addRouteRequest));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteRoute(@PathVariable Long id) {
        routeService.deleteRoute(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity getAllRoutes() {
        return ResponseEntity.ok(routeService.getAllRoutes());
    }

    @ExceptionHandler(RouteNotFoundException.class)
    public final ResponseEntity<ApiError> handlePolicyApprovalException(Exception ex, WebRequest request) {
        log.error("RouteNotFoundException exception occurred!", ex);
        return new ResponseEntity<>(ApiError.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), ErrorCode.ROUTENOTFOUND.code()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ApiError> handleInternalServerError(Exception ex, WebRequest request) {
        log.error("Unhandled exception occurred!", ex);
        return new ResponseEntity<>(ApiError.internalServerError(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
