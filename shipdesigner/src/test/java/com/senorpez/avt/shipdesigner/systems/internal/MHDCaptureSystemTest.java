package com.senorpez.avt.shipdesigner.systems.internal;

import com.senorpez.avt.shipdesigner.Ship;
import com.senorpez.avt.shipdesigner.systems.ProductionLevel;
import com.senorpez.avt.shipdesigner.systems.structural.Drive;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MHDCaptureSystemTest {
    @Mock
    Ship ship;
    @Mock
    Drive drive;
    @Mock
    ReactorArmor reactorArmor;

    private MHDCaptureSystem instance;

    private final double tolerance = 0.001d;

    @BeforeEach
    void setUp() {
        instance = new MHDCaptureSystem(
                ship,
                drive,
                true,
                1,
                ProductionLevel.STANDARD,
                reactorArmor
        );
    }

    @Test
    void getName() {
        String expectedValue = "MHD Capture System";
        assertEquals(expectedValue, instance.getName());
    }

    @Test
    void getQuantity() {
        int expectedValue = 1;
        assertEquals(expectedValue, instance.getQuantity());
    }

    @Test
    void getBasicSpacesUsed() {
        when(drive.getTotalDriveSpaces()).thenReturn(11);
        instance = new MHDCaptureSystem(ship, drive, true, 1, ProductionLevel.STANDARD, reactorArmor);
        int expectedValue = 12;
        assertEquals(expectedValue, instance.getBasicSpacesUsed());
    }

    @Test
    void getShrinkEnhancement() {
        int expectedValue = 1;
        assertEquals(expectedValue, instance.getShrinkEnhancement());
    }

    @Test
    void getSpacesPerSystem() {
        when(drive.getTotalDriveSpaces()).thenReturn(11);
        instance = new MHDCaptureSystem(ship, drive, true, 1, ProductionLevel.STANDARD, reactorArmor);
        int expectedValue = 12;
        assertEquals(expectedValue, instance.getSpacesPerSystem());
    }

    @Test
    void getCostPerSpace() {
        double expectedValue = 10d;
        assertEquals(expectedValue, instance.getCostPerSpace(), tolerance);
    }

    @Test
    void getCrewRequiredPerSpace() {
        double expectedValue = 0.5d;
        assertEquals(expectedValue, instance.getCrewRequiredPerSpace(), tolerance);
    }

    @Test
    void getActualSpacesUsed() {
        when(drive.getTotalDriveSpaces()).thenReturn(11);
        instance = new MHDCaptureSystem(ship, drive, true, 1, ProductionLevel.STANDARD, reactorArmor);
        int expectedValue = 11;
        assertEquals(expectedValue, instance.getActualSpacesUsed());
    }

    @Test
    void getBaseCost() {
        when(drive.getTotalDriveSpaces()).thenReturn(11);
        instance = new MHDCaptureSystem(ship, drive, true, 1, ProductionLevel.STANDARD, reactorArmor);
        int expectedValue = 120;
        assertEquals(expectedValue, instance.getBaseCost());
    }

    @Test
    void getEnhancedCost() {
        when(drive.getTotalDriveSpaces()).thenReturn(11);
        instance = new MHDCaptureSystem(ship, drive, true, 1, ProductionLevel.STANDARD, reactorArmor);
        int expectedValue = 132;
        assertEquals(expectedValue, instance.getEnhancedCost());
    }

    @Test
    void getCrewRequirement() {
        when(drive.getTotalDriveSpaces()).thenReturn(11);
        instance = new MHDCaptureSystem(ship, drive, true, 1, ProductionLevel.STANDARD, reactorArmor);
        double expectedValue = 6d;
        assertEquals(expectedValue, instance.getCrewRequirement(), tolerance);
    }

    @Test
    void getArmorLevel() {
        when(reactorArmor.getArmorLevel()).thenReturn(5);
        int expectedValue = 5;
        assertEquals(expectedValue, instance.getArmorLevel());
    }

    @Test
    void getArmorPointsUsed() {
        when(reactorArmor.getArmorPointsUsed()).thenReturn(570d);
        double expectedValue = 570.00000d;
        assertEquals(expectedValue, instance.getArmorPointsUsed(), tolerance);
    }

    @Test
    void getDuelCost() {
        when(drive.getTotalDriveSpaces()).thenReturn(11);
        instance = new MHDCaptureSystem(ship, drive, true, 1, ProductionLevel.STANDARD, reactorArmor);
        int expectedValue = 132;
        assertEquals(expectedValue, instance.getDuelCost());
    }

    @Test
    void getProductionLevel() {
        ProductionLevel expectedValue = ProductionLevel.STANDARD;
        assertEquals(expectedValue, instance.getProductionLevel());
    }

    @Test
    void getEconomicCost() {
        when(drive.getTotalDriveSpaces()).thenReturn(11);
        instance = new MHDCaptureSystem(ship, drive, true, 1, ProductionLevel.STANDARD, reactorArmor);
        int expectedValue = 132;
        assertEquals(expectedValue, instance.getEconomicCost());
    }

    @Test
    void getMaintenanceRate() {
        double expectedValue = 0.2d;
        assertEquals(expectedValue, instance.getMaintenanceRate(), tolerance);
    }

    @Test
    void getMaintenanceCostPerYear() {
        when(drive.getTotalDriveSpaces()).thenReturn(11);
        instance = new MHDCaptureSystem(ship, drive, true, 1, ProductionLevel.STANDARD, reactorArmor);
        double expectedValue = 26.4d;
        assertEquals(expectedValue, instance.getMaintenanceCostPerYear(), tolerance);
    }
}
