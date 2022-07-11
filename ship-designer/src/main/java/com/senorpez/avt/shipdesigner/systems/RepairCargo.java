package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

public class RepairCargo extends CargoSystem {
    private final static String name = "Repair Cargo";
    private final static int spacesPerSystem = 1;

    RepairCargo(final Ship ship,
                final int quantity,
                final CargoArmorLevel cargoArmorLevel,
                final ProductionLevel productionLevel) {
        super(ship,
                quantity,
                productionLevel,
                name,
                spacesPerSystem,
                cargoArmorLevel);
    }
}
