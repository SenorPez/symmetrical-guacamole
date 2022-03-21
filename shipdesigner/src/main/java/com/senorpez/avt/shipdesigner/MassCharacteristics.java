package com.senorpez.avt.shipdesigner;

class MassCharacteristics {
    private final ShipCharacteristics characteristics;
    private double mastLength;

    MassCharacteristics(ShipCharacteristics characteristics) {
        this.characteristics = characteristics;
        mastLength = calculateMastLength();
    }

    MassCharacteristics(ShipCharacteristics characteristics, final double mastLength) {
        this.characteristics = characteristics;
        this.mastLength = mastLength;
    }

    double getHullMass() {
        return getHullMass(mastLength);
    }

    double getHullMass(final double mastLength) {
        return getHullSpaces(mastLength) * 25;
    }

    double getHullSpaces() {
        return getHullSpaces(mastLength);
    }

    double getHullSpaces(final double mastLength) {
        return characteristics.getShipSpaces() - getHullArmorSpaces() - getOverallDriveSpaces_wArmor(mastLength);
    }

    double getHullPercentage() {
        return getHullPercentage(mastLength);
    }

    double getHullPercentage(final double mastLength) {
        return getHullSpaces(mastLength) / characteristics.getShipSpaces();
    }

    int getHullArmorMass() {
        return getHullArmorSpaces() * 25;
    }

    int getHullArmorSpaces() {
        return characteristics.getHullArmor();
    }

    double getHullArmorPercentage() {
        return getHullArmorSpaces() / (double) characteristics.getShipSpaces();
    }

    double getTotalHullMass() {
        return getTotalHullMass(mastLength);
    }

    double getTotalHullMass(final double mastLength) {
        return getHullMass(mastLength) + getHullArmorMass();
    }

    double getTotalHullSpaces() {
        return getTotalHullSpaces(mastLength);
    }

    double getTotalHullSpaces(final double mastLength) {
        return getHullSpaces(mastLength) + getHullArmorSpaces();
    }

    double getTotalHullPercentage() {
        return getTotalHullPercentage(mastLength);
    }

    double getTotalHullPercentage(final double mastLength) {
        return getTotalHullSpaces(mastLength) / characteristics.getShipSpaces();
    }

    double getMastStructureMass() {
        return getMastStructureMass(mastLength);
    }

    double getMastStructureMass(final double mastLength) {
        return (characteristics.getShipMass() * characteristics.getShipAcceleration() / 70000) * 7.8 * mastLength * getMastMassModifier();
    }

    double getMastStructureSpaces() {
        return getMastStructureSpaces(mastLength);
    }

    double getMastStructureSpaces(final double mastLength) {
        return getMastStructureMass(mastLength) / 25d;
    }

    double getMastStructurePercentage() {
        return getMastStructurePercentage(mastLength);
    }

    double getMastStructurePercentage(final double mastLength) {
        return getMastStructureSpaces(mastLength) / characteristics.getShipSpaces();
    }

    double getMastArmorMass() {
        return getMastArmorMass(mastLength);
    }

    double getMastArmorMass(final double mastLength) {
        return ((1d / 15d) * Math.PI * Math.pow(mastLength, 2)) * characteristics.getMastArmor() * 50 / (1000 + 50 * characteristics.getArmorShrink());
    }

    double getMastArmorSpaces() {
        return getMastArmorSpaces(mastLength);
    }

    double getMastArmorSpaces(final double mastLength) {
        return getMastArmorMass(mastLength) / 25d;
    }

    double getMastArmorPercentage() {
        return getMastArmorPercentage(mastLength);
    }

    double getMastArmorPercentage(final double mastLength) {
        return getMastArmorSpaces(mastLength) / characteristics.getShipSpaces();
    }

    double getMastShieldMass() {
        return getMastShieldMass(mastLength);
    }

    double getMastShieldMass(final double mastLength) {
        return (getTenXRadReduction() * (Math.log10(getNeutronFlux_MR_yr()) + 6) - Math.log10(getRadReductionDueToMast(mastLength))) * getShieldCrossSection(mastLength);
    }

    double getMastShieldSpaces() {
        return getMastShieldSpaces(mastLength);
    }

    double getMastShieldSpaces(final double mastLength) {
        return getMastShieldMass(mastLength) / 25d;
    }

    double getMastShieldPercentage() {
        return getMastShieldPercentage(mastLength);
    }

    double getMastShieldPercentage(final double mastLength) {
        return getMastShieldSpaces(mastLength) / characteristics.getShipSpaces();
    }

    double getDriveMass() {
        if (getNewCombatPower() > 4) {
            return 400 * Math.pow(getNewCombatPower() / 4, 1.5);
        } else if (getNewCombatPower() < 1) {
            return 100 * Math.pow(getNewCombatPower(), 0.5);
        } else {
            return getNewCombatPower() * 100;
        }
    }

    double getDriveSpaces() {
        return getDriveMass() / 25d;
    }

    double getDrivePercentage() {
        return getDriveSpaces() / characteristics.getShipSpaces();
    }

    double getDriveArmorMass() {
        return characteristics.getDriveArmor() * 50 * getDriveCoverageSurface() / (1000 + 50 * characteristics.getArmorShrink());
    }

    double getDriveArmorSpaces() {
        return getDriveArmorMass() / 25d;
    }

    double getDriveArmorPercentage() {
        return getDriveArmorSpaces() / characteristics.getShipSpaces();
    }

    double getOverallDriveMass_wArmor() {
        return getOverallDriveMass_wArmor(mastLength);
    }

    double getOverallDriveMass_wArmor(final double mastLength) {
        return getMastStructureMass(mastLength) + getMastArmorMass(mastLength) + getMastShieldMass(mastLength) + getDriveMass() + getDriveArmorMass();
    }

    double getOverallDriveSpaces_wArmor() {
        return getOverallDriveSpaces_wArmor(mastLength);
    }

    double getOverallDriveSpaces_wArmor(final double mastLength) {
        return getOverallDriveMass_wArmor(mastLength) / 25d;
    }

    double getOverallDrivePercentage_wArmor() {
        return getOverallDrivePercentage_wArmor(mastLength);
    }

    double getOverallDrivePercentage_wArmor(final double mastLength) {
        return getOverallDriveSpaces_wArmor(mastLength) / characteristics.getShipSpaces();
    }

    double getOverallDriveMass_noArmor() {
        return getOverallDriveMass_noArmor(mastLength);
    }

    double getOverallDriveMass_noArmor(final double mastLength) {
        return getMastStructureMass(mastLength) + getMastShieldMass(mastLength) + getDriveMass();
    }

    double getOverallDriveSpaces_noArmor() {
        return getOverallDriveSpaces_noArmor(mastLength);
    }

    double getOverallDriveSpaces_noArmor(final double mastLength) {
        return getOverallDriveMass_noArmor(mastLength) / 25d;
    }

    double getOverallDrivePercentage_noArmor() {
        return getOverallDrivePercentage_noArmor(mastLength);
    }

    double getOverallDrivePercentage_noArmor(final double mastLength) {
        return getOverallDriveSpaces_noArmor(mastLength) / characteristics.getShipSpaces();
    }

    double getTotalShipArmorMass() {
        return getTotalShipArmorMass(mastLength);
    }

    double getTotalShipArmorMass(final double mastLength) {
        return getHullArmorMass() + getMastArmorMass(mastLength) + getDriveArmorMass();
    }

    double getTotalShipArmorSpaces() {
        return getTotalShipArmorSpaces(mastLength);
    }

    double getTotalShipArmorSpaces(final double mastLength) {
        return getTotalShipArmorMass(mastLength) / 25d;
    }

    double getTotalShipArmorPercentage() {
        return getTotalShipArmorPercentage(mastLength);
    }

    double getTotalShipArmorPercentage(final double mastLength) {
        return getTotalShipArmorSpaces(mastLength) / characteristics.getShipSpaces();
    }

    double getTotalShipMass() {
        return getTotalShipMass(mastLength);
    }

    double getTotalShipMass(final double mastLength) {
        return getTotalHullMass(mastLength) + getOverallDriveMass_wArmor(mastLength);
    }

    double getTotalShipSpaces() {
        return getTotalShipSpaces(mastLength);
    }

    double getTotalShipSpaces(final double mastLength) {
        return getTotalHullSpaces(mastLength) + getOverallDriveSpaces_wArmor(mastLength);
    }

    double getTotalShipPercentage() {
        return getTotalShipPercentage(mastLength);
    }

    double getTotalShipPercentage(final double mastLength) {
        return getTotalHullPercentage(mastLength) + getOverallDrivePercentage_wArmor(mastLength);
    }

    private int getDriveMassPower() {
        return 1; // TODO: Allow as input?
    }

    private int getPivotAccelPower() {
        return 1; // TODO: Allow as input?
    }

    private int getRadiantDeflection() {
        return 96; // TODO: Constant? Allow as input?
    }

    private double getTenXRadReduction() {
        return 0.63000d; // TODO: Constant? Allow as input?
    }

    private double getThrustOverride() {
        return 0d; // TODO: Allow as input?
    }

    double getArmorFraction() {
        return characteristics.getHullArmor() / (double) characteristics.getShipSpaces();
    }

    private double getDriveCoverageSurface() {
        return 4 * Math.PI * Math.pow(getDriveDiameter() / 2, 2) / 2;
    }

    private double getDriveDiameter() {
        return ((Math.sqrt((characteristics.getShipMass() * characteristics.getShipAcceleration()) / 125)) * 20) / Math.sqrt(100 / (100d - getRadiantDeflection()));
    }

    private double getDriveFraction(final double mastLength) {
        return getOverallDriveMass_wArmor(mastLength) / characteristics.getShipMass();
    }

    double getDriveFraction_Typical() {
        return 0.1421d * Math.exp(-0.0005 * characteristics.getShipSpaces());
    }

    private double getFigureOfMerit() {
        return getFigureOfMerit(mastLength);
    }

    private double getFigureOfMerit(final double mastLength) {
        // Different from spreadsheet; removed constant.
        return Math.pow(getPivotAccel(mastLength), getPivotAccelPower()) / Math.pow(getOverallDriveMass_wArmor(mastLength), getDriveMassPower());
    }

    private double getHullLength() {
        return characteristics.getHullShape().getHullLength(characteristics.getShipSpaces(), getArmorFraction(), getDriveFraction_Typical());
    }

    private double getMastMassModifier() {
        return characteristics.getHullShape().getMastMassModifier();
    }

    double getMomentOfInertia(final double mastLength) {
        return characteristics.getHullShape().getMomentOfInertia(
                characteristics.getShipSpaces(),
                characteristics.getHullArmor() / (double) characteristics.getShipSpaces(),
                getDriveFraction_Typical(),
                getOverallDriveMass_wArmor(mastLength) / characteristics.getShipMass(),
                characteristics.getShipMass(),
                mastLength,
                getDriveDiameter(),
                getMastStructureMass(mastLength),
                getMastArmorMass(mastLength),
                getDriveMass(),
                getDriveArmorMass(),
                getMastStructureMass(mastLength) + getMastArmorMass(mastLength) + getMastShieldMass(mastLength)
        );
    }

    private double getNeutronFlux_KR_hr() {
        return getNewCombatPower() * 500000 / Math.pow(getDriveDiameter() / 2, 2);
    }

    private double getNeutronFlux_MR_yr() {
        return getNeutronFlux_KR_hr() * 24 * 365.25 / 1000;
    }

    private double getNewCombatPower() {
        return 0.5 * characteristics.getShipMass() * 1000 * characteristics.getShipAcceleration() * 9.765625 * characteristics.getDriveGeneration() * 34722 / 1e12;
    }

    private double getPivotAccel(final double mastLength) {
        return (getPivotThrust() * 1000) * ((1 - getDriveFraction(mastLength)) * (mastLength + getHullLength() / 2) - (getDriveFraction(mastLength)) * (getDriveDiameter() / 2d)) / (getMomentOfInertia(mastLength) * 1000) * ((3 / Math.PI) * 128 * 16);
    }

    private double getPivotThrust() {
        if (getThrustOverride() != 0) return getThrustOverride();
        final double scalingFactor = 14.1522458529503; // TODO: Is this actually a constant?
        // Can't find a place where it's updated in spreadsheet.
        return scalingFactor * Math.pow(characteristics.getShipMass(), 1d / 3d) * characteristics.getHullShape().getPivotModifier();
    }

    private double getRadReductionDueToMast(final double mastLength) {
        return Math.pow((mastLength + (getDriveDiameter() / 2)) / (getDriveDiameter() / 2), 2);
    }

    private double getShieldCrossSection(final double mastLength) {
        return 0.25d * Math.PI * Math.pow(getShieldDiameter(mastLength), 2);
    }

    private double getShieldDiameter(final double mastLength) {
        return characteristics.getHullShape().getShieldDiameter(characteristics.getShipSpaces(), getArmorFraction(), getDriveFraction_Typical(), mastLength, getDriveDiameter());
    }

    private double calculateMastLength() {
        if (characteristics.getShipSpaces() < 100) mastLength = 25d;
        else if (characteristics.getShipSpaces() < 400) mastLength = 50d;
        else if (characteristics.getShipSpaces() < 1000) mastLength = 75d;
        else mastLength = 100d;
        double diff = getDifferenceFunction();
        System.out.println(mastLength + " " + diff);

        double step = 1;
        final double target = 0.001d;
        while (Math.abs(diff) > target) {
            if (diff > 0) {
                step *= -1;
                while (diff > 0 && Math.abs(diff) > target) {
                    mastLength += step;
                    diff = getDifferenceFunction();
                    System.out.println(mastLength + " " + diff + "" + step);
                }
                step *= -1;
            } else {
                while (diff < 0 && Math.abs(diff) > target) {
                    mastLength += step;
                    diff = getDifferenceFunction();
                    System.out.println(mastLength + " " + diff + " " + step);
                }
            }
            step /= 10;
        }
        return mastLength;
    }

    private double getDifferenceFunction() {
        return 1000000 * getFigureOfMerit() - 1000000 * getFigureOfMerit(mastLength + 0.1);
    }
}
