package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DriveMastArmorTest {
    @Mock
    Ship ship;
    @Mock
    ArmorShrink armorShrink;
    @Mock
    ArmorProductionLevel armorProductionLevel;

    private DriveMastArmor instance;
    private final double tolerance = 0.001d;

    @BeforeEach
    void setUp() {
        when(armorShrink.getShrinkEnhancement()).thenReturn(1);
        when(armorProductionLevel.getProductionLevel()).thenReturn(ProductionLevel.STANDARD);
        instance = new DriveMastArmor(
                ship,
                armorShrink,
                armorProductionLevel
        );
    }

    @Test
    void getName() {
        String expectedValue = "Engine and Mast Armor";
        assertEquals(expectedValue, instance.getName());
    }

    @Test
    void getQuantity() {
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getQuantity());
    }

    @Test
    void getMastArmor() {
        when(ship.getMastArmor()).thenReturn(1);
        int expectedValue = 1;
        assertEquals(expectedValue, instance.getMastArmor());
    }

    @Test
    void getDriveArmor() {
        when(ship.getDriveArmor()).thenReturn(2);
        int expectedValue = 2;
        assertEquals(expectedValue, instance.getDriveArmor());
    }

    @Test
    void getBasicSpacesUsed() {
        int expectedValue = 0;
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
        double expectedValue = 0;
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
        double expectedValue = 0;
        assertEquals(expectedValue, instance.getCrewRequirement(), tolerance);
    }

    @Test
    void getDuelCost() {
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getDuelCost());
    }

    @Test
    void getProductionLevel() {
        ProductionLevel expectedValue = ProductionLevel.STANDARD;
        assertEquals(expectedValue, instance.getProductionLevel());
    }

    @Test
    void getEconomicCost() {
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getEconomicCost());
    }

    @Test
    void getMaintenanceRate() {
        double expectedValue = 0.05d;
        assertEquals(expectedValue, instance.getMaintenanceRate(), tolerance);
    }

    @Test
    void getMaintenanceCostPerYear() {
        double expectedValue = 0d;
        assertEquals(expectedValue, instance.getMaintenanceCostPerYear(), tolerance);
    }
}
