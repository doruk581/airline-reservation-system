package com.finartz.ticketreservation.domain;

public class PurchaseTicketException extends RuntimeException {

    private final Long flightId;
    private final String message;

    private PurchaseTicketException(final Long flightId, final String message) {
        this.flightId = flightId;
        this.message = message;
    }

    public static PurchaseTicketException create(final Long flightId, final String message) {
        return new PurchaseTicketException(flightId, message);
    }

    @Override
    public String getMessage() {
        return "Ticket purchase can't completed for flight id: " + flightId + " reason: " + message;
    }
}
