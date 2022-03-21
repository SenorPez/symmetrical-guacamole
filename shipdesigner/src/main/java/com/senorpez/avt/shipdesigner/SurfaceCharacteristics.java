package com.senorpez.avt.shipdesigner;

public class SurfaceCharacteristics {
    final ShipCharacteristics shipCharacteristics;
    final MassCharacteristics massCharacteristics;

    public SurfaceCharacteristics(ShipCharacteristics shipCharacteristics, MassCharacteristics massCharacteristics) {
        this.shipCharacteristics = shipCharacteristics;
        this.massCharacteristics = massCharacteristics;
    }

    double getHullSurfaceArea() {
        return this.shipCharacteristics.getHullShape().getHullSurfaceArea(
            shipCharacteristics.getShipSpaces(),
            massCharacteristics.getArmorFraction(),
            massCharacteristics.getDriveFraction_Typical()
        );
    }

    double getFrontArmorArea() {
        // TODO: Placeholder
        return 0;
    }

    double getLateralArmorArea() {
        // TODO: Placeholder
        return 0;
    }

    double getRearArmorArea() {
        // TODO: Placeholder
        return 0;
    }

    int getAxialHullDepth() {
        // TODO: Placeholder
        return 0;
    }

    int getLateralHullDepth() {
        // TODO: Placeholder
        return 0;
    }

    int getDriveHullDepth() {
        // TODO: Placeholder
        return 0;
    }

    int getMastHullDepth() {
        // TODO: Placeholder
        return 0;
    }
}
