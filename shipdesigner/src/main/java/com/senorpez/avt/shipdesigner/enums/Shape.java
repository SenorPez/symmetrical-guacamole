package com.senorpez.avt.shipdesigner.enums;

import java.util.List;

import static com.senorpez.avt.shipdesigner.enums.MountConfiguration.*;
import static com.senorpez.avt.shipdesigner.enums.UsablePercentageByYear.getUsablePercentage;

public enum Shape {
    CYLINDER("Cylinder", 1d, 0.05d, 1d, 0.0318d, 0.8d, 1, 2, 0.8d, List.of(KEEL, SINGLE, DOUBLE), 1.104d, 12) {
        @Override
        public int getImprovedAccesswayRequirement(final int shipSpaces) {
            return Math.round(shipSpaces / 100f);
        }

        @Override
        public int getLargestWeaponAllowed(int hullSpaces) {
            return shipLookup.getCylinderLargestWeaponMountable_Single(hullSpaces);
        }

        @Override
        public int getLargestWeaponAllowed_Keel(int hullSpaces) {
            return shipLookup.getCylinderLargestWeaponMountable_Keel(hullSpaces);
        }

        @Override
        public double getLargestMountSpaces_Option1(int hullSpaces) {
            return shipLookup.getCylinderLargestMountSpaces_1(hullSpaces);
        }

        @Override
        public double getLargestMountSpaces_Option2(int hullSpaces) {
            return shipLookup.getCylinderLargestMountSpaces_2(hullSpaces);
        }

        @Override
        public int getMaximumMountLines(int hullSpaces) {
            return shipLookup.getCylinderMaximumMountLines_Single(hullSpaces);
        }
    },
    SPHEROID("Spheroid", 1.5d, 0.154d, 1.25d, 0.239d, 1.0d, 3, 5, 1.0d, List.of(SINGLE, DOUBLE, TRIPLE, QUADRUPLE, QUINTUPLE), 1, 21) {
        @Override
        public double getHullLength(double hullSpaces, double armorFraction, double driveFraction_Typical) {
            return getHullDiameter(hullSpaces, armorFraction, driveFraction_Typical);
        }

        @Override
        public double getHullDiameter(double hullSpaces, double armorFraction, double driveFraction_Typical) {
            return Math.pow(100 * hullSpaces * (1 - armorFraction - driveFraction_Typical) / (Math.PI / 6), 1 / 3d);
        }

        @Override
        public int getAxialHullDepth(int hullSpaces, double armorFraction, double driveFraction_Typical) {
            return Long.valueOf(Math.round((approxAxialDepth1(hullSpaces) + ((hullSpaces - getHullSpaces(hullSpaces)) * ((approxAxialDepth2(hullSpaces) - approxAxialDepth1(hullSpaces)) / 25d))))).intValue();
        }

        @Override
        public int getLateralHullDepth(int hullSpaces, double armorFraction, double driveFraction_Typical) {
            return getAxialHullDepth(hullSpaces, armorFraction, driveFraction_Typical);
        }

        @Override
        public int getLargestWeaponAllowed(final int hullSpaces) {
            return shipLookup.getSphereLargestWeaponMountable(hullSpaces);
        }

        @Override
        public int getLargestWeaponAllowed_Keel(int hullSpaces) {
            return getLargestWeaponAllowed(hullSpaces);
        }

        @Override
        public double getLargestMountSpaces_Option1(int hullSpaces) {
            return shipLookup.getSphereLargestMountSpaces_3(hullSpaces);
        }

        @Override
        public double getLargestMountSpaces_Option2(int hullSpaces) {
            return shipLookup.getSphereLargestMountSpaces_5(hullSpaces);
        }

        @Override
        public int getMaximumMountLines(int hullSpaces) {
            return shipLookup.getSphereMaximumMountLines(hullSpaces);
        }

        private int approxAxialDepth1(final int hullSpaces) {
            return shipLookup.getHullDepthSphere(hullSpaces);
        }

        private int approxAxialDepth2(final int hullSpaces) {
            return approxAxialDepth1(hullSpaces + 25);
        }
    },
    LONG_CYLINDER("Long Cylinder", 0.75d, 0.025d, 0.75d, Math.pow(10, -2.25), 0.7d, 1, 2, 0.6d, List.of(KEEL, SINGLE), 1.203d, 12) {
        @Override
        public int getImprovedAccesswayRequirement(final int shipSpaces) {
            return Math.round(shipSpaces / 100f);
        }
    },
    HEMISPHEROID("Hemispheroid", 1.75d, 0.215d, 1.375d, Math.pow(10, -0.3109), 1.1d, 3, 5, 1.0d, List.of(SINGLE, DOUBLE, TRIPLE, QUADRUPLE, QUINTUPLE, SEXTUPLE), 0.976d, 24) {
    },
    CONICAL("Conical", 1.375, 0.1125d, 1.1825d, Math.pow(10, -0.87), 1.05d, 1, 3, 0.9d, List.of(KEEL, SINGLE, DOUBLE, TRIPLE, QUADRUPLE), 1.038d, 23) {
    },
    ELLIPSOID("Ellipsoid", 1.25d, 0.075d, 1.125d, Math.pow(10, -1.05), 0.9d, 2, 4, 1.0d, List.of(SINGLE, DOUBLE, TRIPLE, QUADRUPLE, QUINTUPLE), 1.024d, 21) {
        @Override
        public double getHullLength(double hullSpaces, double armorFraction, double driveFraction_Typical) {
            return 2 * getHullDiameter(hullSpaces, armorFraction, driveFraction_Typical);
        }

        @Override
        public double getHullDiameter(double hullSpaces, double armorFraction, double driveFraction_Typical) {
            return Math.pow(3 * 100 * hullSpaces * (1 - armorFraction - driveFraction_Typical) / Math.PI, 1 / 3d);
        }

        @Override
        public double getShieldDiameter(double hullSpaces, double armorFraction, double driveFraction_Typical, double mastLength, double lanternDiameter) {
            final double hullDiameter = getHullDiameter(hullSpaces, armorFraction, driveFraction_Typical);
            final double value = Math.pow(hullDiameter, 2) / (hullDiameter + mastLength + 0.5 * lanternDiameter);
            return Math.pow(Math.pow(hullDiameter, 2) - Math.pow(value, 2), 0.5) * ((lanternDiameter / 2) / ((lanternDiameter / 2) + mastLength + hullDiameter - value));
        }

        @Override
        public double getMomentOfInertia(double hullSpaces, double armorFraction, double driveFraction_Typical, double driveFraction, double shipMass, double mastLength, double lanternDiameter, double mastStructuralMass, double mastArmorMass, double lanternMass, double driveArmorMass, double mastMass) {
            double hullLength = getHullLength(hullSpaces, armorFraction, driveFraction_Typical);
            return (1 - driveFraction) / 4 * shipMass * (Math.pow(hullLength, 2) * (0.37))
                    + (driveFraction) * (1 - driveFraction) * shipMass * Math.pow(hullLength / 2 + mastLength + lanternDiameter / 2, 2) + (1 / 3d) * (mastStructuralMass + mastArmorMass) * Math.pow(mastLength, 2) + (0.2 * (lanternMass + driveArmorMass) + mastMass) * Math.pow(lanternDiameter / 2, 2);
        }

        @Override
        public double getRollMoment(double armorFraction, double driveFraction, double shipMass, int shipSpaces, double driveMass, double driveArmorMass, double driveDiameter) {
            double rollModifier = Math.max(1 - armorFraction - driveFraction, 0.1);
            return Math.pow(rollModifier, 5 / 3d) * shipMass * Math.pow(shipSpaces * 100, 2 / 3d) * 0.087
                    + 0.05 * (driveMass + driveArmorMass) * Math.pow(driveDiameter, 2);
        }

        @Override
        public double getHullSurfaceArea(final double hullSpaces, final double armorFraction, final double driveFraction_Typical) {
            final double hullLength = getHullLength(hullSpaces, armorFraction, driveFraction_Typical);
            final double hullDiameter = getHullDiameter(hullSpaces, armorFraction, driveFraction_Typical);
            return 4 * Math.PI * Math.pow(2 * Math.pow(hullLength / 2, 1.6075) * Math.pow(hullDiameter / 2, 1.6075) / 3 + Math.pow(hullDiameter / 2, 2 * 1.6075) / 3, 1 / 1.6075);
        }

        @Override
        public double getFrontArmorArea(double hullSpaces, double armorFraction, double driveFraction_Typical) {
            return getHullSurfaceArea(hullSpaces, armorFraction, driveFraction_Typical) / 30;
        }

        @Override
        public double getLateralArmorArea(double hullSpaces, double armorFraction, double driveFraction_Typical) {
            return getHullSurfaceArea(hullSpaces, armorFraction, driveFraction_Typical) / 30;
        }

        @Override
        public double getRearArmorArea(double hullSpaces, double armorFraction, double driveFraction_Typical) {
            return getHullSurfaceArea(hullSpaces, armorFraction, driveFraction_Typical) / 30;
        }

        @Override
        public int getAxialHullDepth(final int hullSpaces, final double armorFraction, final double driveFraction_Typical) {
            return Long.valueOf(Math.round(SPHEROID.getAxialHullDepth(hullSpaces, armorFraction, driveFraction_Typical) * getHullLength(hullSpaces, armorFraction, driveFraction_Typical) / SPHEROID.getHullLength(hullSpaces, armorFraction, driveFraction_Typical))).intValue();
        }

        @Override
        public int getLateralHullDepth(int hullSpaces, double armorFraction, double driveFraction_Typical) {
            return Math.toIntExact(Math.round(SPHEROID.getLateralHullDepth(hullSpaces, armorFraction, driveFraction_Typical) * getHullDiameter(hullSpaces, armorFraction, driveFraction_Typical) / SPHEROID.getHullDiameter(hullSpaces, armorFraction, driveFraction_Typical)));
        }

        @Override
        public int getLargestWeaponAllowed(int hullSpaces) {
            return Long.valueOf(Math.round((SPHEROID.getLargestWeaponAllowed(hullSpaces) + CYLINDER.getLargestWeaponAllowed(hullSpaces)) / 2d)).intValue();
        }

        @Override
        public int getLargestWeaponAllowed_Keel(int hullSpaces) {
            return Long.valueOf(Math.round((SPHEROID.getLargestWeaponAllowed_Keel(hullSpaces) + CYLINDER.getLargestWeaponAllowed_Keel(hullSpaces)) / 2d)).intValue();
        }

        @Override
        public double getLargestMountSpaces_Option1(int hullSpaces) {
            return (SPHEROID.getLargestMountSpaces_Option1(hullSpaces) + CYLINDER.getLargestMountSpaces_Option1(hullSpaces)) / 2;
        }

        @Override
        public double getLargestMountSpaces_Option2(int hullSpaces) {
            return (SPHEROID.getLargestMountSpaces_Option2(hullSpaces) + CYLINDER.getLargestMountSpaces_Option2(hullSpaces)) / 2;
        }
    };

    private final String shapeName;
    private final double mastMassModifier;
    private final double thrusterModifier;
    private final double pivotModifier;
    private final double rollModifier;
    private final double hullCostModifier;
    private final int largestMountLines_Option1;
    private final int largestMountLines_Option2;
    private final double hullCostPerSpace;
    private final List<MountConfiguration> availableMountConfigurations;
    private final double weaponizableSpaceMultiplier;

    private final int secondaryMountFieldOfFire;



    private final int tertiaryMountFieldOfFire = 26;

    private static final ShipLookup shipLookup = new ShipLookup();

    Shape(String shapeName,
          double mastMassModifier,
          double thrusterModifier,
          double pivotModifier,
          double rollModifier,
          double hullCostModifier,
          int largestMountLines_Option1,
          int largestMountLines_Option2,
          double hullCostPerSpace,
          List<MountConfiguration> availableMountConfigurations,
          double weaponizableSpaceMultiplier,
          int secondaryMountFieldOfFire) {
        this.shapeName = shapeName;
        this.mastMassModifier = mastMassModifier;
        this.thrusterModifier = thrusterModifier;
        this.pivotModifier = pivotModifier;
        this.rollModifier = rollModifier;
        this.hullCostModifier = hullCostModifier;
        this.largestMountLines_Option1 = largestMountLines_Option1;
        this.largestMountLines_Option2 = largestMountLines_Option2;
        this.hullCostPerSpace = hullCostPerSpace;
        this.availableMountConfigurations = availableMountConfigurations;
        this.weaponizableSpaceMultiplier = weaponizableSpaceMultiplier;
        this.secondaryMountFieldOfFire = secondaryMountFieldOfFire;
    }

    public String getShapeName() {
        return shapeName;
    }

    public double getMastMassModifier() {
        return mastMassModifier;
    }

    double getThrusterModifier() {
        return thrusterModifier;
    }

    public double getPivotModifier() {
        return pivotModifier;
    }

    public double getRollModifier() {
        return rollModifier;
    }

    public double getHullCostModifier() {
        return hullCostModifier;
    }

    public int getLargestMountLines_Option1() {
        return largestMountLines_Option1;
    }

    public int getLargestMountLines_Option2() {
        return largestMountLines_Option2;
    }

    public double getHullCostPerSpace() {
        return hullCostPerSpace;
    }

    public List<MountConfiguration> getAvailableMountConfigurations() {
        return availableMountConfigurations;
    }

    public double getWeaponizableSpaceMultiplier() {
        return weaponizableSpaceMultiplier;
    }

    public int getSecondaryMountFieldOfFire() {
        return secondaryMountFieldOfFire;
    }

    public int getTertiaryMountFieldOfFire() {
        return tertiaryMountFieldOfFire;
    }

    public double getHullLength(double hullSpaces, double armorFraction, double driveFraction_Typical) {
        return 0;
    }

    public double getHullDiameter(double hullSpaces, double armorFraction, double driveFraction_Typical) {
        return 0;
    }

    public double getShieldDiameter(final double hullSpaces, final double armorFraction, final double driveFraction_Typical, double mastLength, final double lanternDiameter) {
        return 0;
    }

    public double getMomentOfInertia(double hullSpaces, double armorFraction, double driveFraction_Typical, double driveFraction, double shipMass, double mastLength, double lanternDiameter, double mastStructuralMass, double mastArmorMass, double lanternMass, double driveArmorMass, double mastMass) {
        // TODO: Simplify this signature.
        return 0;
    }

    public double getRollMoment(double armorFraction, double driveFraction, double shipMass, int shipSpaces, double driveMass, double driveArmorMass, double driveDiameter) {
        return 0;
    }

    public int getImprovedAccesswayRequirement(final int shipSpaces) {
        return Math.round(shipSpaces / 50f);
    }

    public double getHullSurfaceArea(final double hullSpaces, final double armorFraction, final double driveFraction_Typical) {
        return 0;
    }

    public double getFrontArmorArea(final double hullSpaces, final double armorFraction, final double driveFraction_Typical) {
        return 0;
    }

    public double getLateralArmorArea(final double hullSpaces, final double armorFraction, final double driveFraction_Typical) {
        return 0;
    }

    public double getRearArmorArea(final double hullSpaces, final double armorFraction, final double driveFraction_Typical) {
        return 0;
    }

    public int getAxialHullDepth(final int hullSpaces, final double armorFraction, final double driveFraction_Typical) {
        return 0;
    }

    public int getLateralHullDepth(final int hullSpaces, final double armorFraction, final double driveFraction_Typical) {
        return 0;
    }

    double getHullSpaces(final int hullSpaces) {
        return shipLookup.getHullSpaces(hullSpaces);
    }

    public int getLargestWeaponAllowed(final int hullSpaces) {
        return 0;
    }

    public int getLargestWeaponAllowed_Keel(final int hullSpaces) {
        return 0;
    }

    public double getLargestMountSpaces_Option1(final int hullSpaces) {
        return 0;
    }

    public double getLargestMountSpaces_Option2(final int hullSpaces) {
        return 0;
    }

    public int getMaximumMountLines(final int hullSpaces) {
        return 0;
    }

    public double getTotalWeaponSpaces(final int hullSpaces) {
        return shipLookup.getTotalWeaponSpaces(hullSpaces);
    }
}
