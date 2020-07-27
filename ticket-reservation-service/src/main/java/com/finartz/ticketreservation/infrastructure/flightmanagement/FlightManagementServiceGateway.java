package com.finartz.ticketreservation.infrastructure.flightmanagement;

import com.finartz.ticketreservation.domain.FlightManagementService;
import com.finartz.ticketreservation.interfaces.common.GatewayException;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Slf4j
public class FlightManagementServiceGateway implements FlightManagementService {

    private final RestTemplate restTemplate;
    private final FlightManagementServiceGatewayConfiguration configuration;

    public FlightManagementServiceGateway(RestTemplate restTemplate, FlightManagementServiceGatewayConfiguration configuration) {
        this.restTemplate = restTemplate;
        this.configuration = configuration;
    }

    @Override
    public Either<GatewayException, FlightReservationResponse> makeReservation(final FlightReservationRequest request) {
        return CompletableFuture
                .supplyAsync(() -> restTemplate.postForEntity(getReservationUrl(), request, FlightReservationResponse.class))
                .<Either<GatewayException, FlightReservationResponse>>thenApply(resp -> {
                    log.info("flight management service called");
                    return Either.right(resp.getBody());
                })
                .exceptionally(ex -> Either.left(new GatewayException(FlightManagementServiceGateway.class.getName(), ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value())))
                .join();
    }

    @Override
    public Optional<GatewayException> cancelReservation(FlightReservationRequest request) {
        return CompletableFuture
                .supplyAsync(() -> restTemplate.postForEntity(getCancelReservationUrl(), request, Void.class))
                .<Optional<GatewayException>>thenApply(resp -> {
                    log.info("flight management service cancel reservation called");

                    if (resp.getStatusCode() == HttpStatus.NO_CONTENT) return Optional.empty();
                    return Optional.of(new GatewayException(FlightManagementServiceGateway.class.getName(), "Unexpected status code", resp.getStatusCode().value()));
                })
                .exceptionally(ex -> Optional.of(new GatewayException(FlightManagementServiceGateway.class.getName(), ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value())))
                .join();
    }

    private String getReservationUrl() {
        return this.configuration.getURL() + "/flight/reservation";
    }

    private String getCancelReservationUrl() {return this.configuration.getURL() + "/flight/reservation/cancel";}
}
