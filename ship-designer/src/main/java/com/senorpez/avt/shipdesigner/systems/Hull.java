package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

class Hull extends System {
    private final static String name = "Hull";
    private final static int spacesPerSystem = 1;
    private final static double crewPerSpace = 0;
    private final static double maintenanceRate = 0.15d;

    Hull(Ship ship,
         int shrink,
         ProductionLevel productionLevel) {
        super(ship,
                0,
                shrink,
                productionLevel,
                name,
                spacesPerSystem,
                0,
                crewPerSpace,
                maintenanceRate);
    }

    @Override
    int getQuantity() {
        // Quantity comes from the ship object
        return ship.getHullSpaces();
    }

    @SuppressWarnings("unchecked")
    @Override
    Hull setQuantity(final int quantity) {
        // Hull quantity is immutable.
        return this;
    }

    @Override
    double getCostPerSpace() {
        return ship.getHullShape().getCostPerHullSpace();
    }
}
