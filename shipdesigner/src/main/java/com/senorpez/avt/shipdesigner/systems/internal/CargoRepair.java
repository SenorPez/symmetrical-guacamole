package com.senorpez.avt.shipdesigner.systems.internal;

import com.senorpez.avt.shipdesigner.Ship;
import com.senorpez.avt.shipdesigner.systems.ProductionLevel;

class CargoRepair extends Cargo {
    private final String cargoType = "Repair";
    private final static int spacesPerSystem = 1;

    CargoRepair(Ship ship,
                int quantity,
                ProductionLevel productionLevel,
                ArmorGroup armorGroup) {
        super(ship,
                spacesPerSystem,
                quantity,
                productionLevel,
                armorGroup);
    }

    @Override
    public String getName() {
        return name + " (" + cargoType + ")";
    }
}
