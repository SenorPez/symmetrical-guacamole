package com.senorpez.avt.shipdesigner.systems.internal;

import com.senorpez.avt.shipdesigner.Ship;
import com.senorpez.avt.shipdesigner.systems.ArmoredSystem;
import com.senorpez.avt.shipdesigner.systems.ProductionLevel;

public abstract class Cargo extends ArmoredSystem {
    protected final static String name = "Cargo";
    private final static double costPerSpace = 1;
    private final static double crewRequiredPerSpace = 0.125d;
    private final static double maintenanceRate = 0.05d;

    protected CargoArmor cargoArmor;

    Cargo(Ship ship,
          int spacesPerSystem,
          int quantity,
          ProductionLevel productionLevel,
          CargoArmor cargoArmor) {
        super(ship,
                name,
                spacesPerSystem,
                costPerSpace,
                crewRequiredPerSpace,
                maintenanceRate,
                quantity,
                0,
                productionLevel,
                cargoArmor.getArmorLevel()
        );
        this.cargoArmor = cargoArmor;
    }

    public abstract String getName();

    @Override
    public int getArmorLevel() {
        return cargoArmor.getArmorLevel();
    }

    @Override
    public double getArmorPointsUsed() {
        return cargoArmor.getArmorPointsUsed();
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
        this.cargoArmor.setArmorLevel(armorLevel);
        return this;
    }
}
