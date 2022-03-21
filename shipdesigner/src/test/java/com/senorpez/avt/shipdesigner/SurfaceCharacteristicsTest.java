package com.senorpez.avt.shipdesigner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SurfaceCharacteristicsTest {
    @Mock
    ShipCharacteristics shipCharacteristics;
    @Mock
    MassCharacteristics massCharacteristics;

    private SurfaceCharacteristics instance;
    private final double tolerance = 0.001d;

    @BeforeEach
    void setUp() {
        instance = new SurfaceCharacteristics(shipCharacteristics, massCharacteristics);
    }

    @Test
    void getHullSurfaceArea() {
        when(shipCharacteristics.getHullShape()).thenReturn(Shape.ELLIPSOID);
        when(shipCharacteristics.getShipSpaces()).thenReturn(55);
        when(massCharacteristics.getArmorFraction()).thenReturn(0.0181818d);
        when(massCharacteristics.getDriveFraction_Typical()).thenReturn(0.1382455d);
        double expectedValue = 1447.62138d;

        assertEquals(expectedValue, instance.getHullSurfaceArea(), tolerance);
    }

    @Test
    void getFrontArmorArea() {
        when(shipCharacteristics.getHullShape()).thenReturn(Shape.ELLIPSOID);
        when(shipCharacteristics.getShipSpaces()).thenReturn(55);
        when(massCharacteristics.getArmorFraction()).thenReturn(0.0181818d);
        when(massCharacteristics.getDriveFraction_Typical()).thenReturn(0.1382455d);
        double expectedValue = 48.25405d;

        assertEquals(expectedValue, instance.getFrontArmorArea(), tolerance);
    }

    @Test
    void getLateralArmorArea() {
        when(shipCharacteristics.getHullShape()).thenReturn(Shape.ELLIPSOID);
        when(shipCharacteristics.getShipSpaces()).thenReturn(55);
        when(massCharacteristics.getArmorFraction()).thenReturn(0.0181818d);
        when(massCharacteristics.getDriveFraction_Typical()).thenReturn(0.1382455d);
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
