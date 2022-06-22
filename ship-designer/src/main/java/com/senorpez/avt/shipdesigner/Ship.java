package com.senorpez.avt.shipdesigner;

import com.senorpez.avt.shipdesigner.validators.DriveGenerationValidator;
import com.senorpez.avt.shipdesigner.validators.HullSpacesValidator;
import com.senorpez.avt.shipdesigner.validators.ThrustValidator;
import com.senorpez.avt.shipdesigner.validators.ValidationResult;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private double driveGeneration = 1.0;
    private HullShape hullShape = HullShape.SPHERE;
    private int hullSpaces = 25;
    private double thrust = 0.5;

    private double mastLength;

    public int externalArmor; // TODO: Systems object
    public int internalArmor; // TODO: Systems object
    int mastArmor; // TODO: Systems object
    int lanternArmor; // TODO: Systems object
    int armorShrink; // TODO: Systems object

    private double acceleration;
    private int mass;

    private static final int DRIVE_MASS_POWER = 1;
    private static final int PIVOT_ACCEL_POWER = 1;
    private static final double PIVOT_SCALING_FACTOR = 14.1522458529503d;

    private static final double RAD_REDUCTION = 0.630000d;

    double pivotThrustOverride = 0; // Setter, make private

    private static final int RADIANT_DEFLECTION = 96; // Hand-waved "lensing" to reduce heat load.

    private boolean valid = false;
    private final List<String> validationErrors = new ArrayList<>();

    public Ship build() {
        ValidationResult driveGenerationValidation = DriveGenerationValidator.validate(driveGeneration);
        valid = driveGenerationValidation.valid();
        validationErrors.addAll(driveGenerationValidation.validationErrors());

        ValidationResult hullSpacesValidation = HullSpacesValidator.validate(hullSpaces);
        valid = valid && hullSpacesValidation.valid();
        validationErrors.addAll(hullSpacesValidation.validationErrors());

        ValidationResult thrustValidation = ThrustValidator.validate(thrust);
        valid = valid && thrustValidation.valid();
        validationErrors.addAll(thrustValidation.validationErrors());


        mastLength = calculateMastLength();
        return this;
    }

    boolean isValid() {
        return valid;
    }

    List<String> getValidationErrors() {
        return validationErrors;
    }

    public Ship setDriveGeneration(double driveGeneration) {
        this.driveGeneration = driveGeneration;
        return this;
    }

    public Ship setHullShape(final HullShape hullShape) {
        this.hullShape = hullShape;
        return this;
    }

    public Ship setHullSpaces(final int hullSpaces) {
        this.hullSpaces = hullSpaces;
        this.mass = hullSpaces * 25;
        return this;
    }

    public Ship setThrust(final double thrust) {
        this.thrust = thrust;
        this.acceleration = thrust / 4d;
        return this;
    }

    double getDriveHullSpaces(final double mastLength, final double usableFraction) {
        return getDriveMass(mastLength, usableFraction) / 25d;
    }

    double getDriveMass(final double mastLength, final double usableFraction) {
        return getMastMass(mastLength, usableFraction) + getLanternMass();
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
        return 0.5d * mass * 1000 * acceleration * 9.765625d * driveGeneration * 34722 / Math.pow(10, 12);
    }

    double getLanternArmorMass() {
        return lanternArmor * 50 * getLanternCoverageSurface() / (1000 + 50 * armorShrink);
    }

    double getLanternCoverageSurface() {
        return 4 * Math.PI * Math.pow(getLanternDiameter() / 2, 2) / 2;
    }

    public double getLanternDiameter() {
        return Math.sqrt(mass * acceleration / 125) * 20 / Math.sqrt(100 / (100d - RADIANT_DEFLECTION));
    }

    double getMastMass(final double mastLength, final double usableFraction) {
        return getMastStructureMass(mastLength) + getMastArmorMass(mastLength) + getShieldMass(mastLength, usableFraction);
    }

    double getMastStructureMass(final double mastLength) {
        return mass * acceleration / 70000 * 7.8 * mastLength * getMastMassModifier();
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
        return 1000000 * getFigureOfMerit(mastLength, getUsableFraction(mastLength)) - 1000000 * getFigureOfMerit(mastLength + 0.1, getUsableFraction(mastLength));
    }

    private double getFigureOfMerit(final double mastLength, final double usableFraction) {
        return Math.pow(getPivotAccel(mastLength, usableFraction), PIVOT_ACCEL_POWER) / Math.pow(getDriveMass(mastLength, usableFraction), DRIVE_MASS_POWER);
    }

    double getPivotAccel(final double mastLength, final double usableFraction) {
        return (getPivotThrust() * 1000) * ((1 - getDriveFraction(mastLength)) * (mastLength + getHullLength(mastLength) / 2) - (getDriveFraction(mastLength)) * (getLanternDiameter() / 2)) / (getMomentOfInertia(mastLength, usableFraction) * 1000) * ((3 / Math.PI) * 128 * 16);
    }

    double getPivotThrust() {
        return pivotThrustOverride != 0 ? pivotThrustOverride
                : PIVOT_SCALING_FACTOR * Math.pow(mass, 1 / 3d) * getPivotModifier();
    }

    double getPivotModifier() {
        return hullShape.getPivotModifier();
    }


    double getDriveFraction(final double mastLength) {
        double driveFraction = 0d;
        double actualDriveFraction = 0.14d;
        double driveMass = 0d;

        while (Math.abs(driveFraction - actualDriveFraction) > 1e-9) {
            driveFraction = actualDriveFraction;
            final double usableHullFraction = 1 - driveFraction - getArmorFraction();
            driveMass = getDriveMass(mastLength, usableHullFraction);
            actualDriveFraction = driveMass / this.mass;
        }

        return driveMass / mass;
    }

    double getHullLength(final double mastLength) {
        return hullShape.getHullLength(hullSpaces, 1 - getArmorFraction() - getDriveFraction(mastLength));
    }

    public double getHullDiameter(final double mastLength) {
        return hullShape.getHullDiameter(hullSpaces, 1 - getArmorFraction() - getDriveFraction(mastLength));
    }

    double getArmorFraction() {
        return getHullArmor() / (double) hullSpaces;
    }

    int getHullArmor() {
        return externalArmor + internalArmor;
    }

    double getMomentOfInertia(final double mastLength, final double usableFraction) {
        return hullShape.getMomentOfInertia(hullSpaces,
                usableFraction,
                getDriveFraction(mastLength),
                getLanternMass(),
                getLanternDiameter(),
                mastLength,
                getMastStructureMass(mastLength),
                getMastArmorMass(mastLength),
                getMastMass(mastLength, usableFraction));
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

    double getShieldMass(final double mastLength, final double usableFraction) {
        return (RAD_REDUCTION * (Math.log10(getNeutronFluxAtShield()) + 6) - Math.log10(getRadReductionDueToMast(mastLength))) * getShieldCrossSection(mastLength, usableFraction);
    }

    double getNeutronFluxAtShield() {
        final double flux_kr_per_hour = (getDriveOutput() * 500000) / Math.pow(getLanternDiameter() / 2, 2);
        return flux_kr_per_hour * 24 * 365.25 / 1000;
    }

    double getRadReductionDueToMast(final double mastLength) {
        return Math.pow((mastLength + getLanternDiameter() / 2) / (getLanternDiameter() / 2), 2);
    }

    double getShieldCrossSection(final double mastLength, final double usableFraction) {
        return 0.25 * Math.PI * Math.pow(getShieldMinDiameter(mastLength, usableFraction), 2);
    }

    double getShieldMinDiameter(final double mastLength, final double usableFraction) {
        return hullShape.getShieldMinDiameter(hullSpaces,
                usableFraction,
                getLanternDiameter(),
                mastLength);
    }

    double getShieldMaxDiameter(final double mastLength, final double usableFraction) {
        return hullShape.getShieldMaxDiameter(hullSpaces,
                getArmorFraction(),
                getLanternDiameter(),
                mastLength,
                getShieldThickness(mastLength, usableFraction));
    }

    double getShieldThickness(final double mastLength, final double usableFraction) {
        return getShieldMass(mastLength, usableFraction) / getShieldCrossSection(mastLength, usableFraction) * 0.5;
    }

    public double getMastLength() {
        return mastLength;
    }

    Ship setMastLength(final double mastLength) {
        this.mastLength = mastLength;
        return this;
    }

    public double getMastDiameter() {
        return mastLength / 15;
    }
}
