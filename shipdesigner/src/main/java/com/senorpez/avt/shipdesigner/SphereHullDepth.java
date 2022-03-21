package com.senorpez.avt.shipdesigner;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

record SphereHullDepth() {
    final static HashMap<Double, Integer> data = new HashMap<>();
    static {
        data.put(25d, 6);
        data.put(37.5d, 8);
        data.put(50d, 10);
        data.put(75d, 14);
        data.put(100d, 17);
        data.put(125d, 20);
    }

    static double getSphereHullSpaces(final double hullSpaces) {
        return data.entrySet()
                .stream()
                .min(Comparator.comparingDouble(d -> Math.abs(d.getKey() - hullSpaces)))
                .map(Map.Entry::getKey)
                .orElse(0d);
    }

    static int getSphereHullDepth(final double hullSpaces) {
        return data.entrySet()
                .stream()
                .min(Comparator.comparingDouble(d -> Math.abs(d.getKey() - hullSpaces)))
                .map(Map.Entry::getValue)
                .orElse(0);
    }
}
