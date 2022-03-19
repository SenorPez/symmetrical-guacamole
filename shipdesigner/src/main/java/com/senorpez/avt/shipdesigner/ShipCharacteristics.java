package com.senorpez.avt.shipdesigner;

class ShipCharacteristics {
    private Ship ship;

    ShipCharacteristics(Ship ship) {
        this.ship = ship;
    }

    int getShipSpaces() {
        return ship.getHullSize();
    }

    int getShipMass() {
        return getShipSpaces() * 25;
    }

    double getShipThrust() {
        return ship.getMaximumThrust();
    }

    double getShipAcceleration() {
        return getShipThrust() / 4d;
    }

    double getDriveGeneration() {
        return ship.getDriveGeneration();
    }

    int getMainHullArmor() {
        return ship.getHullArmor();
    }

    int getMastArmor() {
        return ship.getMastArmor();
    }

    int getDriveArmor() {
        return ship.getDriveArmor();
    }

    int getArmorShrink() {
        // TODO: Seems to always be zero; shrink just adds more armor now.
        return ship.getArmorShrink();
    }
}
