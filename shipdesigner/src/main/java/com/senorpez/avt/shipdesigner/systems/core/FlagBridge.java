package com.senorpez.avt.shipdesigner.systems.core;

import com.senorpez.avt.shipdesigner.Ship;
import com.senorpez.avt.shipdesigner.systems.ArmoredSystem;
import com.senorpez.avt.shipdesigner.systems.ProductionLevel;

import java.util.ArrayList;
import java.util.List;

class FlagBridge extends ArmoredSystem {
    private final static String name = "Flag Bridge";
    private final static int spacesPerSystem = 1;
    private final static double costPerSpace = 3d;
    private final static double crewRequiredPerSpace = 1d;
    private final static double maintenanceRate = 0.25d;

    FlagBridge(Ship ship,
           int quantity,
           int shrinkEnhancement,
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

    List<Integer> getFlagPointsAt() {
        final int hullSpaces = getShip().getHullSize();
        if (hullSpaces < 100) return null;

        List<Integer> val = new ArrayList<>();
        val.add(4);

        if (hullSpaces >= 150) val.add(6);
        if (hullSpaces >= 200) val.add(8);
        if (hullSpaces >= 250) val.add(10);
        if (hullSpaces >= 300) val.add(12);
        if (hullSpaces >= 350) val.add(14);
        if (hullSpaces >= 400) val.add(16);
        if (hullSpaces >= 450) val.add(18);

        return val;
    }

    int getFlagPoints() {
        if (getQuantity() >= 4) {
            return Double.valueOf(Math.floor(1 + (getQuantity() - 4) / 2d)).intValue();
        } else {
            return 0;
        }
    }
}
