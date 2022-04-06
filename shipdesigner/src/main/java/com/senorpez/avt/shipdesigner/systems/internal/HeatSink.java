package com.senorpez.avt.shipdesigner.systems.internal;

import com.senorpez.avt.shipdesigner.Ship;
import com.senorpez.avt.shipdesigner.systems.ArmoredSystem;
import com.senorpez.avt.shipdesigner.systems.ProductionLevel;

abstract class HeatSink extends ArmoredSystem {
    protected final static String name = "Heat Sink";

    protected HeatSinkArmor heatSinkArmor;

    HeatSink(Ship ship,
             int spacesPerSystem,
             double costPerSpace,
             double crewRequiredPerSpace,
             double maintenanceRate,
             int quantity,
             int shrinkEnhancement,
             ProductionLevel productionLevel,
             HeatSinkArmor heatSinkArmor) {
        super(ship,
                name,
                spacesPerSystem,
                costPerSpace,
                crewRequiredPerSpace,
                maintenanceRate,
                quantity,
                shrinkEnhancement,
                productionLevel,
                heatSinkArmor.getArmorLevel()
        );
        this.heatSinkArmor = heatSinkArmor;
    }

    public abstract String getName();

    @Override
    public int getArmorLevel() {
        return heatSinkArmor.getArmorLevel();
    }

    @Override
    public double getArmorPointsUsed() {
        return heatSinkArmor.getArmorPointsUsed();
    }

    @SuppressWarnings("unchecked")
    @Override
    public HeatSink setArmorLevel(int armorLevel) {
        this.heatSinkArmor.setArmorLevel(armorLevel);
        return this;
    }

}
