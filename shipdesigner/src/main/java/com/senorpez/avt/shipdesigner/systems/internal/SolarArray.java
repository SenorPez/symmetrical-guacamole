package com.senorpez.avt.shipdesigner.systems.internal;

import com.senorpez.avt.shipdesigner.Ship;
import com.senorpez.avt.shipdesigner.systems.ArmoredSystem;
import com.senorpez.avt.shipdesigner.systems.ProductionLevel;

public class SolarArray extends ArmoredSystem {
    private final ReactorArmor reactorarmor;

    private final static String name = "Solar Array";
    private final static int spacesPerSystem = 5;
    private final static double costPerSpace = 1d;
    private final static double crewRequiredPerSpace = 0.25d;
    private final static double maintenanceRate = 0.15d;

    SolarArray(Ship ship,
               int quantity,
               int shrinkEnhancement,
               ProductionLevel productionLevel,
               ReactorArmor reactorarmor) {
        super(ship,
                name,
                spacesPerSystem,
                costPerSpace,
                crewRequiredPerSpace,
                maintenanceRate,
                quantity,
                shrinkEnhancement,
                productionLevel,
                reactorarmor.getArmorLevel());
        this.reactorarmor = reactorarmor;
    }

    @Override
    public int getArmorLevel() {
        return reactorarmor.getArmorLevel();
    }

    @Override
    public double getArmorPointsUsed() {
        return reactorarmor.getArmorPointsUsed();
    }
}
