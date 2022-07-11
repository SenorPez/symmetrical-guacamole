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
class QuartersTest {
    @Mock
    Ship ship;

    private Quarters instance;

    @BeforeEach
    void setUp() {
        instance = new Quarters(ship, 1, 2, STANDARD);
    }

    @Test
    void getShip() {
        Ship expectedValue = ship;
        assertEquals(expectedValue, instance.getShip());
    }

    @Test
    void getName() {
        String expectedValue = "Quarters";
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

        // Should be immutable
        instance.setShrink(42);
        assertEquals(expectedValue, instance.getShrink());
    }

    @Test
    void getSpacesPerSystem() {
        int expectedValue = 1;
        assertEquals(expectedValue, instance.getSpacesPerSystem());
    }

    @Test
    void getCostPerSpace() {
        double expectedValue = 1d;
        assertEquals(expectedValue, instance.getCostPerSpace());
    }

    @Test
    void getCrewPerSpace() {
        double expectedValue = 0.125d;
        assertEquals(expectedValue, instance.getCrewPerSpace());
    }

    @Test
    void getProductionLevel() {
        ProductionLevel expectedValue = STANDARD;
        assertEquals(expectedValue, instance.getProductionLevel());
    }

    @Test
    void getMaintenanceRate() {
        double expectedValue = 0.05d;
        assertEquals(expectedValue, instance.getMaintenanceRate());
    }

    @Test
    void getBasicSpacesUsed() {
        int expectedValue = 1;
        assertEquals(expectedValue, instance.getBasicSpacesUsed());
    }

    @Test
    void getActualSpacesUsed() {
        int expectedValue = 1;
        assertEquals(expectedValue, instance.getActualSpacesUsed());
    }

    @Test
    void getBaseCost() {
        int expectedValue = 1;
        assertEquals(expectedValue, instance.getBaseCost());
    }

    @Test
    void getEnhancedCost() {
        int expectedValue = 1;
        assertEquals(expectedValue, instance.getEnhancedCost());
    }

    @Test
    void getCrewRequirement() {
        double expectedValue = 0.125d;
        assertEquals(expectedValue, instance.getCrewRequirement());
    }

    @Test
    void getDuelCost() {
        int expectedValue = 1;
        assertEquals(expectedValue, instance.getDuelCost());
    }

    @ParameterizedTest(name = "production level {0} economic cost {1}")
    @MethodSource("economicCostProvider")
    void getEconomicCost(final ProductionLevel productionLevel, final int expectedValue) {
        instance.setProductionLevel(productionLevel);
        assertEquals(expectedValue, instance.getEconomicCost());
    }

    private static Stream<Arguments> economicCostProvider() {
        return Stream.of(
                arguments(PROTOTYPE, 4),
                arguments(LIMITED, 2),
                arguments(STANDARD, 1),
                arguments(MASS, 1)
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
                arguments(PROTOTYPE, 0.2d),
                arguments(LIMITED, 0.1d),
                arguments(STANDARD, 0.05d),
                arguments(MASS, 0.05d)
        );
    }

    @Test
    void getArmorLevel() {
        int expectedValue = 2;
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

    @Test
    void getCrewBerths() {
        int expectedValue = 10;
        assertEquals(expectedValue, instance.getCrewBerths());
    }
}