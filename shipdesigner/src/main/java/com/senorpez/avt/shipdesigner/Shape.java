package com.senorpez.avt.shipdesigner;

enum Shape {
    CYLINDER("Cylinder", 1d, 1d),
    SPHEROID("Spheroid", 1.5d, 1.25d),
    LONG_CYLINDER("Long Cylinder", 0.75d, 0.75d),
    HEMISPHEROID("Hemispheroid", 1.75d, 1.375d),
    CONICAL("Conical", 1.375, 1.1825d),
    ELLIPSOID("Ellipsoid", 1.25d, 1.125d) {
        @Override
        double getHullLength(double hullSpaces, double armorFraction, double driveFraction) {
            return 2 * getHullDiameter(hullSpaces, armorFraction, driveFraction);
        }

        @Override
        double getHullDiameter(double hullSpaces, double armorFraction, double driveFraction) {
            return Math.pow(3 * 100 * hullSpaces * (1 - armorFraction - driveFraction) / Math.PI, 1 / 3d);
        }

        @Override
        double getShieldDiameter(double hullSpaces, double armorFraction, double driveFraction, double mastLength, double lanternDiameter) {
            final double hullDiameter = getHullDiameter(hullSpaces, armorFraction, driveFraction);
            return Math.pow(Math.pow(hullDiameter, 2) - Math.pow(Math.pow(hullDiameter, 2) / (hullDiameter + mastLength + 0.5 * lanternDiameter), 2), 0.5) * ((lanternDiameter / 2) / ((lanternDiameter / 2) + mastLength + hullDiameter - ((Math.pow(hullDiameter, 2)) / (hullDiameter + mastLength + 0.5 * lanternDiameter))));
        }
    };

    private final String shapeName;
    private final double mastMassModifier;
    private final double pivotModifier;

    Shape(String shapeName, double mastMassModifier, double pivotModifier) {
        this.shapeName = shapeName;
        this.mastMassModifier = mastMassModifier;
        this.pivotModifier = pivotModifier;
    }

    String getShapeName() {
        return shapeName;
    }

    double getMastMassModifier() {
        return mastMassModifier;
    }

    double getHullLength(double hullSpaces, double armorFraction, double driveFraction) {
        return 0;
    }

    double getHullDiameter(double hullSpaces, double armorFraction, double driveFraction) {
        return 0;
    }

    double getShieldDiameter(final double hullSpaces, final double armorFraction, final double driveFraction, double mastLength, final double lanternDiameter) {
        return 0;
    }

    double getPivotModifier() {
        return pivotModifier;
    }
}
