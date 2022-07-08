package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

class Accessways extends StandardSystem {
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

    void setImprovedAccessways(final boolean improvedAccessways) {
        this.improvedAccessways = improvedAccessways;
    }

    @Override
    public int getQuantity() {
        return hasImprovedAccessways() ? 1 : 0;
    }

    @Override
    public int getSpacesPerSystem() {
        return getShip().getHullShape().getImprovedAccesswaysRequirement(getShip().getHullSpaces());
    }

    @Override
    public void setQuantity(final int quantity) {
        // Quantity is immutable
    }

    @Override
    public void setShrink(final int shrink) {
        // Shrink is immutable
    }

    @Override
    public void setProductionLevel(final ProductionLevel productionLevel) {
        // Production Level is immutable
    }
}
