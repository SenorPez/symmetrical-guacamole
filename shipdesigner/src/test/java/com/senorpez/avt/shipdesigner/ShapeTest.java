package com.senorpez.avt.shipdesigner;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShapeTest {
    private Shape instance;
    final double tolerance = 0.001d;

    @Test
    void getHullLength() {
        instance = Shape.ELLIPSOID;
        double expectedValue = 32.84850d;

        assertEquals(expectedValue, instance.getHullLength(
                55,
                0.0181811d,
                0.1382455d
        ), tolerance);
    }

    @Test
    void getHullDiameter() {
        instance = Shape.ELLIPSOID;
        double expectedValue = 16.42425d;

        assertEquals(expectedValue, instance.getHullDiameter(
                55,
                0.0181811d,
                0.1382455d
        ), tolerance);
    }
}
