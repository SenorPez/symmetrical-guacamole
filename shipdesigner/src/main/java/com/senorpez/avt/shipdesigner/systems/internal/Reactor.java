package com.senorpez.avt.shipdesigner.systems.internal;

import com.senorpez.avt.shipdesigner.Ship;
import com.senorpez.avt.shipdesigner.systems.ArmoredSystem;
import com.senorpez.avt.shipdesigner.systems.ProductionLevel;

public class Reactor extends ArmoredSystem {
    private int powerGeneration;
    private ReactorArmor reactorarmor;

    Reactor(Ship ship,
            ReactorType reactorType,
            int powerGeneration,
            int quantity,
            int shrinkEnhancement,
            ProductionLevel productionLevel,
            ReactorArmor reactorarmor) {
        super(ship,
                reactorType.getName(),
                reactorType.getSpacesPerSystem(powerGeneration),
                reactorType.getCostPerSpace(powerGeneration),
                reactorType.getBaseReactorCrew(),
                reactorType.getMaintenanceCost(),
                quantity,
                shrinkEnhancement,
                productionLevel,
                reactorarmor.getArmorLevel()
        );
        this.powerGeneration = powerGeneration;
        this.reactorarmor = reactorarmor;
    }

    int getPowerGeneration() {
        return powerGeneration;
    }

    int getTotalPowerOutput() {
        return powerGeneration * getQuantity();
    }

    @Override
    public int getArmorLevel() {
        return reactorarmor.getArmorLevel();
    }

    @Override
    public double getArmorPointsUsed() {
        return reactorarmor.getArmorPointsUsed();
    }

    Reactor setPowerGeneration(int powerGeneration) {
        this.powerGeneration = powerGeneration;
        return this;
    }

    Reactor setArmorGroup(ReactorArmor reactorarmor) {
        this.reactorarmor = reactorarmor;
        return this;
    }
}
