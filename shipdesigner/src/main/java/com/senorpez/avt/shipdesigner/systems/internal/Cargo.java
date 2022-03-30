package com.senorpez.avt.shipdesigner.systems.internal;

import com.senorpez.avt.shipdesigner.Ship;
import com.senorpez.avt.shipdesigner.systems.ArmoredSystem;
import com.senorpez.avt.shipdesigner.systems.ProductionLevel;

abstract class Cargo extends ArmoredSystem {
    protected final static String name = "Cargo";
    private final static double costPerSpace = 1;
    private final static double crewRequiredPerSpace = 0.125d;
    private final static double maintenanceRate = 0.05d;

    protected ArmorGroup armorGroup;

    Cargo(Ship ship,
          int spacesPerSystem,
          int quantity,
          ProductionLevel productionLevel,
          ArmorGroup armorGroup) {
        super(ship,
                name,
                spacesPerSystem,
                costPerSpace,
                crewRequiredPerSpace,
                maintenanceRate,
                quantity,
                0,
                productionLevel,
                armorGroup.getArmorLevel()
        );
        this.armorGroup = armorGroup;
    }

    public abstract String getName();

    @Override
    public int getArmorLevel() {
        return armorGroup.getArmorLevel();
    }

    @Override
    public double getArmorPointsUsed() {
        return armorGroup.getArmorPointsUsed();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Cargo setShrinkEnhancement(int quantity) {
        // Shrink is immutable.
        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Cargo setArmorLevel(int armorLevel) {
        this.armorGroup.setArmorLevel(armorLevel);
        return this;
    }
}
