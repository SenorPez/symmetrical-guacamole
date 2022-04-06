package com.senorpez.avt.shipdesigner.systems.internal;

import com.senorpez.avt.shipdesigner.Ship;
import com.senorpez.avt.shipdesigner.systems.ProductionLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FuelTest {
    @Mock
    Ship ship;

    private Fuel instance;
    private final double tolerance = 0.001d;

    @BeforeEach
    void setUp() {
        instance = new Fuel(
                ship,
                11,
                ProductionLevel.STANDARD,
                2
        );
    }

    @Test
    void getName() {
        String expectedValue = "Fuel";
        assertEquals(expectedValue, instance.getName());
    }

    @Test
    void getQuantity() {
        int expectedValue = 11;
        assertEquals(expectedValue, instance.getQuantity());
    }

    @Test
    void getFuelUnits() {
        when(ship.getHullSize()).thenReturn(106);
        when(ship.getDriveGeneration()).thenReturn(2.3);
        int expectedValue = 50;
        assertEquals(expectedValue, instance.getFuelUnits());
    }

    @Test
    void getBasicSpacesUsed() {
        int expectedValue = 11;
        assertEquals(expectedValue, instance.getBasicSpacesUsed());
    }

    @Test
    void getShrinkEnhancement() {
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getShrinkEnhancement());
    }

    @Test
    void getSpacesPerSystem() {
        int expectedValue = 1;
        assertEquals(expectedValue, instance.getSpacesPerSystem());
    }

    @Test
    void getCostPerSpace() {
        double expectedValue = 0.25d;
        assertEquals(expectedValue, instance.getCostPerSpace(), tolerance);
    }

    @Test
    void getCrewRequiredPerSpace() {
        double expectedValue = 0d;
        assertEquals(expectedValue, instance.getCrewRequiredPerSpace(), tolerance);
    }

    @Test
    void getActualSpacesUsed() {
        int expectedValue = 11;
        assertEquals(expectedValue, instance.getActualSpacesUsed());
    }

    @Test
    void getBaseCost() {
        int expectedValue = 3;
        assertEquals(expectedValue, instance.getBaseCost());
    }

    @Test
    void getEnhancedCost() {
        int expectedValue = 3;
        assertEquals(expectedValue, instance.getEnhancedCost());
    }

    @Test
    void getCrewRequirement() {
        double expectedValue = 0d;
        assertEquals(expectedValue, instance.getCrewRequirement(), tolerance);
    }

    @Test
    void getArmorLevel() {
        int expectedValue = 2;
        assertEquals(expectedValue, instance.getArmorLevel());
    }

    @Test
    void getArmorPointsUsed() {
        double expectedValue = 11.00000d;
        assertEquals(expectedValue, instance.getArmorPointsUsed(), tolerance);
    }

    @Test
    void getDuelCost() {
        int expectedValue = 3;
        assertEquals(expectedValue, instance.getDuelCost());
    }

    @Test
    void getProductionLevel() {
        ProductionLevel expectedValue = ProductionLevel.STANDARD;
        assertEquals(expectedValue, instance.getProductionLevel());
    }

    @Test
    void getEconomicCost() {
        int expectedValue = 3;
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
