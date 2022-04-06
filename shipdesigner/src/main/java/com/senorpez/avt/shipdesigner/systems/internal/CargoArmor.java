package com.senorpez.avt.shipdesigner.systems.internal;

import com.senorpez.avt.shipdesigner.systems.System;

import java.util.Optional;

class CargoArmor extends ArmorGroup {
    private CargoSupport cargoSupport = null;
    private CargoRepair cargoRepair = null;
    private CargoMagazine cargoMagazine = null;
    private CargoShuttle cargoShuttle = null;

    CargoArmor(int armorLevel) {
        super(armorLevel);
    }

    @Override
    double getArmorPointsUsed() {
        final int totalActualSpacesUsed = getCargoSupport().map(System::getActualSpacesUsed).orElse(0)
                + getCargoRepair().map(System::getActualSpacesUsed).orElse(0)
                + getCargoMagazine().map(System::getActualSpacesUsed).orElse(0)
                + getCargoShuttle().map(System::getActualSpacesUsed).orElse(0);
        return totalActualSpacesUsed * armorUsage.get(getArmorLevel());
    }

    CargoArmor setCargoSupport(CargoSupport cargoSupport) {
        this.cargoSupport = cargoSupport;
        return this;
    }

    CargoArmor setCargoRepair(CargoRepair cargoRepair) {
        this.cargoRepair = cargoRepair;
        return this;
    }

    CargoArmor setCargoMagazine(CargoMagazine cargoMagazine) {
        this.cargoMagazine = cargoMagazine;
        return this;
    }

    CargoArmor setCargoShuttle(CargoShuttle cargoShuttle) {
        this.cargoShuttle = cargoShuttle;
        return this;
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
}
