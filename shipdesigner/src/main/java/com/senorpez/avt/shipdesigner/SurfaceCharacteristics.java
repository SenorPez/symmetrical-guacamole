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
        return this.shipCharacteristics.getHullShape().getFrontArmorArea(
                shipCharacteristics.getShipSpaces(),
                massCharacteristics.getArmorFraction(),
                massCharacteristics.getDriveFraction_Typical()
        );
    }

    double getLateralArmorArea() {
        return this.shipCharacteristics.getHullShape().getLateralArmorArea(
                shipCharacteristics.getShipSpaces(),
                massCharacteristics.getArmorFraction(),
                massCharacteristics.getDriveFraction_Typical()
        );
    }

    double getRearArmorArea() {
        return this.shipCharacteristics.getHullShape().getRearArmorArea(
                shipCharacteristics.getShipSpaces(),
                massCharacteristics.getArmorFraction(),
                massCharacteristics.getDriveFraction_Typical()
        );
    }

    int getAxialHullDepth() {
        return this.shipCharacteristics.getHullShape().getAxialHullDepth(
                shipCharacteristics.getShipSpaces(),
                massCharacteristics.getArmorFraction(),
                massCharacteristics.getDriveFraction_Typical()
        );
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
