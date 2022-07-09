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

import java.util.List;
import java.util.stream.Stream;

import static com.senorpez.avt.shipdesigner.systems.ProductionLevel.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BridgeTest {
    @Mock
    Ship ship;

    private Bridge instance;

    @BeforeEach
    void setUp() {
        instance = new Bridge(ship, 1, 0, 3, STANDARD);
    }

    @Test
    void getShip() {
        Ship expectedValue = ship;
        assertEquals(expectedValue, instance.getShip());
    }

    @Test
    void getName() {
        String expectedValue = "Bridge";
        assertEquals(expectedValue, instance.getName());
    }

    @Test
    void getQuantity() {
        int expectedValue = 1;
        assertEquals(expectedValue, instance.getQuantity());
    }

    @Test
    void getShrink() {
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getShrink());
    }

    @Test
    void getSpacesPerSystem() {
        int expectedValue = 1;
        assertEquals(expectedValue, instance.getSpacesPerSystem());
    }

    @Test
    void getCostPerSpace() {
        double expectedValue = 3d;
        assertEquals(expectedValue, instance.getCostPerSpace());
    }

    @Test
    void getCrewPerSpace() {
        double expectedValue = 1d;
        assertEquals(expectedValue, instance.getCrewPerSpace());
    }

    @Test
    void getProductionLevel() {
        ProductionLevel expectedValue = STANDARD;
        assertEquals(expectedValue, instance.getProductionLevel());
    }

    @Test
    void getMaintenanceRate() {
        double expectedValue = 0.25d;
        assertEquals(expectedValue, instance.getMaintenanceRate());
    }

    @Test
    void getBasicSpacesUsed() {
        int expectedValue = 1;
        assertEquals(expectedValue, instance.getBasicSpacesUsed());
    }

    @ParameterizedTest(name = "shrink {0} actual spaces used {1}")
    @MethodSource("actualSpacesUsedProvider")
    void getActualSpacesUsed(final int shrink, final int expectedValue) {
        instance.setShrink(shrink);
        assertEquals(expectedValue, instance.getActualSpacesUsed());
    }

    private static Stream<Arguments> actualSpacesUsedProvider() {
        return Stream.of(
                arguments(0, 1),
                arguments(1, 1),
                arguments(2, 1),
                arguments(3, 1),
                arguments(4, 1),
                arguments(5, 1),
                arguments(6, 1),
                arguments(7, 1),
                arguments(8, 1),
                arguments(9, 1)
        );
    }

    @Test
    void getBaseCost() {
        int expectedValue = 3;
        assertEquals(expectedValue, instance.getBaseCost());
    }

    @ParameterizedTest(name = "shrink {0} enhanced cost {1}")
    @MethodSource("enhancedCostProvider")
    void getEnhancedCost(final int shrink, final int expectedValue) {
        instance.setShrink(shrink);
        assertEquals(expectedValue, instance.getEnhancedCost());
    }

    private static Stream<Arguments> enhancedCostProvider() {
        return Stream.of(
                arguments(0, 3),
                arguments(1, 4),
                arguments(2, 4),
                arguments(3, 5),
                arguments(4, 6),
                arguments(5, 7),
                arguments(6, 8),
                arguments(7, 10),
                arguments(8, 12),
                arguments(9, 14)
        );
    }

    @Test
    void getCrewRequirement() {
        double expectedValue = 1d;
        assertEquals(expectedValue, instance.getCrewRequirement());
    }

    @ParameterizedTest(name = "shrink {0} duel cost {1}")
    @MethodSource("duelCostProvider")
    void getDuelCost(final int shrink, final int expectedValue) {
        instance.setShrink(shrink);
        assertEquals(expectedValue, instance.getDuelCost());
    }

    private static Stream<Arguments> duelCostProvider() {
        return Stream.of(
                arguments(0, 3),
                arguments(1, 4),
                arguments(2, 4),
                arguments(3, 5),
                arguments(4, 6),
                arguments(5, 7),
                arguments(6, 8),
                arguments(7, 10),
                arguments(8, 12),
                arguments(9, 14)
        );
    }

    @ParameterizedTest(name = "production level {0} economic cost {1}")
    @MethodSource("economicCostProvider")
    void getEconomicCost(final ProductionLevel productionLevel, final int expectedValue) {
        instance.setProductionLevel(productionLevel);
        assertEquals(expectedValue, instance.getEconomicCost());
    }

    private static Stream<Arguments> economicCostProvider() {
        return Stream.of(
                arguments(PROTOTYPE, 12),
                arguments(LIMITED, 6),
                arguments(STANDARD, 3),
                arguments(MASS, 2)
        );
    }

    @ParameterizedTest(name = "production level {0} maintenance cost {1}")
    @MethodSource("maintenanceCostProvider")
    void getMaintenanceCost(final ProductionLevel productionLevel, final double expectedValue) {
        instance.setProductionLevel(productionLevel);
        assertEquals(expectedValue, instance.getMaintenanceCost());
    }

    private static Stream<Arguments> maintenanceCostProvider() {
        return Stream.of(
                arguments(PROTOTYPE, 3d),
                arguments(LIMITED, 1.5d),
                arguments(STANDARD, 0.75d),
                arguments(MASS, 0.5d)
        );
    }

    @Test
    void getArmorLevel() {
        int expectedValue = 3;
        assertEquals(expectedValue, instance.getArmorLevel());
    }

    @ParameterizedTest(name = "armor level {0} armor points used {1}")
    @MethodSource("armorPointsUsedProvider")
    void getArmorPointsUsed(final int armorLevel, final double expectedValue) {
        instance.setArmorLevel(armorLevel);
        assertEquals(expectedValue, instance.getArmorPointsUsed());
    }

    private static Stream<Arguments> armorPointsUsedProvider() {
        return Stream.of(
                arguments(0, 0d),
                arguments(1, 0.5d),
                arguments(2, 1),
                arguments(3, 2),
                arguments(4, 3.5),
                arguments(5, 5),
                arguments(6, 7),
                arguments(7, 9)
        );
    }

    @ParameterizedTest(name = "minimum crew {0} flex points {1}")
    @MethodSource("flexPointsProvider")
    void getFlexPoints(final int minimumCrew, final int expectedValue) {
        when(ship.getMinimumCrew()).thenReturn(minimumCrew);
        instance.setQuantity(5);
        assertEquals(expectedValue, instance.getFlexPoints());
    }

    private static Stream<Arguments> flexPointsProvider() {
        return Stream.of(
                arguments(10, 5),
                arguments(20, 5),
                arguments(30, 4),
                arguments(40, 3),
                arguments(50, 3),
                arguments(60, 2),
                arguments(70, 2),
                arguments(80, 2),
                arguments(90, 1),
                arguments(100, 1)
        );
    }

    @ParameterizedTest(name = "minimum crew {0} flex points at {1}")
    @MethodSource("flexPointsAtProvider")
    void getFlexPointsAt(final int minimumCrew, final List<Integer> expectedValue) {
        when(ship.getMinimumCrew()).thenReturn(minimumCrew);
        if (minimumCrew > 25) when(ship.getHullSpaces()).thenReturn(200);
        instance.setQuantity(5);
        assertEquals(expectedValue, instance.getFlexPointsAt());
    }

    private static Stream<Arguments> flexPointsAtProvider() {
        return Stream.of(
                arguments(10, List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)),
                arguments(20, List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)),
                arguments(30, List.of(1, 2, 3, 5, 6, 7, 8, 9, 11)),
                arguments(40, List.of(1, 3, 4, 6, 8, 9, 11, 12, 14)),
                arguments(50, List.of(1, 3, 5, 7, 9, 11, 13, 15, 17)),
                arguments(60, List.of(2, 4, 6, 9, 11, 14, 16, 18, 21)),
                arguments(70, List.of(2, 5, 7, 10, 13, 16, 19, 21, 24)),
                arguments(80, List.of(2, 5, 8, 12, 15, 18, 21, 24, 28)),
                arguments(90, List.of(2, 6, 9, 13, 17, 20, 24, 27, 31)),
                arguments(100, List.of(2, 6, 10, 14, 18, 22, 26, 30, 34))
        );
    }
}