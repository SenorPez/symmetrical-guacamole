package com.senorpez.avt.shipdesigner;

class MassCharacteristics {
    private Ship ship;
    private ShipCharacteristics characteristics;

    private double mastLength;

    MassCharacteristics(Ship ship, ShipCharacteristics characteristics) {
        this.ship = ship;
        this.characteristics = characteristics;
    }

    // Table Values
    double getHullMass() {
        return getHullSpaces() * 25;
    }

    double getHullSpaces() {
        return ship.getHullSize() - getHullArmorSpaces() - getOverallDriveSpaces_wArmor();
    }

    double getHullPercentage() {
        return getHullSpaces() / ship.getHullSize();
    }

    int getHullArmorMass() {
        return getHullArmorSpaces() * 25;
    }

    int getHullArmorSpaces() {
        return ship.getHullArmor();
    }

    double getHullArmorPercentage() {
        return getHullArmorSpaces() / (double) ship.getHullSize();
    }

    double getTotalHullMass() {
        return getHullMass() + getHullArmorMass();
    }

    double getTotalHullSpaces() {
        return getHullSpaces() + getHullArmorSpaces();
    }

    double getTotalHullPercentage() {
        return getTotalHullSpaces() / ship.getHullSize();
    }

    double getMastStructureMass() {
        return (characteristics.getShipMass() * characteristics.getShipAcceleration() / 70000) * 7.8 * getMastLength() * getMastMassModifier();
    }

    double getMastStructureSpaces() {
        return getMastStructureMass() / 25d;
    }

    double getMastStructurePercentage() {
        return getMastStructureSpaces() / ship.getHullSize();
    }

    double getMastArmorMass() {
        return ((1d / 15d) * Math.PI * Math.pow(getMastLength(), 2)) * characteristics.getMastArmor() * 50 / (1000 + 50 * characteristics.getArmorShrink());
    }

    double getMastArmorSpaces() {
        return getMastArmorMass() / 25d;
    }

    double getMastArmorPercentage() {
        return getMastArmorSpaces() / ship.getHullSize();
    }

    double getMastShieldMass() {
        return (getTenXRadReduction() * (Math.log10(getNeutronFlux_MR_yr()) + 6) - Math.log10(getRadReductionDueToMast())) * getShieldCrossSection();
    }

    double getMastShieldSpaces() {
        return getMastShieldMass() / 25d;
    }

    double getMastShieldPercentage() {
        return getMastShieldSpaces() / ship.getHullSize();
    }

    double getDriveMass() {
        // TODO: Placeholder.
        return 0;
    }

    double getDriveSpaces() {
        return getDriveMass() / 25d;
    }

    double getDrivePercentage() {
        return getDriveSpaces() / ship.getHullSize();
    }

    double getDriveArmorMass() {
        // TODO: Placeholder.
        return 0;
    }

    double getDriveArmorSpaces() {
        return getDriveArmorMass() / 25d;
    }

    double getDriveArmorPercentage() {
        return getDriveArmorSpaces() / ship.getHullSize();
    }

    double getOverallDriveMass_wArmor() {
        // TODO: Placeholder values.
        return getMastMass() + 198.733859;
    }

    double getOverallDriveSpaces_wArmor() {
        return getOverallDriveMass_wArmor() / 25d;
    }

    double getOverallDrivePercentage_wArmor() {
        return getOverallDriveSpaces_wArmor() / ship.getHullSize();
    }

    double getOverallDriveMass_noArmor() {
        return getLanternMass() + getMastStructureMass() + getMastShieldMass();
    }

    double getOverallDriveSpaces_noArmor() {
        return getOverallDriveMass_noArmor() / 25d;
    }

    double getOverallDrivePercentage_noArmor() {
        return getOverallDriveSpaces_noArmor() / ship.getHullSize();
    }

    double getTotalShipArmorMass() {
        return getHullArmorMass() + getOverallDriveMass_wArmor() - getOverallDriveMass_noArmor();
    }

    double getTotalShipArmorSpaces() {
        return getHullArmorSpaces() + getOverallDriveSpaces_wArmor() - getOverallDriveSpaces_noArmor();
    }

    double getTotalShipArmorPercentage() {
        return getTotalShipArmorSpaces() / ship.getHullSize();
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







    double getMastMass() {
        return getMastStructureMass() + getMastArmorMass() + getMastShieldMass();
    }




    double getMastMassModifier() {
        return ship.getShape().getMastMassModifier();
    }

    double getNewCombatPower() {
        return 0.5 * characteristics.getShipMass() * 1000 * characteristics.getShipAcceleration() * 9.765625 * ship.getEngineGeneration() * 34722 / 1e12;
    }

    double getNeutronFlux_KR_hr() {
        return getNewCombatPower() * 500000 / Math.pow(getLanternDiameter() / 2, 2);
    }

    double getNeutronFlux_MR_yr() {
        return getNeutronFlux_KR_hr() * 24 * 365.25 / 1000;
    }

    double getTenXRadReduction() {
        // TODO: Placeholder
        return 0.63d;
    }

    double getRadReductionDueToMast() {
        // TODO: Placeholder
        return 17.006457d;
    }

    double getShieldCrossSection() {
        // TODO: Placeholder
        return 7.225657d;
    }

    double getLanternMass() {
        // TODO: Placeholder
        return 199;
    }




    double getMastLength() {
        // TODO: Placeholder
        if (ship.getHullSize() < 100) mastLength = 25;
        else if (ship.getHullSize() < 400) mastLength = 50;
        else if (ship.getHullSize() < 1000) mastLength = 75;
        else mastLength = 100;

        return 34.36277d;
    }

    double getFigureOfMerit() {
        return 1000 * Math.pow(getPivotAccel(), getPivotAccelPower()) / (Math.pow(getOverallDriveMass_wArmor(), getDriveMassPower()));
    }

    double getPivotAccel() {
        return (getPivotThrust() * 1000) * ((1 - getActualDriveFraction()) * (getMastLength() + getMainHullLength() / 2) - (getActualDriveFraction()) * (getLanternDiameter() / 2d)) / (getMomentOfInertia() * 1000) * ((3 / Math.PI) * 128 * 16);
    }

    double getPivotThrust() {
        if (getThrustOverride() != 0) return getThrustOverride();
        final double scalingFactor = 14.1522458529503; // TODO: Is this actually a constant?
                                                       // Can't find a place where it's updated in spreadsheet.
        return scalingFactor * Math.pow(characteristics.getShipMass(), 1d / 3d) * ship.getShape().getPivotModifier();
    }

    double getActualDriveFraction() {
        return getOverallDriveMass_wArmor() / characteristics.getShipMass();
    }

    double getMainHullLength() {
        return ship.getShape().getHullLength();
    }

    double getLanternDiameter() {
        return ((Math.sqrt((characteristics.getShipMass() * characteristics.getShipAcceleration()) / 125)) * 20) / Math.sqrt(100 / (100d - getRadiantDeflection()));
    }

    double getMomentOfInertia() {
        // TODO: Placeholder
        return 9.29197e5;
    }

    int getPivotAccelPower() {
        return 1; // TODO: Allow as input?
    }

    int getDriveMassPower() {
        return 1; // TODO: Allow as input?
    }

    int getRadiantDeflection() {
        return 96; // TODO: Allow as input?
    }

    double getThrustOverride() {
        return 0d; // TODO: Allow as input?
    }
}
