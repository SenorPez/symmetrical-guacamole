package com.senorpez.avt.shipdesigner.weapons;

import com.senorpez.avt.shipdesigner.systems.ProductionLevel;

abstract class ZoneDefense extends Weapon {
    ZoneDefense(Mount mount, int shrinkEnhancement, ProductionLevel productionLevel) {
        super(mount, shrinkEnhancement, productionLevel);
    }

    abstract double getPower();
}
