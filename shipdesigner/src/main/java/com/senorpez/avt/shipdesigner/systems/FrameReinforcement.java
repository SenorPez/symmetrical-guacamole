package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

class FrameReinforcement extends System {
    private int quantity;

    private final static String name = "Frame Reinforcement";
    private final static int spacesPerSystem = 1;
    private final static double crewRequiredPerSpace = 0d;

    FrameReinforcement(Ship ship,
                       int quantity,
                       double maintenanceRate,
                       ProductionLevel productionLevel) {
        super(ship, name, spacesPerSystem, crewRequiredPerSpace, maintenanceRate, productionLevel, 0);
        this.quantity = quantity;
    }

    @Override
    int getQuantity() {
        return quantity;
    }

    int getMaximumExternalArmorSpaces() {
        return Double.valueOf(getQuantity() * Math.ceil(200d / (0.5 * Math.pow(getShip().getMaximumThrust(), 2) * Math.pow(getShip().getHullSize(), (1 / 3d))))).intValue();
    }

    @Override
    int getBasicSpacesUsed() {
        return getQuantity() * getSpacesPerSystem();
    }

    @Override
    double getCostPerSpace() {
        return getShip().getShape().getHullCostPerSpace() * 2;
    }

    FrameReinforcement setQuantity(final int quantity) {
        this.quantity = quantity;
        return this;
    }
}
