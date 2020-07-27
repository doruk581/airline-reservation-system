package com.finartz.ticketreservation.domain;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DefaultPaymentInformationFactoryTest {

    private DefaultPaymentInformationFactory factory;

    @Before
    public void init() {
        factory = new DefaultPaymentInformationFactory();
    }

    @Test
    public void controlThatPrepareCreditCardNumberWorksCorrectly() {
        final String creditCard = "4444-5555-6666-7777";
        final String maskedCard = factory.prepareCreditCardNumber(creditCard);

        assertThat(maskedCard, is(equalTo("44445555****7777")));
    }

}