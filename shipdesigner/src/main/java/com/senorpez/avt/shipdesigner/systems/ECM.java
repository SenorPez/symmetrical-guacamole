package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

class ECM extends System {
    private final static String name = "ECM";
    private final static double crewRequiredPerSpace = 1d;
    private final static double maintenanceRate = 0.25d;

    ECM(Ship ship,
        int quantity,
        int shrinkEnhancement,
        ProductionLevel productionLevel) {
        super(ship,
                name,
                Long.valueOf(Math.round(2 + ship.getHullSize() / 70d)).intValue(),
                50 * quantity * quantity,
                crewRequiredPerSpace,
                maintenanceRate,
                quantity,
                shrinkEnhancement,
                productionLevel);
    }

    @Override
    int getBasicSpacesUsed() {
        return getSpacesPerSystem();
    }

    @Override
    double getCostPerSpace() {
        return super.getCostPerSpace() / getSpacesPerSystem();
    }
}
