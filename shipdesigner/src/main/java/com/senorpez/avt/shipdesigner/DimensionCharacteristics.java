package com.senorpez.avt.shipdesigner;

record DimensionCharacteristics(ShipCharacteristics shipCharacteristics,
                                MassCharacteristics massCharacteristics) {
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
        return massCharacteristics.getMastLength() / 15;
    }

    double getShieldDiameter() {
        return shipCharacteristics.getHullShape().getShieldDiameter(
                shipCharacteristics.getShipSpaces(),
                massCharacteristics.getArmorFraction(),
                massCharacteristics.getDriveFraction_Typical(),
                massCharacteristics.getMastLength(),
                massCharacteristics.getDriveDiameter()
        );
    }

    double getShieldThickness() {
        return massCharacteristics.getShieldThickness();
    }

    double getDriveDiameter() {
        return massCharacteristics.getDriveDiameter();
    }

    double getTotalShipLength() {
        final double hullLength = shipCharacteristics.getHullShape().getHullLength(
                shipCharacteristics.getShipSpaces(),
                massCharacteristics.getArmorFraction(),
                massCharacteristics.getDriveFraction_Typical()
        );

        return hullLength
                + massCharacteristics.getMastLength()
                + massCharacteristics.getDriveDiameter() / 2;
    }

    double getTotalBoxes() {
        return shipCharacteristics.getShipSpaces() - massCharacteristics.getTotalShipArmorSpaces();
    }

    double getStructuralIntegrity() {
        return 3.8558 * Math.pow(shipCharacteristics.getShipSpaces() / 25d, 0.6052d);
    }

    long getArmorPointsAvailable() {
        return Math.round(massCharacteristics.getHullArmorSpaces() * 5 / (1 + 0.05 * shipCharacteristics.getArmorShrink()));
    }

    double getHullDuelCost() {
        return shipCharacteristics.getShipSpaces() * shipCharacteristics.getHullShape().getHullCostModifier();
    }
}
