package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

class HIRTS extends System {
    private final static String name = "HIRTS";
    private final static int spacesPerSystem = 1;
    private final static double costPerSpace = 1d;
    private final static double crewRequiredPerSpace = 1d;
    private final static double maintenanceRate = 0.25d;

    HIRTS(Ship ship,
          int shrinkEnhancement,
          ProductionLevel productionLevel) {
        super(ship,
                name,
                spacesPerSystem,
                costPerSpace,
                crewRequiredPerSpace,
                maintenanceRate,
                1,
                shrinkEnhancement,
                productionLevel);
    }

    @SuppressWarnings("unchecked")
    @Override
    HIRTS setQuantity(int quantity) {
        // Hull quantity is immutable.
        return this;
    }
}
