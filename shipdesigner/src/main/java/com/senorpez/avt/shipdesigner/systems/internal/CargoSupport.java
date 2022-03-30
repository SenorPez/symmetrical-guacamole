package com.senorpez.avt.shipdesigner.systems.internal;

import com.senorpez.avt.shipdesigner.Ship;
import com.senorpez.avt.shipdesigner.systems.ProductionLevel;

class CargoSupport extends Cargo {
    private int endurance;

    private final String cargoType = "Support";

    private final static int spacesPerSystem = 1;

    CargoSupport(Ship ship,
                 int endurance,
                 ProductionLevel productionLevel,
                 ArmorGroup armorGroup) {
        super(ship,
                spacesPerSystem,
                2, // TODO: Calculate quantity based on endurance; placeholder for now.
                productionLevel,
                armorGroup);
        this.endurance = endurance;
    }

    @Override
    public String getName() {
        return name + " (" + cargoType + ")";
    }

    int getEndurance() {
        return endurance;
    }

    CargoSupport setEndurance(int endurance) {
        this.endurance = endurance;
        return this;
    }
}
