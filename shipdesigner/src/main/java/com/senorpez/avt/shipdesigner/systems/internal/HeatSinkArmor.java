package com.senorpez.avt.shipdesigner.systems.internal;

class HeatSinkArmor extends ArmorGroup {
    HeatSinkArmor(int armorLevel) {
        super(armorLevel);
    }

    @Override
    double getArmorPointsUsed() {
        return 0;
    }
}
