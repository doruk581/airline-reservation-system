package com.finartz.ticketreservation.domain;

import java.util.NoSuchElementException;

public class TicketNotFoundException extends NoSuchElementException {

    private final String ticketId;

    private TicketNotFoundException(String ticketId){
        this.ticketId = ticketId;
    }

    public static TicketNotFoundException create(String ticketId){
        return new TicketNotFoundException(ticketId);
    }

    @Override
    public String getMessage() {
        return "Ticket not found for ticked id: " + ticketId;
    }
}
