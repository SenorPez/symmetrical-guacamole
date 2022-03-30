package com.senorpez.avt.shipdesigner.systems;

public enum ProductionLevel {
    PROTOTYPE(4d),
    LIMITED(2d),
    STANDARD(1d),
    MASS(0.5d);

    private final double economicCostModifier;

    ProductionLevel(double economicCostModifier) {
        this.economicCostModifier = economicCostModifier;
    }

    double getEconomicCostModifier() {
        return economicCostModifier;
    }
}
