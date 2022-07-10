package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

public class ECCM extends StandardSystem {
    private final static String name = "ECCM";
    private final static int spacesPerSystem = 10;
    private final static double crewPerSpace = 1d;
    private final static double maintenanceRate = 0.25d;

    public ECCM(final Ship ship,
                final int quantity,
                final int shrink,
                final ProductionLevel productionLevel) {
        super(ship,
                quantity,
                shrink,
                productionLevel,
                name,
                spacesPerSystem,
                50 * Math.pow(quantity, 1.75d) / (double) spacesPerSystem,
                crewPerSpace,
                maintenanceRate);
    }

    @Override
    public int getBasicSpacesUsed() {
        return getSpacesPerSystem();
    }
}
