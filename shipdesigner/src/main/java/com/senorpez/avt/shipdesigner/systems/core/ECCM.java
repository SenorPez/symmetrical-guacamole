package com.senorpez.avt.shipdesigner.systems.core;

import com.senorpez.avt.shipdesigner.Ship;
import com.senorpez.avt.shipdesigner.systems.ProductionLevel;
import com.senorpez.avt.shipdesigner.systems.System;

class ECCM extends System {
    private final static String name = "ECCM";
    private final static int spacesPerSystem = 10;
    private final static double crewRequiredPerSpace = 1d;
    private final static double maintenanceRate = 0.25d;

    ECCM(Ship ship,
         int quantity,
         int shrinkEnhancement,
         ProductionLevel productionLevel) {
        super(ship,
                name,
                spacesPerSystem,
                50 * Math.pow(quantity, 1.75d),
                crewRequiredPerSpace,
                maintenanceRate,
                quantity,
                shrinkEnhancement,
                productionLevel);
    }

    @Override
    public int getBasicSpacesUsed() {
        return getSpacesPerSystem();
    }

    @Override
    public double getCostPerSpace() {
        return super.getCostPerSpace() / getSpacesPerSystem();
    }
}
