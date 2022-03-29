package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;
import com.senorpez.avt.shipdesigner.characteristics.MassCharacteristics;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Drive {
    private int extraStructure;
    private int shrinkEnhancement;
    private ProductionLevel productionLevel;

    private Ship ship;
    private MassCharacteristics massCharacteristics;

    private final String name = "Drive";
    private final int spacesPerSystem = 1;
    private final double crewRequiredPerSpace = 0.5d;
    private final double maintenanceRate = 0.2d;

    private final List<Double> driveModifier = new ArrayList<>();
    private final List<Double> shrinkCost = new ArrayList<>();

    Drive(int extraStructure, int shrinkEnhancement, ProductionLevel productionLevel, Ship ship, MassCharacteristics massCharacteristics) {
        this.extraStructure = extraStructure;
        this.shrinkEnhancement = shrinkEnhancement;
        this.productionLevel = productionLevel;

        this.ship = ship;
        this.massCharacteristics = massCharacteristics;

        driveModifier.addAll(
                Stream.of(1.0d, 1.1d, 1.3d, 1.6d, 2.0d, 2.5d, 3.1d, 3.8d, 4.6d, 5.5d, 6.5d).collect(Collectors.toList()));
        shrinkCost.addAll(Stream.iterate(new double[]{1, 1d, 1d + 1 / 10d}, t -> new double[]{t[0] + 1, t[2], t[2] + (t[0] + 1) / 10d * 0.75})
                .limit(10)
                .map(t -> t[1])
                .collect(Collectors.toList()));
    }

    String getName() {
        return name;
    }

    int getQuantity() {
        return Long.valueOf(Math.round(massCharacteristics.getDriveSpaces())).intValue();
    }

    int getExtraStructure() {
        return extraStructure;
    }

    int getEngineDamage() {
        return Double.valueOf(Math.ceil(getTotalEngineSpaces() / ((ship.getHullSize() / 50d) * (ship.getMaximumThrust() * 0.25) * (ship.getDriveGeneration() / 10)) * ((driveModifier.get(getShrinkEnhancement()) - 1) * 2 + 1))).intValue();
    }

    int getBasicSpacesUsed() {
        return getTotalEngineSpaces() * getSpacesPerSystem();
    }

    int getShrinkEnhancement() {
        return shrinkEnhancement;
    }

    int getSpacesPerSystem() {
        return spacesPerSystem;
    }

    double getCostPerSpace() {
        return 4.5d * Math.pow(ship.getDriveGeneration(), 1.2) * driveModifier.get(getShrinkEnhancement());
    }

    double getCrewRequiredPerSpace() {
        return crewRequiredPerSpace;
    }

    double getActualSpacesUsed() {
        return getTotalEngineSpaces();
    }

    int getBaseCost() {
        return Double.valueOf(Math.ceil(getBasicSpacesUsed() * getCostPerSpace())).intValue();
    }

    int getEnhancedCost() {
        return Double.valueOf(Math.ceil(getBasicSpacesUsed() * getCostPerSpace() * shrinkCost.get(getShrinkEnhancement()))).intValue();
    }

    double getCrewRequirements() {
        return getTotalEngineSpaces() * getCrewRequiredPerSpace();
    }

    double getArmorLevel() {
        // TODO: Seems to always be 0 in spreadsheet.
        return 0;
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

    Drive setExtraStructure(int extraStructure) {
        this.extraStructure = extraStructure;
        return this;
    }

    Drive setShrinkEnhancement(int shrinkEnhancement) {
        this.shrinkEnhancement = shrinkEnhancement;
        return this;
    }

    Drive setProductionLevel(ProductionLevel productionLevel) {
        this.productionLevel = productionLevel;
        return this;
    }

    Drive setShip(Ship ship) {
        this.ship = ship;
        return this;
    }

    Drive setMassCharacteristics(MassCharacteristics massCharacteristics) {
        this.massCharacteristics = massCharacteristics;
        return this;
    }

    private int getTotalEngineSpaces() {
        return getQuantity() + getExtraStructure();
    }
}
