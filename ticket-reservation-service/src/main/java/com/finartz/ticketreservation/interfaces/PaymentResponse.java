package com.finartz.ticketreservation.interfaces;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class PaymentResponse {
    private Boolean isSuccessful;
    private BigDecimal totalCharge;
    private String ticketId;
}
