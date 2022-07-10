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
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ECCMTest {
    @Mock
    Ship ship;

    private ECCM instance;
    private double tolerance;

    @BeforeEach
    void setUp() {
        instance = new ECCM(ship, 3, 0, STANDARD);
        tolerance = 1e-5;
    }

    @Test
    void getShip() {
        Ship expectedValue = ship;
        assertEquals(expectedValue, instance.getShip());
    }

    @Test
    void getName() {
        String expectedValue = "ECCM";
        assertEquals(expectedValue, instance.getName());
    }

    @Test
    void getQuantity() {
        int expectedValue = 3;
        assertEquals(expectedValue, instance.getQuantity());
    }

    @Test
    void getShrink() {
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getShrink());
    }

    @Test
    void getSpacesPerSystem() {
        int expectedValue = 10;
        assertEquals(expectedValue, instance.getSpacesPerSystem());
    }

    @Test
    void getCostPerSpace() {
        double expectedValue = 34.19261;
        assertEquals(expectedValue, instance.getCostPerSpace(), tolerance);
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
        int expectedValue = 10;
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
                arguments(0, 10),
                arguments(1, 9),
                arguments(2, 9),
                arguments(3, 8),
                arguments(4, 8),
                arguments(5, 7),
                arguments(6, 7),
                arguments(7, 6),
                arguments(8, 6),
                arguments(9, 5)
        );
    }

    @Test
    void getBaseCost() {
        int expectedValue = 342;
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
                arguments(0, 342),
                arguments(1, 377),
                arguments(2, 428),
                arguments(3, 505),
                arguments(4, 607),
                arguments(5, 736),
                arguments(6, 890),
                arguments(7, 1069),
                arguments(8, 1274),
                arguments(9, 1505)
        );
    }

    @Test
    void getCrewRequirement() {
        double expectedValue = 10d;
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
                arguments(0, 342),
                arguments(1, 377),
                arguments(2, 428),
                arguments(3, 505),
                arguments(4, 607),
                arguments(5, 736),
                arguments(6, 890),
                arguments(7, 1069),
                arguments(8, 1274),
                arguments(9, 1505)
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
                arguments(PROTOTYPE, 1368),
                arguments(LIMITED, 684),
                arguments(STANDARD, 342),
                arguments(MASS, 171)
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
                arguments(PROTOTYPE, 342d),
                arguments(LIMITED, 171d),
                arguments(STANDARD, 85.5d),
                arguments(MASS, 42.75d)
        );
    }
}