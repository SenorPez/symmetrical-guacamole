package com.senorpez.avt.shipdesigner.validators;

import java.util.ArrayList;
import java.util.List;

public class DriveGenerationValidator {
    private static final double minDriveGeneration = 1.0d;
    private static final double maxDriveGeneration = 9.0d;

    public static final String driveGenerationOutOfBounds = String.format(
            "Drive generation must be between %1.1f and %1.1f",
            minDriveGeneration,
            maxDriveGeneration
    );

    public static final String driveGenerationInvalid = "Drive generation must only be in increments of 0.1 ";

    public static ValidationResult validate(final double driveGeneration) {
        List<String> validationErrors = new ArrayList<>();

        if (driveGeneration < minDriveGeneration || driveGeneration > maxDriveGeneration) {
            validationErrors.add(driveGenerationOutOfBounds);
        }

        return new ValidationResult(validationErrors.size() == 0, validationErrors);
    }
}
