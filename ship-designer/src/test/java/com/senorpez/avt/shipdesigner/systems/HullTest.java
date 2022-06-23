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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HullTest {
    @Mock
    Ship ship;

    private Hull instance;
    private final double tolerance = 1e-5;

    @BeforeEach
    void setUp() {
        ship.setHullSpaces(300);
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

    @Test
    void getActualSpacesUsed() {
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getActualSpacesUsed());
    }

    @Test
    void getBaseCost() {
        when(ship.getHullShape()).thenReturn(SPHERE);
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getBaseCost());
    }

    @Test
    void getEnhancedCost() {
        when(ship.getHullSpaces()).thenReturn(25);
        when(ship.getHullShape()).thenReturn(SPHERE);
        int expectedValue = 25;
        assertEquals(expectedValue, instance.getEnhancedCost());
    }

    @Test
    void getCrewRequirement() {
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getCrewRequirement());
    }

    @Test
    void getDuelCost() {
        when(ship.getHullSpaces()).thenReturn(25);
        when(ship.getHullShape()).thenReturn(SPHERE);
        int expectedValue = 25;
        assertEquals(expectedValue, instance.getDuelCost());
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