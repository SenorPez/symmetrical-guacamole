package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;
import com.senorpez.avt.shipdesigner.characteristics.MassCharacteristics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DriveTest {
    @Mock
    Ship ship;
    @Mock
    MassCharacteristics massCharacteristics;

    private Drive instance;
    private final double tolerance = 0.001d;

    @BeforeEach
    void setUp() {
        instance = new Drive(
                ship,
                massCharacteristics,
                0,
                ProductionLevel.STANDARD,
                0
        );
    }

    @Test
    void getName() {
        String expectedValue = "Drive";
        assertEquals(expectedValue, instance.getName());
    }

    @Test
    void getQuantity() {
        when(massCharacteristics.getDriveSpaces()).thenReturn(10.68039d);
        int expectedValue = 11;
        assertEquals(expectedValue, instance.getQuantity());
    }

    @Test
    void getExtraStructure() {
        instance = instance.setExtraStructure(3);
        int expectedValue = 3;
        assertEquals(expectedValue, instance.getExtraStructure());
    }

    @Test
    void getEngineDamage() {
        when(ship.getHullSize()).thenReturn(55);
        when(ship.getMaximumThrust()).thenReturn(11d);
        when(ship.getDriveGeneration()).thenReturn(3.1d);
        when(massCharacteristics.getDriveSpaces()).thenReturn(10.68039d);

        int expectedValue = 12;
        assertEquals(expectedValue, instance.getEngineDamage());
    }

    @Test
    void getBasicSpacesUsed() {
        when(massCharacteristics.getDriveSpaces()).thenReturn(10.68039d);
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
        when(ship.getDriveGeneration()).thenReturn(3.1d);
        double expectedValue = 17.49229d;
        assertEquals(expectedValue, instance.getCostPerSpace(), tolerance);

        instance = instance.setShrinkEnhancement(6);
        expectedValue = 54.22608d;
        assertEquals(expectedValue, instance.getCostPerSpace(), tolerance);
    }

    @Test
    void getCrewRequiredPerSpace() {
        double expectedValue = 0.5d;
        assertEquals(expectedValue, instance.getCrewRequiredPerSpace(), tolerance);
    }

    @Test
    void getActualSpacesUsed() {
        when(massCharacteristics.getDriveSpaces()).thenReturn(10.68039d);
        double expectedValue = 11;
        assertEquals(expectedValue, instance.getActualSpacesUsed());
    }

    @Test
    void getBaseCost() {
        when(massCharacteristics.getDriveSpaces()).thenReturn(10.68039d);
        when(ship.getDriveGeneration()).thenReturn(3.1d);
        int expectedValue = 193;
        assertEquals(expectedValue, instance.getBaseCost());
    }

    @Test
    void getEnhancedCost() {
        when(massCharacteristics.getDriveSpaces()).thenReturn(10.68039d);
        when(ship.getDriveGeneration()).thenReturn(3.1d);
        int expectedValue = 193;
        assertEquals(expectedValue, instance.getEnhancedCost());

        instance = instance.setShrinkEnhancement(6);
        expectedValue = 1551;
        assertEquals(expectedValue, instance.getEnhancedCost());
    }

    @Test
    void getCrewRequirement() {
        when(massCharacteristics.getDriveSpaces()).thenReturn(10.68039d);
        double expectedValue = 5.5d;
        assertEquals(expectedValue, instance.getCrewRequirement(), tolerance);
    }

    @Test
    void getArmorLevel() {
        double expectedValue = 0;
        assertEquals(expectedValue, instance.getArmorLevel());
    }

    @Test
    void getDuelCost() {
        when(massCharacteristics.getDriveSpaces()).thenReturn(10.68039d);
        when(ship.getDriveGeneration()).thenReturn(3.1d);
        int expectedValue = 193;
        assertEquals(expectedValue, instance.getDuelCost());

        instance = instance.setShrinkEnhancement(6);
        expectedValue = 1551;
        assertEquals(expectedValue, instance.getDuelCost());
    }

    @Test
    void getProductionLevel() {
        ProductionLevel expectedValue = ProductionLevel.STANDARD;
        assertEquals(expectedValue, instance.getProductionLevel());
    }

    @Test
    void getEconomicCost() {
        when(massCharacteristics.getDriveSpaces()).thenReturn(10.68039d);
        when(ship.getDriveGeneration()).thenReturn(3.1d);
        int expectedValue = 193;
        assertEquals(expectedValue, instance.getEconomicCost());

        instance = instance.setProductionLevel(ProductionLevel.PROTOTYPE);
        expectedValue = 772;
        assertEquals(expectedValue, instance.getEconomicCost());
    }

    @Test
    void getMaintenanceRate() {
        double expectedValue = 0.2d;
        assertEquals(expectedValue, instance.getMaintenanceRate(), tolerance);
    }

    @Test
    void getMaintenanceCostPerYear() {
        when(massCharacteristics.getDriveSpaces()).thenReturn(10.68039d);
        when(ship.getDriveGeneration()).thenReturn(3.1d);
        double expectedValue = 38.60000d;
        assertEquals(expectedValue, instance.getMaintenanceCostPerYear(), tolerance);

        instance = instance.setProductionLevel(ProductionLevel.PROTOTYPE);
        expectedValue = 154.40000d;
        assertEquals(expectedValue, instance.getMaintenanceCostPerYear(), tolerance);
    }
}
