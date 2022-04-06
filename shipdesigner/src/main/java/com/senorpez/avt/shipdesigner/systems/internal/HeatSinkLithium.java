package com.senorpez.avt.shipdesigner.systems.internal;

import com.senorpez.avt.shipdesigner.Ship;
import com.senorpez.avt.shipdesigner.systems.ProductionLevel;

class HeatSinkLithium extends HeatSink {
    private final String heatSinkType = "Lithium";
    private final static int spacesPerSystem = 1;
    private final static double costPerSpace = 60d;
    private final static double crewRequiredPerSpace = 0.5d;
    private final static double maintenanceRate = 0.2d;

    HeatSinkLithium(Ship ship,
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
