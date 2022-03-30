package com.senorpez.avt.shipdesigner.systems.core;

import com.senorpez.avt.shipdesigner.Ship;
import com.senorpez.avt.shipdesigner.systems.ProductionLevel;

class EpsilonHyperdrive extends Hyperdrive {
    private final String hyperdriveType = "Epsilon";

    private final static int spacesPerSystem = 12;
    private final static double costPerSpace = 7;

    EpsilonHyperdrive(Ship ship,
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
