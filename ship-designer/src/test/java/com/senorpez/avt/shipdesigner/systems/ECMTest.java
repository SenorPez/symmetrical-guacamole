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
class ECMTest {
    @Mock
    Ship ship;

    private ECM instance;
    private double tolerance;

    @BeforeEach
    void setUp() {
        when(ship.getHullSpaces()).thenReturn(250);
        instance = new ECM(ship, 2, 0, STANDARD);
        tolerance = 1e-5;
    }

    @Test
    void getShip() {
        Ship expectedValue = ship;
        assertEquals(expectedValue, instance.getShip());
    }

    @Test
    void getName() {
        String expectedValue = "ECM";
        assertEquals(expectedValue, instance.getName());
    }

    @Test
    void getQuantity() {
        int expectedValue = 2;
        assertEquals(expectedValue, instance.getQuantity());
    }

    @Test
    void getShrink() {
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getShrink());
    }

    @ParameterizedTest
    @MethodSource("spacesPerSystemProvider")
    void getSpacesPerSystem(final int hullSpaces, final int expectedValue) {
        when(ship.getHullSpaces()).thenReturn(hullSpaces);
        instance = new ECM(ship, 2, 0, STANDARD);
        assertEquals(expectedValue, instance.getSpacesPerSystem());
    }

    private static Stream<Arguments> spacesPerSystemProvider() {
        return Stream.of(
                arguments(25, 2),
                arguments(100, 3),
                arguments(175, 5),
                arguments(250, 6),
                arguments(325, 7),
                arguments(400, 8)
        );
    }

    @Test
    void getCostPerSpace() {
        double expectedValue = 33.33333d;
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
        int expectedValue = 6;
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
                arguments(0, 6),
                arguments(1, 5),
                arguments(2, 5),
                arguments(3, 5),
                arguments(4, 4),
                arguments(5, 4),
                arguments(6, 4),
                arguments(7, 3),
                arguments(8, 3),
                arguments(9, 3)
        );
    }

    @Test
    void getBaseCost() {
        int expectedValue = 200;
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
                arguments(0, 200),
                arguments(1, 220),
                arguments(2, 250),
                arguments(3, 295),
                arguments(4, 355),
                arguments(5, 430),
                arguments(6, 520),
                arguments(7, 625),
                arguments(8, 745),
                arguments(9, 880)
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
                arguments(0, 200),
                arguments(1, 220),
                arguments(2, 250),
                arguments(3, 295),
                arguments(4, 355),
                arguments(5, 430),
                arguments(6, 520),
                arguments(7, 625),
                arguments(8, 745),
                arguments(9, 880)
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
                arguments(PROTOTYPE, 800),
                arguments(LIMITED, 400),
                arguments(STANDARD, 200),
                arguments(MASS, 100)
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
                arguments(PROTOTYPE, 200d),
                arguments(LIMITED, 100d),
                arguments(STANDARD, 50d),
                arguments(MASS, 25d)
        );
    }
}