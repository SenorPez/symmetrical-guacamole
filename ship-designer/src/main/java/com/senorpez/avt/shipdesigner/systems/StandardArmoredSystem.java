package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

abstract class StandardArmoredSystem extends StandardSystem implements ArmoredSystem {
    private int armorLevel;

    StandardArmoredSystem(final Ship ship,
                          final int quantity,
                          final int shrink,
                          final ProductionLevel productionLevel,
                          final String name,
                          final int spacesPerSystem,
                          final double costPerSpace,
                          final double crewPerSpace,
                          final double maintenanceRate,
                          final int armorLevel) {
        super(ship,
                quantity,
                shrink,
                productionLevel,
                name,
                spacesPerSystem,
                costPerSpace,
                crewPerSpace,
                maintenanceRate);
        this.armorLevel = armorLevel;
    }

    @Override
    public int getArmorLevel() {
        return armorLevel;
    }

    @Override
    public void setArmorLevel(final int armorLevel) {
        this.armorLevel = armorLevel;
    }
}
