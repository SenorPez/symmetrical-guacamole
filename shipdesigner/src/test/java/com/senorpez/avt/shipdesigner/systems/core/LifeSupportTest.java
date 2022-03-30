package com.senorpez.avt.shipdesigner.systems.core;

import com.senorpez.avt.shipdesigner.Ship;
import com.senorpez.avt.shipdesigner.systems.ProductionLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class LifeSupportTest {
    @Mock
    Ship ship;

    private LifeSupport instance;
    private final double tolerance = 0.001d;

    @BeforeEach
    void setUp() {
        instance = new LifeSupport(
                ship,
                1,
                0,
                ProductionLevel.STANDARD,
                3,
                LifeSupportClass.MILITARY
        );
    }

    @Test
    void getName() {
        String expectedValue = "Military Life Support";
        assertEquals(expectedValue, instance.getName());
    }

    @Test
    void getQuantity() {
        int expectedValue = 1;
        assertEquals(expectedValue, instance.getQuantity());
    }

    @Test
    void lifeSupportCrewCapacity() {
        instance = instance.setQuantity(2);
        int expectedValue = 400;
        assertEquals(expectedValue, instance.getLifeSupportCrewCapacity());

        instance = instance.setLifeSupportClass(LifeSupportClass.GUNBOAT);
        expectedValue = 20;
        assertEquals(expectedValue, instance.getLifeSupportCrewCapacity());

        instance = instance
                .setLifeSupportClass(LifeSupportClass.EXTENDED)
                .setQuantity(50);
        expectedValue = 66;
        assertEquals(expectedValue, instance.getLifeSupportCrewCapacity());

        instance = instance.setQuantity(150);
        expectedValue = 225;
        assertEquals(expectedValue, instance.getLifeSupportCrewCapacity());

        instance = instance.setQuantity(350);
        expectedValue = 475;
        assertEquals(expectedValue, instance.getLifeSupportCrewCapacity());
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
        double expectedValue = 0.5;
        assertEquals(expectedValue, instance.getCrewRequirement(), tolerance);
    }

    @Test
    void getArmorLevel() {
        int expectedValue = 3;
        assertEquals(expectedValue, instance.getArmorLevel());
    }

    @Test
    void getArmorPointsUsed() {
        double expectedValue = 2.00000d;
        assertEquals(expectedValue, instance.getArmorPointsUsed());
    }

    @Test
    void getDuelCost() {
        int expectedValue = 1;
        assertEquals(expectedValue, instance.getDuelCost());
    }

    @Test
    void getProductionLevel() {
        ProductionLevel expectedValue = ProductionLevel.STANDARD;
        assertEquals(expectedValue, instance.getProductionLevel());
    }

    @Test
    void getEconomicCost() {
        int expectedValue = 1;
        assertEquals(expectedValue, instance.getEconomicCost());
    }

    @Test
    void getMaintenanceRate() {
        double expectedValue = 0.2d;
        assertEquals(expectedValue, instance.getMaintenanceRate(), tolerance);
    }

    @Test
    void getMaintenanceCostPerYear() {
        double expectedValue = 0.2d;
        assertEquals(expectedValue, instance.getMaintenanceCostPerYear(), tolerance);
    }
}
