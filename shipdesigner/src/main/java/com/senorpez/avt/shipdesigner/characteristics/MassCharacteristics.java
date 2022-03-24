package com.senorpez.avt.shipdesigner.characteristics;

public class MassCharacteristics {
    private final ShipCharacteristics shipCharacteristics;
    private double mastLength;

    public MassCharacteristics(ShipCharacteristics shipCharacteristics) {
        this.shipCharacteristics = shipCharacteristics;
        mastLength = calculateMastLength();
    }

    MassCharacteristics(ShipCharacteristics shipCharacteristics, final double mastLength) {
        this.shipCharacteristics = shipCharacteristics;
        this.mastLength = mastLength;
    }

    public double getMastLength() {
        return mastLength;
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
        return shipCharacteristics.getShipSpaces() - getHullArmorSpaces() - getOverallDriveSpaces_wArmor(mastLength);
    }

    double getHullPercentage() {
        return getHullPercentage(mastLength);
    }

    double getHullPercentage(final double mastLength) {
        return getHullSpaces(mastLength) / shipCharacteristics.getShipSpaces();
    }

    int getHullArmorMass() {
        return getHullArmorSpaces() * 25;
    }

    int getHullArmorSpaces() {
        return shipCharacteristics.getHullArmor();
    }

    double getHullArmorPercentage() {
        return getHullArmorSpaces() / (double) shipCharacteristics.getShipSpaces();
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
        return getTotalHullSpaces(mastLength) / shipCharacteristics.getShipSpaces();
    }

    double getMastStructureMass() {
        return getMastStructureMass(mastLength);
    }

    double getMastStructureMass(final double mastLength) {
        return (shipCharacteristics.getShipMass() * shipCharacteristics.getShipAcceleration() / 70000) * 7.8 * mastLength * getMastMassModifier();
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
        return getMastStructureSpaces(mastLength) / shipCharacteristics.getShipSpaces();
    }

    double getMastArmorMass() {
        return getMastArmorMass(mastLength);
    }

    double getMastArmorMass(final double mastLength) {
        return ((1d / 15d) * Math.PI * Math.pow(mastLength, 2)) * shipCharacteristics.getMastArmor() * 50 / (1000 + 50 * shipCharacteristics.getArmorShrink());
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
        return getMastArmorSpaces(mastLength) / shipCharacteristics.getShipSpaces();
    }

    double getMastShieldMass() {
        return getMastShieldMass(mastLength);
    }

    double getMastShieldMass(final double mastLength) {
        return (getTenXRadReduction() * (Math.log10(getNeutronFlux_MR_yr()) + 6) - Math.log10(getRadReductionDueToMast(mastLength))) * getShieldCrossSection(mastLength);
    }

    public double getMastShieldSpaces() {
        return getMastShieldSpaces(mastLength);
    }

    double getMastShieldSpaces(final double mastLength) {
        return getMastShieldMass(mastLength) / 25d;
    }

    double getMastShieldPercentage() {
        return getMastShieldPercentage(mastLength);
    }

    double getMastShieldPercentage(final double mastLength) {
        return getMastShieldSpaces(mastLength) / shipCharacteristics.getShipSpaces();
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

    public double getDriveSpaces() {
        return getDriveMass() / 25d;
    }

    double getDrivePercentage() {
        return getDriveSpaces() / shipCharacteristics.getShipSpaces();
    }

    double getDriveArmorMass() {
        return shipCharacteristics.getDriveArmor() * 50 * getDriveCoverageSurface() / (1000 + 50 * shipCharacteristics.getArmorShrink());
    }

    double getDriveArmorSpaces() {
        return getDriveArmorMass() / 25d;
    }

    double getDriveArmorPercentage() {
        return getDriveArmorSpaces() / shipCharacteristics.getShipSpaces();
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
        return getOverallDriveSpaces_wArmor(mastLength) / shipCharacteristics.getShipSpaces();
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
        return getOverallDriveSpaces_noArmor(mastLength) / shipCharacteristics.getShipSpaces();
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
        return getTotalShipArmorSpaces(mastLength) / shipCharacteristics.getShipSpaces();
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
        return shipCharacteristics.getHullArmor() / (double) shipCharacteristics.getShipSpaces();
    }

    private double getDriveCoverageSurface() {
        return 4 * Math.PI * Math.pow(getDriveDiameter() / 2, 2) / 2;
    }

    double getDriveDiameter() {
        return ((Math.sqrt((shipCharacteristics.getShipMass() * shipCharacteristics.getShipAcceleration()) / 125)) * 20) / Math.sqrt(100 / (100d - getRadiantDeflection()));
    }

    private double getDriveFraction(final double mastLength) {
        return getOverallDriveMass_wArmor(mastLength) / shipCharacteristics.getShipMass();
    }

    double getDriveFraction_Typical() {
        return 0.1421d * Math.exp(-0.0005 * shipCharacteristics.getShipSpaces());
    }

    private double getFigureOfMerit() {
        return getFigureOfMerit(mastLength);
    }

    private double getFigureOfMerit(final double mastLength) {
        // Different from spreadsheet; removed constant.
        return Math.pow(getPivotAccel(mastLength), getPivotAccelPower()) / Math.pow(getOverallDriveMass_wArmor(mastLength), getDriveMassPower());
    }

    private double getHullLength() {
        return shipCharacteristics.getHullShape().getHullLength(shipCharacteristics.getShipSpaces(), getArmorFraction(), getDriveFraction_Typical());
    }

    private double getMastMassModifier() {
        return shipCharacteristics.getHullShape().getMastMassModifier();
    }

    double getMomentOfInertia(final double mastLength) {
        return shipCharacteristics.getHullShape().getMomentOfInertia(
                shipCharacteristics.getShipSpaces(),
                shipCharacteristics.getHullArmor() / (double) shipCharacteristics.getShipSpaces(),
                getDriveFraction_Typical(),
                getOverallDriveMass_wArmor(mastLength) / shipCharacteristics.getShipMass(),
                shipCharacteristics.getShipMass(),
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

    double getNewCombatPower() {
        return 0.5 * shipCharacteristics.getShipMass() * 1000 * shipCharacteristics.getShipAcceleration() * 9.765625 * shipCharacteristics.getDriveGeneration() * 34722 / 1e12;
    }

    double getPivotAccel() {
        return getPivotAccel(mastLength);
    }

    final double getPivotAccel(final double mastLength) {
        return (getPivotThrust() * 1000) * ((1 - getDriveFraction(mastLength)) * (mastLength + getHullLength() / 2) - (getDriveFraction(mastLength)) * (getDriveDiameter() / 2d)) / (getMomentOfInertia(mastLength) * 1000) * ((3 / Math.PI) * 128 * 16);
    }

    double getPivotThrust() {
        if (getThrustOverride() != 0) return getThrustOverride();
        final double scalingFactor = 14.1522458529503; // TODO: Is this actually a constant?
        // Can't find a place where it's updated in spreadsheet.
        return scalingFactor * Math.pow(shipCharacteristics.getShipMass(), 1d / 3d) * shipCharacteristics.getHullShape().getPivotModifier();
    }

    private double getRadReductionDueToMast(final double mastLength) {
        return Math.pow((mastLength + (getDriveDiameter() / 2)) / (getDriveDiameter() / 2), 2);
    }

    double getRollAccel() {
        return getRollAccel(mastLength);
    }

    private double getRollAccel(final double mastLength) {
        double rollFactor = Math.max(1 - getArmorFraction() - getDriveFraction(mastLength), 0.1);
        return (getPivotThrust() * 1000) * (Math.pow((shipCharacteristics.getShipSpaces() * 100 * rollFactor) * shipCharacteristics.getHullShape().getRollModifier(), (1 / 3d)) + 2) / (shipCharacteristics.getHullShape().getRollMoment(getArmorFraction(), getDriveFraction(mastLength), shipCharacteristics.getShipMass(), shipCharacteristics.getShipSpaces(), getDriveMass(), getDriveArmorMass(), getDriveDiameter()) * 1000) * ((3 / Math.PI) * 16 * 128 / 2);
    }

    private double getShieldCrossSection(final double mastLength) {
        return 0.25d * Math.PI * Math.pow(getShieldDiameter(mastLength), 2);
    }

    private double getShieldDiameter(final double mastLength) {
        return shipCharacteristics.getHullShape().getShieldDiameter(shipCharacteristics.getShipSpaces(), getArmorFraction(), getDriveFraction_Typical(), mastLength, getDriveDiameter());
    }

    double getShieldThickness() {
        return getShieldThickness(mastLength);
    }

    private double getShieldThickness(final double mastLength) {
        return getMastShieldMass(mastLength) / getShieldCrossSection(mastLength) * 0.5;
    }

    private double calculateMastLength() {
        if (shipCharacteristics.getShipSpaces() < 100) mastLength = 25d;
        else if (shipCharacteristics.getShipSpaces() < 400) mastLength = 50d;
        else if (shipCharacteristics.getShipSpaces() < 1000) mastLength = 75d;
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
