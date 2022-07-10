package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

public class HIRTS extends StandardSystem {
    private final static String name = "HIRTS";
    private final static int spacesPerSystem = 1;
    private final static double costPerSpace = 1d;
    private final static double crewPerSpace = 1d;
    private final static double maintenanceRate = 0.25d;

    public HIRTS(final Ship ship,
                 final int shrink,
                 final ProductionLevel productionLevel) {
        super(ship,
                1,
                shrink,
                productionLevel,
                name,
                spacesPerSystem,
                costPerSpace,
                crewPerSpace,
                maintenanceRate);
    }

    @Override
    public void setQuantity(final int quantity) {
        // HIRTS quantity is immutable.
    }
}
