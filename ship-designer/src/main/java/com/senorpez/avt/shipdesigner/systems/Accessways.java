package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

class Accessways extends System {
    private boolean improvedAccessways;

    private final static String name = "Accessways";
    private final static double costPerSpace = 0d;
    private final static double crewPerSpace = 0d;
    private final static double maintenanceRate = 0d;

    Accessways(final Ship ship,
               final boolean improvedAccessways) {
        super(ship,
                0,
                0,
                ProductionLevel.STANDARD,
                name,
                0,
                costPerSpace,
                crewPerSpace,
                maintenanceRate);
        this.improvedAccessways = improvedAccessways;
    }

    boolean hasImprovedAccessways() {
        return improvedAccessways;
    }

    Accessways setImprovedAccessways(final boolean improvedAccessways) {
        this.improvedAccessways = improvedAccessways;
        return this;
    }

    @Override
    int getQuantity() {
        return hasImprovedAccessways() ? 1 : 0;
    }

    @Override
    int getSpacesPerSystem() {
        return getShip().getHullShape().getImprovedAccesswaysRequirement(getShip().getHullSpaces());
    }

    @SuppressWarnings("unchecked")
    @Override
    Accessways setQuantity(final int quantity) {
        // Quantity is immutable.
        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    Accessways setShrink(final int shrink) {
        // Shrink is immutable.
        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    Accessways setProductionLevel(final ProductionLevel productionLevel) {
        // Production level is immutable.
        return this;
    }
}
