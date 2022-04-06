package com.senorpez.avt.shipdesigner.systems.internal;

import com.senorpez.avt.shipdesigner.Ship;
import com.senorpez.avt.shipdesigner.systems.ProductionLevel;

class HeatSinkWater extends HeatSink {
    private final String heatSinkType = "Water";
    private final static int spacesPerSystem = 5;
    private final static double costPerSpace = 0.25d;
    private final static double crewRequiredPerSpace = 0.25d;
    private final static double maintenanceRate = 0.15d;

    HeatSinkWater(Ship ship,
                  int quantity,
                  int shrinkEnhancement,
                  ProductionLevel productionLevel,
                  HeatSinkArmor heatSinkArmor) {
        super(ship,
                spacesPerSystem,
                costPerSpace,
                crewRequiredPerSpace,
                maintenanceRate,
                quantity,
                shrinkEnhancement,
                productionLevel,
                heatSinkArmor);
    }

    @Override
    public String getName() {
        return heatSinkType + " " + name;
    }
}
