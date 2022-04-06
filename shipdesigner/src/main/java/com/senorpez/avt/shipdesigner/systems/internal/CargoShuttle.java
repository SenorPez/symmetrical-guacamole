package com.senorpez.avt.shipdesigner.systems.internal;

import com.senorpez.avt.shipdesigner.Ship;
import com.senorpez.avt.shipdesigner.systems.ProductionLevel;

public class CargoShuttle extends Cargo {
    private final String cargoType = "Shuttle";
    private final static int spacesPerSystem = 2;

    CargoShuttle(Ship ship,
                 int quantity,
                 ProductionLevel productionLevel,
                 CargoArmor cargoArmor) {
        super(ship,
                spacesPerSystem,
                quantity,
                productionLevel,
                cargoArmor);
    }

    @Override
    public String getName() {
        return name + " (" + cargoType + ")";
    }
}
