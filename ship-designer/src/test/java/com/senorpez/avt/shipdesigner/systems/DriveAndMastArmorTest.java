package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.senorpez.avt.shipdesigner.systems.ProductionLevel.PROTOTYPE;
import static com.senorpez.avt.shipdesigner.systems.ProductionLevel.STANDARD;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DriveAndMastArmorTest {
    @Mock
    Ship ship;

    @Mock
    ArmorShrink armorShrink;

    @Mock
    ArmorProductionLevel armorProductionLevel;

    private DriveAndMastArmor instance;
    private final double tolerance = 1e-4;

    @BeforeEach
    void setUp() {
        instance = new DriveAndMastArmor(ship, 2, 1, armorShrink, armorProductionLevel);
    }

    @Test
    void getShip() {
        Ship expectedValue = ship;
        assertEquals(expectedValue, instance.getShip());
    }

    @Test
    void getName() {
        String expectedValue = "Drive and Mast Armor";
        assertEquals(expectedValue, instance.getName());
    }

    @Test
    void getQuantity() {
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getQuantity());
    }

    @Test
    void getDriveArmor() {
        int expectedValue = 2;
        assertEquals(expectedValue, instance.getDriveArmor());
    }

    @Test
    void getMastArmor() {
        int expectedValue = 1;
        assertEquals(expectedValue, instance.getMastArmor());
    }

    @Test
    void getBasicSpacesUsed() {
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getBasicSpacesUsed());
    }

    @Test
    void getShrink() {
        int expectedValue = 0;
        when(armorShrink.getShrink()).thenReturn(expectedValue);
        assertEquals(expectedValue, instance.getShrink());

        // Should change when Armor object changes.
        expectedValue = 2;
        when(armorShrink.getShrink()).thenReturn(expectedValue);
        assertEquals(expectedValue, instance.getShrink());
    }

    @Test
    void getSpacesPerSystem() {
        int expectedValue = 1;
        assertEquals(expectedValue, instance.getSpacesPerSystem());
    }

    @Test
    void getCostPerSpace() {
        int expectedValue = 2;
        assertEquals(expectedValue, instance.getCostPerSpace());
    }

    @Test
    void getCrewPerSpace() {
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getCrewPerSpace());
    }

    @Test
    void getActualSpacesUsed() {
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getActualSpacesUsed());
    }

    @Test
    void getBaseCost() {
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getBaseCost());
    }

    @Test
    void getEnhancedCost() {
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getEnhancedCost());
    }

    @Test
    void getCrewRequirement() {
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getCrewRequirement());
    }

    @Test
    void getDuelCost() {
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getDuelCost());
    }

    @Test
    void getProductionLevel() {
        ProductionLevel expectedValue = ProductionLevel.STANDARD;
        when(armorProductionLevel.getProductionLevel()).thenReturn(expectedValue);
        assertEquals(expectedValue, instance.getProductionLevel());

        // Should change when ArmorProductionLevel object changes.
        expectedValue = PROTOTYPE;
        when(armorProductionLevel.getProductionLevel()).thenReturn(expectedValue);
        assertEquals(expectedValue, instance.getProductionLevel());
    }

    @Test
    void getEconomicCost() {
        when(armorProductionLevel.getProductionLevel()).thenReturn(STANDARD);
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getEconomicCost());
    }

    @Test
    void getMaintenanceRate() {
        double expectedValue = 0.05d;
        assertEquals(expectedValue, instance.getMaintenanceRate());
    }

    @Test
    void getMaintenanceCost() {
        when(armorProductionLevel.getProductionLevel()).thenReturn(STANDARD);
        double expectedValue = 0d;
        assertEquals(expectedValue, instance.getMaintenanceCost());
    }

    @Test
    void setQuantity() {
        instance.setQuantity(42);
        // Should be immutable
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getQuantity());
    }

    @Test
    void setShrink() {
        when(armorShrink.getShrink()).thenReturn(2);
        instance.setShrink(9);
        // Should be immutable
        int expectedValue = 2;
        assertEquals(expectedValue, instance.getShrink());
    }

    @Test
    void setProductionLevel() {
        when(armorProductionLevel.getProductionLevel()).thenReturn(PROTOTYPE);
        instance.setProductionLevel(STANDARD);
        // Should be immutable
        ProductionLevel expectedValue = PROTOTYPE;
        assertEquals(expectedValue, instance.getProductionLevel());
    }
}