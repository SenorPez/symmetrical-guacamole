package com.senorpez.avt.shipdesigner.systems.internal;

import com.senorpez.avt.shipdesigner.Ship;
import com.senorpez.avt.shipdesigner.systems.ArmoredSystem;
import com.senorpez.avt.shipdesigner.systems.ProductionLevel;

public class CivilianReactor extends ArmoredSystem {
    private ReactorArmor reactorArmor;

    private final static String name = "Civilian Reactor";
    private final static int spacesPerSystem = 3;
    private final static double costPerSpace = 4d;
    private final static double crewRequiredPerSpace = 0.25d;
    private final static double maintenanceRate = 0.15d;

    CivilianReactor(Ship ship,
                    int quantity,
                    int shrinkEnhancement,
                    ProductionLevel productionLevel,
                    ReactorArmor reactorArmor) {
        super(ship,
                name,
                spacesPerSystem,
                costPerSpace,
                crewRequiredPerSpace,
                maintenanceRate,
                quantity,
                shrinkEnhancement,
                productionLevel,
                reactorArmor.getArmorLevel());
        this.reactorArmor = reactorArmor;
    }

    @Override
    public int getArmorLevel() {
        return reactorArmor.getArmorLevel();
    }

    @Override
    public double getArmorPointsUsed() {
        return reactorArmor.getArmorPointsUsed();
    }
}
