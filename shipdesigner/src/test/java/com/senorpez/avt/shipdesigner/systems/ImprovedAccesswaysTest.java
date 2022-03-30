package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;
import com.senorpez.avt.shipdesigner.characteristics.DriveCharacteristics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ImprovedAccesswaysTest {
    @Mock
    Ship ship;
    @Mock
    DriveCharacteristics driveCharacteristics;

    private ImprovedAccessways instance;
    private final double tolerance = 0.001d;

    @BeforeEach
    void setUp() {
        instance = new ImprovedAccessways(
                ship,
                false,
                driveCharacteristics
        );
    }

    @Test
    void getName() {
        String expectedValue = "No Improved Accessways";
        assertEquals(expectedValue, instance.getName());

        instance = instance.setHasImprovedAccessways(true);
        expectedValue = "Improved Accessways";
        assertEquals(expectedValue, instance.getName());
    }


    @Test
    void getBasicSpacesUsed() {
        when(driveCharacteristics.getImprovedAccesswayRequirement()).thenReturn(1);
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getBasicSpacesUsed());

        instance = instance.setHasImprovedAccessways(true);
        expectedValue = 1;
        assertEquals(expectedValue, instance.getBasicSpacesUsed());
    }

    @Test
    void getSpacesPerSystem() {
        when(driveCharacteristics.getImprovedAccesswayRequirement()).thenReturn(1);
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getSpacesPerSystem());

        instance = instance.setHasImprovedAccessways(true);
        expectedValue = 1;
        assertEquals(expectedValue, instance.getSpacesPerSystem());
    }

    @Test
    void getActualSpacesUsed() {
        when(driveCharacteristics.getImprovedAccesswayRequirement()).thenReturn(1);
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getActualSpacesUsed());

        instance = instance.setHasImprovedAccessways(true);
        expectedValue = 1;
        assertEquals(expectedValue, instance.getActualSpacesUsed());
    }

    @Test
    void getBaseCost() {
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getBaseCost());
    }

    @Test
    void getEnhancedCost() {
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getEnhancedCost());
    }

    @Test
    void getCrewRequirement() {
        double expectedValue = 0;
        assertEquals(expectedValue, instance.getCrewRequirement(), tolerance);
    }

    @Test
    void getDuelCost() {
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getDuelCost());
    }

    @Test
    void getEconomicCost() {
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getEconomicCost());
    }

    @Test
    void getMaintenanceRate() {
        double expectedValue = 0d;
        assertEquals(expectedValue, instance.getMaintenanceRate(), tolerance);
    }

    @Test
    void getMaintenanceCostPerYear() {
        double expectedValue = 0d;
        assertEquals(expectedValue, instance.getMaintenanceCostPerYear(), tolerance);
    }
}
