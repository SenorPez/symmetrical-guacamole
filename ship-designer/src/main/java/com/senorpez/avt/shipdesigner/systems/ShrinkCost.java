package com.senorpez.avt.shipdesigner.systems;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

record ShrinkCost() {
    static final private Map<Integer, Double> systemShrinkModifierLookup = new HashMap<>();
    static final private Map<Integer, Double> driveShrinkModifierLookup = new HashMap<>();
    static {
        systemShrinkModifierLookup.put(0, 1d);
        systemShrinkModifierLookup.put(1, 1.1d);
        systemShrinkModifierLookup.put(2, 1.25d);
        systemShrinkModifierLookup.put(3, 1.475d);
        systemShrinkModifierLookup.put(4, 1.775d);
        systemShrinkModifierLookup.put(5, 2.15d);
        systemShrinkModifierLookup.put(6, 2.6d);
        systemShrinkModifierLookup.put(7, 3.125d);
        systemShrinkModifierLookup.put(8, 3.725d);
        systemShrinkModifierLookup.put(9, 4.4d);

        driveShrinkModifierLookup.put(0, 1d);
        driveShrinkModifierLookup.put(1, 1.1d);
        driveShrinkModifierLookup.put(2, 1.3d);
        driveShrinkModifierLookup.put(3, 1.6d);
        driveShrinkModifierLookup.put(4, 2d);
        driveShrinkModifierLookup.put(5, 2.5d);
        driveShrinkModifierLookup.put(6, 3.1d);
        driveShrinkModifierLookup.put(7, 3.8d);
        driveShrinkModifierLookup.put(8, 4.6d);
        driveShrinkModifierLookup.put(9, 5.5d);
        driveShrinkModifierLookup.put(10, 6.5d);
    }

    static double getSystemShrinkModifier(final int shrinkLevel) {
        if (systemShrinkModifierLookup.containsKey(shrinkLevel)) {
            return systemShrinkModifierLookup.get(shrinkLevel);
        }
        throw new IndexOutOfBoundsException("Shrink Level Not Found");
    }

    static double getDriveShrinkModifier(final int shrinkLevel) {
        if (driveShrinkModifierLookup.containsKey(shrinkLevel)) {
            return driveShrinkModifierLookup.get(shrinkLevel);
        }
        throw new IndexOutOfBoundsException("Shrink Level Not Found");
    }
}
