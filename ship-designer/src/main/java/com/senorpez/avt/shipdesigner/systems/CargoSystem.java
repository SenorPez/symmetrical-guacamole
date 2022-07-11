package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

abstract class CargoSystem extends SharedArmoredSystem implements Cargo {
    private final static double costPerSpace = 1d;
    private final static double crewPerSpace = 0.125d;
    private final static double maintenanceRate = 0.05d;

    CargoSystem(final Ship ship,
                final int quantity,
                final ProductionLevel productionLevel,
                final String name,
                final int spacesPerSystem,
                final CargoArmorLevel cargoArmorLevel) {
        super(ship,
                quantity,
                0,
                productionLevel,
                name,
                spacesPerSystem,
                costPerSpace,
                crewPerSpace,
                maintenanceRate,
                cargoArmorLevel);
    }

    @Override
    public final void setShrink(final int shrink) {
        // Shrink is immutable
    }
}
