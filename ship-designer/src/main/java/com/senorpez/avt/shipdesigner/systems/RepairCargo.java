package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

public class RepairCargo extends SharedArmoredSystem implements Cargo {
    private final static String name = "Repair Cargo";
    private final static int spacesPerSystem = 1;
    private final static double costPerSpace = 1d;
    private final static double crewPerSpace = 0.125d;
    private final static double maintenanceRate = 0.05d;

    RepairCargo(final Ship ship,
                final int quantity,
                final CargoArmorLevel cargoArmorLevel,
                final ProductionLevel productionLevel) {
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
    public void setShrink(final int shrink) {
        // Shrink is immutable.
    }
}
