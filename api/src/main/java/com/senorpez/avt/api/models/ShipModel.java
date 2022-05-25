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

    public ShipModel(ShipEntity entity) {
        this.hullDiameter = entity.getHullDiameter();
        this.mastLength = entity.getMastLength();
        this.mastDiameter = entity.getMastDiameter();
        this.shieldMaxDiameter = entity.getShieldMaxDiameter();
        this.shieldMinDiameter = entity.getShieldMinDiameter();
        this.shieldThickness = entity.getShieldThickness();
        this.lanternDiameter = entity.getLanternDiameter();
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
