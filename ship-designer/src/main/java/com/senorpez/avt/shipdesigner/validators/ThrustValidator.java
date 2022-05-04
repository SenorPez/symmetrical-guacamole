package com.senorpez.avt.shipdesigner.validators;

import java.util.ArrayList;
import java.util.List;

public class ThrustValidator {
    private static final double minThrust = 0.5d;
    private static final double maxThrust = 16d;

    public static final String thrustOutOfBounds = String.format(
            "Thrust must be between %2.1f and %2.1f",
            minThrust,
            maxThrust
    );

    public static final String thrustInvalid = "Thrust must only be in increments of 0.5";

    public static ValidationResult validate(final double thrust) {
        List<String> validationErrors = new ArrayList<>();

        if (thrust < minThrust || thrust > maxThrust) {
            validationErrors.add(thrustOutOfBounds);
        }
        if (thrust % 0.5 != 0) {
            validationErrors.add(thrustInvalid);
        }

        return new ValidationResult(validationErrors.size() == 0, validationErrors);
    }
}
