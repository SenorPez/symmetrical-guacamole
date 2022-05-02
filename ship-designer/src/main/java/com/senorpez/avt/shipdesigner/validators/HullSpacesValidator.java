package com.senorpez.avt.shipdesigner.validators;

import java.util.List;

public record HullSpacesValidator() {
    private static final int minHullSpaces = 25;
    private static final int maxHullSpaces = 2500;

    public static final String hullSpacesOutOfBounds = String.format(
            "Hull spaces must be between %d and %d",
            minHullSpaces,
            maxHullSpaces
    );

    public static ValidationResult validate(final int hullSpaces) {
        if (hullSpaces >= minHullSpaces && hullSpaces <= maxHullSpaces) {
            return new ValidationResult(true, List.of());
        } else {
            return new ValidationResult(false, List.of(hullSpacesOutOfBounds));
        }
    }
}

