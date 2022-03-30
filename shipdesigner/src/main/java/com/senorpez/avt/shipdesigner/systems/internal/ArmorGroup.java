package com.senorpez.avt.shipdesigner.systems.internal;

import java.util.ArrayList;
import java.util.List;

abstract class ArmorGroup {
    int armorLevel;

    protected final List<Double> armorUsage = new ArrayList<>(
            List.of(0d, 0.5d, 1.0d, 2.0d, 3.5d, 5.0d, 7.0d, 9.0d));

    ArmorGroup(int armorLevel) {
        this.armorLevel = armorLevel;
    }

    int getArmorLevel() {
        return armorLevel;
    }

    abstract double getArmorPointsUsed();

    ArmorGroup setArmorLevel(int armorLevel) {
        this.armorLevel = armorLevel;
        return this;
    }
}
