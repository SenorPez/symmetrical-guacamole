package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

class DeltaHyperdrive extends StandardHyperdrive {
    private final static String name = "Delta Hyperdrive";
    private final static int spacesPerSystem = 8;
    private final static double costPerSpace = 5d;
    private final static double crewPerSpace = 0.5d;
    private final static double maintenanceRate = 0.2d;

    DeltaHyperdrive(final Ship ship,
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
