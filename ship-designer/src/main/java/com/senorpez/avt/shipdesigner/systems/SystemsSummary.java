package com.senorpez.avt.shipdesigner.systems;

import java.util.List;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;

interface SystemsSummary {
    List<System> getSystems();

    default int getBasicSpacesUsed() {
        return systemsSum(System::getBasicSpacesUsed);
    }

    default int getActualSpacesUsed() {
        return systemsSum(System::getActualSpacesUsed);
    }

    default int getBaseCost() {
        return systemsSum(System::getBaseCost);
    }

    default int getEnhancedCost() {
        return systemsSum(System::getEnhancedCost);
    }

    default int getDuelCost() {
        return systemsSum(System::getDuelCost);
    }

    default int getEconomicCost() {
        return systemsSum(System::getEconomicCost);
    }

    default double getMaintenanceCost() {
        return systemsSum(System::getMaintenanceCost);
    }

    default double getArmorPointsUsed() {
        return armorSum();
    }

    private int systemsSum(final ToIntFunction<System> f) {
        return getSystems().stream().mapToInt(f).sum();
    }

    private double systemsSum(final ToDoubleFunction<System> f) {
        return getSystems().stream().mapToDouble(f).sum();
    }

    private double armorSum() {
        return getSystems()
                .stream()
                .filter(s -> s instanceof ArmoredSystem)
                .mapToDouble(s -> ((ArmoredSystem) s).getArmorPointsUsed())
                .sum();
    }
}
