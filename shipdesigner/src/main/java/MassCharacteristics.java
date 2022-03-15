class MassCharacteristics {
    private Ship ship;
    private ShipCharacteristics characteristics;

    MassCharacteristics(Ship ship, ShipCharacteristics characteristics) {
        this.ship = ship;
        this.characteristics = characteristics;
    }

    double getDriveMass() {
        return Math.floor(getMastMass() + 199);
    }

    double getHullMass() {
        return getHullSpaces_Double() * 25d;
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

    double getMastLength() {
        // TODO: Placeholder
        return 34.36277d;
    }

    double getMastMassModifier() {
        return ship.getShape().getMastMassModifier();
    }

    double getNeutronFlux_MR_yr() {
        // TODO: Placeholder
        return 71987.6451d;
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

    private double getHullSpaces_Double() {
        return ship.getHullSize() - getHullArmorSpaces() - getDriveSpaces();
    }
}
