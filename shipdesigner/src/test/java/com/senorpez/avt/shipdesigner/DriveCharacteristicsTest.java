package com.senorpez.avt.shipdesigner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DriveCharacteristicsTest {
    @Mock
    ShipCharacteristics shipCharacteristics;
    @Mock
    MassCharacteristics massCharacteristics;

    private DriveCharacteristics instance;
    private final double tolerance = 0.001d;

    @BeforeEach
    void setUp() {
        instance = new DriveCharacteristics(shipCharacteristics, massCharacteristics);
    }

    @Test
    void getDriveOutput_Combat() {
        when(massCharacteristics.getNewCombatPower()).thenReturn(1.98734d);
        double expectedValue = 1.98734d;

        assertEquals(expectedValue, instance.getDriveOutput_Combat(), tolerance);
    }

    @Test
    void getDriveFlux() {
        when(massCharacteristics.getNewCombatPower()).thenReturn(1.98734d);
        double expectedValue = 2.60497d;

        assertEquals(expectedValue, instance.getDriveFlux(), tolerance);
    }

    @Test
    void getDriveAcceleration_Transit() {
        when(shipCharacteristics.getShipAcceleration()).thenReturn(2.75d);
        double expectedValue = 55.00000d;

        assertEquals(expectedValue, instance.getDriveAcceleration_Transit(), tolerance);
    }

    @Test
    void getDriveOutput_Transit() {
        double expectedValue = 0.36926d;

        assertEquals(expectedValue, instance.getDriveOutput_Transit(), tolerance);
    }

    @Test
    void getPivotThrust() {
        double expectedValue = 177.04301d;

        assertEquals(expectedValue, instance.getPivotThrust(), tolerance);
    }

    @Test
    void getPivotAcceleration() {
        double expectedValue = 11.35718d;

        assertEquals(expectedValue, instance.getPivotAcceleration(), tolerance);
    }

    @Test
    void getPivotMode() {
        char expectedValue = 'H';

        assertEquals(expectedValue, instance.getPivotMode(), tolerance);
    }

    @Test
    void getRollAcceleration() {
        double expectedValue = 55.13342d;

        assertEquals(expectedValue, instance.getRollAcceleration(), tolerance);
    }

    @Test
    void getRollMode() {
        char expectedValue = 'C';

        assertEquals(expectedValue, instance.getRollMode(), tolerance);
    }

    @Test
    void getFuelDensity() {
        double expectedValue = 11.27273d;

        assertEquals(expectedValue, instance.getFuelDensity(), tolerance);
    }

    @Test
    void getEngineDamage() {
        int expectedValue = 12;

        assertEquals(expectedValue, instance.getEngineDamage(), tolerance);
    }

    @Test
    void getMaxIntegratedReactor() {
        int expectedValue = 1;

        assertEquals(expectedValue, instance.getMaxIntegratedReactor(), tolerance);
    }

    @Test
    void getImprovedAccesswayRequirement() {
        int expectedValue = 1;

        assertEquals(expectedValue, instance.getImprovedAccesswayRequirement(), tolerance);
    }
}
