package com.senorpez.avt.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.senorpez.avt.api.entities.ShipEntity;

public class ShipModel {
    @JsonProperty
    private final double hullDiameter;
    @JsonProperty
    private final double mastLength;
    @JsonProperty
    private final double mastDiameter;
    @JsonProperty
    private final double shieldMaxDiameter;
    @JsonProperty
    private final double shieldMinDiameter;
    @JsonProperty
    private final double shieldThickness;
    @JsonProperty
    private final double lanternDiameter;

    public ShipModel() {
        this.hullDiameter = 14.94895d;
        this.mastLength = 28.20816d;
        this.mastDiameter = 1.88054d;
        this.shieldMaxDiameter = 2.99936d;
        this.shieldMinDiameter = 2.02292d;
        this.shieldThickness = 2.64379d;
        this.lanternDiameter = 10.95445d;
    }

    double getHullDiameter() {
        return hullDiameter;
    }

    double getMastLength() {
        return mastLength;
    }

    double getMastDiameter() {
        return mastDiameter;
    }

    double getShieldMaxDiameter() {
        return shieldMaxDiameter;
    }

    double getShieldMinDiameter() {
        return shieldMinDiameter;
    }

    double getShieldThickness() {
        return shieldThickness;
    }

    double getLanternDiameter() {
        return lanternDiameter;
    }
}
