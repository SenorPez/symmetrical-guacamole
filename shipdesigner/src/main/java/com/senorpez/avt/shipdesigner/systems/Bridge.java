package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

import java.util.ArrayList;
import java.util.List;

public class Bridge extends ArmoredSystem {
    private final static String name = "Bridge";
    private final static int spacesPerSystem = 1;
    private final static double costPerSpace = 3d;
    private final static double crewRequiredPerSpace = 1d;
    private final static double maintenanceRate = 0.25d;

    Bridge(Ship ship,
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

    List<Integer> getFlexPointsAt() {
        List<Integer> val = new ArrayList<>();
        final int minCrew = getShip().getMinimumCrew();

        if (minCrew / 25d < 1) {
            val.addAll(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        } else if (getShip().getHullSize() >= 100) {
            val.add(Double.valueOf(Math.ceil(minCrew / 25d * 0.5)).intValue());
            val.add(Double.valueOf(Math.ceil(minCrew / 25d * 1.5)).intValue());
            val.add(Double.valueOf(Math.ceil(minCrew / 25d * 2.5)).intValue());
            val.add(Double.valueOf(Math.ceil(minCrew / 25d * 3.5)).intValue());
            val.add(Double.valueOf(Math.ceil(minCrew / 25d * 4.5)).intValue());
            val.add(Double.valueOf(Math.ceil(minCrew / 25d * 5.5)).intValue());
            val.add(Double.valueOf(Math.ceil(minCrew / 25d * 6.5)).intValue());
            val.add(Double.valueOf(Math.ceil(minCrew / 25d * 7.5)).intValue());
            val.add(Double.valueOf(Math.ceil(minCrew / 25d * 8.5)).intValue());
        } else {
            val.add(1);
        }
        return val;
    }

    int getFlexPoints() {
        return Math.min(Double.valueOf(Math.round((double) getQuantity() / getShip().getMinimumCrew() * 25)).intValue(), getQuantity());
    }
}
