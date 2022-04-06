package com.senorpez.avt.shipdesigner.systems.internal;

import com.senorpez.avt.shipdesigner.systems.System;

import java.util.Optional;

public class HeatSinkArmor extends ArmorGroup {
    private HeatSinkWater heatSinkWater = null;
    private HeatSinkSodium heatSinkSodium = null;
    private HeatSinkLithium heatSinkLithium = null;

    HeatSinkArmor(int armorLevel) {
        super(armorLevel);
    }

    @Override
    public double getArmorPointsUsed() {
        final int totalActualSpacesUsed = getHeatSinkWater().map(System::getActualSpacesUsed).orElse(0)
                + getHeatSinkSodium().map(System::getActualSpacesUsed).orElse(0)
                + getHeatSinkLithium().map(System::getActualSpacesUsed).orElse(0);
        return totalActualSpacesUsed * armorUsage.get(getArmorLevel());
    }

    public HeatSinkArmor setHeatSinkWater(HeatSinkWater heatSinkWater) {
        this.heatSinkWater = heatSinkWater;
        return this;
    }

    public HeatSinkArmor setHeatSinkSodium(HeatSinkSodium heatSinkSodium) {
        this.heatSinkSodium = heatSinkSodium;
        return this;
    }

    public HeatSinkArmor setHeatSinkLithium(HeatSinkLithium heatSinkLithium) {
        this.heatSinkLithium = heatSinkLithium;
        return this;
    }

    private Optional<HeatSinkWater> getHeatSinkWater() {
        return Optional.ofNullable(heatSinkWater);
    }

    private Optional<HeatSinkSodium> getHeatSinkSodium() {
        return Optional.ofNullable(heatSinkSodium);
    }

    private Optional<HeatSinkLithium> getHeatSinkLithium() {
        return Optional.ofNullable(heatSinkLithium);
    }
}
