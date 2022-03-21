package com.senorpez.avt.shipdesigner;

public class DriveCharacteristics {
    private final ShipCharacteristics shipCharacteristics;
    private final MassCharacteristics massCharacteristics;

    public DriveCharacteristics(ShipCharacteristics shipCharacteristics, MassCharacteristics massCharacteristics) {
        this.shipCharacteristics = shipCharacteristics;
        this.massCharacteristics = massCharacteristics;
    }

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
        // TODO: Placeholder
        return 0;
    }

    double getPivotThrust() {
        // TODO: Placeholder
        return 0;
    }

    double getPivotAcceleration() {
        // TODO: Placeholder
        return 0;
    }

    char getPivotMode() {
        // TODO: Placeholder
        return 0;
    }

    double getRollAcceleration() {
        // TODO: Placeholder
        return 0;
    }

    char getRollMode() {
        // TODO: Placeholder
        return 0;
    }

    double getFuelDensity() {
        // TODO: Placeholder
        return 0;
    }

    int getEngineDamage() {
        // TODO: Placeholder
        return 0;
    }

    int getMaxIntegratedReactor() {
        // TODO: Placeholder
        return 0;
    }

    int getImprovedAccesswayRequirement() {
        // TODO: Placeholder
        return 0;
    }
}
