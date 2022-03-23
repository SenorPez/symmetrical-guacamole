package com.senorpez.avt.shipdesigner;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

record SphereLargestMountSpaces() {
    final static HashMap<Double, Double> data = new HashMap<>();
    static {
        data.put(25d, 0.08d);
        data.put(37.5d, 0.06d);
        data.put(50d, 0.04d);
        data.put(75d, 0.04d);
        data.put(100d, 0.04d);
        data.put(125d, 0.04d);
    }

    static double get(final double hullSpaces) {
        return data.entrySet()
                .stream()
                .min(Comparator.comparingDouble(d -> Math.abs(d.getKey() - hullSpaces)))
                .map(Map.Entry::getValue)
                .orElse(0d);
    }
}
