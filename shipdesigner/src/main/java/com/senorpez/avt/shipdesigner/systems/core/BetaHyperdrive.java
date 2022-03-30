package com.senorpez.avt.shipdesigner.systems.core;

import com.senorpez.avt.shipdesigner.Ship;
import com.senorpez.avt.shipdesigner.systems.ProductionLevel;

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
    public String getName() {
        return hyperdriveType + " " + name;
    }
}
