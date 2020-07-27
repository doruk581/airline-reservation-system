package com.finartz.ticketreservation.application.validation;

public interface ValidationService<T> {
    ValidationResult validate(T request);
}