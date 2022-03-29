package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HullTest {
    @Mock
    Ship ship;

    private Hull instance;
    private final double tolerance = 0.001d;

    @BeforeEach
    void setUp() {
        instance = new Hull(ship, 0, ProductionLevel.STANDARD);
    }

    @Test
    void getName() {
        String expectedValue = "Hull";
        assertEquals(expectedValue, instance.getName());
    }

    @Test
    void getSpacesPerSystem() {
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getSpacesPerSystem());
    }

    @Test
    void getBasicSpacesUsed() {
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getBasicSpacesUsed());
    }

    @Test
    void getActualSpacesUsed() {
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getActualSpacesUsed());
    }

    @Test
    void getCostPerSpace() {
        int expectedValue = 1;
        assertEquals(expectedValue, instance.getCostPerSpace());
    }

    @Test
    void getCrewRequiredPerSpace() {
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getCrewRequiredPerSpace());
    }

    @Test
    void getProductionLevel() {
        ProductionLevel expectedValue = ProductionLevel.STANDARD;
        assertEquals(expectedValue, instance.getProductionLevel());
    }

    @Test
    void getMaintenanceRate() {
        double expectedValue = 0.15d;
        assertEquals(expectedValue, instance.getMaintenanceRate());
    }

    @Test
    void getBaseCost() {
        when(ship.getHullSize()).thenReturn(55);
        int expectedValue = 55;
        assertEquals(expectedValue, instance.getBaseCost());
    }

    @Test
    void getEnhancedCost() {
        when(ship.getHullSize()).thenReturn(55);

        instance.setShrinkEnhancement(0);
        int expectedValue = 55;
        assertEquals(expectedValue, instance.getEnhancedCost());

        instance.setShrinkEnhancement(3);
        expectedValue = 82;
        assertEquals(expectedValue, instance.getEnhancedCost());
    }

    @Test
    void getCrewRequired() {
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getCrewRequired());
    }

    @Test
    void getDuelCost() {
        when(ship.getHullSize()).thenReturn(55);
        int expectedValue = 55;
        assertEquals(expectedValue, instance.getDuelCost());
    }

    @Test
    void getEconomicCost() {
        when(ship.getHullSize()).thenReturn(55);

        instance.setProductionLevel(ProductionLevel.STANDARD);
        int expectedValue = 55;
        assertEquals(expectedValue, instance.getEconomicCost());

        instance.setProductionLevel(ProductionLevel.PROTOTYPE);
        expectedValue = 220;
        assertEquals(expectedValue, instance.getEconomicCost());
    }

    @Test
    void getMaintenanceCostPerYear() {
        when(ship.getHullSize()).thenReturn(55);

        instance.setProductionLevel(ProductionLevel.STANDARD);
        double expectedValue = 8.25000d;
        assertEquals(expectedValue, instance.getMaintenanceCostPerYear(), tolerance);

        instance.setProductionLevel(ProductionLevel.PROTOTYPE);
        expectedValue = 33.00000d;
        assertEquals(expectedValue, instance.getMaintenanceCostPerYear(), tolerance);
    }
}
