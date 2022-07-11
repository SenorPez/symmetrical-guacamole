package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

public class ShuttleCargo extends CargoSystem {
    private final static String name = "Shuttle Cargo";
    private final static int spacesPerSystem = 2;

    ShuttleCargo(final Ship ship,
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
