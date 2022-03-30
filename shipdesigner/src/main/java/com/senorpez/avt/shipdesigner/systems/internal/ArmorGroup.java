package com.senorpez.avt.shipdesigner.systems.internal;

import com.senorpez.avt.shipdesigner.systems.System;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class ArmorGroup {
    private int armorLevel;

    private CargoSupport cargoSupport = null;
    private CargoRepair cargoRepair = null;
    private CargoMagazine cargoMagazine = null;
    private CargoShuttle cargoShuttle = null;

    private final List<Double> armorUsage = new ArrayList<>(
            List.of(0d, 0.5d, 1.0d, 2.0d, 3.5d, 5.0d, 7.0d, 9.0d));

    ArmorGroup(int armorLevel) {
        this.armorLevel = armorLevel;
    }

    int getArmorLevel() {
        return armorLevel;
    }

    double getArmorPointsUsed() {
        final int totalActualSpacesUsed = getCargoSupport().map(System::getActualSpacesUsed).orElse(0)
                + getCargoRepair().map(System::getActualSpacesUsed).orElse(0)
                + getCargoMagazine().map(System::getActualSpacesUsed).orElse(0)
                + getCargoShuttle().map(System::getActualSpacesUsed).orElse(0);
        return totalActualSpacesUsed * armorUsage.get(getArmorLevel());
    }

    private Optional<CargoSupport> getCargoSupport() {
        return Optional.ofNullable(cargoSupport);
    }

    private Optional<CargoRepair> getCargoRepair() {
        return Optional.ofNullable(cargoRepair);
    }

    private Optional<CargoMagazine> getCargoMagazine() {
        return Optional.ofNullable(cargoMagazine);
    }

    private Optional<CargoShuttle> getCargoShuttle() {
        return Optional.ofNullable(cargoShuttle);
    }

    ArmorGroup setArmorLevel(int armorLevel) {
        this.armorLevel = armorLevel;
        return this;
    }

    ArmorGroup setCargoSupport(CargoSupport cargoSupport) {
        this.cargoSupport = cargoSupport;
        return this;
    }

    ArmorGroup setCargoRepair(CargoRepair cargoRepair) {
        this.cargoRepair = cargoRepair;
        return this;
    }

    ArmorGroup setCargoMagazine(CargoMagazine cargoMagazine) {
        this.cargoMagazine = cargoMagazine;
        return this;
    }

    ArmorGroup setCargoShuttle(CargoShuttle cargoShuttle) {
        this.cargoShuttle = cargoShuttle;
        return this;
    }
}
