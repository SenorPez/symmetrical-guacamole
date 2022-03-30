package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

abstract class System {
    private final Ship ship;

    private final String name;
    private final int spacesPerSystem;
    private final double costPerSpace;
    private final double crewRequiredPerSpace;
    private final double maintenanceRate;

    private int quantity;
    private int shrinkEnhancement;
    private ProductionLevel productionLevel;

    final List<Double> shrinkCost = new ArrayList<>();

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

    String getName() {
        return name;
    }

    int getQuantity() {
        return quantity;
    }

    int getBasicSpacesUsed() {
        return getQuantity() * getSpacesPerSystem();
    }

    int getShrinkEnhancement() {
        return shrinkEnhancement;
    }

    int getSpacesPerSystem() {
        return spacesPerSystem;
    }

    double getCostPerSpace() {
        return costPerSpace;
    }

    double getCrewRequiredPerSpace() {
        return crewRequiredPerSpace;
    }

    int getActualSpacesUsed() {
        return getBasicSpacesUsed();
    }

    int getBaseCost() {
        return Double.valueOf(Math.ceil(getBasicSpacesUsed() * getCostPerSpace())).intValue();
    }

    int getEnhancedCost() {
        return Double.valueOf(Math.ceil(getBasicSpacesUsed() * getCostPerSpace() * shrinkCost.get(getShrinkEnhancement()))).intValue();
    }

    double getCrewRequirement() {
        return getBasicSpacesUsed() * getCrewRequiredPerSpace();
    }

    int getDuelCost() {
        return getEnhancedCost();
    }

    ProductionLevel getProductionLevel() {
        return productionLevel;
    }

    int getEconomicCost() {
        return Double.valueOf(Math.ceil(getDuelCost() * getProductionLevel().getEconomicCostModifier())).intValue();
    }

    double getMaintenanceRate() {
        return maintenanceRate;
    }

    double getMaintenanceCostPerYear() {
        return getEconomicCost() * getMaintenanceRate();
    }

    @SuppressWarnings("unchecked")
    <T extends System> T setQuantity(int quantity) {
        this.quantity = quantity;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    <T extends System> T setShrinkEnhancement(int shrinkEnhancement) {
        this.shrinkEnhancement = shrinkEnhancement;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    <T extends System> T setProductionLevel(ProductionLevel productionLevel) {
        this.productionLevel = productionLevel;
        return (T) this;
    }

    Ship getShip() {
        return ship;
    }
}
