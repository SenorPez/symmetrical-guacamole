package com.senorpez.avt.shipdesigner;

import com.senorpez.avt.shipdesigner.validators.HullSpacesValidator;
import com.senorpez.avt.shipdesigner.validators.ValidationResult;

import java.util.ArrayList;
import java.util.List;

class Ship {
    private HullShape hullShape;
    private int hullSpaces;
    double maxAcceleration; // TODO: Setter and validation, make private
    double driveGeneration; // TODO: Setter and validation, make private

    double mastLength; // TODO: Setter, make private

    int externalArmor; // TODO: Systems object
    int internalArmor; // TODO: Systems object
    int mastArmor; // TODO: Systems object
    int lanternArmor; // TODO: Systems object
    int armorShrink; // TODO: Systems object

    private int mass;

    private static final int DRIVE_MASS_POWER = 1;
    private static final int PIVOT_ACCEL_POWER = 1;
    private static final double PIVOT_SCALING_FACTOR = 14.1522458529503d;

    private static final double RAD_REDUCTION = 0.630000d;

    double pivotThrustOverride = 0; // Setter, make private

    private static final int RADIANT_DEFLECTION = 96; // Handwaved "lensing" to reduce heat load.

    private boolean valid = false;
    private final List<String> validationErrors = new ArrayList<>();

    Ship build() {
        ValidationResult hullSpacesValidation = HullSpacesValidator.validate(hullSpaces);
        valid = hullSpacesValidation.valid();
        validationErrors.addAll(hullSpacesValidation.validationErrors());
        mass = hullSpaces * 25;

        mastLength = calculateMastLength();
        return this;
    }

    boolean isValid() {
        return valid;
    }

    List<String> getValidationErrors() {
        return validationErrors;
    }

    Ship setHullShape(final HullShape hullShape) {
        this.hullShape = hullShape;
        return this;
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
        return getLanternStructureMass() + getLanternArmorMass();
    }

    double getLanternStructureMass() {
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

    double getMastMass(final double mastLength) {
        return getMastStructureMass(mastLength) + getMastArmorMass(mastLength) + getShieldMass(mastLength);
    }

    double getMastStructureMass(final double mastLength) {
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
        return (getPivotThrust() * 1000) * ((1 - getDriveFraction(mastLength)) * (mastLength + getHullLength(mastLength) / 2) - (getDriveFraction(mastLength)) * (getLanternDiameter() / 2)) / (getMomentOfInertia(mastLength) * 1000) * ((3 / Math.PI) * 128 * 16);
    }

    double getPivotThrust() {
        return pivotThrustOverride != 0 ? pivotThrustOverride
                : PIVOT_SCALING_FACTOR * Math.pow(mass, 1 / 3d) * getPivotModifier();
    }

    double getPivotModifier() {
        return hullShape.getPivotModifier();
    }

    double getDriveFraction(final double mastLength) {
        return getDriveMass(mastLength) / mass;
    }

    double getHullLength(final double mastLength) {
        double usableFraction = 1 - getArmorFraction() - getDriveFraction(mastLength);
        return hullShape.getHullLength(hullSpaces, usableFraction);
    }

    double getArmorFraction() {
        return getHullArmor() / (double) hullSpaces;
    }

    int getHullArmor() {
        return externalArmor + internalArmor;
    }

    double getMomentOfInertia(final double mastLength) {
        return hullShape.getMomentOfInertia(hullSpaces,
                getUsableFraction(mastLength),
                getDriveFraction(mastLength),
                getLanternMass(),
                getLanternDiameter(),
                mastLength,
                getMastStructureMass(mastLength),
                getMastArmorMass(mastLength),
                getMastMass(mastLength));
    }

    double getUsableFraction(final double mastLength) {
        return 1 - getArmorFraction() - getDriveFraction(mastLength);
    }

    double getMastMassModifier() {
        return hullShape.getMastMassModifier();
    }

    double getMastArmorMass(final double mastLength) {
        return ((1 / 15d) * Math.PI * Math.pow(mastLength, 2)) * mastArmor * 50 / (1000 + 50 * armorShrink);
    }

    double getShieldMass(final double mastLength) {
        return (RAD_REDUCTION * (Math.log10(getNeutronFluxAtShield()) + 6) - Math.log10(getRadReductionDueToMast(mastLength))) * getShieldCrossSection(mastLength);
    }

    double getNeutronFluxAtShield() {
        final double flux_kr_per_hour = (getDriveOutput() * 500000) / Math.pow(getLanternDiameter() / 2, 2);
        return flux_kr_per_hour * 24 * 365.25 / 1000;
    }

    double getRadReductionDueToMast(final double mastLength) {
        return Math.pow((mastLength + getLanternDiameter() / 2) / (getLanternDiameter() / 2), 2);
    }

    double getShieldCrossSection(final double mastLength) {
        return 0.25 * Math.PI * Math.pow(getShieldDiameter(mastLength), 2);
    }

    double getShieldDiameter(final double mastLength) {
        return hullShape.getShieldDiameter(hullSpaces,
                getUsableFraction(mastLength),
                getLanternDiameter(),
                mastLength);
    }
}
