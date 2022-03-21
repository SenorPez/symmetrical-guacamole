package com.senorpez.avt.shipdesigner;

public class Dimensions {
    private final MassCharacteristics massCharacteristics;

    public Dimensions(MassCharacteristics massCharacteristics) {
        this.massCharacteristics = massCharacteristics;
    }

    double getHullVolume() {
        return 100 * massCharacteristics.getHullSpaces();
    }

    double getHullLength() {
        // TODO: Placeholder
        return 0;
    }

    double getHullDiameter() {
        // TODO: Placeholder
        return 0;
    }

    double getMastLength() {
        // TODO: Placeholder
        return 0;
    }

    double getMastDiameter() {
        // TODO: Placeholder
        return 0;
    }

    double getShieldDiameter() {
        // TODO: Placeholder
        return 0;
    }

    double getShieldThickness() {
        // TODO: Placeholder
        return 0;
    }

    double getDriveDiameter() {
        // TODO: Placeholder
        return 0;
    }

    double getTotalShipLength() {
        // TODO: Placeholder
        return 0;
    }

    double getTotalBoxes() {
        // TODO: Placeholder
        return 0;
    }

    double getStructuralIntegrity() {
        // TODO: Placeholder
        return 0;
    }

    int getArmorPointsAvailable() {
        // TODO: Placeholder
        return 0;
    }

    double getHullDuelCost() {
        // TODO: Placeholder
        return 0;
    }
}
