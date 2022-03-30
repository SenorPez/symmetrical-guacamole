package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;
import com.senorpez.avt.shipdesigner.enums.Shape;
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
        instance = new Hull(
                ship,
                0,
                ProductionLevel.STANDARD
        );
    }

    @Test
    void getName() {
        String expectedValue = "Hull";
        assertEquals(expectedValue, instance.getName());
    }

    @Test
    void getQuantity() {
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getQuantity());
    }

    @Test
    void getBasicSpacesUsed() {
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getBasicSpacesUsed());
    }

    @Test
    void getShrinkEnhancement() {
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getShrinkEnhancement());
    }

    @Test
    void getSpacesPerSystem() {
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getSpacesPerSystem());
    }

    @Test
    void getCostPerSpace() {
        when(ship.getShape()).thenReturn(Shape.ELLIPSOID);
        int expectedValue = 1;
        assertEquals(expectedValue, instance.getCostPerSpace());
    }

    @Test
    void getCrewRequiredPerSpace() {
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getCrewRequiredPerSpace());
    }

    @Test
    void getActualSpacesUsed() {
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getActualSpacesUsed());
    }

    @Test
    void getBaseCost() {
        when(ship.getHullSize()).thenReturn(55);
        when(ship.getShape()).thenReturn(Shape.ELLIPSOID);
        int expectedValue = 55;
        assertEquals(expectedValue, instance.getBaseCost());
    }

    @Test
    void getEnhancedCost() {
        when(ship.getHullSize()).thenReturn(55);
        when(ship.getShape()).thenReturn(Shape.ELLIPSOID);
        int expectedValue = 55;
        assertEquals(expectedValue, instance.getEnhancedCost());

        instance = instance.setShrinkEnhancement(3);
        expectedValue = 82;
        assertEquals(expectedValue, instance.getEnhancedCost());
    }

    @Test
    void getCrewRequirement() {
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getCrewRequirement());
    }

    @Test
    void getDuelCost() {
        when(ship.getHullSize()).thenReturn(55);
        when(ship.getShape()).thenReturn(Shape.ELLIPSOID);
        int expectedValue = 55;
        assertEquals(expectedValue, instance.getDuelCost());
    }

    @Test
    void getProductionLevel() {
        ProductionLevel expectedValue = ProductionLevel.STANDARD;
        assertEquals(expectedValue, instance.getProductionLevel());
    }

    @Test
    void getEconomicCost() {
        when(ship.getHullSize()).thenReturn(55);
        when(ship.getShape()).thenReturn(Shape.ELLIPSOID);
        int expectedValue = 55;
        assertEquals(expectedValue, instance.getEconomicCost());

        instance = instance.setProductionLevel(ProductionLevel.PROTOTYPE);
        expectedValue = 220;
        assertEquals(expectedValue, instance.getEconomicCost());
    }

    @Test
    void getMaintenanceRate() {
        double expectedValue = 0.15d;
        assertEquals(expectedValue, instance.getMaintenanceRate());
    }

    @Test
    void getMaintenanceCostPerYear() {
        when(ship.getHullSize()).thenReturn(55);
        when(ship.getShape()).thenReturn(Shape.ELLIPSOID);
        double expectedValue = 8.25000d;
        assertEquals(expectedValue, instance.getMaintenanceCostPerYear(), tolerance);

        instance = instance.setProductionLevel(ProductionLevel.PROTOTYPE);
        expectedValue = 33.00000d;
        assertEquals(expectedValue, instance.getMaintenanceCostPerYear(), tolerance);
    }
}
