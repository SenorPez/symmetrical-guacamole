package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.HullShape;
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

import static com.senorpez.avt.shipdesigner.HullShape.SPHERE;
import static com.senorpez.avt.shipdesigner.systems.ProductionLevel.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HullTest {
    @Mock
    Ship ship;

    private Hull instance;

    @BeforeEach
    void setUp() {
        instance = new Hull(ship, 0, STANDARD);
    }

    @Test
    void getShip() {
        Ship expectedValue = ship;
        assertEquals(expectedValue, instance.getShip());
    }

    @Test
    void getName() {
        String expectedValue = "Hull";
        assertEquals(expectedValue, instance.getName());
    }

    @Test
    void getQuantity() {
        int expectedValue = 25;
        when(ship.getHullSpaces()).thenReturn(expectedValue);
        assertEquals(expectedValue, instance.getQuantity());

        // Should change when changed on the ship.
        expectedValue = 250;
        when(ship.getHullSpaces()).thenReturn(expectedValue);
        assertEquals(expectedValue, instance.getQuantity());
    }

    @Test
    void getBasicSpacesUsed() {
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getBasicSpacesUsed());
    }

    @Test
    void getShrink() {
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getShrink());
    }

    @Test
    void getSpacesPerSystem() {
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getSpacesPerSystem());
    }

    @ParameterizedTest(name = "{0} cost per space {1}")
    @MethodSource("costPerSpaceProvider")
    void getCostPerSpace(final HullShape shape, final double expectedValue) {
        when(ship.getHullShape()).thenReturn(shape);
        assertEquals(expectedValue, instance.getCostPerSpace());
    }

    private static Stream<Arguments> costPerSpaceProvider() {
        return Stream.of(
                arguments(SPHERE, 1)
        );
    }

    @Test
    void getCostPerSpace() {
        when(ship.getHullShape()).thenReturn(SPHERE);
        double expectedValue = 1;

        assertEquals(expectedValue, instance.getCostPerSpace());

        // TODO: Verify changes when ship shape changes (when you implement other shapes)
//        expectedValue = 0.9d;
//        ship.setHullShape(null); // CONICAL
//        assertEquals(expectedValue, instance.getCostPerSpace());
    }

    @Test
    void getCrewPerSpace() {
        double expectedValue = 0;
        assertEquals(expectedValue, instance.getCrewPerSpace());
    }

    @ParameterizedTest(name = "shrink {0} actual spaces {1}")
    @MethodSource("actualSpacesUsedProvider")
    void getActualSpacesUsed(final int shrink, final int expectedValue) {
        instance = instance.setShrink(shrink);
        assertEquals(expectedValue, instance.getActualSpacesUsed());
    }

    private static Stream<Arguments> actualSpacesUsedProvider() {
        return Stream.of(
                arguments(0, 0),
                arguments(1, 0),
                arguments(2, 0),
                arguments(3, 0),
                arguments(4, 0),
                arguments(5, 0),
                arguments(6, 0),
                arguments(7, 0),
                arguments(8, 0),
                arguments(9, 0)
        );
    }

    @Test
    void getBaseCost() {
        when(ship.getHullShape()).thenReturn(SPHERE);
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getBaseCost());
    }

    @ParameterizedTest(name = "shrink {0} base cost {1}")
    @MethodSource("enhancedCostProvider")
    void getEnhancedCost(final int shrink, final int expectedValue) {
        when(ship.getHullSpaces()).thenReturn(25);
        when(ship.getHullShape()).thenReturn(SPHERE);
        instance = instance.setShrink(shrink);
        assertEquals(expectedValue, instance.getEnhancedCost());
    }

    private static Stream<Arguments> enhancedCostProvider() {
        return Stream.of(
                arguments(0, 25),
                arguments(1, 28),
                arguments(2, 32),
                arguments(3, 37),
                arguments(4, 45),
                arguments(5, 54),
                arguments(6, 65),
                arguments(7, 79),
                arguments(8, 94),
                arguments(9, 110)
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
        when(ship.getHullSpaces()).thenReturn(25);
        when(ship.getHullShape()).thenReturn(SPHERE);
        instance = instance.setShrink(shrink);
        assertEquals(expectedValue, instance.getDuelCost());
    }

    private static Stream<Arguments> duelCostProvider() {
        return Stream.of(
                arguments(0, 25),
                arguments(1, 28),
                arguments(2, 32),
                arguments(3, 37),
                arguments(4, 45),
                arguments(5, 54),
                arguments(6, 65),
                arguments(7, 79),
                arguments(8, 94),
                arguments(9, 110)
        );
    }

    @Test
    void getProductionLevel() {
        ProductionLevel expectedValue = STANDARD;
        assertEquals(expectedValue, instance.getProductionLevel());
    }

    @ParameterizedTest(name = "{0} economic cost {1}")
    @MethodSource("economicCostProvider")
    void getEconomicCost(final ProductionLevel productionLevel, final int expectedValue) {
        when(ship.getHullSpaces()).thenReturn(25);
        when(ship.getHullShape()).thenReturn(SPHERE);
        instance.setProductionLevel(productionLevel);
        assertEquals(expectedValue, instance.getEconomicCost());
    }

    private static Stream<Arguments> economicCostProvider() {
        return Stream.of(
                arguments(PROTOTYPE, 100),
                arguments(LIMITED, 50),
                arguments(STANDARD, 25),
                arguments(MASS, 13)
        );
    }

    @Test
    void getMaintenanceRate() {
        double expectedValue = 0.15d;
        assertEquals(expectedValue, instance.getMaintenanceRate());
    }

    @ParameterizedTest(name = "{0} maintenanceCost {1}")
    @MethodSource("maintenanceCostProvider")
    void getMaintenanceCost(final ProductionLevel productionLevel, final double expectedValue) {
        when(ship.getHullSpaces()).thenReturn(25);
        when(ship.getHullShape()).thenReturn(SPHERE);
        instance = instance.setProductionLevel(productionLevel);
        assertEquals(expectedValue, instance.getMaintenanceCost());
    }

    private static Stream<Arguments> maintenanceCostProvider() {
        return Stream.of(
                arguments(PROTOTYPE, 15d),
                arguments(LIMITED, 7.5d),
                arguments(STANDARD, 3.75d),
                arguments(MASS, 1.95d)
        );
    }
}