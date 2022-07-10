package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

class ECM extends StandardSystem {
    private final static String name = "ECM";
    private final static double crewPerSpace = 1d;
    private final static double maintenanceRate = 0.25d;

    ECM(final Ship ship,
        final int quantity,
        final int shrink,
        final ProductionLevel productionLevel) {
        super(ship,
                quantity,
                shrink,
                productionLevel,
                name,
                Long.valueOf(Math.round(2 + ship.getHullSpaces() / 70d)).intValue(),
                50 * quantity * quantity / Long.valueOf(Math.round(2 + ship.getHullSpaces() / 70d)).doubleValue(),
                crewPerSpace,
                maintenanceRate);
    }

    @Override
    public int getBasicSpacesUsed() {
        return getSpacesPerSystem();
    }
}
