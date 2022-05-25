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

    double getMastMass(final double mastLength) {
        return getMastStructureMass(mastLength) + getMastArmorMass(mastLength) + getShieldMass(mastLength);
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
        return 1000000 * getFigureOfMerit(mastLength) - 1000000 * getFigureOfMerit(mastLength + 0.1);
    }

    private double getFigureOfMerit(final double mastLength) {
        return Math.pow(getPivotAccel(mastLength), PIVOT_ACCEL_POWER) / Math.pow(getDriveMass(mastLength), DRIVE_MASS_POWER);
    }

    double getPivotAccel(final double mastLength) {
        return (getPivotThrust() * 1000) * ((1 - getDriveFraction(mastLength)) * (mastLength + getHullLength() / 2) - (getDriveFraction(mastLength)) * (getLanternDiameter() / 2)) / (getMomentOfInertia(mastLength) * 1000) * ((3 / Math.PI) * 128 * 16);
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

    double getHullLength() {
        return hullShape.getHullLength(hullSpaces, getArmorFraction());
    }

    public double getHullDiameter() {
        return hullShape.getHullDiameter(hullSpaces, getArmorFraction());
    }

    double getArmorFraction() {
        return getHullArmor() / (double) hullSpaces;
    }

    int getHullArmor() {
        return externalArmor + internalArmor;
    }

    double getMomentOfInertia(final double mastLength) {
        return hullShape.getMomentOfInertia(hullSpaces,
                getArmorFraction(),
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
        return 0.25 * Math.PI * Math.pow(getShieldMinDiameter(mastLength), 2);
    }

    double getShieldMinDiameter(final double mastLength) {
        return hullShape.getShieldMinDiameter(hullSpaces,
                getArmorFraction(),
                getLanternDiameter(),
                mastLength);
    }

    public double getShieldMinDiameter() {
        return getShieldMinDiameter(mastLength);
    }

    double getShieldMaxDiameter(final double mastLength) {
        return hullShape.getShieldMaxDiameter(hullSpaces,
                getArmorFraction(),
                getLanternDiameter(),
                mastLength,
                getShieldThickness(mastLength));
    }

    public double getShieldMaxDiameter() {
        return getShieldMaxDiameter(mastLength);
    }

    double getShieldThickness(final double mastLength) {
        return getShieldMass(mastLength) / getShieldCrossSection(mastLength) * 0.5;
    }

    public double getShieldThickness() {
        return getShieldThickness(mastLength);
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
