package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

class BetaHyperdrive extends StandardHyperdrive {
    private final static String name = "Beta Hyperdrive";
    private final static int spacesPerSystem = 2;
    private final static double costPerSpace = 3d;
    private final static double crewPerSpace = 0.5d;
    private final static double maintenanceRate = 0.2d;

    BetaHyperdrive(final Ship ship,
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
}
