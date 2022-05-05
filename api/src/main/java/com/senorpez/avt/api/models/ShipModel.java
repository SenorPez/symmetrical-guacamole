package com.senorpez.avt.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.senorpez.avt.api.entities.ShipEntity;

public class ShipModel {
    @JsonProperty
    private final double hullLength;
    @JsonProperty
    private final double mastDiameter;
    @JsonProperty
    private final double mastLength;
    @JsonProperty
    private final double shieldDiameter;
    @JsonProperty
    private final double shieldWidth;
    @JsonProperty
    private final double lanternDiameter;


    public ShipModel(ShipEntity entity) {
        this.hullLength = entity.getHullLength();
        this.mastDiameter = entity.getMastDiameter();
        this.mastLength = entity.getMastLength();
        this.shieldDiameter = entity.getShieldDiameter();
        this.shieldWidth = entity.getShieldWidth();
        this.lanternDiameter = entity.getLanternDiameter();
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
