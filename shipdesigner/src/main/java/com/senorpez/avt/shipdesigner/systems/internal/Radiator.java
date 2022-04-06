package com.senorpez.avt.shipdesigner.systems.internal;

import com.senorpez.avt.shipdesigner.Ship;
import com.senorpez.avt.shipdesigner.systems.ArmoredSystem;
import com.senorpez.avt.shipdesigner.systems.ProductionLevel;
import com.senorpez.avt.shipdesigner.systems.System;

public class Radiator extends System {
    private final static String name = "Radiator";
    private final static int spacesPerSystem = 1;
    private final static double costPerSpace = 1d;
    private final static double crewRequiredPerSpace = 0.5d;
    private final static double maintenanceRate = 0.2d;

    Radiator(Ship ship,
             int quantity,
             int shrinkEnhancement,
             ProductionLevel productionLevel) {
        super(ship,
                name,
                spacesPerSystem,
                costPerSpace,
                crewRequiredPerSpace,
                maintenanceRate,
                quantity,
                shrinkEnhancement,
                productionLevel);
    }

    double getRadiatorDissipation() {
        return getQuantity() * 0.4d;
    }
}
