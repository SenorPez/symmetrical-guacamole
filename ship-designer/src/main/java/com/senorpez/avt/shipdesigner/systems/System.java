package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

abstract class System {
    protected final Ship ship;

    private int quantity;
    private int shrink;
    private ProductionLevel productionLevel;

    private final String name;
    private final int spacesPerSystem;
    private final double costPerSpace;
    private final double crewPerSpace;
    private final double maintenanceRate;

    protected System(final Ship ship,
                     final int quantity,
                     final int shrink,
                     final ProductionLevel productionLevel,
                     final String name,
                     final int spacesPerSystem,
                     final double costPerSpace,
                     final double crewPerSpace,
                     final double maintenanceRate) {
        this.ship = ship;

        this.quantity = quantity;
        this.shrink = shrink;
        this.productionLevel = productionLevel;

        this.name = name;
        this.spacesPerSystem = spacesPerSystem;
        this.costPerSpace = costPerSpace;
        this.crewPerSpace = crewPerSpace;
        this.maintenanceRate = maintenanceRate;
    }

    Ship getShip() {
        return ship;
    }

    String getName() {
        return name;
    }

    int getQuantity() {
        return quantity;
    }

    int getBasicSpacesUsed() {
        return getQuantity() * getSpacesPerSystem();
    }

    int getShrink() {
        return shrink;
    }

    int getSpacesPerSystem() {
        return spacesPerSystem;
    }

    double getCostPerSpace() {
        return costPerSpace;
    }

    double getCrewPerSpace() {
        return crewPerSpace;
    }

    int getActualSpacesUsed() {
        if (getBasicSpacesUsed() > 0) {
            return Math.max(1, Double.valueOf(Math.floor(getBasicSpacesUsed() * (1 - getShrink() * 0.05))).intValue());
        } else {
            return 0;
        }
    }

    int getBaseCost() {
        return Double.valueOf(Math.ceil(getBasicSpacesUsed() * getCostPerSpace())).intValue();
    }

    int getEnhancedCost() {
        double val = getBasicSpacesUsed() * getCostPerSpace() * ShrinkCost.getSystemShrinkModifier(getShrink());
        return Double.valueOf(Math.ceil(Math.round(val * 100000d) / 100000d)).intValue();
    }

    double getCrewRequirement() {
        return getBasicSpacesUsed() * getCrewPerSpace();
    }

    int getDuelCost() {
        return getEnhancedCost();
    }

    ProductionLevel getProductionLevel() {
        return productionLevel;
    }

    int getEconomicCost() {
        return Double.valueOf(Math.ceil(getDuelCost() * getProductionLevel().getCostModifier())).intValue();
    }

    double getMaintenanceRate() {
        return maintenanceRate;
    }

    double getMaintenanceCost() {
        return getEconomicCost() * getMaintenanceRate();
    }

    @SuppressWarnings("unchecked")
    <T extends System> T setQuantity(final int quantity) {
        this.quantity = quantity;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    <T extends System> T setShrink(final int shrink) {
        this.shrink = shrink;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    <T extends System> T setProductionLevel(final ProductionLevel productionLevel) {
        this.productionLevel = productionLevel;
        return (T) this;
    }
}
