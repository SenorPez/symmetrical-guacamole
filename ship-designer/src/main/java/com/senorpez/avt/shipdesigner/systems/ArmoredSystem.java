package com.senorpez.avt.shipdesigner.systems;

interface ArmoredSystem extends System {
    int getArmorLevel();
    void setArmorLevel(final int armorLevel);

    default double getArmorPointsUsed() {
        return getActualSpacesUsed() * ArmorPoints.getArmorPointsUsed(getArmorLevel());
    }
}
