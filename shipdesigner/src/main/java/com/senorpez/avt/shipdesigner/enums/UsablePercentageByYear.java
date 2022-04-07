package com.senorpez.avt.shipdesigner.enums;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public record UsablePercentageByYear() {
    final static HashMap<Integer, Double> data = new HashMap<>();

    static {
        data.put(2103, 0.9d);
        data.put(2111, 0.91d);
        data.put(2121, 0.92d);
        data.put(2131, 0.93d);
        data.put(2141, 0.94d);
        data.put(2151, 0.95d);
        data.put(2161, 0.96d);
        data.put(2171, 0.97d);
        data.put(2181, 0.98d);
        data.put(2191, 0.99d);
        data.put(2201, 1d);
        data.put(2214, 0.92d);
        data.put(2256, 0.93d);
        data.put(2291, 0.94d);
        data.put(2316, 0.95d);
        data.put(2336, 0.96d);
        data.put(2356, 0.97d);
        data.put(2376, 0.98d);
        data.put(2396, 0.99d);
        data.put(2416, 1d);
        data.put(2426, 1.01d);
        data.put(2431, 1.02d);
        data.put(2436, 1.03d);
        data.put(2441, 1.04d);
        data.put(2446, 1.05d);
        data.put(2451, 1.06d);
        data.put(2456, 1.07d);
        data.put(2461, 1.08d);
        data.put(2466, 1.09d);
        data.put(2471, 1.1d);
        data.put(2476, 1.11d);
        data.put(2481, 1.12d);
        data.put(2486, 1.13d);
        data.put(2491, 1.14d);
        data.put(2496, 1.15d);
        data.put(2501, 1.16d);
        data.put(2506, 1.17d);
        data.put(2511, 1.18d);
        data.put(2516, 1.19d);
        data.put(2521, 1.2d);
        data.put(2526, 1.21d);
        data.put(2531, 1.22d);
        data.put(2536, 1.23d);
        data.put(2541, 1.24d);
        data.put(2546, 1.25d);
        data.put(2551, 1.26d);
    }

    public static double getUsablePercentage(final int year) {
        return data.entrySet()
                .stream()
                .min(Comparator.comparingInt(d -> Math.abs(d.getKey() - year)))
                .map(Map.Entry::getValue)
                .orElse(0d);
    }
}
