package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.systems.structural.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StructuralSummaryTest {
    @Mock
    Hull hull;
    @Mock
    Drive drive;
    @Mock
    FrameReinforcement frameReinforcement;
    @Mock
    DriveMastArmor driveMastArmor;
    @Mock
    ExternalArmor externalArmor;
    @Mock
    InternalArmor internalArmor;
    @Mock
    ImprovedAccessways improvedAccessways;

    StructuralSummary instance;
    final double tolerance = 0.001d;

    @BeforeEach
    void setUp() {
        instance = new StructuralSummary(hull,
                drive,
                frameReinforcement,
                driveMastArmor,
                externalArmor,
                internalArmor,
                improvedAccessways);
    }

    @Test
    void getBasicSpacesUsed() {
        when(hull.getBasicSpacesUsed()).thenReturn(0);
        when(drive.getBasicSpacesUsed()).thenReturn(11);
        when(frameReinforcement.getBasicSpacesUsed()).thenReturn(1);
        when(driveMastArmor.getBasicSpacesUsed()).thenReturn(0);
        when(externalArmor.getBasicSpacesUsed()).thenReturn(1);
        when(internalArmor.getBasicSpacesUsed()).thenReturn(1);
        when(improvedAccessways.getBasicSpacesUsed()).thenReturn(0);
        
        int expectedValue = 14;
        assertEquals(expectedValue, instance.getBasicSpacesUsed());
    }

    @Test
    void getActualSpacesUsed() {
        when(hull.getActualSpacesUsed()).thenReturn(0);
        when(drive.getActualSpacesUsed()).thenReturn(11);
        when(frameReinforcement.getActualSpacesUsed()).thenReturn(1);
        when(driveMastArmor.getActualSpacesUsed()).thenReturn(0);
        when(externalArmor.getActualSpacesUsed()).thenReturn(1);
        when(internalArmor.getActualSpacesUsed()).thenReturn(1);
        when(improvedAccessways.getActualSpacesUsed()).thenReturn(0);

        int expectedValue = 14;
        assertEquals(expectedValue, instance.getActualSpacesUsed());
    }

    @Test
    void getBaseCost() {
        when(hull.getBaseCost()).thenReturn(55);
        when(drive.getBaseCost()).thenReturn(193);
        when(frameReinforcement.getBaseCost()).thenReturn(2);
        when(driveMastArmor.getBaseCost()).thenReturn(0);
        when(externalArmor.getBaseCost()).thenReturn(2);
        when(internalArmor.getBaseCost()).thenReturn(2);
        when(improvedAccessways.getBaseCost()).thenReturn(0);

        int expectedValue = 254;
        assertEquals(expectedValue, instance.getBaseCost());
    }

    @Test
    void getEnhancedCost() {
        when(hull.getEnhancedCost()).thenReturn(55);
        when(drive.getEnhancedCost()).thenReturn(193);
        when(frameReinforcement.getEnhancedCost()).thenReturn(2);
        when(driveMastArmor.getEnhancedCost()).thenReturn(0);
        when(externalArmor.getEnhancedCost()).thenReturn(3);
        when(internalArmor.getEnhancedCost()).thenReturn(3);
        when(improvedAccessways.getEnhancedCost()).thenReturn(0);

        int expectedValue = 256;
        assertEquals(expectedValue, instance.getEnhancedCost());
    }

    @Test
    void getDuelCost() {
        when(hull.getDuelCost()).thenReturn(55);
        when(drive.getDuelCost()).thenReturn(193);
        when(frameReinforcement.getDuelCost()).thenReturn(2);
        when(driveMastArmor.getDuelCost()).thenReturn(0);
        when(externalArmor.getDuelCost()).thenReturn(3);
        when(internalArmor.getDuelCost()).thenReturn(3);
        when(improvedAccessways.getDuelCost()).thenReturn(0);

        int expectedValue = 256;
        assertEquals(expectedValue, instance.getDuelCost());
    }

    @Test
    void getEconomicCost() {
        when(hull.getEconomicCost()).thenReturn(55);
        when(drive.getEconomicCost()).thenReturn(193);
        when(frameReinforcement.getEconomicCost()).thenReturn(2);
        when(driveMastArmor.getEconomicCost()).thenReturn(0);
        when(externalArmor.getEconomicCost()).thenReturn(3);
        when(internalArmor.getEconomicCost()).thenReturn(3);
        when(improvedAccessways.getEconomicCost()).thenReturn(0);

        int expectedValue = 256;
        assertEquals(expectedValue, instance.getEconomicCost());
    }

    @Test
    void getMaintenanceCostPerYear() {
        when(hull.getMaintenanceCostPerYear()).thenReturn(8.25000d);
        when(drive.getMaintenanceCostPerYear()).thenReturn(38.60000d);
        when(frameReinforcement.getMaintenanceCostPerYear()).thenReturn(0.20000d);
        when(driveMastArmor.getMaintenanceCostPerYear()).thenReturn(0.00000d);
        when(externalArmor.getMaintenanceCostPerYear()).thenReturn(0.15000d);
        when(internalArmor.getMaintenanceCostPerYear()).thenReturn(0.15000d);
        when(improvedAccessways.getMaintenanceCostPerYear()).thenReturn(0.00000d);

        double expectedValue = 47.3500d;
        assertEquals(expectedValue, instance.getMaintenanceCostPerYear());
    }
}
