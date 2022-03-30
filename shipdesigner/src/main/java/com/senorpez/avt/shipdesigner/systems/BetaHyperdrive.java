package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

class BetaHyperdrive extends Hyperdrive {
    private final String hyperdriveType = "Beta";

    private final static int spacesPerSystem = 2;
    private final static double costPerSpace = 3;

    BetaHyperdrive(Ship ship,
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
