package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

class ZetaHyperdrive extends Hyperdrive {
    private final String hyperdriveType = "Zeta";

    private final static int spacesPerSystem = 20;
    private final static double costPerSpace = 10;

    ZetaHyperdrive(Ship ship,
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
