package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static com.senorpez.avt.shipdesigner.systems.ProductionLevel.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InternalArmorTest {
    @Mock
    Ship ship;

    @Mock
    ArmorShrink armorShrink;

    @Mock
    ArmorProductionLevel armorProductionLevel;

    private InternalArmor instance;
    private final double tolerance = 1e-4;

    @BeforeEach
    void setUp() {
        instance = new InternalArmor(ship, 4, armorShrink, armorProductionLevel);
    }

    @Test
    void getShip() {
        Ship expectedValue = ship;
        assertEquals(expectedValue, instance.getShip());
    }

    @Test
    void getName() {
        String expectedValue = "Internal Armor";
        assertEquals(expectedValue, instance.getName());
    }

    @Test
    void getQuantity() {
        int expectedValue = 4;
        assertEquals(expectedValue, instance.getQuantity());
    }

    @Test
    void getExternalArmorPoints() {
        int expectedValue = 20;
        assertEquals(expectedValue, instance.getInternalArmorPoints());
    }

    @Test
    void getBasicSpacesUsed() {
        int expectedValue = 4;
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
        int expectedValue = 4;
        assertEquals(expectedValue, instance.getActualSpacesUsed());
    }

    @Test
    void getBaseCost() {
        int expectedValue = 8;
        assertEquals(expectedValue, instance.getBaseCost());
    }

    @ParameterizedTest(name = "shrink {0} enhanced cost {1}")
    @MethodSource("enhancedCostProvider")
    void getEnhancedCost(final int shrink, final int expectedValue) {
        when(armorShrink.getShrink()).thenReturn(shrink);
        assertEquals(expectedValue, instance.getEnhancedCost());
    }

    private static Stream<Arguments> enhancedCostProvider() {
        return Stream.of(
                arguments(0, 8),
                arguments(1, 9),
                arguments(2, 10),
                arguments(3, 12),
                arguments(4, 15),
                arguments(5, 18),
                arguments(6, 21),
                arguments(7, 25),
                arguments(8, 30),
                arguments(9, 36)
        );
    }

    @Test
    void getCrewRequirement() {
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getCrewRequirement());
    }

    @ParameterizedTest(name = "shrink {0} duel cost {1}")
    @MethodSource("duelCostProvider")
    void getDuelCost(final int shrink, final int expectedValue) {
        when(armorShrink.getShrink()).thenReturn(shrink);
        assertEquals(expectedValue, instance.getEnhancedCost());
    }

    private static Stream<Arguments> duelCostProvider() {
        return Stream.of(
                arguments(0, 8),
                arguments(1, 9),
                arguments(2, 10),
                arguments(3, 12),
                arguments(4, 15),
                arguments(5, 18),
                arguments(6, 21),
                arguments(7, 25),
                arguments(8, 30),
                arguments(9, 36)
        );
    }

    @Test
    void getProductionLevel() {
        ProductionLevel expectedValue = STANDARD;
        when(armorProductionLevel.getProductionLevel()).thenReturn(expectedValue);
        assertEquals(expectedValue, instance.getProductionLevel());

        // Should change when ArmorProductionLevel object changes.
        expectedValue = PROTOTYPE;
        when(armorProductionLevel.getProductionLevel()).thenReturn(expectedValue);
        assertEquals(expectedValue, instance.getProductionLevel());
    }

    @ParameterizedTest(name = "{0} economic cost {1}")
    @MethodSource("economicCostProvider")
    void getEconomicCost(final ProductionLevel productionLevel, final int expectedValue) {
        when(armorProductionLevel.getProductionLevel()).thenReturn(productionLevel);
        assertEquals(expectedValue, instance.getEconomicCost());
    }

    private static Stream<Arguments> economicCostProvider() {
        return Stream.of(
                arguments(PROTOTYPE, 32),
                arguments(LIMITED, 16),
                arguments(STANDARD, 8),
                arguments(MASS, 4)
        );
    }

    @Test
    void getMaintenanceRate() {
        double expectedValue = 0.05d;
        assertEquals(expectedValue, instance.getMaintenanceRate());
    }

    @Test
    void getMaintenanceCost() {
        when(armorProductionLevel.getProductionLevel()).thenReturn(STANDARD);
        double expectedValue = 0.4d;
        assertEquals(expectedValue, instance.getMaintenanceCost());
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