package com.senorpez.avt.shipdesigner;

public record DriveCharacteristics(ShipCharacteristics shipCharacteristics,
                                   MassCharacteristics massCharacteristics) {
    double getDriveOutput_Combat() {
        return massCharacteristics.getNewCombatPower();
    }

    double getDriveFlux() {
        return massCharacteristics.getNewCombatPower() / 0.7629d;
    }

    double getDriveAcceleration_Transit() {
        return shipCharacteristics.getShipAcceleration() * 20;
    }

    double getDriveOutput_Transit() {
        return ((((shipCharacteristics.getShipMass() * 1000) * ((shipCharacteristics.getShipAcceleration() * 20 / 1000) * 9.765625)) * 1000000) / 2) / Math.pow(10, 12);
    }

    double getPivotThrust() {
        return massCharacteristics.getPivotThrust();
    }

    double getPivotAcceleration() {
        return massCharacteristics.getPivotAccel();
    }

    ManeuverMode getPivotMode() {
        return ManeuverMode.getMode(massCharacteristics.getPivotAccel());
    }

    double getRollAcceleration() {
        return massCharacteristics.getRollAccel();
    }

    ManeuverMode getRollMode() {
        return ManeuverMode.getMode(massCharacteristics.getRollAccel());
    }

    double getFuelDensity() {
        return shipCharacteristics.getDriveGeneration() * 200 / shipCharacteristics.getShipSpaces();
    }

    int getEngineDamage() {
        return Double.valueOf(Math.ceil(massCharacteristics.getOverallDriveSpaces_noArmor() / ((shipCharacteristics.getShipSpaces() / 50d) * shipCharacteristics.getShipAcceleration() * (shipCharacteristics.getDriveGeneration() / 10)))).intValue();
    }

    int getMaxIntegratedReactor() {
        return Long.valueOf(Math.round(shipCharacteristics.getShipSpaces() / 50d)).intValue();
    }

    int getImprovedAccesswayRequirement() {
        return shipCharacteristics.getHullShape().getImprovedAccesswayRequirement(shipCharacteristics.getShipSpaces());
    }
}
