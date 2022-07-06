package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

class FrameReinforcement extends System {
    private final static String name = "Frame Reinforcement";
    private final static int spacesPerSystem = 1;
    private final static double crewPerSpace = 0d;
    private final static double maintenanceRate = 0.1d;

    FrameReinforcement(final Ship ship,
                       final int quantity,
                       final ProductionLevel productionLevel) {
        super(ship,
                quantity,
                0,
                productionLevel,
                name,
                spacesPerSystem,
                0,
                crewPerSpace,
                maintenanceRate);
    }

    int getMaximumExternalArmorSpaces() {
        final double val = 200d / (0.5d * Math.pow(ship.getThrust(), 2) * Math.pow(ship.getHullSpaces(), 1 / 3d));
        return getQuantity() * Double.valueOf(Math.ceil(val)).intValue();
    }

    @SuppressWarnings("unchecked")
    @Override
    FrameReinforcement setShrink(final int shrink) {
        // Shrink is immutable at 0.
        return this;
    }

    @Override
    double getCostPerSpace() {
        return 2 * ship.getHullShape().getCostPerHullSpace();
    }
}
