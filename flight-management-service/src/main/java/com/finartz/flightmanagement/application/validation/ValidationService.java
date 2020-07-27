package com.finartz.flightmanagement.application.validation;

public interface ValidationService<T> {

    ValidationResult validate(T request);

}