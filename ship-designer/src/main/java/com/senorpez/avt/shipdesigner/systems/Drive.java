package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

import static com.senorpez.avt.shipdesigner.systems.ShrinkCost.getDriveShrinkModifier;

class Drive extends System {
    private final int extraStructure;

    private final static String name = "Drive";
    private final static int spacesPerSystem = 1;
    private final static double crewPerSpace = 0.5d;
    private final static double maintenanceRate = 0.2d;

    Drive(final Ship ship,
          final int shrink,
          final ProductionLevel productionLevel,
          final int extraStructure) {
        super(ship,
                0,
                shrink,
                productionLevel,
                name,
                spacesPerSystem,
                0,
                crewPerSpace,
                maintenanceRate);
        this.extraStructure = extraStructure;
    }

    int getExtraStructure() {
        return extraStructure;
    }

    int getDriveDamage() {
        return Double.valueOf(Math.ceil((getQuantity() + getExtraStructure()) / ((ship.getHullSpaces() / 50d) * (ship.getThrust() * 0.25) * (ship.getDriveGeneration() / 10)) * (((getDriveShrinkModifier(getShrink()) - 1) * 2) + 1))).intValue();
    }

    @Override
    int getQuantity() {
        // Quantity comes from the ship object
        final double mastLength = ship.getMastLength();
        return Double.valueOf(Math.ceil(ship.getDriveSpacesWithoutArmor(mastLength))).intValue();
    }

    @SuppressWarnings("unchecked")
    @Override
    Drive setQuantity(final int quantity) {
        // Drive quantity is immutable.
        return this;
    }

    @Override
    double getCostPerSpace() {
        return 4.5 * Math.pow(ship.getDriveGeneration(), 1.2) * getDriveShrinkModifier(getShrink());
    }

    @Override
    int getActualSpacesUsed() {
        return getQuantity() + getExtraStructure();
    }
}
