package com.senorpez.avt.shipdesigner.systems;

public interface SharedArmorSystem {
    ArmorLevel getSharedArmorLevel();
    void setSharedArmorLevel(final ArmorLevel armorLevel);
    double getArmorPointsUsed();
}
