package com.senorpez.avt.shipdesigner;

enum HullShape {
    SPHERE(1.25d) {
        @Override
        double getHullLength(final int hullSpaces, final double usableFraction) {
            return Math.pow(6 / Math.PI, 1 / 3d) * Math.pow(hullSpaces * 100 * usableFraction, 1 / 3d);
        }

        @Override
        double getMomentOfInertia(final int hullSpaces,
                                  final double usableFraction,
                                  final double driveFraction,
                                  final double lanternMass,
                                  final double lanternDiameter,
                                  final double mastLength,
                                  final double mastStructureMass,
                                  final double mastArmorMass,
                                  final double mastMass) {
            final double hullLength = getHullLength(hullSpaces, usableFraction);

            return (1 - driveFraction) / 4 * hullSpaces * 25 * (Math.pow(hullLength, 2) * 0.4)
                    + driveFraction * (1 - driveFraction) * hullSpaces * 25 * Math.pow(hullLength / 2 + mastLength + lanternDiameter / 2, 2)
                    + (1 / 3d) * (mastStructureMass + mastArmorMass) * Math.pow(mastLength, 2)
                    + (0.2 * lanternMass + mastMass) * Math.pow(lanternDiameter / 2, 2);
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

    abstract double getMomentOfInertia(final int hullSpaces,
                                       final double usableFraction,
                                       final double driveFraction,
                                       final double lanternMass,
                                       final double lanternDiameter,
                                       final double mastLength,
                                       final double mastStructureMass,
                                       final double mastArmorMass,
                                       final double mastMass);
}
