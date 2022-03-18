package com.senorpez.avt.shipdesigner;

class ShipCharacteristics {
    private Ship ship;
    private int shipMass;
    private double shipAcceleration;

    ShipCharacteristics(Ship ship) {
        this.ship = ship;
        this.shipMass = ship.getHullSize() * 25;
        this.shipAcceleration = ship.getMaximumThrust() / 4d;
    }

    int getShipMass() {
        return shipMass;
    }

    double getShipAcceleration() {
        return shipAcceleration;
    }

    int getMainHullArmor() {
        return ship.getHullArmor();
    }

    int getMastArmor() {
        return ship.getMastArmor();
    }

    int getEngineArmor() {
        return ship.getEngineArmor();
    }

    int getArmorShrink() {
        //TODO: Seems to always be zero; shrink just adds more armor now.
        return ship.getArmorShrink();
    }
}
