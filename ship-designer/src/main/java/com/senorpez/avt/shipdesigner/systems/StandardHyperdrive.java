package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

abstract class StandardHyperdrive extends StandardArmoredSystem implements Hyperdrive {
    StandardHyperdrive(final Ship ship,
                       final int quantity,
                       final int shrink,
                       final ProductionLevel productionLevel,
                       final String name,
                       final int spacesPerSystem,
                       final double costPerSpace,
                       final double crewPerSpace,
                       final double maintenanceRate,
                       final int armorLevel) {
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
