package com.senorpez.avt.shipdesigner;

public enum HullShape {
    SPHERE(1.25d, 1.5d, 1) {
        @Override
        double getHullLength(final int hullSpaces, final double usableFraction) {
            return Math.pow((100 * hullSpaces * usableFraction / (Math.PI / 6)), 1 / 3d);
        }

        @Override
        double getHullDiameter(int hullSpaces, final double usableFraction) {
            return getHullLength(hullSpaces, usableFraction);
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

        @Override
        double getShieldMinDiameter(final int hullSpaces,
                                    final double usableFraction,
                                    final double lanternDiameter,
                                    final double mastLength) {
            final double hullLength = getHullLength(hullSpaces, usableFraction);
            return lanternDiameter * Math.tan(Math.asin((hullLength / 2) / (mastLength + ((lanternDiameter + hullLength) / 2))));
        }

        @Override
        double getShieldMaxDiameter(final int hullSpaces,
                                    final double usableFraction,
                                    final double lanternDiameter,
                                    final double mastLength,
                                    final double shieldThickness) {
            final double hullLength = getHullLength(hullSpaces, usableFraction);
            return ((lanternDiameter / 2 + shieldThickness) * Math.tan(Math.asin((hullLength / 2) / (mastLength + ((lanternDiameter + hullLength) / 2)))) * 2);
        }
    };

    private final double pivotModifier;
    private final double mastMassModifier;
    private final double costPerHullSpace;

    HullShape(final double pivotModifier, final double mastMassModifier, final double costPerHullSpace) {
        this.pivotModifier = pivotModifier;
        this.mastMassModifier = mastMassModifier;
        this.costPerHullSpace = costPerHullSpace;
    }

    double getPivotModifier() {
        return pivotModifier;
    }

    double getMastMassModifier() {
        return mastMassModifier;
    }

    public double getCostPerHullSpace() {
        return costPerHullSpace;
    }

    abstract double getHullLength(final int hullSpaces, final double usableFraction);

    abstract double getHullDiameter(final int hullSpaces, final double usableFraction);

    abstract double getMomentOfInertia(final int hullSpaces,
                                       final double usableFraction,
                                       final double driveFraction,
                                       final double lanternMass,
                                       final double lanternDiameter,
                                       final double mastLength,
                                       final double mastStructureMass,
                                       final double mastArmorMass,
                                       final double mastMass);

    abstract double getShieldMinDiameter(final int hullSpaces,
                                         final double usableFraction,
                                         final double lanternDiameter,
                                         final double mastLength);

    abstract double getShieldMaxDiameter(final int hullSpaces,
                                         final double usableFraction,
                                         final double lanternDiameter,
                                         final double mastLength,
                                         final double shieldThickness);
}
