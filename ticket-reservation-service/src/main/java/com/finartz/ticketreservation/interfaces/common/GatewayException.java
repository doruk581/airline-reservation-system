package com.finartz.ticketreservation.interfaces.common;

public class GatewayException extends RuntimeException {

    private final String name;
    private final String externalData;
    private final Integer status;

    public GatewayException(String name, String externalData, Integer status) {
        this.name = name;
        this.externalData = externalData;
        this.status = status;
    }

    @Override
    public String getMessage() {
        return String.format("Gateway service  exception occurred. Name: %s , ExternalData: %s , Status %d", name, externalData, status);
    }
}
