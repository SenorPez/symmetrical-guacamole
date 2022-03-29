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
    private final double crewRequiredPerSpace;
    private final double maintenanceRate;

    private ProductionLevel productionLevel;
    private int shrinkEnhancement;

    final List<Double> shrinkCost = new ArrayList<>();

    protected System(Ship ship,
                  String name,
                  int spacesPerSystem,
                  double crewRequiredPerSpace,
                  double maintenanceRate,
                  ProductionLevel productionLevel,
                  int shrinkEnhancement) {
        this.ship = ship;
        this.name = name;
        this.shrinkEnhancement = shrinkEnhancement;
        this.spacesPerSystem = spacesPerSystem;
        this.crewRequiredPerSpace = crewRequiredPerSpace;
        this.productionLevel = productionLevel;
        this.maintenanceRate = maintenanceRate;

        shrinkCost.addAll(Stream.iterate(new double[]{1, 1d, 1d + 1 / 10d}, t -> new double[]{t[0] + 1, t[2], t[2] + (t[0] + 1) / 10d * 0.75})
                .limit(10)
                .map(t -> t[1])
                .collect(Collectors.toList()));
    }

    String getName() {
        return name;
    }

    abstract int getQuantity();

    abstract int getBasicSpacesUsed();

    int getShrinkEnhancement() {
        return shrinkEnhancement;
    }

    int getSpacesPerSystem() {
        return spacesPerSystem;
    }

    abstract double getCostPerSpace();

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
