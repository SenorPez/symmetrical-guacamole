package com.senorpez.avt.shipdesigner;

class MassCharacteristics {
    private final ShipCharacteristics characteristics;
    private Double mastLength = null;

    MassCharacteristics(ShipCharacteristics characteristics) {
        this.characteristics = characteristics;
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
        return characteristics.getShipSpaces() - getHullArmorSpaces(mastLength) - getOverallDriveSpaces_wArmor(mastLength);
    }

    double getHullPercentage() {
        return getHullPercentage(mastLength);
    }

    double getHullPercentage(final double mastLength) {
        return getHullSpaces(mastLength) / characteristics.getShipSpaces();
    }

    int getHullArmorMass() {
        return getHullArmorMass(mastLength);
    }

    int getHullArmorMass(final double mastLength) {
        return getHullArmorSpaces(mastLength) * 25;
    }

    int getHullArmorSpaces() {
        return getHullArmorSpaces(mastLength);
    }

    int getHullArmorSpaces(final double mastLength) {
        return characteristics.getHullArmor();
    }

    double getHullArmorPercentage() {
        return getHullArmorPercentage(mastLength);
    }

    double getHullArmorPercentage(final double mastLength) {
        return getHullArmorSpaces(mastLength) / (double) characteristics.getShipSpaces();
    }

    double getTotalHullMass() {
        return getTotalHullMass(mastLength);
    }

    double getTotalHullMass(final double mastLength) {
        return getHullMass(mastLength) + getHullArmorMass(mastLength);
    }

    double getTotalHullSpaces() {
        return getTotalHullSpaces(mastLength);
    }

    double getTotalHullSpaces(final double mastLength) {
        return getHullSpaces(mastLength) + getHullArmorSpaces(mastLength);
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
        return (characteristics.getShipMass() * characteristics.getShipAcceleration() / 70000) * 7.8 * mastLength * getMastMassModifier(mastLength);
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
        return (getTenXRadReduction() * (Math.log10(getNeutronFlux_MR_yr(mastLength)) + 6) - Math.log10(getRadReductionDueToMast(mastLength))) * getShieldCrossSection(mastLength);
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
        return getDriveMass(mastLength);
    }
    double getDriveMass(final double mastLength) {
        if (getNewCombatPower(mastLength) > 4) {
            return 400 * Math.pow(getNewCombatPower(mastLength) / 4, 1.5);
        } else if (getNewCombatPower(mastLength) < 1) {
            return 100 * Math.pow(getNewCombatPower(mastLength), 0.5);
        } else {
            return getNewCombatPower(mastLength) * 100;
        }
    }

    double getDriveSpaces() {
        return getDriveSpaces(mastLength);
    }

    double getDriveSpaces(final double mastLength) {
        return getDriveMass(mastLength) / 25d;
    }

    double getDrivePercentage() {
        return getDrivePercentage(mastLength);
    }

    double getDrivePercentage(final double mastLength) {
        return getDriveSpaces(mastLength) / characteristics.getShipSpaces();
    }

    double getDriveArmorMass() {
        return getDriveArmorMass(mastLength);
    }

    double getDriveArmorMass(final double mastLength) {
        return characteristics.getDriveArmor() * 50 * getDriveCoverageSurface(mastLength) / (1000 + 50 * characteristics.getArmorShrink());
    }

    double getDriveArmorSpaces() {
        return getDriveArmorSpaces(mastLength);
    }

    double getDriveArmorSpaces(final double mastLength) {
        return getDriveArmorMass(mastLength) / 25d;
    }

    double getDriveArmorPercentage() {
        return getDriveArmorPercentage(mastLength);
    }

    double getDriveArmorPercentage(final double mastLength) {
        return getDriveArmorSpaces(mastLength) / characteristics.getShipSpaces();
    }

    double getOverallDriveMass_wArmor() {
        return getOverallDriveMass_wArmor(mastLength);
    }

    double getOverallDriveMass_wArmor(final double mastLength) {
        return getMastStructureMass(mastLength) + getMastArmorMass(mastLength) + getMastShieldMass(mastLength) + getDriveMass(mastLength) + getDriveArmorMass(mastLength);
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
        return getMastStructureMass(mastLength) + getMastShieldMass(mastLength) + getDriveMass(mastLength);
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
        return getHullArmorMass(mastLength) + getMastArmorMass(mastLength) + getDriveArmorMass(mastLength);
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
        return getHullMass(mastLength) + getOverallDriveMass_wArmor(mastLength);
    }

    double getTotalShipSpaces() {
        return getTotalShipSpaces(mastLength);
    }

    double getTotalShipSpaces(final double mastLength) {
        return getHullSpaces(mastLength) + getOverallDriveSpaces_wArmor(mastLength);
    }

    double getTotalShipPercentage() {
        return getTotalShipPercentage(mastLength);
    }

    double getTotalShipPercentage(final double mastLength) {
        return getHullPercentage(mastLength) + getOverallDrivePercentage_wArmor(mastLength);
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

    private double getArmorFraction() {
        return getArmorFraction(mastLength);
    }

    private double getArmorFraction(final double mastLength) {
        return characteristics.getHullArmor() / (double) characteristics.getShipSpaces();
    }

    private double getDriveCoverageSurface() {
        return getDriveCoverageSurface(mastLength);
    }

    private double getDriveCoverageSurface(final double mastLength) {
        return 4 * Math.PI * Math.pow(getDriveDiameter(mastLength) / 2, 2) / 2;
    }

    private double getDriveDiameter() {
        return getDriveDiameter(mastLength);
    }

    private double getDriveDiameter(final double mastLength) {
        return ((Math.sqrt((characteristics.getShipMass() * characteristics.getShipAcceleration()) / 125)) * 20) / Math.sqrt(100 / (100d - getRadiantDeflection()));
    }

    private double getDriveFraction() {
        return getDriveFraction(mastLength);
    }

    private double getDriveFraction(final double mastLength) {
        return getOverallDriveMass_wArmor(mastLength) / characteristics.getShipMass();
    }

    private double getDriveFraction_Typical() {
        return getDriveFraction_Typical(mastLength);
    }

    private double getDriveFraction_Typical(final double mastLength) {
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
        return getHullLength(mastLength);
    }

    private double getHullLength(final double mastLength) {
        return characteristics.getHullShape().getHullLength(characteristics.getShipSpaces(), getArmorFraction(mastLength), getDriveFraction_Typical(mastLength));
    }

    private double getMastMassModifier() {
        return getMastMassModifier(mastLength);
    }

    private double getMastMassModifier(final double mastLength) {
        return characteristics.getHullShape().getMastMassModifier();
    }

    double getMomentOfInertia() {
        return getMomentOfInertia(mastLength);
    }

    double getMomentOfInertia(final double mastLength) {
        return characteristics.getHullShape().getMomentOfInertia(
                characteristics.getShipSpaces(),
                characteristics.getHullArmor() / (double) characteristics.getShipSpaces(),
                getDriveFraction_Typical(mastLength),
                getOverallDriveMass_wArmor(mastLength) / characteristics.getShipMass(),
                characteristics.getShipMass(),
                mastLength,
                getDriveDiameter(mastLength),
                getMastStructureMass(mastLength),
                getMastArmorMass(mastLength),
                getDriveMass(mastLength),
                getDriveArmorMass(mastLength),
                getMastStructureMass(mastLength) + getMastArmorMass(mastLength) + getMastShieldMass(mastLength)
        );
    }

    private double getNeutronFlux_KR_hr() {
        return getNeutronFlux_KR_hr(mastLength);
    }

    private double getNeutronFlux_KR_hr(final double mastLength) {
        return getNewCombatPower(mastLength) * 500000 / Math.pow(getDriveDiameter(mastLength) / 2, 2);
    }

    private double getNeutronFlux_MR_yr() {
        return getNeutronFlux_MR_yr(mastLength);
    }

    private double getNeutronFlux_MR_yr(final double mastLength) {
        return getNeutronFlux_KR_hr(mastLength) * 24 * 365.25 / 1000;
    }

    private double getNewCombatPower() {
        return getNewCombatPower(mastLength);
    }

    private double getNewCombatPower(final double mastLength) {
        return 0.5 * characteristics.getShipMass() * 1000 * characteristics.getShipAcceleration() * 9.765625 * characteristics.getDriveGeneration() * 34722 / 1e12;
    }

    private double getPivotAccel() {
        return getPivotAccel(mastLength);
    }

    private double getPivotAccel(final double mastLength) {
        return (getPivotThrust(mastLength) * 1000) * ((1 - getDriveFraction(mastLength)) * (mastLength + getHullLength(mastLength) / 2) - (getDriveFraction(mastLength)) * (getDriveDiameter(mastLength) / 2d)) / (getMomentOfInertia(mastLength) * 1000) * ((3 / Math.PI) * 128 * 16);
    }

    private double getPivotThrust() {
        return getPivotThrust(mastLength);
    }

    private double getPivotThrust(final double mastLength) {
        if (getThrustOverride() != 0) return getThrustOverride();
        final double scalingFactor = 14.1522458529503; // TODO: Is this actually a constant?
        // Can't find a place where it's updated in spreadsheet.
        return scalingFactor * Math.pow(characteristics.getShipMass(), 1d / 3d) * characteristics.getHullShape().getPivotModifier();
    }

    private double getRadReductionDueToMast() {
        return getRadReductionDueToMast(mastLength);
    }

    private double getRadReductionDueToMast(final double mastLength) {
        return Math.pow((mastLength + (getDriveDiameter(mastLength) / 2)) / (getDriveDiameter(mastLength) / 2), 2);
    }

    private double getShieldCrossSection() {
        return getShieldCrossSection(mastLength);
    }

    private double getShieldCrossSection(final double mastLength) {
        return 0.25d * Math.PI * Math.pow(getShieldDiameter(mastLength), 2);
    }

    private double getShieldDiameter() {
        return getShieldDiameter(mastLength);
    }

    private double getShieldDiameter(final double mastLength) {
        return characteristics.getHullShape().getShieldDiameter(characteristics.getShipSpaces(), getArmorFraction(mastLength), getDriveFraction_Typical(mastLength), mastLength, getDriveDiameter(mastLength));
    }

    double calculateMastLength() {
        // TODO: Incomplete.
        if (characteristics.getShipSpaces() < 100) mastLength = 25d;
        else if (characteristics.getShipSpaces() < 400) mastLength = 50d;
        else if (characteristics.getShipSpaces() < 1000) mastLength = 75d;
        else mastLength = 100d;

        return 0;
    }

    double getDifferenceFunction() {
        return 1000000 * getFigureOfMerit() - 1000000 * getFigureOfMerit(mastLength + 0.1);
    }

    public MassCharacteristics setMastLength(Double mastLength) {
        this.mastLength = mastLength;
        return this;
    }
}
