package com.senorpez.avt.shipdesigner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShipCharacteristicsTest {
    @Mock
    Ship ship;

    private ShipCharacteristics instance;
    private final double tolerance = 0.001d;

    @BeforeEach
    void setUp() {
        instance = spy(new ShipCharacteristics(ship));
    }

    @Test
    void getShipSpaces() {
        when(ship.getHullSize()).thenReturn(55);
        int expectedValue = 55;

        assertEquals(expectedValue, instance.getShipSpaces());
    }

    @Test
    void getShipMass() {
        doReturn(55).when(instance).getShipSpaces();
        int expectedValue = 1375;

        assertEquals(expectedValue, instance.getShipMass());
    }

    @Test
    void getShipThrust() {
        when(ship.getMaximumThrust()).thenReturn(11.0d);
        double expectedValue = 11.0d;

        assertEquals(expectedValue, instance.getShipThrust(), tolerance);
    }

    @Test
    void getShipAcceleration() {
        doReturn(11.0d).when(instance).getShipThrust();
        double expectedValue = 2.75d;

        assertEquals(expectedValue, instance.getShipAcceleration(), tolerance);
    }

    @Test
    void getHullShape() {
        when(ship.getShape()).thenReturn(Shape.ELLIPSOID);
        Shape expectedValue = Shape.ELLIPSOID;

        assertEquals(expectedValue, instance.getHullShape());
    }

    @Test
    void getDriveGeneration() {
        when(ship.getDriveGeneration()).thenReturn(3.1);
        double expectedValue = 3.1d;

        assertEquals(expectedValue, instance.getDriveGeneration(), tolerance);
    }

    @Test
    void getHullArmor() {
        when(ship.getHullArmor()).thenReturn(1);
        int expectedValue = 1;

        assertEquals(expectedValue, instance.getHullArmor());
    }

    @Test
    void getMastArmor() {
        when(ship.getMastArmor()).thenReturn(1);
        int expectedValue = 1;

        assertEquals(expectedValue, instance.getMastArmor());
    }

    @Test
    void getDriveArmor() {
        when(ship.getDriveArmor()).thenReturn(2);
        int expectedValue = 2;

        assertEquals(expectedValue, instance.getDriveArmor());
    }

    @Test
    void getArmorShrink() {
        when(ship.getArmorShrink()).thenReturn(0);
        int expectedValue = 0;

        assertEquals(expectedValue, instance.getArmorShrink());
    }
}
