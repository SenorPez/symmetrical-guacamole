package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

import java.util.List;
import java.util.stream.DoubleStream;

class Bridge extends StandardArmoredSystem {
    private final static String name = "Bridge";
    private final static int spacesPerSystem = 1;
    private final static double costPerSpace = 3d;
    private final static double crewPerSpace = 1d;
    private final static double maintenanceRate = 0.25d;

    Bridge(final Ship ship,
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

    int getFlexPoints() {
        return Long.valueOf(Math.min(
                getQuantity(),
                Math.round(getQuantity() / (double) getShip().getMinimumCrew() * 25)
        )).intValue();
    }

    List<Integer> getFlexPointsAt() {
        if (getShip().getMinimumCrew() / 25d < 1) {
            return List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        } else if (getShip().getHullSpaces() >= 100) {
            return DoubleStream.of(0.5d, 1.5d, 2.5d, 3.5d, 4.5d, 5.5d, 6.5d, 7.5d, 8.5d)
                    .map(n -> Math.ceil(getShip().getMinimumCrew() / 25d * n))
                    .mapToInt(n -> (int) n)
                    .boxed()
                    .toList();
        } else {
            return List.of();
        }
    }
}
