package com.senorpez.avt.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.senorpez.avt.api.entities.ShipEntity;

public class ShipModel {
    @JsonProperty
    private final double hullLength;

    public ShipModel(ShipEntity entity) {
        this.hullLength = entity.getHullLength();
    }

    public double getHullLength() {
        return hullLength;
    }
}
