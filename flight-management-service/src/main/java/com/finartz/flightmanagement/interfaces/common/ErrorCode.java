package com.finartz.flightmanagement.interfaces.common;

public enum ErrorCode {
    AIRLINEIDNOTVALID("FMS1000"),
    ARRIVALAIRPORTIDNOTVALID("FMS1001"),
    DEPARTUREAIRPORTIDNOTVALID("FMS1002"),
    SEATCOUNDNOTVALID("FMS1003"),
    FLIGHTIDNOTVALID("FMS1004"),
    AIRLINENOTFOUND("FMS1005"),
    AIRPORTNOTFOUND("FMS1006"),
    FLIGHTNOTFOUND("FMS1007"),
    ROUTENOTFOUND("FMS1008");

    private String code;

    ErrorCode(String code) {
        this.code = code;
    }

    public String code() {
        return this.code;
    }
}