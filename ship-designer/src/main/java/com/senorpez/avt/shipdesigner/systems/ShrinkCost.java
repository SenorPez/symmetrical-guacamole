package com.senorpez.avt.shipdesigner.systems;

import java.util.HashMap;
import java.util.Map;

public record ShrinkCost() {
    static final private Map<Integer, Double> systemShrinkModifierLookup = new HashMap<>();
    static {
        systemShrinkModifierLookup.put(0, 1d);
        systemShrinkModifierLookup.put(1, 1.1d);
        systemShrinkModifierLookup.put(2, 1.25d);
        systemShrinkModifierLookup.put(3, 1.475d);
        systemShrinkModifierLookup.put(4, 1.775d);
        systemShrinkModifierLookup.put(5, 2.15d);
        systemShrinkModifierLookup.put(6, 2.6);
        systemShrinkModifierLookup.put(7, 3.125);
        systemShrinkModifierLookup.put(8, 3.725d);
        systemShrinkModifierLookup.put(9, 4.4d);
    }

    static double getSystemShrinkModifier(final int shrinkLevel) {
        if (systemShrinkModifierLookup.containsKey(shrinkLevel)) {
            return systemShrinkModifierLookup.get(shrinkLevel);
        }
        throw new IndexOutOfBoundsException("Shrink Level Not Found");
    }
}
