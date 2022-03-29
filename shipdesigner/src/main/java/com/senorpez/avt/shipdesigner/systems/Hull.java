package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

class Hull extends System {
    private final static String name = "Hull";
    private final static int quantity = 0;
    private final static int spacesPerSystem = 0;
    private final static int crewRequiredPerSpace = 0;
    private final static double maintenanceRate = 0.15d;

    private final int basicSpacesUsed = 0;

    Hull(final Ship ship,
         final ProductionLevel productionLevel,
         final int shrinkEnhancement) {
        super(ship, name, spacesPerSystem, crewRequiredPerSpace, maintenanceRate, productionLevel, shrinkEnhancement);
    }

    int getQuantity() {
        return quantity;
    }

    int getBasicSpacesUsed() {
        return basicSpacesUsed;
    }

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
}
