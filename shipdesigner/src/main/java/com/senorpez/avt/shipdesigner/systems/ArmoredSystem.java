package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

import java.util.ArrayList;
import java.util.List;

public abstract class ArmoredSystem extends System {
    private int armorLevel;

    final List<Double> armorUsage = new ArrayList<>();

    protected ArmoredSystem(Ship ship,
                            String name,
                            int spacesPerSystem,
                            double costPerSpace,
                            double crewRequiredPerSpace,
                            double maintenanceRate,
                            int quantity,
                            int shrinkEnhancement,
                            ProductionLevel productionLevel,
                            int armorLevel) {
        super(ship,
                name,
                spacesPerSystem,
                costPerSpace,
                crewRequiredPerSpace,
                maintenanceRate,
                quantity,
                shrinkEnhancement,
                productionLevel);
        this.armorLevel = armorLevel;

        armorUsage.addAll(List.of(0d, 0.5d, 1.0d, 2.0d, 3.5d, 5.0d, 7.0d, 9.0d));
    }

    public int getArmorLevel() {
        return armorLevel;
    }

    public double getArmorPointsUsed() {
        return getActualSpacesUsed() * armorUsage.get(getArmorLevel());
    }

    @SuppressWarnings("unchecked")
    <T extends ArmoredSystem> T setArmorLevel(int armorLevel) {
        this.armorLevel = armorLevel;
        return (T) this;
    }
}
