package com.senorpez.avt.shipdesigner.systems;

import java.util.List;

interface ArmorLevel {
    int getArmorLevel();
    void setArmorLevel(final int armorLevel);
    List<System> getSystems();
    void setSystems(final List<System> systems);

    default int getActualSpacesUsed() {
        return getSystems().stream().mapToInt(System::getActualSpacesUsed).sum();
    }
}
