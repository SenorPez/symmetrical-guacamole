package com.senorpez.avt.shipdesigner;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

record CylinderKeelLargestWeaponMountable() {
    final static HashMap<Double, Integer> data = new HashMap<>();
    static {
        data.put(25d, 2);
        data.put(37.5d, 2);
        data.put(50d, 2);
        data.put(75d, 3);
        data.put(100d, 3);
        data.put(125d, 3);
    }

    static int getLargestWeaponMountable(final double hullSpaces) {
        return data.entrySet()
                .stream()
                .min(Comparator.comparingDouble(d -> Math.abs(d.getKey() - hullSpaces)))
                .map(Map.Entry::getValue)
                .orElse(0);
    }
}
