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
        this.hullDiameter = 14.94895d;
        this.mastLength = 28.20816d;
        this.mastDiameter = 1.88054d;
        this.shieldMaxDiameter = 2.99936d;
        this.shieldMinDiameter = 2.02292d;
        this.shieldThickness = 2.64379d;
        this.lanternDiameter = 10.95445d;
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
