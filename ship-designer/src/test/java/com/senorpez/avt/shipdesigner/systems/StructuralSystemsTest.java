package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StructuralSystemsTest {
    @Mock
    Ship ship;
    @Mock
    Hull hull;
    @Mock
    Drive drive;
    @Mock
    FrameReinforcement frameReinforcement;
    @Mock
    DriveAndMastArmor driveAndMastArmor;
    @Mock
    ExternalArmor externalArmor;
    @Mock
    InternalArmor internalArmor;
    @Mock
    Accessways accessways;

    private StructuralSystems instance;

    @Test
    void initialShipTest() {
        // Mock out as if a blank ship.
        when(hull.getBasicSpacesUsed()).thenReturn(0);
        when(hull.getActualSpacesUsed()).thenReturn(0);
        when(hull.getBaseCost()).thenReturn(25);
        when(hull.getEnhancedCost()).thenReturn(25);
        when(hull.getDuelCost()).thenReturn(25);
        when(hull.getEconomicCost()).thenReturn(25);
        when(hull.getMaintenanceCost()).thenReturn(3.75d);

        when(drive.getBasicSpacesUsed()).thenReturn(1);
        when(drive.getActualSpacesUsed()).thenReturn(1);
        when(drive.getBaseCost()).thenReturn(5);
        when(drive.getEnhancedCost()).thenReturn(5);
        when(drive.getDuelCost()).thenReturn(5);
        when(drive.getEconomicCost()).thenReturn(5);
        when(drive.getMaintenanceCost()).thenReturn(1d);

        when(frameReinforcement.getBasicSpacesUsed()).thenReturn(0);
        when(frameReinforcement.getActualSpacesUsed()).thenReturn(0);
        when(frameReinforcement.getBaseCost()).thenReturn(0);
        when(frameReinforcement.getEnhancedCost()).thenReturn(0);
        when(frameReinforcement.getDuelCost()).thenReturn(0);
        when(frameReinforcement.getEconomicCost()).thenReturn(0);
        when(frameReinforcement.getMaintenanceCost()).thenReturn(0d);

        when(driveAndMastArmor.getBasicSpacesUsed()).thenReturn(0);
        when(driveAndMastArmor.getActualSpacesUsed()).thenReturn(0);
        when(driveAndMastArmor.getBaseCost()).thenReturn(0);
        when(driveAndMastArmor.getEnhancedCost()).thenReturn(0);
        when(driveAndMastArmor.getDuelCost()).thenReturn(0);
        when(driveAndMastArmor.getEconomicCost()).thenReturn(0);
        when(driveAndMastArmor.getMaintenanceCost()).thenReturn(0d);

        when(externalArmor.getBasicSpacesUsed()).thenReturn(0);
        when(externalArmor.getActualSpacesUsed()).thenReturn(0);
        when(externalArmor.getBaseCost()).thenReturn(0);
        when(externalArmor.getEnhancedCost()).thenReturn(0);
        when(externalArmor.getDuelCost()).thenReturn(0);
        when(externalArmor.getEconomicCost()).thenReturn(0);
        when(externalArmor.getMaintenanceCost()).thenReturn(0d);

        when(internalArmor.getBasicSpacesUsed()).thenReturn(0);
        when(internalArmor.getActualSpacesUsed()).thenReturn(0);
        when(internalArmor.getBaseCost()).thenReturn(0);
        when(internalArmor.getEnhancedCost()).thenReturn(0);
        when(internalArmor.getDuelCost()).thenReturn(0);
        when(internalArmor.getEconomicCost()).thenReturn(0);
        when(internalArmor.getMaintenanceCost()).thenReturn(0d);

        when(accessways.getBasicSpacesUsed()).thenReturn(0);
        when(accessways.getActualSpacesUsed()).thenReturn(0);
        when(accessways.getBaseCost()).thenReturn(0);
        when(accessways.getEnhancedCost()).thenReturn(0);
        when(accessways.getDuelCost()).thenReturn(0);
        when(accessways.getEconomicCost()).thenReturn(0);
        when(accessways.getMaintenanceCost()).thenReturn(0d);

        instance = new StructuralSystems(hull,
                drive,
                frameReinforcement,
                driveAndMastArmor,
                externalArmor,
                internalArmor,
                accessways);

        assertEquals(1, instance.getBasicSpacesUsed());
        assertEquals(1, instance.getActualSpacesUsed());
        assertEquals(30, instance.getBaseCost());
        assertEquals(30, instance.getEnhancedCost());
        assertEquals(0, instance.getArmorPointsUsed());
        assertEquals(30, instance.getDuelCost());
        assertEquals(30, instance.getEconomicCost());
        assertEquals(4.75d, instance.getMaintenanceCost());
    }

    @Test
    void yorkTest() {
        // Mock out as if a York.
        when(hull.getBasicSpacesUsed()).thenReturn(0);
        when(hull.getActualSpacesUsed()).thenReturn(0);
        when(hull.getBaseCost()).thenReturn(25);
        when(hull.getEnhancedCost()).thenReturn(25);
        when(hull.getDuelCost()).thenReturn(25);
        when(hull.getEconomicCost()).thenReturn(25);
        when(hull.getMaintenanceCost()).thenReturn(3.75d);

        when(drive.getBasicSpacesUsed()).thenReturn(4);
        when(drive.getActualSpacesUsed()).thenReturn(4);
        when(drive.getBaseCost()).thenReturn(79);
        when(drive.getEnhancedCost()).thenReturn(79);
        when(drive.getDuelCost()).thenReturn(79);
        when(drive.getEconomicCost()).thenReturn(79);
        when(drive.getMaintenanceCost()).thenReturn(15.8d);

        when(frameReinforcement.getBasicSpacesUsed()).thenReturn(0);
        when(frameReinforcement.getActualSpacesUsed()).thenReturn(0);
        when(frameReinforcement.getBaseCost()).thenReturn(0);
        when(frameReinforcement.getEnhancedCost()).thenReturn(0);
        when(frameReinforcement.getDuelCost()).thenReturn(0);
        when(frameReinforcement.getEconomicCost()).thenReturn(0);
        when(frameReinforcement.getMaintenanceCost()).thenReturn(0d);

        when(driveAndMastArmor.getBasicSpacesUsed()).thenReturn(0);
        when(driveAndMastArmor.getActualSpacesUsed()).thenReturn(0);
        when(driveAndMastArmor.getBaseCost()).thenReturn(0);
        when(driveAndMastArmor.getEnhancedCost()).thenReturn(0);
        when(driveAndMastArmor.getDuelCost()).thenReturn(0);
        when(driveAndMastArmor.getEconomicCost()).thenReturn(0);
        when(driveAndMastArmor.getMaintenanceCost()).thenReturn(0d);

        when(externalArmor.getBasicSpacesUsed()).thenReturn(0);
        when(externalArmor.getActualSpacesUsed()).thenReturn(0);
        when(externalArmor.getBaseCost()).thenReturn(0);
        when(externalArmor.getEnhancedCost()).thenReturn(0);
        when(externalArmor.getDuelCost()).thenReturn(0);
        when(externalArmor.getEconomicCost()).thenReturn(0);
        when(externalArmor.getMaintenanceCost()).thenReturn(0d);

        when(internalArmor.getBasicSpacesUsed()).thenReturn(4);
        when(internalArmor.getActualSpacesUsed()).thenReturn(4);
        when(internalArmor.getBaseCost()).thenReturn(8);
        when(internalArmor.getEnhancedCost()).thenReturn(8);
        when(internalArmor.getDuelCost()).thenReturn(8);
        when(internalArmor.getEconomicCost()).thenReturn(8);
        when(internalArmor.getMaintenanceCost()).thenReturn(0.4d);

        when(accessways.getBasicSpacesUsed()).thenReturn(0);
        when(accessways.getActualSpacesUsed()).thenReturn(0);
        when(accessways.getBaseCost()).thenReturn(0);
        when(accessways.getEnhancedCost()).thenReturn(0);
        when(accessways.getDuelCost()).thenReturn(0);
        when(accessways.getEconomicCost()).thenReturn(0);
        when(accessways.getMaintenanceCost()).thenReturn(0d);

        instance = new StructuralSystems(hull,
                drive,
                frameReinforcement,
                driveAndMastArmor,
                externalArmor,
                internalArmor,
                accessways);

        assertEquals(8, instance.getBasicSpacesUsed());
        assertEquals(8, instance.getActualSpacesUsed());
        assertEquals(112, instance.getBaseCost());
        assertEquals(112, instance.getEnhancedCost());
        assertEquals(0, instance.getArmorPointsUsed());
        assertEquals(112, instance.getDuelCost());
        assertEquals(112, instance.getEconomicCost());
        assertEquals(19.95d, instance.getMaintenanceCost());
    }
}