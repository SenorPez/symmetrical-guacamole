package com.senorpez.avt.shipdesigner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DimensionsTest {
    @Mock
    MassCharacteristics massCharacteristics;

    private Dimensions instance;
    private final double tolerance = 0.001d;

    @BeforeEach
    void setUp() {
        instance = new Dimensions(massCharacteristics);
    }

    @Test
    void getHullVolume() {
        when(massCharacteristics.getHullSpaces()).thenReturn(39.96318d);
        double expectedValue = 3996.31795d;

        assertEquals(expectedValue, instance.getHullVolume(), tolerance);
    }

    @Test
    void getHullLength() {
        double expectedValue = 32.84850d;

        assertEquals(expectedValue, instance.getHullLength(), tolerance);
    }

    @Test
    void getHullDiameter() {
        double expectedValue = 16.42425d;

        assertEquals(expectedValue, instance.getHullDiameter(), tolerance);
    }

    @Test
    void getMastLength() {
        double expectedValue = 27.43900d;

        assertEquals(expectedValue, instance.getMastLength(), tolerance);
    }

    @Test
    void getMastDiameter() {
        double expectedValue = 1.82927d;

        assertEquals(expectedValue, instance.getMastDiameter(), tolerance);
    }

    @Test
    void getShieldDiameter() {
        double expectedValue = 3.45132d;

        assertEquals(expectedValue, instance.getShieldDiameter(), tolerance);
    }

    @Test
    void getShieldThickness() {
        double expectedValue = 2.87666d;

        assertEquals(expectedValue, instance.getShieldThickness(), tolerance);
    }

    @Test
    void getDriveDiameter() {
        double expectedValue = 22.00000d;

        assertEquals(expectedValue, instance.getDriveDiameter(), tolerance);
    }

    @Test
    void getTotalShipLength() {
        double expectedValue = 71.28750d;

        assertEquals(expectedValue, instance.getTotalShipLength(), tolerance);
    }

    @Test
    void getTotalBoxes() {
        double expectedValue = 50.64356d;

        assertEquals(expectedValue, instance.getTotalBoxes(), tolerance);
    }

    @Test
    void getStructuralIntegrity() {
        double expectedValue = 6.21368d;

        assertEquals(expectedValue, instance.getStructuralIntegrity(), tolerance);
    }

    @Test
    void getArmorPointsAvailable() {
        int expectedValue = 5;

        assertEquals(expectedValue, instance.getArmorPointsAvailable());
    }

    @Test
    void getHullDuelCost() {
        double expectedValue = 49.50000d;

        assertEquals(expectedValue, instance.getHullDuelCost(), tolerance);
    }
}
