package com.senorpez.avt.shipdesigner;

import com.senorpez.avt.shipdesigner.validators.HullSpacesValidator;
import com.senorpez.avt.shipdesigner.validators.ValidationResult;

import java.util.ArrayList;
import java.util.List;

class Ship {
    private int hullSpaces;
    int shipMass; // TODO: Setter and validation, make private
    double shipMaxAcceleration; // TODO: Setter and validation, make private
    double driveGeneration; // TODO: Setter and validation, make private

    int lanternArmor; // TODO: Systems object
    int armorShrink; // TODO: Systems object

    private static final int RADIANT_DEFLECTION = 96; // Handwaved "lensing" to reduce heat load.

    private boolean valid = false;
    private final List<String> validationErrors = new ArrayList<>();

    Ship build() {
        ValidationResult hullSpacesValidation = HullSpacesValidator.validate(hullSpaces);
        valid = hullSpacesValidation.valid();
        validationErrors.addAll(hullSpacesValidation.validationErrors());
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
        return getMastMass() + getLanternMass();
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
        return 0.5d * shipMass * 1000 * shipMaxAcceleration * 9.765625d * driveGeneration * 34722 / Math.pow(10, 12);
    }

    double getLanternArmorMass() {
        return lanternArmor * 50 * getLanternCoverageSurface() / (1000 + 50 * armorShrink);
    }

    double getLanternCoverageSurface() {
        return 4 * Math.PI * Math.pow(getLanternDiameter() / 2, 2) / 2;
    }

    double getLanternDiameter() {
        return Math.sqrt(shipMass * shipMaxAcceleration / 125) * 20 / Math.sqrt(100 / (100d - RADIANT_DEFLECTION));
    }

    double getMastMass() {
        return getMastStructuralMass() + getMastArmorMass() + getShieldMass();
    }

    double getMastStructuralMass() {
        // TODO: Placeholder
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
