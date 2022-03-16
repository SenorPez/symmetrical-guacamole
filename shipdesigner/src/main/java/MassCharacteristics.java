class MassCharacteristics {
    private Ship ship;
    private ShipCharacteristics characteristics;

    private double mastLength;

    MassCharacteristics(Ship ship, ShipCharacteristics characteristics) {
        this.ship = ship;
        this.characteristics = characteristics;
    }

    double getDriveMass() {
        return getMastMass() + 198.733859;
    }

    double getHullMass() {
        return getHullSpaces_Double() * 25;
    }

    double getHullArmorMass() {
        return getHullArmorSpaces() * 25d;
    }

    double getMastMass() {
        return getMastStructuralMass() + getMastArmorMass() + getMastShieldMass();
    }

    double getMastArmorMass() {
        return ((1d / 15d) * Math.PI * Math.pow(getMastLength(), 2)) * characteristics.getMastArmor() * 50 / (1000 + 50 * characteristics.getArmorShrink());
    }

    double getMastShieldMass() {
        return (getTenXRadReduction() * (Math.log10(getNeutronFlux_MR_yr()) + 6) - Math.log10(getRadReductionDueToMast())) * getShieldCrossSection();
    }

    double getMastStructuralMass() {
        return (((characteristics.getShipMass() * characteristics.getShipAcceleration()) / 70000) * 7.8) * getMastLength() * getMastMassModifier();
    }

    double getMastMassModifier() {
        return ship.getShape().getMastMassModifier();
    }

    double getNewCombatPower() {
        // TODO: Placeholder
        return 1.98734d;
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

    double getDriveSpaces() {
        return getDriveMass() / 25d;
    }

    int getHullArmorSpaces() {
        return ship.getHullArmor();
    }

    int getHullSpaces() {
        return Long.valueOf(Math.round(getHullSpaces_Double())).intValue();
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
        return 1000 * Math.pow(getPivotAccel(), getPivotAccelPower()) / (Math.pow(getDriveMass(), getDriveMassPower()));
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
        return getDriveMass() / characteristics.getShipMass();
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

    private double getHullSpaces_Double() {
        return ship.getHullSize() - getHullArmorSpaces() - getDriveSpaces();
    }

}
