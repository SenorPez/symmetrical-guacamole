package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

class EpsilonHyperdrive extends StandardHyperdrive {
    private final static String name = "Epsilon Hyperdrive";
    private final static int spacesPerSystem = 12;
    private final static double costPerSpace = 7d;
    private final static double crewPerSpace = 0.5d;
    private final static double maintenanceRate = 0.2d;

    EpsilonHyperdrive(final Ship ship,
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
