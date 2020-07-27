package com.finartz.ticketreservation.domain;

import java.util.Optional;

public interface PaymentInformationRepository {

    void save(final PaymentInformation paymentInformation);

    Optional<PaymentInformation> findByTicketId(final String ticketId);

    void delete(final PaymentInformation paymentInformation);
}
