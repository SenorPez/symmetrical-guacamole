package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

class FrameReinforcement extends System {
    private final static String name = "Frame Reinforcement";
    private final static int spacesPerSystem = 1;
    private final static double costPerSpace = 0;
    private final static double crewRequiredPerSpace = 0d;
    private final static double maintenanceRate = 0.1d;

    FrameReinforcement(Ship ship,
                       int quantity,
                       ProductionLevel productionLevel) {
        super(ship,
                name,
                spacesPerSystem,
                costPerSpace,
                crewRequiredPerSpace,
                maintenanceRate,
                quantity,
                0,
                productionLevel);
    }

    int getMaximumExternalArmorSpaces() {
        return Double.valueOf(getQuantity() * Math.ceil(200d / (0.5 * Math.pow(getShip().getMaximumThrust(), 2) * Math.pow(getShip().getHullSize(), (1 / 3d))))).intValue();
    }

    @Override
    double getCostPerSpace() {
        return getShip().getShape().getHullCostPerSpace() * 2;
    }

    @Override
    int getActualSpacesUsed() {
        return getBasicSpacesUsed();
    }

    @SuppressWarnings("unchecked")
    @Override
    FrameReinforcement setShrinkEnhancement(int quantity) {
        // Shrink is immutable.
        return this;
    }
}
