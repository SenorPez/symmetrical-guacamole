package com.senorpez.avt.api.entities;

import com.senorpez.avt.shipdesigner.Ship;

public class ShipEntity {
    private final double hullLength;

    public ShipEntity(Ship ship) {
        this.hullLength = ship.getHullLength();
    }

    public double getHullLength() {
        return hullLength;
    }
}
