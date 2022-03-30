package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

class DeltaHyperdrive extends Hyperdrive {
    private final String hyperdriveType = "Delta";

    private final static int spacesPerSystem = 8;
    private final static double costPerSpace = 5;

    DeltaHyperdrive(Ship ship,
                    int quantity,
                    int shrinkEnhancement,
                    ProductionLevel productionLevel,
                    int armorLevel) {
        super(ship,
                quantity,
                shrinkEnhancement,
                spacesPerSystem,
                costPerSpace,
                productionLevel,
                armorLevel);
    }

    @Override
    String getName() {
        return hyperdriveType + " " + name;
    }
}
