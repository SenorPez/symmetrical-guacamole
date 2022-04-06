package com.senorpez.avt.shipdesigner.systems.internal;

import com.senorpez.avt.shipdesigner.Ship;
import com.senorpez.avt.shipdesigner.systems.ProductionLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class RadiatorTest {
    @Mock
    Ship ship;

    private Radiator instance;
    private final double tolerance = 0.001d;

    @BeforeEach
    void setUp() {
        instance = new Radiator(
                ship,
                2,
                1,
                ProductionLevel.STANDARD
        );
    }

    @Test
    void getName() {
        String expectedValue = "Radiator";
        assertEquals(expectedValue, instance.getName());
    }

    @Test
    void getQuantity() {
        int expectedValue = 2;
        assertEquals(expectedValue, instance.getQuantity());
    }

    @Test
    void getRadiatorDissipation() {
        double expectedValue = 0.8d;
        assertEquals(expectedValue, instance.getRadiatorDissipation(), tolerance);
    }

    @Test
    void getBasicSpacesUsed() {
        int expectedValue = 2;
        assertEquals(expectedValue, instance.getBasicSpacesUsed());
    }

    @Test
    void getShrinkEnhancement() {
        int expectedValue = 1;
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
        double expectedValue = 0.5d;
        assertEquals(expectedValue, instance.getCrewRequiredPerSpace(), tolerance);
    }

    @Test
    void getActualSpacesUsed() {
        int expectedValue = 1;
        assertEquals(expectedValue, instance.getActualSpacesUsed());
    }

    @Test
    void getBaseCost() {
        int expectedValue = 2;
        assertEquals(expectedValue, instance.getBaseCost());
    }

    @Test
    void getEnhancedCost() {
        int expectedValue = 3;
        assertEquals(expectedValue, instance.getEnhancedCost());
    }

    @Test
    void getCrewRequirement() {
        double expectedValue = 1d;
        assertEquals(expectedValue, instance.getCrewRequirement(), tolerance);
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
        double expectedValue = 0.2d;
        assertEquals(expectedValue, instance.getMaintenanceRate(), tolerance);
    }

    @Test
    void getMaintenanceCostPerYear() {
        double expectedValue = 0.6d;
        assertEquals(expectedValue, instance.getMaintenanceCostPerYear(), tolerance);
    }
}
