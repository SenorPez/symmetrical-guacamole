package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Hull {
    private Ship ship;
    private int shrinkEnhancement;
    private ProductionLevel productionLevel;

    private final String name = "Hull";
    private final int spacesPerSystem = 0;
    private final int basicSpacesUsed = 0;
    private final int actualSpacesUsed = 0;
    private final int costPerSpace = 1;
    private final int crewRequiredPerSpace = 0;
    private final double maintenanceRate = 0.15d;

    private final List<Double> shrinkCost = new ArrayList<>();

    public Hull(Ship ship, int shrinkEnhancement, ProductionLevel productionLevel) {
        this.ship = ship;
        this.shrinkEnhancement = shrinkEnhancement;
        this.productionLevel = productionLevel;

        shrinkCost.addAll(Stream.iterate(new double[]{1, 1d, 1d + 1 / 10d}, t -> new double[]{t[0] + 1, t[2], t[2] + (t[0] + 1) / 10d * 0.75})
                .limit(10)
                .map(t -> t[1])
                .collect(Collectors.toList()));
    }

    public Ship getShip() {
        return ship;
    }

    public Hull setShip(Ship ship) {
        this.ship = ship;
        return this;
    }

    public int getShrinkEnhancement() {
        return shrinkEnhancement;
    }

    public Hull setShrinkEnhancement(int shrinkEnhancement) {
        this.shrinkEnhancement = shrinkEnhancement;
        return this;
    }

    public ProductionLevel getProductionLevel() {
        return productionLevel;
    }

    public Hull setProductionLevel(ProductionLevel productionLevel) {
        this.productionLevel = productionLevel;
        return this;
    }

    public String getName() {
        return name;
    }

    public int getSpacesPerSystem() {
        return spacesPerSystem;
    }

    public int getBasicSpacesUsed() {
        return basicSpacesUsed;
    }

    public int getActualSpacesUsed() {
        return actualSpacesUsed;
    }

    public int getCostPerSpace() {
        return costPerSpace;
    }

    public int getCrewRequiredPerSpace() {
        return crewRequiredPerSpace;
    }

    public double getMaintenanceRate() {
        return maintenanceRate;
    }

    final int getBaseCost() {
        return Double.valueOf(Math.ceil(costPerSpace * ship.getHullSize())).intValue();
    }

    final int getEnhancedCost() {
        return Double.valueOf(Math.ceil(getBaseCost() * shrinkCost.get(shrinkEnhancement))).intValue();
    }

    final int getCrewRequired() {
        return basicSpacesUsed * crewRequiredPerSpace;
    }

    final int getDuelCost() {
        return getEnhancedCost();
    }

    final int getEconomicCost() {
        return Double.valueOf(Math.ceil(getDuelCost() * getProductionLevel().getEconomicCostModifier())).intValue();
    }

    final double getMaintenanceCostPerYear() {
        return getEconomicCost() * getMaintenanceRate();
    }
}
