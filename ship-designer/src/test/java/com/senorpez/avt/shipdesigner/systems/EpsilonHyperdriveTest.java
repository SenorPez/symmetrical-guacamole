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

@ExtendWith(MockitoExtension.class)
class EpsilonHyperdriveTest {
    @Mock
    Ship ship;

    private EpsilonHyperdrive instance;
    private double tolerance;

    @BeforeEach
    void setUp() {
        instance = new EpsilonHyperdrive(ship, 1, 0, 0, STANDARD);
        tolerance = 1e-5;
    }

    @Test
    void getShip() {
        Ship expectedValue = ship;
        assertEquals(expectedValue, instance.getShip());
    }

    @Test
    void getName() {
        String expectedValue = "Epsilon Hyperdrive";
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
        int expectedValue = 12;
        assertEquals(expectedValue, instance.getSpacesPerSystem());
    }

    @Test
    void getCostPerSpace() {
        double expectedValue = 7d;
        assertEquals(expectedValue, instance.getCostPerSpace());
    }

    @Test
    void getCrewPerSpace() {
        double expectedValue = 0.5d;
        assertEquals(expectedValue, instance.getCrewPerSpace());
    }

    @Test
    void getProductionLevel() {
        ProductionLevel expectedValue = STANDARD;
        assertEquals(expectedValue, instance.getProductionLevel());
    }

    @Test
    void getMaintenanceRate() {
        double expectedValue = 0.2d;
        assertEquals(expectedValue, instance.getMaintenanceRate());
    }

    @Test
    void getBasicSpacesUsed() {
        int expectedValue = 12;
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
                arguments(0, 12),
                arguments(1, 11),
                arguments(2, 10),
                arguments(3, 10),
                arguments(4, 9),
                arguments(5, 9),
                arguments(6, 8),
                arguments(7, 7),
                arguments(8, 7),
                arguments(9, 6)
        );
    }

    @Test
    void getBaseCost() {
        int expectedValue = 84;
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
                arguments(0, 84),
                arguments(1, 93),
                arguments(2, 105),
                arguments(3, 124),
                arguments(4, 150),
                arguments(5, 181),
                arguments(6, 219),
                arguments(7, 263),
                arguments(8, 313),
                arguments(9, 370)
        );
    }

    @Test
    void getCrewRequirement() {
        double expectedValue = 6d;
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
                arguments(0, 84),
                arguments(1, 93),
                arguments(2, 105),
                arguments(3, 124),
                arguments(4, 150),
                arguments(5, 181),
                arguments(6, 219),
                arguments(7, 263),
                arguments(8, 313),
                arguments(9, 370)
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
                arguments(PROTOTYPE, 336),
                arguments(LIMITED, 168),
                arguments(STANDARD, 84),
                arguments(MASS, 42)
        );
    }

    @ParameterizedTest(name = "production level {0} maintenance cost {1}")
    @MethodSource("maintenanceCostProvider")
    void getMaintenanceCost(final ProductionLevel productionLevel, final double expectedValue) {
        instance.setProductionLevel(productionLevel);
        assertEquals(expectedValue, instance.getMaintenanceCost(), tolerance);
    }

    private static Stream<Arguments> maintenanceCostProvider() {
        return Stream.of(
                arguments(PROTOTYPE, 67.2d),
                arguments(LIMITED, 33.6d),
                arguments(STANDARD, 16.8d),
                arguments(MASS, 8.4d)
        );
    }

    @Test
    void getArmorLevel() {
        int expectedValue = 0;
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
                arguments(1, 6d),
                arguments(2, 12d),
                arguments(3, 24d),
                arguments(4, 42d),
                arguments(5, 60d),
                arguments(6, 84d),
                arguments(7, 108d)
        );
    }
}