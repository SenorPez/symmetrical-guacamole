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
import static com.senorpez.avt.shipdesigner.systems.ProductionLevel.MASS;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FrameReinforcementTest {
    @Mock
    Ship ship;

    private FrameReinforcement instance;
    private final double tolerance = 1e-4;
    
    @BeforeEach
    void setUp() {
        instance = new FrameReinforcement(ship, 2, STANDARD);
    }

    @Test
    void getShip() {
        Ship expectedValue = ship;
        assertEquals(expectedValue, instance.getShip());
    }

    @Test
    void getName() {
        String expectedValue = "Frame Reinforcement";
        assertEquals(expectedValue, instance.getName());
    }

    @Test
    void getQuantity() {
        int expectedValue = 2;
        assertEquals(expectedValue, instance.getQuantity());
    }

    @Test
    void getMaximumExternalArmorSpaces() {
        when(ship.getHullSpaces()).thenReturn(25);
        when(ship.getThrust()).thenReturn(6d);
        int expectedValue = 8;
        assertEquals(expectedValue, instance.getMaximumExternalArmorSpaces());
        
        expectedValue = 16;
        instance.setQuantity(4);
        assertEquals(expectedValue, instance.getMaximumExternalArmorSpaces());
    }

    @Test
    void getBasicSpacesUsed() {
        int expectedValue = 2;
        assertEquals(expectedValue, instance.getBasicSpacesUsed());
    }

    @Test
    void getShrinkEnhancement() {
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getShrink());

        // Should be immutable.
        instance.setShrink(4);
        assertEquals(expectedValue, instance.getShrink());
    }

    @Test
    void getSpacesPerSystem() {
        int expectedValue = 1;
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
                arguments(SPHERE, 2)
        );
    }

    @Test
    void getCostPerSpace() {
        when(ship.getHullShape()).thenReturn(SPHERE);
        double expectedValue = 2d;
        assertEquals(expectedValue, instance.getCostPerSpace());

    // TODO: Verify changes when ship shape changes (when you implement other shapes)

//        expectedValue = 1.8d;
//        ship.setHullShape(null); // CONICAL
//        assertEquals(expectedValue, instance.getCostPerSpace());
    }

    @Test
    void getCrewPerSpace() {
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getCrewPerSpace());
    }

    @Test
    void getActualSpacesUsed() {
        int expectedValue = 2;
        assertEquals(expectedValue, instance.getActualSpacesUsed());
    }

    @Test
    void getBaseCost() {
        when(ship.getHullShape()).thenReturn(SPHERE);
        int expectedValue = 4;
        assertEquals(expectedValue, instance.getBaseCost());
    }

    @Test
    void getEnhancedCost() {
        when(ship.getHullShape()).thenReturn(SPHERE);
        int expectedValue = 4;
        assertEquals(expectedValue, instance.getEnhancedCost());
    }

    @Test
    void getCrewRequirement() {
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getCrewRequirement());
    }

    @Test
    void getDuelCost() {
        when(ship.getHullShape()).thenReturn(SPHERE);
        int expectedValue = 4;
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
        when(ship.getHullShape()).thenReturn(SPHERE);
        instance.setProductionLevel(productionLevel);
        assertEquals(expectedValue, instance.getEconomicCost());
    }

    private static Stream<Arguments> economicCostProvider() {
        return Stream.of(
                arguments(PROTOTYPE, 16),
                arguments(LIMITED, 8),
                arguments(STANDARD, 4),
                arguments(MASS, 2)
        );
    }

    @Test
    void getMaintenanceRate() {
        double expectedValue = 0.1d;
        assertEquals(expectedValue, instance.getMaintenanceRate());
    }

    @Test
    void getMaintenanceCost() {
        when(ship.getHullShape()).thenReturn(SPHERE);
        double expectedValue = 0.4d;
        assertEquals(expectedValue, instance.getMaintenanceCost());
    }
}