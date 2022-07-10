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
class GammaHyperdriveTest {
    @Mock
    Ship ship;

    private GammaHyperdrive instance;
    private double tolerance;

    @BeforeEach
    void setUp() {
        instance = new GammaHyperdrive(ship, 1, 0, 0, STANDARD);
        tolerance = 1e-5;
    }

    @Test
    void getShip() {
        Ship expectedValue = ship;
        assertEquals(expectedValue, instance.getShip());
    }

    @Test
    void getName() {
        String expectedValue = "Gamma Hyperdrive";
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
        int expectedValue = 4;
        assertEquals(expectedValue, instance.getSpacesPerSystem());
    }

    @Test
    void getCostPerSpace() {
        double expectedValue = 4d;
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
        int expectedValue = 4;
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
                arguments(0, 4),
                arguments(1, 3),
                arguments(2, 3),
                arguments(3, 3),
                arguments(4, 3),
                arguments(5, 3),
                arguments(6, 2),
                arguments(7, 2),
                arguments(8, 2),
                arguments(9, 2)
        );
    }

    @Test
    void getBaseCost() {
        int expectedValue = 16;
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
                arguments(0, 16),
                arguments(1, 18),
                arguments(2, 20),
                arguments(3, 24),
                arguments(4, 29),
                arguments(5, 35),
                arguments(6, 42),
                arguments(7, 50),
                arguments(8, 60),
                arguments(9, 71)
        );
    }

    @Test
    void getCrewRequirement() {
        double expectedValue = 2d;
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
                arguments(0, 16),
                arguments(1, 18),
                arguments(2, 20),
                arguments(3, 24),
                arguments(4, 29),
                arguments(5, 35),
                arguments(6, 42),
                arguments(7, 50),
                arguments(8, 60),
                arguments(9, 71)
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
                arguments(PROTOTYPE, 64),
                arguments(LIMITED, 32),
                arguments(STANDARD, 16),
                arguments(MASS, 8)
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
                arguments(PROTOTYPE, 12.8d),
                arguments(LIMITED, 6.4d),
                arguments(STANDARD, 3.2d),
                arguments(MASS, 1.6d)
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
                arguments(1, 2d),
                arguments(2, 4d),
                arguments(3, 8d),
                arguments(4, 14d),
                arguments(5, 20d),
                arguments(6, 28d),
                arguments(7, 36d)
        );
    }
}