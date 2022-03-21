package com.senorpez.avt.shipdesigner;

enum Shape {
    CYLINDER("Cylinder", 1d, 0.05d, 1d, 0.8d),
    SPHEROID("Spheroid", 1.5d, 0.154d, 1.25d, 1.0d),
    LONG_CYLINDER("Long Cylinder", 0.75d, 0.025d, 0.75d, 0.7d),
    HEMISPHEROID("Hemispheroid", 1.75d, 0.215d, 1.375d, 1.1d),
    CONICAL("Conical", 1.375, 0.1125d, 1.1825d, 1.05d),
    ELLIPSOID("Ellipsoid", 1.25d, 0.075d, 1.125d, 0.9d) {
        @Override
        double getHullLength(double hullSpaces, double armorFraction, double driveFraction_Typical) {
            return 2 * getHullDiameter(hullSpaces, armorFraction, driveFraction_Typical);
        }

        @Override
        double getHullDiameter(double hullSpaces, double armorFraction, double driveFraction_Typical) {
            return Math.pow(3 * 100 * hullSpaces * (1 - armorFraction - driveFraction_Typical) / Math.PI, 1 / 3d);
        }

        @Override
        double getShieldDiameter(double hullSpaces, double armorFraction, double driveFraction_Typical, double mastLength, double lanternDiameter) {
            final double hullDiameter = getHullDiameter(hullSpaces, armorFraction, driveFraction_Typical);
            return Math.pow(Math.pow(hullDiameter, 2) - Math.pow(Math.pow(hullDiameter, 2) / (hullDiameter + mastLength + 0.5 * lanternDiameter), 2), 0.5) * ((lanternDiameter / 2) / ((lanternDiameter / 2) + mastLength + hullDiameter - ((Math.pow(hullDiameter, 2)) / (hullDiameter + mastLength + 0.5 * lanternDiameter))));
        }

        @Override
        double getMomentOfInertia(double hullSpaces, double armorFraction, double driveFraction_Typical, double driveFraction, double shipMass, double mastLength, double lanternDiameter, double mastStructuralMass, double mastArmorMass, double lanternMass, double driveArmorMass, double mastMass) {
            double hullLength = getHullLength(hullSpaces, armorFraction, driveFraction_Typical);
            return (1 - driveFraction) / 4 * shipMass * (Math.pow(hullLength, 2) * (0.37))
                    + (driveFraction) * (1 - driveFraction) * shipMass * Math.pow(hullLength / 2 + mastLength + lanternDiameter / 2, 2) + (1 / 3d) * (mastStructuralMass + mastArmorMass) * Math.pow(mastLength, 2) + (0.2 * (lanternMass + driveArmorMass) + mastMass) * Math.pow(lanternDiameter / 2, 2);
        }
    };

    private final String shapeName;
    private final double mastMassModifier;
    private final double thrusterModifier;
    private final double pivotModifier;
    private final double hullCostModifier;

    Shape(String shapeName, double mastMassModifier, double thrusterModifier, double pivotModifier, double hullCostModifier) {
        this.shapeName = shapeName;
        this.mastMassModifier = mastMassModifier;
        this.thrusterModifier = thrusterModifier;
        this.pivotModifier = pivotModifier;
        this.hullCostModifier = hullCostModifier;
    }

    String getShapeName() {
        return shapeName;
    }

    double getMastMassModifier() {
        return mastMassModifier;
    }

    double getHullLength(double hullSpaces, double armorFraction, double driveFraction_Typical) {
        return 0;
    }

    double getHullDiameter(double hullSpaces, double armorFraction, double driveFraction_Typical) {
        return 0;
    }

    double getShieldDiameter(final double hullSpaces, final double armorFraction, final double driveFraction_Typical, double mastLength, final double lanternDiameter) {
        return 0;
    }

    double getThrusterModifier() {
        return thrusterModifier;
    }

    double getPivotModifier() {
        return pivotModifier;
    }

    public double getHullCostModifier() {
        return hullCostModifier;
    }

    double getMomentOfInertia(double hullSpaces, double armorFraction, double driveFraction_Typical, double driveFraction, double shipMass, double mastLength, double lanternDiameter, double mastStructuralMass, double mastArmorMass, double lanternMass, double driveArmorMass, double mastMass) {
        // TODO: Simplify this signature.
        return 0;
    }
}
