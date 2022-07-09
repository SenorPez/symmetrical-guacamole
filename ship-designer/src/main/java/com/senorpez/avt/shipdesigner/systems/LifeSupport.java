package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

class LifeSupport extends StandardArmoredSystem {
    private final static String name = "Life Support";
    private final static int spacesPerSystem = 1;
    private final static double costPerSpace = 1d;
    private final static double crewPerSpace = 0.5d;
    private final static double maintenanceRate = 0.2d;

    // TODO: Implement non-military life support

    public LifeSupport(final Ship ship,
                       final int quantity,
                       final int shrink,
                       final int armorLevel,
                       final ProductionLevel productionLevel) {
        super(ship,
                quantity,
                shrink,
                productionLevel,
                name,
                spacesPerSystem,
                costPerSpace,
                crewPerSpace,
                maintenanceRate,
                armorLevel);
    }

    int getCrewSupport() {
        return getQuantity() * 200;
    }
}
