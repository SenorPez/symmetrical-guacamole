package com.senorpez.avt.shipdesigner.systems;

import java.util.HashMap;
import java.util.Map;

record ArmorPoints() {
    static final private Map<Integer, Double> armorPointUsageLookup = new HashMap<>();
    static {
        armorPointUsageLookup.put(0, 0d);
        armorPointUsageLookup.put(1, 0.5d);
        armorPointUsageLookup.put(2, 1d);
        armorPointUsageLookup.put(3, 2d);
        armorPointUsageLookup.put(4, 3.5d);
        armorPointUsageLookup.put(5, 5d);
        armorPointUsageLookup.put(6, 7d);
        armorPointUsageLookup.put(7, 9d);
    }

    static double getArmorPointsUsed(final int armorLevel) {
        if (armorPointUsageLookup.containsKey(armorLevel)) {
            return armorPointUsageLookup.get(armorLevel);
        }
        throw new IndexOutOfBoundsException("Armor Level Not Found");
    }
}
