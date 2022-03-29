package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Hull {
    private int shrinkEnhancement;
    private ProductionLevel productionLevel;

    private Ship ship;

    private final String name = "Hull";
    private final int quantity = 0;
    private final int spacesPerSystem = 0;
    private final int basicSpacesUsed = 0;
    private final int actualSpacesUsed = 0;
    private final int costPerSpace = 1;
    private final int crewRequiredPerSpace = 0;
    private final double maintenanceRate = 0.15d;

    private final List<Double> shrinkCost = new ArrayList<>();

    Hull(int shrinkEnhancement, ProductionLevel productionLevel, Ship ship) {
        this.ship = ship;
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
        return basicSpacesUsed;
    }

    int getShrinkEnhancement() {
        return shrinkEnhancement;
    }

    int getSpacesPerSystem() {
        return spacesPerSystem;
    }

    int getCostPerSpace() {
        return costPerSpace;
    }

    int getCrewRequiredPerSpace() {
        return crewRequiredPerSpace;
    }

    int getActualSpacesUsed() {
        return actualSpacesUsed;
    }

    int getBaseCost() {
        return Double.valueOf(Math.ceil(costPerSpace * ship.getHullSize())).intValue();
    }

    int getEnhancedCost() {
        return Double.valueOf(Math.ceil(getBaseCost() * shrinkCost.get(shrinkEnhancement))).intValue();
    }

    int getCrewRequirement() {
        return basicSpacesUsed * crewRequiredPerSpace;
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

    Hull setShrinkEnhancement(int shrinkEnhancement) {
        this.shrinkEnhancement = shrinkEnhancement;
        return this;
    }

    Hull setProductionLevel(ProductionLevel productionLevel) {
        this.productionLevel = productionLevel;
        return this;
    }
}
