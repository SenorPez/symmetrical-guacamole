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
class FlagBridgeTest {
    @Mock
    Ship ship;

    private FlagBridge instance;

    @BeforeEach
    void setUp() {
        instance = new FlagBridge(ship, 1, 0, 3, STANDARD);
    }

    @Test
    void getShip() {
        Ship expectedValue = ship;
        assertEquals(expectedValue, instance.getShip());
    }

    @Test
    void getName() {
        String expectedValue = "Flag Bridge";
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
    @MethodSource("flagPointsProvider")
    void getFlexPoints(final int quantity, final int expectedValue) {
        instance.setQuantity(quantity);
        assertEquals(expectedValue, instance.getFlagPoints());
    }

    private static Stream<Arguments> flagPointsProvider() {
        return Stream.of(
                arguments(1, 0),
                arguments(2, 0),
                arguments(3, 0),
                arguments(4, 1),
                arguments(5, 1),
                arguments(6, 2),
                arguments(7, 2),
                arguments(8, 3),
                arguments(9, 3),
                arguments(10, 4)
        );
    }

    @ParameterizedTest(name = "minimum crew {0} flex points at {1}")
    @MethodSource("flagPointsAtProvider")
    void getFlexPointsAt(final int hullSpaces, final List<Integer> expectedValue) {
        when(ship.getHullSpaces()).thenReturn(hullSpaces);
        assertEquals(expectedValue, instance.getFlagPointsAt());
    }

    private static Stream<Arguments> flagPointsAtProvider() {
        return Stream.of(
                arguments(50, List.of()),
                arguments(100, List.of(4)),
                arguments(150, List.of(4, 6)),
                arguments(200, List.of(4, 6, 8)),
                arguments(250, List.of(4, 6, 8, 10)),
                arguments(300, List.of(4, 6, 8, 10, 12)),
                arguments(350, List.of(4, 6, 8, 10, 12, 14)),
                arguments(400, List.of(4, 6, 8, 10, 12, 14, 16)),
                arguments(450, List.of(4, 6, 8, 10, 12, 14, 16, 18)),
                arguments(500, List.of(4, 6, 8, 10, 12, 14, 16, 18))
        );
    }
}