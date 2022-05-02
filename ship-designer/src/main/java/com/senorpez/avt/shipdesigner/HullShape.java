package com.senorpez.avt.shipdesigner;

enum HullShape {
    SPHERE(1.25d);

    private final double pivotModifier;

    HullShape(final double pivotModifier) {
        this.pivotModifier = pivotModifier;
    }

    double getPivotModifier() {
        return pivotModifier;
    }
}
