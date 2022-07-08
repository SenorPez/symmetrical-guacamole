package com.senorpez.avt.shipdesigner.systems;

public enum ProductionLevel {
    PROTOTYPE(4d),
    LIMITED(2d),
    STANDARD(1d),
    MASS(0.5d);

    private final double costModifier;

    ProductionLevel(final double costModifier) {
        this.costModifier = costModifier;
    }

    double getCostModifier() {
        return costModifier;
    }
}
