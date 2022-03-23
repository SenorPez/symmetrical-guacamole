package com.senorpez.avt.shipdesigner;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

record TotalWeaponsSpaces_10Worlds() {
    final static HashMap<Double, Double> data = new HashMap<>();
    static {
        data.put(25d, 0.2000d);
        data.put(37.5d, 0.1900d);
        data.put(50d, 0.1800d);
        data.put(75d, 0.1850d);
        data.put(100d, 0.1700d);
        data.put(125d, 0.1675d);
    }

    static double getTotalWeaponsSpaces(final double hullSpaces) {
        return data.entrySet()
                .stream()
                .min(Comparator.comparingDouble(d -> Math.abs(d.getKey() - hullSpaces)))
                .map(Map.Entry::getValue)
                .orElse(0d);
    }
}
