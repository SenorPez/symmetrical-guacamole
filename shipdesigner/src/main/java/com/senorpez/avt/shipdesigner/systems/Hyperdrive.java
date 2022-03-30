package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

abstract class Hyperdrive extends ArmoredSystem {
    protected final static String name = "Hyperdrive";
    private final static double maintenanceRate = 0.2d;
    private final static double crewRequiredPerSpace = 0.5d;

    Hyperdrive(Ship ship,
               int quantity,
               int shrinkEnhancement,
               int spacesPerSystem,
               double costPerSpace,
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
                productionLevel,
                armorLevel);
    }

    abstract String getName();
}
