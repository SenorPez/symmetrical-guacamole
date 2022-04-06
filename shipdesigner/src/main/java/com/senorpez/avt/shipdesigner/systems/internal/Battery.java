package com.senorpez.avt.shipdesigner.systems.internal;

import com.senorpez.avt.shipdesigner.Ship;
import com.senorpez.avt.shipdesigner.systems.ArmoredSystem;
import com.senorpez.avt.shipdesigner.systems.ProductionLevel;

import java.util.ArrayList;
import java.util.List;

class Battery extends ArmoredSystem {
    private final static String name = "Battery";
    private final static int spacesPerSystem = 1;
    private final static double crewRequiredPerSpace = 0.5d;
    private final static double maintenanceRate = 0.2d;

    private final static List<Double> batteryCostPerSpace = new ArrayList<>(List.of(0.5d, 3d, 8d, 15d, 24d, 35d, 48d, 63d, 80d));

    Battery(Ship ship,
            int generation,
            int quantity,
            int shrinkEnhancement,
            ProductionLevel productionLevel,
            int armorLevel) {
        super(ship,
                name,
                spacesPerSystem,
                batteryCostPerSpace.get(generation - 1),
                crewRequiredPerSpace,
                maintenanceRate,
                quantity,
                shrinkEnhancement,
                productionLevel,
                armorLevel);
    }
}
