package com.senorpez.avt.shipdesigner.systems.internal;

import com.senorpez.avt.shipdesigner.Ship;
import com.senorpez.avt.shipdesigner.systems.ArmoredSystem;
import com.senorpez.avt.shipdesigner.systems.ProductionLevel;

public class Fuel extends ArmoredSystem {
    private final static String name = "Fuel";
    private final static int spacesPerSystem = 1;
    private final static double costPerSpace = 0.25d;
    private final static double crewRequiredPerSpace = 0d;
    private final static double maintenanceRate = 0d;

    Fuel(Ship ship,
         int quantity,
         ProductionLevel productionLevel,
         int armorLevel) {
        super(ship,
                name,
                spacesPerSystem,
                costPerSpace,
                crewRequiredPerSpace,
                maintenanceRate,
                quantity,
                0,
                productionLevel,
                armorLevel);
    }

    int getFuelUnits() {
        return Double.valueOf(Math.floor((200 / (double) getShip().getHullSize() * getShip().getDriveGeneration()) * getQuantity() / (1 - 0.5d * (getQuantity() * 0.9 / getShip().getHullSize())))).intValue();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Fuel setShrinkEnhancement(int quantity) {
        // Shrink is immutable.
        return this;
    }
}
