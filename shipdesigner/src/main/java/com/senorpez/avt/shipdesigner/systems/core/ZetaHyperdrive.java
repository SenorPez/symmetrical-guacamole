package com.senorpez.avt.shipdesigner.systems.core;

import com.senorpez.avt.shipdesigner.Ship;
import com.senorpez.avt.shipdesigner.systems.ProductionLevel;

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
    public String getName() {
        return hyperdriveType + " " + name;
    }
}
