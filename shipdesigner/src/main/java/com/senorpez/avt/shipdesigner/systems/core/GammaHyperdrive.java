package com.senorpez.avt.shipdesigner.systems.core;

import com.senorpez.avt.shipdesigner.Ship;
import com.senorpez.avt.shipdesigner.systems.ProductionLevel;

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
    public String getName() {
        return hyperdriveType + " " + name;
    }
}
