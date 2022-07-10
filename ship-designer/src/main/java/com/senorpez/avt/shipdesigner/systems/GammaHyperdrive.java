package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

public class GammaHyperdrive extends StandardHyperdrive {
    private final static String name = "Gamma Hyperdrive";
    private final static int spacesPerSystem = 4;
    private final static double costPerSpace = 4d;
    private final static double crewPerSpace = 0.5d;
    private final static double maintenanceRate = 0.2d;

    public GammaHyperdrive(final Ship ship,
                           final int quantity,
                           final int shrink,
                           final int armorLevel,
                           final ProductionLevel productionLevel) {
        super(ship,
                quantity,
                shrink,
                productionLevel,
                name,
                spacesPerSystem,
                costPerSpace,
                crewPerSpace,
                maintenanceRate,
                armorLevel);
    }
}
