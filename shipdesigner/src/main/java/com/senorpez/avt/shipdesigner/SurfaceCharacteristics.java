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
        return this.shipCharacteristics.getHullShape().getLateralHullDepth(
                shipCharacteristics.getShipSpaces(),
                massCharacteristics.getArmorFraction(),
                massCharacteristics.getDriveFraction_Typical()
        );
    }

    int getDriveHullDepth() {
        return Double.valueOf(Math.round(Math.max(Math.floor(Math.pow(massCharacteristics.getDriveSpaces(), 0.5)), 1))).intValue();
    }

    int getMastHullDepth() {
        return Double.valueOf(Math.round(Math.max(Math.pow(massCharacteristics.getMastStructureMass(), 0.25), 1))).intValue();
    }
}
