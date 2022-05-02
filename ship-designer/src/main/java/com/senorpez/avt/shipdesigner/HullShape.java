package com.senorpez.avt.shipdesigner;

enum HullShape {
    SPHERE(1.25d) {
        @Override
        double getHullLength(final int hullSpaces, final double useableFraction) {
            return Math.pow(6 / Math.PI, 1 / 3d) * Math.pow(hullSpaces * 100 * useableFraction, 1 / 3d);
        }
    };

    private final double pivotModifier;

    HullShape(final double pivotModifier) {
        this.pivotModifier = pivotModifier;
    }

    double getPivotModifier() {
        return pivotModifier;
    }

    abstract double getHullLength(final int hullSpaces, final double usableFraction);
}
