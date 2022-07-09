package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

abstract class StandardSystem implements System {
    protected final Ship ship;

    private int quantity;
    private int shrink;
    private ProductionLevel productionLevel;

    private final String name;
    private final int spacesPerSystem;
    private final double costPerSpace;
    private final double crewPerSpace;
    private final double maintenanceRate;

    StandardSystem(final Ship ship,
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

    @Override
    final public Ship getShip() {
        return ship;
    }

    @Override
    final public String getName() {
        return name;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public int getShrink() {
        return shrink;
    }

    @Override
    public int getSpacesPerSystem() {
        return spacesPerSystem;
    }

    @Override
    public double getCostPerSpace() {
        return costPerSpace;
    }

    @Override
    final public double getCrewPerSpace() {
        return crewPerSpace;
    }

    @Override
    public ProductionLevel getProductionLevel() {
        return productionLevel;
    }

    @Override
    final public double getMaintenanceRate() {
        return maintenanceRate;
    }

    @Override
    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }

    @Override
    public void setShrink(final int shrink) {
        this.shrink = shrink;
    }

    @Override
    public void setProductionLevel(final ProductionLevel productionLevel) {
        this.productionLevel = productionLevel;
    }
}
