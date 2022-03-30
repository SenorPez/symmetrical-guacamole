package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;
import com.senorpez.avt.shipdesigner.characteristics.MassCharacteristics;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Drive extends System {
    private int extraStructure;
    private MassCharacteristics massCharacteristics;

    private final static String name = "Drive";
    private final static int spacesPerSystem = 1;
    private final static double costPerSpace = 0;
    private final static double crewRequiredPerSpace = 0.5d;
    private final static double maintenanceRate = 0.2d;

    private final List<Double> driveModifier = new ArrayList<>();

    Drive(final Ship ship,
          final MassCharacteristics massCharacteristics,
          final int extraStructure,
          final int shrinkEnhancement,
          final ProductionLevel productionLevel) {
        super(ship,
                name,
                spacesPerSystem,
                costPerSpace,
                crewRequiredPerSpace,
                maintenanceRate,
                Long.valueOf(Math.round(massCharacteristics.getDriveSpaces())).intValue(),
                shrinkEnhancement,
                productionLevel);
        this.extraStructure = extraStructure;
        this.massCharacteristics = massCharacteristics;

        driveModifier.addAll(
                Stream.of(1.0d, 1.1d, 1.3d, 1.6d, 2.0d, 2.5d, 3.1d, 3.8d, 4.6d, 5.5d, 6.5d).collect(Collectors.toList()));
        shrinkCost.addAll(Stream.iterate(new double[]{1, 1d, 1d + 1 / 10d}, t -> new double[]{t[0] + 1, t[2], t[2] + (t[0] + 1) / 10d * 0.75})
                .limit(10)
                .map(t -> t[1])
                .collect(Collectors.toList()));
    }

    int getQuantity() {
        return Long.valueOf(Math.round(massCharacteristics.getDriveSpaces())).intValue();
    }

    int getExtraStructure() {
        return extraStructure;
    }

    int getDriveDamage() {
        return Double.valueOf(Math.ceil(getTotalDriveSpaces() / ((getShip().getHullSize() / 50d) * (getShip().getMaximumThrust() * 0.25) * (getShip().getDriveGeneration() / 10)) * ((driveModifier.get(getShrinkEnhancement()) - 1) * 2 + 1))).intValue();
    }

    int getBasicSpacesUsed() {
        return getTotalDriveSpaces() * getSpacesPerSystem();
    }

    double getCostPerSpace() {
        return 4.5d * Math.pow(getShip().getDriveGeneration(), 1.2) * driveModifier.get(getShrinkEnhancement());
    }

    @Override
    int getActualSpacesUsed() {
        return getBasicSpacesUsed();
    }

    double getArmorLevel() {
        // TODO: Seems to always be 0 in spreadsheet.
        return 0;
    }

    Drive setExtraStructure(int extraStructure) {
        this.extraStructure = extraStructure;
        return this;
    }

    Drive setMassCharacteristics(MassCharacteristics massCharacteristics) {
        this.massCharacteristics = massCharacteristics;
        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    Drive setQuantity(int quantity) {
        // Hull quantity is not directly changeable.
        return this;
    }

    private int getTotalDriveSpaces() {
        return getQuantity() + getExtraStructure();
    }
}
