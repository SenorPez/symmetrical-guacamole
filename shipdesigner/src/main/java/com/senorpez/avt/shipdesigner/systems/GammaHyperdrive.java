package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

class GammaHyperdrive extends Hyperdrive {
    private final String hyperdriveType = "Gamma";

    private final static int spacesPerSystem = 4;
    private final static double costPerSpace = 4;

    GammaHyperdrive(Ship ship,
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
