package com.senorpez.avt.shipdesigner.systems.internal;

import com.senorpez.avt.shipdesigner.Ship;
import com.senorpez.avt.shipdesigner.systems.ArmoredSystem;
import com.senorpez.avt.shipdesigner.systems.ProductionLevel;

class Quarters extends ArmoredSystem {
    private final static String name = "Quarters";
    private final static int spacesPerSystem = 1;
    private final static double costPerSpace = 1d;
    private final static double crewRequiredPerSpace = 0.125d;
    private final static double maintenanceRate = 0.05d;

    Quarters(Ship ship,
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

    int getBerths() {
        return getQuantity() * 10;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Quarters setShrinkEnhancement(int quantity) {
        // Shrink is immutable.
        return this;
    }
}
