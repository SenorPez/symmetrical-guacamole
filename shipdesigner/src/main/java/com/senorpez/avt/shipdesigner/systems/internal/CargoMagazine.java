package com.senorpez.avt.shipdesigner.systems.internal;

import com.senorpez.avt.shipdesigner.Ship;
import com.senorpez.avt.shipdesigner.systems.ProductionLevel;

class CargoMagazine extends Cargo {
    private final String cargoType = "Magazine";
    private final static int spacesPerSystem = 1;

    CargoMagazine(Ship ship,
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
