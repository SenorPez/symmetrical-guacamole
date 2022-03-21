package com.senorpez.avt.shipdesigner;

public class Dimensions {
    private final ShipCharacteristics shipCharacteristics;
    private final MassCharacteristics massCharacteristics;

    public Dimensions(ShipCharacteristics shipCharacteristics, MassCharacteristics massCharacteristics) {
        this.shipCharacteristics = shipCharacteristics;
        this.massCharacteristics = massCharacteristics;
    }

    double getHullVolume() {
        return 100 * massCharacteristics.getHullSpaces();
    }

    double getHullLength() {
        return shipCharacteristics.getHullShape().getHullLength(
                shipCharacteristics.getShipSpaces(),
                massCharacteristics.getArmorFraction(),
                massCharacteristics.getDriveFraction_Typical()
        );
    }

    double getHullDiameter() {
        return shipCharacteristics.getHullShape().getHullDiameter(
                shipCharacteristics.getShipSpaces(),
                massCharacteristics.getArmorFraction(),
                massCharacteristics.getDriveFraction_Typical()
        );
    }

    double getMastLength() {
        return massCharacteristics.getMastLength();
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
