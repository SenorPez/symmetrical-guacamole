package com.senorpez.avt.shipdesigner.systems;

import java.util.List;

public class CargoArmorLevel implements ArmorLevel {
    private int armorLevel;
    private List<System> systems;

    CargoArmorLevel(final int armorLevel, final List<System> systems) {
        this.armorLevel = armorLevel;
        this.systems = systems;
    }

    @Override
    public int getArmorLevel() {
        return armorLevel;
    }

    @Override
    public void setArmorLevel(final int armorLevel) {
        this.armorLevel = armorLevel;
    }

    @Override
    public List<System> getSystems() {
        return systems;
    }

    @Override
    public void setSystems(final List<System> systems) {
        this.systems = systems;
    }
}
