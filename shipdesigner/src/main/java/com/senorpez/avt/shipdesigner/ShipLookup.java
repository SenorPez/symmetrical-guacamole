package com.senorpez.avt.shipdesigner;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

record ShipLookup() {
    final static HashMap<Double, ShipData> data = new HashMap<>();
    static {
        data.put(25d,    new ShipData(1, 2, 2, 2, 2, 2, 2, 2, 0.08d, 0.060d, 0.080d, 0.08d, 0.200d, 7,  1, 6));
        data.put(37.5d,  new ShipData(1, 2, 2, 2, 2, 2, 2, 2, 0.06d, 0.048d, 0.070d, 0.06d, 0.190d, 10, 2, 8));
        data.put(50d,    new ShipData(1, 2, 2, 2, 2, 2, 2, 2, 0.04d, 0.035d, 0.060d, 0.04d, 0.180d, 13, 3, 10));
        data.put(75d,    new ShipData(1, 2, 2, 3, 2, 2, 2, 2, 0.04d, 0.035d, 0.055d, 0.04d, 0.185d, 18, 4, 14));
        data.put(100d,   new ShipData(2, 3, 3, 3, 2, 2, 2, 2, 0.04d, 0.035d, 0.050d, 0.04d, 0.170d, 23, 5, 17));
    }

    int getSphereLargestWeaponMountable(final int hullSpaces) {
        return getShipData(hullSpaces)
                .map(ShipData::SphereLargestWeaponMountable)
                .orElse(0);
    }

    int getCylinderLargestWeaponMountable_Single(final int hullSpaces) {
        return getShipData(hullSpaces)
                .map(ShipData::CylinderLargestWeaponMountable_Single)
                .orElse(0);
    }

    int getCylinderLargestWeaponMountable_Double(final int hullSpaces) {
        return getShipData(hullSpaces)
                .map(ShipData::CylinderLargestWeaponMountable_Double)
                .orElse(0);
    }

    int getCylinderLargestWeaponMountable_Keel(final int hullSpaces) {
        return getShipData(hullSpaces)
                .map(ShipData::CylinderLargestWeaponMountable_Keel)
                .orElse(0);
    }

    int getSphereMaximumMountLines(final int hullSpaces) {
        return getShipData(hullSpaces)
                .map(ShipData::SphereMaximumMountLines)
                .orElse(0);
    }

    int getCylinderMaximumMountLines_Single(final int hullSpaces) {
        return getShipData(hullSpaces)
                .map(ShipData::CylinderMaximumMountLines_Single)
                .orElse(0);
    }

    int getCylinderMaximumMountLines_Double(final int hullSpaces) {
        return getShipData(hullSpaces)
                .map(ShipData::CylinderMaximumMountLines_Double)
                .orElse(0);
    }

    int getCylinderMaximumMountLines_Keel(final int hullSpaces) {
        return getShipData(hullSpaces)
                .map(ShipData::CylinderMaximumMountLines_Keel)
                .orElse(0);
    }

    double getSphereLargestMountSpaces_3(final int hullSpaces) {
        return getShipData(hullSpaces)
                .map(ShipData::SphereLargestMountSpaces_3)
                .orElse(0d);
    }

    double getSphereLargestMountSpaces_5(final int hullSpaces) {
        return getShipData(hullSpaces)
                .map(ShipData::SphereLargestMountSpaces_5)
                .orElse(0d);
    }

    double getCylinderLargestMountSpaces_1(final int hullSpaces) {
        return getShipData(hullSpaces)
                .map(ShipData::CylinderLargestMountSpaces_1)
                .orElse(0d);
    }

    double getCylinderLargestMountSpaces_2(final int hullSpaces) {
        return getShipData(hullSpaces)
                .map(ShipData::CylinderLargestMountSpaces_2)
                .orElse(0d);
    }

    double getTotalWeaponSpaces(final int hullSpaces) {
        return getShipData(hullSpaces)
                .map(ShipData::TotalWeaponSpaces)
                .orElse(0d);
    }

    int getHullDepthAxial(final double hullSpaces) {
        return getShipData(hullSpaces)
                .map(ShipData::HullDepthAxial)
                .orElse(0);
    }

    int getHullDepthLateral(final double hullSpaces) {
        return getShipData(hullSpaces)
                .map(ShipData::HullDepthLateral)
                .orElse(0);
    }

    int getHullDepthSphere(final double hullSpaces) {
        return getShipData(hullSpaces)
                .map(ShipData::HullDepthSphere)
                .orElse(0);
    }

    private Optional<ShipData> getShipData(final double hullSpaces) {
        return data.entrySet()
                .stream()
                .min(Comparator.comparingDouble(d -> Math.abs(d.getKey() - hullSpaces)))
                .map(Map.Entry::getValue);
    }

    private record ShipData(
            int SphereLargestWeaponMountable,
            int CylinderLargestWeaponMountable_Single,
            int CylinderLargestWeaponMountable_Double,
            int CylinderLargestWeaponMountable_Keel,

            int SphereMaximumMountLines,
            int CylinderMaximumMountLines_Single,
            int CylinderMaximumMountLines_Double,
            int CylinderMaximumMountLines_Keel,

            double SphereLargestMountSpaces_3,
            double SphereLargestMountSpaces_5,
            double CylinderLargestMountSpaces_1,
            double CylinderLargestMountSpaces_2,

            double TotalWeaponSpaces,

            int HullDepthAxial,
            int HullDepthLateral,
            int HullDepthSphere
    ) { }
}
