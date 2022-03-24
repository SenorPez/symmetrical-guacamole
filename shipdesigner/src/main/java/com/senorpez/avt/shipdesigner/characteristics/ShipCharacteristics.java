package com.senorpez.avt.shipdesigner.characteristics;

import com.senorpez.avt.shipdesigner.Ship;
import com.senorpez.avt.shipdesigner.enums.Shape;

public record ShipCharacteristics(Ship ship) {
    int getShipSpaces() {
        return ship.getHullSize();
    }

    int getShipMass() {
        return ship.getHullSize() * 25;
    }

    double getShipThrust() {
        return ship.getMaximumThrust();
    }

    double getShipAcceleration() {
        return ship.getMaximumThrust() / 4d;
    }

    Shape getHullShape() {
        return ship.getShape();
    }

    double getDriveGeneration() {
        return ship.getDriveGeneration();
    }

    int getHullArmor() {
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
