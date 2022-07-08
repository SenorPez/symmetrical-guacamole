package com.senorpez.avt.api.entities;

import com.senorpez.avt.shipdesigner.Ship;

public class ShipEntity {
    private final double hullDiameter;
    private final double mastLength;
    private final double mastDiameter;
    private final double shieldMaxDiameter;
    private final double shieldMinDiameter;
    private final double shieldThickness;
    private final double lanternDiameter;

    public ShipEntity(Ship ship) {
        this.mastLength = ship.getMastLength();

        this.hullDiameter = ship.getHullDiameter();
        this.mastDiameter = ship.getMastDiameter();
        this.shieldMaxDiameter = ship.getShieldMaxDiameter();
        this.shieldMinDiameter = ship.getShieldMinDiameter();
        this.shieldThickness = ship.getShieldThickness();
        this.lanternDiameter = ship.getLanternDiameter();
    }

    public double getHullDiameter() {
        return hullDiameter;
    }

    public double getMastLength() {
        return mastLength;
    }

    public double getMastDiameter() {
        return mastDiameter;
    }

    public double getShieldMaxDiameter() {
        return shieldMaxDiameter;
    }

    public double getShieldMinDiameter() {
        return shieldMinDiameter;
    }

    public double getShieldThickness() {
        return shieldThickness;
    }

    public double getLanternDiameter() {
        return lanternDiameter;
    }
}
