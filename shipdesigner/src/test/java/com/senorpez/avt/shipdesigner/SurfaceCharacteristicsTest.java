package com.senorpez.avt.shipdesigner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SurfaceCharacteristicsTest {
    private SurfaceCharacteristics instance;
    private final double tolerance = 0.001d;

    @BeforeEach
    void setUp() {
        instance = new SurfaceCharacteristics();
    }

    @Test
    void getHullSurfaceArea() {
        double expectedValue = 1447.62138d;

        assertEquals(expectedValue, instance.getHullSurfaceArea(), tolerance);
    }

    @Test
    void getFrontArmorArea() {
        double expectedValue = 48.25405d;

        assertEquals(expectedValue, instance.getFrontArmorArea(), tolerance);
    }

    @Test
    void getLateralArmorArea() {
        double expectedValue = 48.25405d;

        assertEquals(expectedValue, instance.getLateralArmorArea(), tolerance);
    }

    @Test
    void getRearArmorArea() {
        double expectedValue = 48.25405d;

        assertEquals(expectedValue, instance.getRearArmorArea(), tolerance);
    }

    @Test
    void getAxialHullDepth() {
        int expectedValue = 17;

        assertEquals(expectedValue, instance.getAxialHullDepth());
    }

    @Test
    void getLateralHullDepth() {
        int expectedValue = 9;

        assertEquals(expectedValue, instance.getLateralHullDepth());
    }

    @Test
    void getDriveHullDepth() {
        int expectedValue = 2;

        assertEquals(expectedValue, instance.getDriveHullDepth());
    }

    @Test
    void getMastHullDepth() {
        int expectedValue = 2;

        assertEquals(expectedValue, instance.getMastHullDepth());
    }
}
