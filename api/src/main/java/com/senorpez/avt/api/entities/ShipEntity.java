package com.senorpez.avt.api.entities;

import com.senorpez.avt.shipdesigner.Ship;

public class ShipEntity {
    private final double hullLength;

    private final double mastDiameter;
    private final double mastLength;

    private final double shieldDiameter;
    private final double shieldWidth;

    private final double lanternDiameter;

    public ShipEntity(Ship ship) {
        this.mastLength = ship.mastLength;

        this.hullLength = ship.getHullLength();
        this.mastDiameter = ship.mastLength / 15;
        this.shieldDiameter = ship.getShieldDiameter(ship.mastLength);
        this.shieldWidth = ship.getShieldWidth(ship.mastLength);
        this.lanternDiameter = ship.getLanternDiameter();
    }

    public double getHullLength() {
        return hullLength;
    }

    public double getMastDiameter() {
        return mastDiameter;
    }

    public double getMastLength() {
        return mastLength;
    }

    public double getShieldDiameter() {
        return shieldDiameter;
    }

    public double getShieldWidth() {
        return shieldWidth;
    }

    public double getLanternDiameter() {
        return lanternDiameter;
    }
}
