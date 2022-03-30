package com.senorpez.avt.shipdesigner.systems.structural;

import com.senorpez.avt.shipdesigner.Ship;
import com.senorpez.avt.shipdesigner.enums.Shape;
import com.senorpez.avt.shipdesigner.systems.ProductionLevel;
import com.senorpez.avt.shipdesigner.systems.structural.FrameReinforcement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FrameReinforcementTest {
    @Mock
    Ship ship;

    private FrameReinforcement instance;
    private final double tolerance = 0.001d;

    @BeforeEach
    void setUp() {
        instance = new FrameReinforcement(
                ship,
                1,
                ProductionLevel.STANDARD
        );
    }

    @Test
    void getName() {
        String expectedValue = "Frame Reinforcement";
        assertEquals(expectedValue, instance.getName());
    }

    @Test
    void getQuantity() {
        int expectedValue = 1;
        assertEquals(expectedValue, instance.getQuantity());
    }

    @Test
    void getMaximumExternalArmorSpaces() {
        when(ship.getMaximumThrust()).thenReturn(11d);
        when(ship.getHullSize()).thenReturn(55);
        int expectedValue = 1;
        assertEquals(expectedValue, instance.getMaximumExternalArmorSpaces());
    }

    @Test
    void getBasicSpacesUsed() {
        int expectedValue = 1;
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
        when(ship.getShape()).thenReturn(Shape.ELLIPSOID);
        double expectedValue = 2d;
        assertEquals(expectedValue, instance.getCostPerSpace(), tolerance);
    }

    @Test
    void getCrewRequiredPerSpace() {
        double expectedValue = 0d;
        assertEquals(expectedValue, instance.getCrewRequiredPerSpace(), tolerance);
    }

    @Test
    void getActualSpacesUsed() {
        double expectedValue = 1;
        assertEquals(expectedValue, instance.getActualSpacesUsed());
    }

    @Test
    void getBaseCost() {
        when(ship.getShape()).thenReturn(Shape.ELLIPSOID);
        int expectedValue = 2;
        assertEquals(expectedValue, instance.getBaseCost());
    }

    @Test
    void getEnhancedCost() {
        when(ship.getShape()).thenReturn(Shape.ELLIPSOID);
        int expectedValue = 2;
        assertEquals(expectedValue, instance.getEnhancedCost());
    }

    @Test
    void getCrewRequirement() {
        double expectedValue = 0;
        assertEquals(expectedValue, instance.getCrewRequirement(), tolerance);
    }

    @Test
    void getDuelCost() {
        when(ship.getShape()).thenReturn(Shape.ELLIPSOID);
        int expectedValue = 2;
        assertEquals(expectedValue, instance.getDuelCost());
    }

    @Test
    void getProductionLevel() {
        ProductionLevel expectedValue = ProductionLevel.STANDARD;
        assertEquals(expectedValue, instance.getProductionLevel());
    }

    @Test
    void getEconomicCost() {
        when(ship.getShape()).thenReturn(Shape.ELLIPSOID);
        int expectedValue = 2;
        assertEquals(expectedValue, instance.getEconomicCost());

        instance = instance.setProductionLevel(ProductionLevel.PROTOTYPE);
        expectedValue = 8;
        assertEquals(expectedValue, instance.getEconomicCost());
    }

    @Test
    void getMaintenanceRate() {
        double expectedValue = 0.1d;
        assertEquals(expectedValue, instance.getMaintenanceRate(), tolerance);
    }

    @Test
    void getMaintenanceCostPerYear() {
        when(ship.getShape()).thenReturn(Shape.ELLIPSOID);
        double expectedValue = 0.2d;
        assertEquals(expectedValue, instance.getMaintenanceCostPerYear(), tolerance);

        instance = instance.setProductionLevel(ProductionLevel.PROTOTYPE);
        expectedValue = 0.8d;
        assertEquals(expectedValue, instance.getMaintenanceCostPerYear(), tolerance);
    }
}
