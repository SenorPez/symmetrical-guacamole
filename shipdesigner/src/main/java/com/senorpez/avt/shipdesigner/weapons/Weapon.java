package com.senorpez.avt.shipdesigner.weapons;

import com.senorpez.avt.shipdesigner.systems.ProductionLevel;

import java.util.List;

abstract class Weapon {
    private Mount mount;
    private int shrinkEnhancement;
    private ProductionLevel productionLevel;

    final static List<Double> enhancementTable = List.of(1d, 1.1d, 1.25d, 1.475d, 1.775d, 2.15d, 2.6d, 3.125d, 3.725d, 4.4d, 5.15d, 5.975d, 6.875d, 7.85d, 8.9d, 10.025d, 11.225d, 12.5d, 13.85d, 15.275, 16.775d, 18.35);

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
