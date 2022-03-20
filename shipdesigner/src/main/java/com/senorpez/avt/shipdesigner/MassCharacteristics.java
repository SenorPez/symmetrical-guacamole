package com.senorpez.avt.shipdesigner;

class MassCharacteristics {
    private final ShipCharacteristics characteristics;

    private Double mastLength = null;

    MassCharacteristics(ShipCharacteristics characteristics) {
        this.characteristics = characteristics;
    }

    double getHullMass() {
        return getHullSpaces() * 25;
    }

    double getHullSpaces() {
        return characteristics.getShipSpaces() - getHullArmorSpaces() - getOverallDriveSpaces_wArmor();
    }

    double getHullPercentage() {
        return getHullSpaces() / characteristics.getShipSpaces();
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
        return getHullMass() + getHullArmorMass();
    }

    double getTotalHullSpaces() {
        return getHullSpaces() + getHullArmorSpaces();
    }

    double getTotalHullPercentage() {
        return getTotalHullSpaces() / characteristics.getShipSpaces();
    }

    double getMastStructureMass() {
        return (characteristics.getShipMass() * characteristics.getShipAcceleration() / 70000) * 7.8 * getMastLength() * getMastMassModifier();
    }

    double getMastStructureSpaces() {
        return getMastStructureMass() / 25d;
    }

    double getMastStructurePercentage() {
        return getMastStructureSpaces() / characteristics.getShipSpaces();
    }

    double getMastArmorMass() {
        return ((1d / 15d) * Math.PI * Math.pow(getMastLength(), 2)) * characteristics.getMastArmor() * 50 / (1000 + 50 * characteristics.getArmorShrink());
    }

    double getMastArmorSpaces() {
        return getMastArmorMass() / 25d;
    }

    double getMastArmorPercentage() {
        return getMastArmorSpaces() / characteristics.getShipSpaces();
    }

    double getMastShieldMass() {
        return (getTenXRadReduction() * (Math.log10(getNeutronFlux_MR_yr()) + 6) - Math.log10(getRadReductionDueToMast())) * getShieldCrossSection();
    }

    double getMastShieldSpaces() {
        return getMastShieldMass() / 25d;
    }

    double getMastShieldPercentage() {
        return getMastShieldSpaces() / characteristics.getShipSpaces();
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
        return characteristics.getDriveArmor() * 50 * getLanternCoverageSurface() / (1000 + 50 * characteristics.getArmorShrink());
    }

    double getDriveArmorSpaces() {
        return getDriveArmorMass() / 25d;
    }

    double getDriveArmorPercentage() {
        return getDriveArmorSpaces() / characteristics.getShipSpaces();
    }

    double getOverallDriveMass_wArmor() {
        return getMastStructureMass() + getMastArmorMass() + getMastShieldMass() + getDriveMass() + getDriveArmorMass();
    }

    double getOverallDriveSpaces_wArmor() {
        return getOverallDriveMass_wArmor() / 25d;
    }

    double getOverallDrivePercentage_wArmor() {
        return getOverallDriveSpaces_wArmor() / characteristics.getShipSpaces();
    }

    double getOverallDriveMass_noArmor() {
        return getMastStructureMass() + getMastShieldMass() + getDriveMass();
    }

    double getOverallDriveSpaces_noArmor() {
        return getOverallDriveMass_noArmor() / 25d;
    }

    double getOverallDrivePercentage_noArmor() {
        return getOverallDriveSpaces_noArmor() / characteristics.getShipSpaces();
    }

    double getTotalShipArmorMass() {
        return getHullArmorMass() + getMastArmorMass() + getDriveArmorMass();
    }

    double getTotalShipArmorSpaces() {
        return getTotalShipArmorMass() / 25d;
    }

    double getTotalShipArmorPercentage() {
        return getTotalShipArmorSpaces() / characteristics.getShipSpaces();
    }

    double getTotalShipMass() {
        return getHullMass() + getOverallDriveMass_wArmor();
    }

    double getTotalShipSpaces() {
        return getHullSpaces() + getOverallDriveSpaces_wArmor();
    }

    double getTotalShipPercentage() {
        return getHullPercentage() + getOverallDrivePercentage_wArmor();
    }

    private int getRadiantDeflection() {
        return 96; // TODO: Constant? Allow as input?
    }

    private double getTenXRadReduction() {
        return 0.63000d; // TODO: Constant? Allow as input?
    }

    private double getMastMassModifier() {
        return characteristics.getHullShape().getMastMassModifier();
    }

    private double getNewCombatPower() {
        return 0.5 * characteristics.getShipMass() * 1000 * characteristics.getShipAcceleration() * 9.765625 * characteristics.getDriveGeneration() * 34722 / 1e12;
    }

    private double getLanternDiameter() {
        return ((Math.sqrt((characteristics.getShipMass() * characteristics.getShipAcceleration()) / 125)) * 20) / Math.sqrt(100 / (100d - getRadiantDeflection()));
    }

    private double getNeutronFlux_KR_hr() {
        return getNewCombatPower() * 500000 / Math.pow(getLanternDiameter() / 2, 2);
    }

    private double getNeutronFlux_MR_yr() {
        return getNeutronFlux_KR_hr() * 24 * 365.25 / 1000;
    }

    private double getRadReductionDueToMast() {
        return Math.pow((getMastLength() + (getLanternDiameter() / 2)) / (getLanternDiameter() / 2), 2);
    }








    double getShieldCrossSection() {
        // TODO: Placeholder
        return 0;
    }

    double getMastLength() {
        // TODO: Incomplete.
        if (characteristics.getShipSpaces() < 100) mastLength = 25d;
        else if (characteristics.getShipSpaces() < 400) mastLength = 50d;
        else if (characteristics.getShipSpaces() < 1000) mastLength = 75d;
        else mastLength = 100d;

        return 0;
    }

    double getDifferenceFunction() {
        // TODO: Incomplete
        return 1000000 * getFigureOfMerit(mastLength) - 1000000;
    }

    double getFigureOfMerit() {
        return getFigureOfMerit(mastLength);
    }

    double getFigureOfMerit(final double mastLength) {
        // Different from spreadsheet; removed constant.
        return Math.pow(getPivotAccel(mastLength), getPivotAccelPower()) / Math.pow(getOverallDriveMass_wArmor(), getDriveMassPower());
    }

    private double getPivotAccel(final double mastLength) {
        return (getPivotThrust() * 1000) * ((1 - getDriveFraction()) * (mastLength + getHullLength() / 2) - (getDriveFraction()) * (getLanternDiameter() / 2d)) / (getMomentOfInertia(mastLength) * 1000) * ((3 / Math.PI) * 128 * 16);
    }

    private double getPivotThrust() {
        if (getThrustOverride() != 0) return getThrustOverride();
        final double scalingFactor = 14.1522458529503; // TODO: Is this actually a constant?
                                                       // Can't find a place where it's updated in spreadsheet.
        return scalingFactor * Math.pow(characteristics.getShipMass(), 1d / 3d) * characteristics.getHullShape().getPivotModifier();
    }

    private double getDriveFraction() {
        return getOverallDriveMass_wArmor() / characteristics.getShipMass();
    }

    private double getHullLength() {
        return characteristics.getHullShape().getHullLength(characteristics.getShipSpaces(), getArmorFraction(), getTypicalDriveFraction());
    }

    private double getArmorFraction() {
        return characteristics.getHullArmor() / (double) characteristics.getShipSpaces();
    }

    double getMomentOfInertia(final double mastLength) {
        return characteristics.getHullShape().getMomentOfInertia(
                characteristics.getShipSpaces(),
                characteristics.getHullArmor() / (double) characteristics.getShipSpaces(),
                getTypicalDriveFraction(),
                getOverallDriveMass_wArmor() / characteristics.getShipMass(),
                characteristics.getShipMass(),
                mastLength,
                getLanternDiameter(),
                getMastStructureMass(),
                getMastArmorMass(),
                getDriveMass(),
                getDriveArmorMass(),
                getMastStructureMass() + getMastArmorMass() + getMastShieldMass()
        );
    }

    public MassCharacteristics setMastLength(Double mastLength) {
        this.mastLength = mastLength;
        return this;
    }

    private double getTypicalDriveFraction() {
        return 0.1421d * Math.exp(-0.0005 * characteristics.getShipSpaces());
    }

    double getLanternCoverageSurface() {
        // TODO: Placeholder
        return 0d;
    }

    private int getPivotAccelPower() {
        return 1; // TODO: Allow as input?
    }

    private int getDriveMassPower() {
        return 1; // TODO: Allow as input?
    }

    double getThrustOverride() {
        return 0d; // TODO: Allow as input?
    }
}
