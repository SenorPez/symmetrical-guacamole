package com.senorpez.avt.shipdesigner.weapons;

import com.senorpez.avt.shipdesigner.systems.ProductionLevel;

class PodDock extends Weapon {
    public PodDock(Mount mount, int shrinkEnhancement, ProductionLevel productionLevel) {
        super(mount, shrinkEnhancement, productionLevel);
    }

    @Override
    int getSpacesUsed() {
        return 1;
    }

    @Override
    int getDuelCost() {
        final double enhancementCost = enhancementTable.get(getShrinkEnhancement());
        final int baseCost = getBaseCost();
        final double fieldOfFireCost = 0.9 + getMount().getFiringArc().size() / 10d;
        return Double.valueOf(Math.ceil(enhancementCost * baseCost * fieldOfFireCost)).intValue();
    }

    @Override
    int getBaseCost() {
        return 3;
    }
}
