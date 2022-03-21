package com.senorpez.avt.shipdesigner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DimensionsTest {
    @Mock
    ShipCharacteristics shipCharacteristics;
    @Mock
    MassCharacteristics massCharacteristics;

    private Dimensions instance;
    private final double tolerance = 0.001d;

    @BeforeEach
    void setUp() {
        instance = spy(new Dimensions(shipCharacteristics, massCharacteristics));
    }

    @Test
    void getHullVolume() {
        when(massCharacteristics.getHullSpaces()).thenReturn(39.96318d);
        double expectedValue = 3996.31795d;

        assertEquals(expectedValue, instance.getHullVolume(), tolerance);
    }

    @Test
    void getHullLength() {
        when(shipCharacteristics.getHullShape()).thenReturn(Shape.ELLIPSOID);
        when(shipCharacteristics.getShipSpaces()).thenReturn(55);
        when(massCharacteristics.getArmorFraction()).thenReturn(0.0181818d);
        when(massCharacteristics.getDriveFraction_Typical()).thenReturn(0.1382455d);
        double expectedValue = 32.84850d;

        assertEquals(expectedValue, instance.getHullLength(), tolerance);
    }

    @Test
    void getHullDiameter() {
        when(shipCharacteristics.getHullShape()).thenReturn(Shape.ELLIPSOID);
        when(shipCharacteristics.getShipSpaces()).thenReturn(55);
        when(massCharacteristics.getArmorFraction()).thenReturn(0.0181818d);
        when(massCharacteristics.getDriveFraction_Typical()).thenReturn(0.1382455d);
        double expectedValue = 16.42425d;

        assertEquals(expectedValue, instance.getHullDiameter(), tolerance);
    }

    @Test
    void getMastLength() {
        when(massCharacteristics.getMastLength()).thenReturn(27.43900d);
        double expectedValue = 27.43900d;

        assertEquals(expectedValue, instance.getMastLength(), tolerance);
    }

    @Test
    void getMastDiameter() {
        when(massCharacteristics.getMastLength()).thenReturn(27.43900d);
        double expectedValue = 1.82927d;

        assertEquals(expectedValue, instance.getMastDiameter(), tolerance);
    }

    @Test
    void getShieldDiameter() {
        when(shipCharacteristics.getHullShape()).thenReturn(Shape.ELLIPSOID);
        when(shipCharacteristics.getShipSpaces()).thenReturn(55);
        when(massCharacteristics.getArmorFraction()).thenReturn(0.0181818d);
        when(massCharacteristics.getDriveFraction_Typical()).thenReturn(0.1382455d);
        when(massCharacteristics.getMastLength()).thenReturn(27.43900d);
        when(massCharacteristics.getDriveDiameter()).thenReturn(22.00000d);
        double expectedValue = 3.45132d;

        assertEquals(expectedValue, instance.getShieldDiameter(), tolerance);
    }

    @Test
    void getShieldThickness() {
        when(massCharacteristics.getShieldThickness()).thenReturn(2.87666d);
        double expectedValue = 2.87666d;

        assertEquals(expectedValue, instance.getShieldThickness(), tolerance);
    }

    @Test
    void getDriveDiameter() {
        when(massCharacteristics.getDriveDiameter()).thenReturn(22.00000d);
        double expectedValue = 22.00000d;

        assertEquals(expectedValue, instance.getDriveDiameter(), tolerance);
    }

    @Test
    void getTotalShipLength() {
        when(shipCharacteristics.getHullShape()).thenReturn(Shape.ELLIPSOID);
        when(shipCharacteristics.getShipSpaces()).thenReturn(55);
        when(massCharacteristics.getArmorFraction()).thenReturn(0.0181818d);
        when(massCharacteristics.getDriveFraction_Typical()).thenReturn(0.1382455d);
        when(massCharacteristics.getMastLength()).thenReturn(27.43900d);
        when(massCharacteristics.getDriveDiameter()).thenReturn(22.00000d);
        double expectedValue = 71.28750d;

        assertEquals(expectedValue, instance.getTotalShipLength(), tolerance);
    }

    @Test
    void getTotalBoxes() {
        when(shipCharacteristics.getShipSpaces()).thenReturn(55);
        when(massCharacteristics.getTotalShipArmorSpaces()).thenReturn(4.35644d);
        double expectedValue = 50.64356d;

        assertEquals(expectedValue, instance.getTotalBoxes(), tolerance);
    }

    @Test
    void getStructuralIntegrity() {
        when(shipCharacteristics.getShipSpaces()).thenReturn(55);
        double expectedValue = 6.21368d;

        assertEquals(expectedValue, instance.getStructuralIntegrity(), tolerance);
    }

    @Test
    void getArmorPointsAvailable() {
        when(shipCharacteristics.getArmorShrink()).thenReturn(0);
        when(massCharacteristics.getHullArmorSpaces()).thenReturn(1);
        long expectedValue = 5L;

        assertEquals(expectedValue, instance.getArmorPointsAvailable());
    }

    @Test
    void getHullDuelCost() {
        when(shipCharacteristics.getShipSpaces()).thenReturn(55);
        when(shipCharacteristics.getHullShape()).thenReturn(Shape.ELLIPSOID);
        double expectedValue = 49.50000d;

        assertEquals(expectedValue, instance.getHullDuelCost(), tolerance);
    }

    @Test
    void fullResultsTest() {
        when(massCharacteristics.getHullSpaces()).thenReturn(39.96318d);
        when(shipCharacteristics.getHullShape()).thenReturn(Shape.ELLIPSOID);
        when(shipCharacteristics.getShipSpaces()).thenReturn(55);
        when(massCharacteristics.getArmorFraction()).thenReturn(0.0181818d);
        when(massCharacteristics.getDriveFraction_Typical()).thenReturn(0.1382455d);
        when(massCharacteristics.getMastLength()).thenReturn(27.43900d);
        when(massCharacteristics.getDriveDiameter()).thenReturn(22.00000d);
        when(massCharacteristics.getShieldThickness()).thenReturn(2.87666d);
        when(massCharacteristics.getTotalShipArmorSpaces()).thenReturn(4.35644d);
        when(shipCharacteristics.getArmorShrink()).thenReturn(0);
        when(massCharacteristics.getHullArmorSpaces()).thenReturn(1);

        instance = new Dimensions(shipCharacteristics, massCharacteristics);

        assertEquals(3996.31795d, instance.getHullVolume(), tolerance);
        assertEquals(32.84850d, instance.getHullLength(), tolerance);
        assertEquals(16.42425d, instance.getHullDiameter(), tolerance);
        assertEquals(27.43900d, instance.getMastLength(), tolerance);
        assertEquals(1.82927d, instance.getMastDiameter(), tolerance);
        assertEquals(3.45132d, instance.getShieldDiameter(), tolerance);
        assertEquals(2.87666d, instance.getShieldThickness(), tolerance);
        assertEquals(22.00000d, instance.getDriveDiameter(), tolerance);
        assertEquals(71.28750d, instance.getTotalShipLength(), tolerance);
        assertEquals(50.64356d, instance.getTotalBoxes(), tolerance);
        assertEquals(6.21368d, instance.getStructuralIntegrity(), tolerance);
        assertEquals(5L, instance.getArmorPointsAvailable(), tolerance);
        assertEquals(49.50000d, instance.getHullDuelCost(), tolerance);
    }
}
