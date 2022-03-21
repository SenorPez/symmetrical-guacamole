package com.senorpez.avt.shipdesigner;

enum Shape {
    CYLINDER("Cylinder", 1d, 0.05d, 1d, 0.0318d, 0.8d) {
        @Override
        int getImprovedAccesswayRequirement(final int shipSpaces) {
            return Math.round(shipSpaces / 100f);
        }
    },
    SPHEROID("Spheroid", 1.5d, 0.154d, 1.25d, 0.239d, 1.0d),
    LONG_CYLINDER("Long Cylinder", 0.75d, 0.025d, 0.75d, Math.pow(10, -2.25), 0.7d) {
        @Override
        int getImprovedAccesswayRequirement(final int shipSpaces) {
            return Math.round(shipSpaces / 100f);
        }
    },
    HEMISPHEROID("Hemispheroid", 1.75d, 0.215d, 1.375d, Math.pow(10, -0.3109), 1.1d),
    CONICAL("Conical", 1.375, 0.1125d, 1.1825d, Math.pow(10, -0.87), 1.05d),
    ELLIPSOID("Ellipsoid", 1.25d, 0.075d, 1.125d, Math.pow(10, -1.05), 0.9d) {
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

        @Override
        double getRollMoment(double armorFraction, double driveFraction, double shipMass, int shipSpaces, double driveMass, double driveArmorMass, double driveDiameter) {
            double rollModifier = Math.max(1 - armorFraction - driveFraction, 0.1);
            return Math.pow(rollModifier, 5 / 3d) * shipMass * Math.pow(shipSpaces * 100, 2 / 3d) * 0.087
                    + 0.05 * (driveMass + driveArmorMass) * Math.pow(driveDiameter, 2);
        }

        @Override
        double getHullSurfaceArea(final double hullSpaces, final double armorFraction, final double driveFraction_Typical) {
            final double hullLength = getHullLength(hullSpaces, armorFraction, driveFraction_Typical);
            final double hullDiameter = getHullDiameter(hullSpaces, armorFraction, driveFraction_Typical);
            return 4 * Math.PI * Math.pow(2 * Math.pow(hullLength / 2, 1.6075) * Math.pow(hullDiameter / 2, 1.6075) / 3 + Math.pow(hullDiameter / 2, 2 * 1.6075) / 3, 1 / 1.6075);
        }

        @Override
        double getFrontArmorArea(double hullSpaces, double armorFraction, double driveFraction_Typical) {
            return getHullSurfaceArea(hullSpaces, armorFraction, driveFraction_Typical) / 30;
        }

        @Override
        double getLateralArmorArea(double hullSpaces, double armorFraction, double driveFraction_Typical) {
            return getHullSurfaceArea(hullSpaces, armorFraction, driveFraction_Typical) / 30;
        }
    };

    private final String shapeName;
    private final double mastMassModifier;
    private final double thrusterModifier;
    private final double pivotModifier;
    private final double rollModifier;
    private final double hullCostModifier;

    Shape(String shapeName, double mastMassModifier, double thrusterModifier, double pivotModifier, double rollModifier, double hullCostModifier) {
        this.shapeName = shapeName;
        this.mastMassModifier = mastMassModifier;
        this.thrusterModifier = thrusterModifier;
        this.pivotModifier = pivotModifier;
        this.rollModifier = rollModifier;
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

    double getRollModifier() {
        return rollModifier;
    }

    double getHullCostModifier() {
        return hullCostModifier;
    }

    double getMomentOfInertia(double hullSpaces, double armorFraction, double driveFraction_Typical, double driveFraction, double shipMass, double mastLength, double lanternDiameter, double mastStructuralMass, double mastArmorMass, double lanternMass, double driveArmorMass, double mastMass) {
        // TODO: Simplify this signature.
        return 0;
    }

    double getRollMoment(double armorFraction, double driveFraction, double shipMass, int shipSpaces, double driveMass, double driveArmorMass, double driveDiameter) {
        return 0;
    }

    int getImprovedAccesswayRequirement(final int shipSpaces) {
        return Math.round(shipSpaces / 50f);
    }

    double getHullSurfaceArea(final double hullSpaces, final double armorFraction, final double driveFraction_Typical) {
        return 0;
    }

    double getFrontArmorArea(final double hullSpaces, final double armorFraction, final double driveFraction_Typical) {
        return 0;
    }

    double getLateralArmorArea(final double hullSpaces, final double armorFraction, final double driveFraction_Typical) {
        return 0;
    }
}
