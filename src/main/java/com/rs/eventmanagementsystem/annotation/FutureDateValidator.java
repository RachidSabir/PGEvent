package com.rs.eventmanagementsystem.annotation;

import java.time.LocalDate;

import com.rs.eventmanagementsystem.validations.FutureDate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FutureDateValidator implements ConstraintValidator<FutureDate, LocalDate> {
    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        return value == null || value.isAfter(LocalDate.now());
    }
}
