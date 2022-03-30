package com.senorpez.avt.shipdesigner.systems.core;

import com.senorpez.avt.shipdesigner.Ship;
import com.senorpez.avt.shipdesigner.systems.ArmoredSystem;
import com.senorpez.avt.shipdesigner.systems.ProductionLevel;

class LifeSupport extends ArmoredSystem {
    private LifeSupportClass lifeSupportClass;

    private final static String name = "Life Support";
    private final static int spacesPerSystem = 1;
    private final static double costPerSpace = 1d;
    private final static double crewRequiredPerSpace = 0.5d;
    private final static double maintenanceRate = 0.2d;

    LifeSupport(Ship ship,
                int quantity,
                int shrinkEnhancement,
                ProductionLevel productionLevel,
                int armorLevel,
                LifeSupportClass lifeSupportClass) {
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
        this.lifeSupportClass = lifeSupportClass;
    }

    @Override
    public String getName() {
        return getLifeSupportClass().getClassName() + " " + super.getName();
    }

    int getLifeSupportCrewCapacity() {
        double val;
        if (getLifeSupportClass() == LifeSupportClass.EXTENDED) {
            if (getQuantity() > 300) {
                val = 375 + (getQuantity() - 300) * 2;
            } else if (getQuantity() > 75) {
                val = 100 + (getQuantity() -75) * 5 / 3d;
            } else {
                val = getQuantity() * 4 / 3d;
            }
        } else if (getLifeSupportClass() == LifeSupportClass.GUNBOAT) {
            val = getQuantity() * 10;
        } else {
            val = getQuantity() * 200;
        }
        return Double.valueOf(Math.floor(val)).intValue();
    }

    LifeSupportClass getLifeSupportClass() {
        return lifeSupportClass;
    }

    LifeSupport setLifeSupportClass(LifeSupportClass lifeSupportClass) {
        this.lifeSupportClass = lifeSupportClass;
        return this;
    }

    private int getMinimumLifeSupport() {
        // TODO: Read from completed ship systems object.
        return 1;
    }
}
