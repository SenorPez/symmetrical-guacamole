package com.senorpez.avt.shipdesigner.systems.core;

import com.senorpez.avt.shipdesigner.Ship;
import com.senorpez.avt.shipdesigner.systems.ProductionLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FlagBridgeTest {
    @Mock
    Ship ship;

    private FlagBridge instance;
    private final double tolerance = 0.001d;

    @BeforeEach
    void setUp() {
        instance = new FlagBridge(
                ship,
                7,
                6,
                ProductionLevel.LIMITED,
                3
        );
    }

    @Test
    void getName() {
        String expectedValue = "Flag Bridge";
        assertEquals(expectedValue, instance.getName());
    }

    @Test
    void getQuantity() {
        int expectedValue = 7;
        assertEquals(expectedValue, instance.getQuantity());
    }

    @Test
    void getFlagPointsAt() {
        when(ship.getHullSize()).thenReturn(50);
        assertNull(instance.getFlagPointsAt());

        when(ship.getHullSize()).thenReturn(500);
        List<Integer> expectedValue = new ArrayList<>(List.of(4, 6, 8, 10, 12, 14, 16, 18));
        assertEquals(expectedValue, instance.getFlagPointsAt());
    }

    @Test
    void getFlagPoints() {
        int expectedValue = 2;
        assertEquals(expectedValue, instance.getFlagPoints());
    }

    @Test
    void getBasicSpacesUsed() {
        int expectedValue = 7;
        assertEquals(expectedValue, instance.getBasicSpacesUsed());
    }

    @Test
    void getShrinkEnhancement() {
        int expectedValue = 6;
        assertEquals(expectedValue, instance.getShrinkEnhancement());
    }

    @Test
    void getSpacesPerSystem() {
        int expectedValue = 1;
        assertEquals(expectedValue, instance.getSpacesPerSystem());
    }

    @Test
    void getCostPerSpace() {
        double expectedValue = 3d;
        assertEquals(expectedValue, instance.getCostPerSpace(), tolerance);
    }

    @Test
    void getCrewRequiredPerSpace() {
        double expectedValue = 1d;
        assertEquals(expectedValue, instance.getCrewRequiredPerSpace(), tolerance);
    }

    @Test
    void getActualSpacesUsed() {
        int expectedValue = 4;
        assertEquals(expectedValue, instance.getActualSpacesUsed());
    }

    @Test
    void getBaseCost() {
        int expectedValue = 21;
        assertEquals(expectedValue, instance.getBaseCost());
    }

    @Test
    void getEnhancedCost() {
        int expectedValue = 55;
        assertEquals(expectedValue, instance.getEnhancedCost());
    }

    @Test
    void getCrewRequirement() {
        double expectedValue = 7d;
        assertEquals(expectedValue, instance.getCrewRequirement(), tolerance);
    }

    @Test
    void getArmorLevel() {
        int expectedValue = 3;
        assertEquals(expectedValue, instance.getArmorLevel());
    }

    @Test
    void getArmorPointsUsed() {
        double expectedValue = 8.00000d;
        assertEquals(expectedValue, instance.getArmorPointsUsed(), tolerance);
    }

    @Test
    void getDuelCost() {
        int expectedValue = 55;
        assertEquals(expectedValue, instance.getDuelCost());
    }

    @Test
    void getProductionLevel() {
        ProductionLevel expectedValue = ProductionLevel.LIMITED;
        assertEquals(expectedValue, instance.getProductionLevel());
    }

    @Test
    void getEconomicCost() {
        int expectedValue = 110;
        assertEquals(expectedValue, instance.getEconomicCost());
    }

    @Test
    void getMaintenanceRate() {
        double expectedValue = 0.25d;
        assertEquals(expectedValue, instance.getMaintenanceRate(), tolerance);
    }

    @Test
    void getMaintenanceCostPerYear() {
        double expectedValue = 27.5d;
        assertEquals(expectedValue, instance.getMaintenanceCostPerYear(), tolerance);
    }
}
