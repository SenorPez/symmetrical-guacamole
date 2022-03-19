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

    @Test
    void getShieldDiameter() {
        instance = Shape.ELLIPSOID;
        double expectedValue = 3.20935d;

        assertEquals(expectedValue, instance.getShieldDiameter(
                55,
                0.0181811d,
                0.1382455d,
                31.21665d,
                22.00000d
        ), tolerance);
    }

    @Test
    void getMomentOfInertia() {
        instance = Shape.ELLIPSOID;
        double expectedValue = 1.01926e6d;

        assertEquals(expectedValue, instance.getMomentOfInertia(
                55,
                0.0181811d,
                0.1382455d,
                0.2525739d,
                1375,
                31.21665d,
                22.00000d,
                16.44100d,
                10.20473d,
                198.73386d,
                76.02654d,
                72.52874d
        ), tolerance * 1e4);
    }
}
