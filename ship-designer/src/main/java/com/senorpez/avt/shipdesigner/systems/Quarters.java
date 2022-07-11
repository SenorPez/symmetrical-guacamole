package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

public class Quarters extends StandardArmoredSystem {
    private final static String name = "Quarters";
    private final static int spacesPerSystem = 1;
    private final static double costPerSpace = 1d;
    private final static double crewPerSpace = 0.125d;
    private final static double maintenanceRate = 0.05d;

    Quarters(final Ship ship,
             final int quantity,
             final int armorLevel,
             final ProductionLevel productionLevel) {
        super(ship,
                quantity,
                0,
                productionLevel,
                name,
                spacesPerSystem,
                costPerSpace,
                crewPerSpace,
                maintenanceRate,
                armorLevel);
    }

    int getCrewBerths() {
        return getQuantity() * 10;
    }

    @Override
    public void setShrink(final int shrink) {
        // Shrink is immutable.
    }
}
