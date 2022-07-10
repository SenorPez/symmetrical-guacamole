package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

import java.util.ArrayList;
import java.util.List;

public class FlagBridge extends StandardArmoredSystem {
    private final static String name = "Flag Bridge";
    private final static int spacesPerSystem = 1;
    private final static double costPerSpace = 3d;
    private final static double crewPerSpace = 1d;
    private final static double maintenanceRate = 0.25d;

    public FlagBridge(final Ship ship,
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

    // TODO: Add limitation that a flag bridge may not exceed 7% of hull spaces.

    int getFlagPoints() {
        if (getQuantity() >= 4) {
            return Double.valueOf(Math.floor(1 + (getQuantity() - 4) / 2d)).intValue();
        }
        return 0;
    }

    List<Integer> getFlagPointsAt() {
        final List<Integer> flagPointsAt = new ArrayList<>();
        if (ship.getHullSpaces() >= 100) flagPointsAt.add(4);
        if (ship.getHullSpaces() >= 150) flagPointsAt.add(6);
        if (ship.getHullSpaces() >= 200) flagPointsAt.add(8);
        if (ship.getHullSpaces() >= 250) flagPointsAt.add(10);
        if (ship.getHullSpaces() >= 300) flagPointsAt.add(12);
        if (ship.getHullSpaces() >= 350) flagPointsAt.add(14);
        if (ship.getHullSpaces() >= 400) flagPointsAt.add(16);
        if (ship.getHullSpaces() >= 450) flagPointsAt.add(18);
        return flagPointsAt;
    }
}
