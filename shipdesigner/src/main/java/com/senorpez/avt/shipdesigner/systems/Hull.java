package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

class Hull extends System {
    private final static String name = "Hull";
    private final static int spacesPerSystem = 0;
    private final static double costPerSpace = 0;
    private final static int crewRequiredPerSpace = 0;
    private final static double maintenanceRate = 0.15d;

    Hull(Ship ship,
         int shrinkEnhancement,
         ProductionLevel productionLevel) {
        super(ship,
                name,
                spacesPerSystem,
                costPerSpace,
                crewRequiredPerSpace,
                maintenanceRate,
                0,
                shrinkEnhancement,
                productionLevel);
    }

    @Override
    double getCostPerSpace() {
        return getShip().getShape().getHullCostPerSpace();
    }

    @Override
    int getBaseCost() {
        return Double.valueOf(Math.ceil(getShip().getHullSize() * getCostPerSpace())).intValue();
    }

    @Override
    int getEnhancedCost() {
        return Double.valueOf(Math.ceil(getShip().getHullSize() * getCostPerSpace() * shrinkCost.get(getShrinkEnhancement()))).intValue();
    }

    @SuppressWarnings("unchecked")
    @Override
    Hull setQuantity(int quantity) {
        // Hull quantity is immutable.
        return this;
    }
}
