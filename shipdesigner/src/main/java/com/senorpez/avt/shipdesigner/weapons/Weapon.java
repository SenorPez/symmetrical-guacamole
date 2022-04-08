package com.senorpez.avt.shipdesigner.weapons;

import com.senorpez.avt.shipdesigner.systems.ProductionLevel;

abstract class Weapon {
    private Mount mount;
    private int shrinkEnhancement;
    private ProductionLevel productionLevel;

    Weapon(final Mount mount,
           final int shrinkEnhancement,
           final ProductionLevel productionLevel) {
        this.mount = mount;
        this.shrinkEnhancement = shrinkEnhancement;
        this.productionLevel = productionLevel;
    }

    abstract int getSpacesUsed();

    abstract int getDuelCost();

    int getEconomicCost() {
        return Double.valueOf(Math.ceil(productionLevel.getEconomicCostModifier() * getDuelCost())).intValue();
    }
    
    abstract int getRecycleTime();

    abstract int getBaseCost();
    
    // GETTERS & SETTERS
    Mount getMount() {
        return mount;
    }

    Weapon setMount(Mount mount) {
        this.mount = mount;
        return this;
    }

    int getShrinkEnhancement() {
        return shrinkEnhancement;
    }

    Weapon setShrinkEnhancement(int shrinkEnhancement) {
        this.shrinkEnhancement = shrinkEnhancement;
        return this;
    }

    ProductionLevel getProductionLevel() {
        return productionLevel;
    }

    Weapon setProductionLevel(ProductionLevel productionLevel) {
        this.productionLevel = productionLevel;
        return this;
    }
}
