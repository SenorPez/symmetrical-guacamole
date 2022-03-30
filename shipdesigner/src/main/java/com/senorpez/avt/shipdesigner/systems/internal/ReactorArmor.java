package com.senorpez.avt.shipdesigner.systems.internal;

import com.senorpez.avt.shipdesigner.systems.System;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class ReactorArmor extends ArmorGroup {
    private List<Reactor> reactors = new ArrayList<>();
    private CivilianReactor civilianReactor = null;
    private SolarArray solarArray = null;
    private ChemicalBattery chemicalBattery = null;
    private MHDCaptureSystem mhdCaptureSystem = null;

    ReactorArmor(int armorLevel) {
        super(armorLevel);
    }

    double getArmorPointsUsed() {
        final int totalReactorActualSpacesUsed = reactors.stream().map(Reactor::getActualSpacesUsed).mapToInt(Integer::intValue).sum();
        final int totalOtherActualSpacesUsed = getCivilianReactor().map(System::getActualSpacesUsed).orElse(0)
                + getSolarArray().map(System::getActualSpacesUsed).orElse(0)
                + getChemicalBattery().map(System::getActualSpacesUsed).orElse(0)
                + getMHDCaptureSystem().map(System::getActualSpacesUsed).orElse(0);
        return (totalReactorActualSpacesUsed + totalOtherActualSpacesUsed) * armorUsage.get(getArmorLevel());
    }

    public ReactorArmor setCivilianReactor(CivilianReactor civilianReactor) {
        this.civilianReactor = civilianReactor;
        return this;
    }

    public ReactorArmor setSolarArray(SolarArray solarArray) {
        this.solarArray = solarArray;
        return this;
    }

    public ReactorArmor setChemicalBattery(ChemicalBattery chemicalBattery) {
        this.chemicalBattery = chemicalBattery;
        return this;
    }

    public ReactorArmor setMhdCaptureSystem(MHDCaptureSystem mhdCaptureSystem) {
        this.mhdCaptureSystem = mhdCaptureSystem;
        return this;
    }

    private List<Reactor> getReactors() {
        return reactors;
    }

    private Optional<CivilianReactor> getCivilianReactor() {
        return Optional.ofNullable(civilianReactor);
    }

    private Optional<SolarArray> getSolarArray() {
        return Optional.ofNullable(solarArray);
    }

    private Optional<ChemicalBattery> getChemicalBattery() {
        return Optional.ofNullable(chemicalBattery);
    }

    private Optional<MHDCaptureSystem> getMHDCaptureSystem() {
        return Optional.ofNullable(mhdCaptureSystem);
    }
}
