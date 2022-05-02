package com.senorpez.avt.shipdesigner;

import com.senorpez.avt.shipdesigner.validators.HullSpacesValidator;
import com.senorpez.avt.shipdesigner.validators.ValidationResult;

import java.util.ArrayList;
import java.util.List;

class Ship {
    private int hullSpaces;
    int mass; // TODO: Setter and validation, make private
    double maxAcceleration; // TODO: Setter and validation, make private
    double driveGeneration; // TODO: Setter and validation, make private

    double mastLength;

    int lanternArmor; // TODO: Systems object
    int armorShrink; // TODO: Systems object

    private static final int DRIVE_MASS_POWER = 1;
    private static final int PIVOT_ACCEL_POWER = 1;
    private static final int RADIANT_DEFLECTION = 96; // Handwaved "lensing" to reduce heat load.

    private boolean valid = false;
    private final List<String> validationErrors = new ArrayList<>();

    Ship build() {
        ValidationResult hullSpacesValidation = HullSpacesValidator.validate(hullSpaces);
        valid = hullSpacesValidation.valid();
        validationErrors.addAll(hullSpacesValidation.validationErrors());

        mastLength = calculateMastLength();
        return this;
    }

    boolean isValid() {
        return valid;
    }

    List<String> getValidationErrors() {
        return validationErrors;
    }

    Ship setHullSpaces(final int hullSpaces) {
        this.hullSpaces = hullSpaces;
        return this;
    }

    double getDriveHullSpaces() {
        return getDriveMass() / 25d;
    }

    double getDriveMass() {
        return getDriveMass(mastLength);
    }

    double getDriveMass(final double mastLength) {
        return getMastMass(mastLength) + getLanternMass();
    }

    double getLanternMass() {
        return getLanternStructuralMass() + getLanternArmorMass();
    }

    double getLanternStructuralMass() {
        double driveOutput = getDriveOutput();
        if (driveOutput > 4) {
            return 400 * Math.pow(driveOutput / 4d, 1.5);
        } else if (driveOutput < 1) {
            return 100 * Math.pow(driveOutput, 0.5);
        } else {
            return driveOutput * 100;
        }
    }

    double getDriveOutput() { // TODO: Add validation
        return 0.5d * mass * 1000 * maxAcceleration * 9.765625d * driveGeneration * 34722 / Math.pow(10, 12);
    }

    double getLanternArmorMass() {
        return lanternArmor * 50 * getLanternCoverageSurface() / (1000 + 50 * armorShrink);
    }

    double getLanternCoverageSurface() {
        return 4 * Math.PI * Math.pow(getLanternDiameter() / 2, 2) / 2;
    }

    double getLanternDiameter() {
        return Math.sqrt(mass * maxAcceleration / 125) * 20 / Math.sqrt(100 / (100d - RADIANT_DEFLECTION));
    }

    double getMastMass() {
        return getMastMass(mastLength);
    }

    double getMastMass(final double mastLength) {
        return getMastStructuralMass(mastLength) + getMastArmorMass() + getShieldMass();
    }

    double getMastStructuralMass() {
        return getMastStructuralMass(mastLength);
    }

    double getMastStructuralMass(final double mastLength) {
        return mass * maxAcceleration / 70000 * 7.8 * mastLength * getMastMassModifier();
    }

    double calculateMastLength() {
        double mastLength;
        if (hullSpaces < 100) mastLength = 25d;
        else if (hullSpaces < 400) mastLength = 50d;
        else if (hullSpaces < 1000) mastLength = 75d;
        else mastLength = 100d;
        double diff = getDifference(mastLength);

        double step = 1;
        final double target = 0.001d;
        while (Math.abs(diff) > target) {
            if (diff > 0) {
                step *= -1;
                while (diff > 0 && Math.abs(diff) > target) {
                    mastLength += step;
                    diff = getDifference(mastLength);
                }
                step *= -1;
            } else {
                while (diff < 0 && Math.abs(diff) > target) {
                    mastLength += step;
                    diff = getDifference(mastLength);
                }
            }
            step /= 10;
        }
        return mastLength;
    }

    private double getDifference(final double mastLength) {
        return 100000 * getFigureOfMerit(mastLength) - 1000000 * getFigureOfMerit(mastLength + 0.1);
    }

    private double getFigureOfMerit(final double mastLength) {
        return Math.pow(getPivotAccel(mastLength), PIVOT_ACCEL_POWER) / Math.pow(getDriveMass(mastLength), DRIVE_MASS_POWER);
    }

    double getPivotAccel(final double mastLength) {
        return (getPivotThrust() * 1000) * ((1 - getDriveFraction()) * (mastLength + getHullLength() / 2) - (getDriveFraction()) * (getLanternDiameter() / 2)) / (getMomentOfInertia() * 1000) * ((3 / Math.PI) * 128 * 16);
    }

    double getPivotThrust() {
        // TODO: Placeholder.
        return 0;
    }

    double getDriveFraction() {
        // TODO: Placeholder.
        return 0;
    }

    double getHullLength() {
        // TODO: Placeholder.
        return 0;
    }

    double getMomentOfInertia() {
        // TODO: Placeholder.
        return 0;
    }

    double getMastMassModifier() {
        // TODO: Placeholder.
        return 0;
    }

    double getMastArmorMass() {
        // TODO: Placeholder
        return 0;
    }

    double getShieldMass() {
        // TODO: Placeholder
        return 0;
    }
}
