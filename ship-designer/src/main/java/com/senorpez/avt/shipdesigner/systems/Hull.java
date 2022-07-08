package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

class Hull extends StandardSystem {
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
    public int getQuantity() {
        // Quantity comes from the ship object
        return ship.getHullSpaces();
    }

    @Override
    public double getCostPerSpace() {
        return ship.getHullShape().getCostPerHullSpace();
    }

    @Override
    public void setQuantity(final int quantity) {
        // Quantity is immutable
    }
}
