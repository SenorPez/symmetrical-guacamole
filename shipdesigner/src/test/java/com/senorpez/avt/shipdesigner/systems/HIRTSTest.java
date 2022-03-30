package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class HIRTSTest {
    @Mock
    Ship ship;

    private HIRTS instance;
    private final double tolerance = 0.001d;

    @BeforeEach
    void setUp() {
        instance = new HIRTS(
                ship,
                0,
                ProductionLevel.PROTOTYPE
        );
    }

    @Test
    void getName() {
        String expectedValue = "HIRTS";
        assertEquals(expectedValue, instance.getName());
    }

    @Test
    void getQuantity() {
        int expectedValue = 1;
        assertEquals(expectedValue, instance.getQuantity());
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
        double expectedValue = 1d;
        assertEquals(expectedValue, instance.getCostPerSpace(), tolerance);
    }

    @Test
    void getCrewRequiredPerSpace() {
        double expectedValue = 1d;
        assertEquals(expectedValue, instance.getCrewRequiredPerSpace(), tolerance);
    }

    @Test
    void getActualSpacesUsed() {
        int expectedValue = 1;
        assertEquals(expectedValue, instance.getActualSpacesUsed());
    }

    @Test
    void getBaseCost() {
        int expectedValue = 1;
        assertEquals(expectedValue, instance.getBaseCost());
    }

    @Test
    void getEnhancedCost() {
        int expectedValue = 1;
        assertEquals(expectedValue, instance.getEnhancedCost());
    }

    @Test
    void getCrewRequirement() {
        double expectedValue = 1;
        assertEquals(expectedValue, instance.getCrewRequirement(), tolerance);
    }

    @Test
    void getDuelCost() {
        int expectedValue = 1;
        assertEquals(expectedValue, instance.getDuelCost());
    }

    @Test
    void getProductionLevel() {
        ProductionLevel expectedValue = ProductionLevel.PROTOTYPE;
        assertEquals(expectedValue, instance.getProductionLevel());
    }

    @Test
    void getEconomicCost() {
        int expectedValue = 4;
        assertEquals(expectedValue, instance.getEconomicCost());
    }

    @Test
    void getMaintenanceRate() {
        double expectedValue = 0.25d;
        assertEquals(expectedValue, instance.getMaintenanceRate(), tolerance);
    }

    @Test
    void getMaintenanceCostPerYear() {
        double expectedValue = 1d;
        assertEquals(expectedValue, instance.getMaintenanceCostPerYear(), tolerance);
    }
}
