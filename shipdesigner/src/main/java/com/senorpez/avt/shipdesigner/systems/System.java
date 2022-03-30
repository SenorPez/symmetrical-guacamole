package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class System {
    private final Ship ship;

    private final String name;
    private final int spacesPerSystem;
    private final double costPerSpace;
    private final double crewRequiredPerSpace;
    private final double maintenanceRate;

    private int quantity;
    private int shrinkEnhancement;
    private ProductionLevel productionLevel;

    protected final List<Double> shrinkCost = new ArrayList<>();

    protected System(Ship ship,
                     String name,
                     int spacesPerSystem,
                     double costPerSpace,
                     double crewRequiredPerSpace,
                     double maintenanceRate,
                     int quantity,
                     int shrinkEnhancement,
                     ProductionLevel productionLevel) {
        this.ship = ship;

        this.name = name;
        this.spacesPerSystem = spacesPerSystem;
        this.costPerSpace = costPerSpace;
        this.crewRequiredPerSpace = crewRequiredPerSpace;
        this.maintenanceRate = maintenanceRate;

        this.quantity = quantity;
        this.shrinkEnhancement = shrinkEnhancement;
        this.productionLevel = productionLevel;

        shrinkCost.addAll(Stream.iterate(new double[]{1, 1d, 1d + 1 / 10d}, t -> new double[]{t[0] + 1, t[2], t[2] + (t[0] + 1) / 10d * 0.75})
                .limit(10)
                .map(t -> t[1])
                .collect(Collectors.toList()));
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getBasicSpacesUsed() {
        return getQuantity() * getSpacesPerSystem();
    }

    public int getShrinkEnhancement() {
        return shrinkEnhancement;
    }

    public int getSpacesPerSystem() {
        return spacesPerSystem;
    }

    public double getCostPerSpace() {
        return costPerSpace;
    }

    public double getCrewRequiredPerSpace() {
        return crewRequiredPerSpace;
    }

    public int getActualSpacesUsed() {
        if (getBasicSpacesUsed() > 0) {
            return Math.max(1, Double.valueOf(Math.floor(getBasicSpacesUsed() * (1 - getShrinkEnhancement() * 0.05))).intValue());
        } else {
            return 0;
        }
    }

    public int getBaseCost() {
        return Double.valueOf(Math.ceil(getBasicSpacesUsed() * getCostPerSpace())).intValue();
    }

    public int getEnhancedCost() {
        return Double.valueOf(Math.ceil(getBasicSpacesUsed() * getCostPerSpace() * shrinkCost.get(getShrinkEnhancement()))).intValue();
    }

    public double getCrewRequirement() {
        return getBasicSpacesUsed() * getCrewRequiredPerSpace();
    }

    public int getDuelCost() {
        return getEnhancedCost();
    }

    public ProductionLevel getProductionLevel() {
        return productionLevel;
    }

    public int getEconomicCost() {
        return Double.valueOf(Math.ceil(getDuelCost() * getProductionLevel().getEconomicCostModifier())).intValue();
    }

    public double getMaintenanceRate() {
        return maintenanceRate;
    }

    public double getMaintenanceCostPerYear() {
        return getEconomicCost() * getMaintenanceRate();
    }

    @SuppressWarnings("unchecked")
    public <T extends System> T setQuantity(int quantity) {
        this.quantity = quantity;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public <T extends System> T setShrinkEnhancement(int shrinkEnhancement) {
        this.shrinkEnhancement = shrinkEnhancement;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public <T extends System> T setProductionLevel(ProductionLevel productionLevel) {
        this.productionLevel = productionLevel;
        return (T) this;
    }

    protected Ship getShip() {
        return ship;
    }
}
