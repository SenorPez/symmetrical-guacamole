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
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DriveTest {
    @Mock
    Ship ship;

    private Drive instance;
    private final double tolerance = 1e-4;

    @BeforeEach
    void setUp() {
        instance = new Drive(ship, 0, STANDARD, 0);
    }

    @Test
    void getShip() {
        Ship expectedValue = ship;
        assertEquals(expectedValue, instance.getShip());
    }

    @Test
    void getName() {
        String expectedValue = "Drive";
        assertEquals(expectedValue, instance.getName());
    }

    @Test
    void getQuantity() {
        double expectedValue = 4d;
        when(ship.getDriveSpacesWithoutArmor(anyDouble())).thenReturn(expectedValue);
        assertEquals(expectedValue, instance.getQuantity());

        // Should change when changed on the ship
        expectedValue = 25d;
        when(ship.getDriveSpacesWithoutArmor(anyDouble())).thenReturn(expectedValue);
        assertEquals(expectedValue, instance.getQuantity());
    }

    @Test
    void getExtraStructure() {
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getExtraDriveStructure());
    }

    @ParameterizedTest(name = "shrink {0} drive damage {1}")
    @MethodSource("driveDamageProvider")
    void getDriveDamage(final int extraDriveStructure, final int shrink, final int expectedValue) {
        when(ship.getDriveSpacesWithoutArmor(anyDouble())).thenReturn(4d);
        when(ship.getHullSpaces()).thenReturn(25);
        when(ship.getThrust()).thenReturn(6d);
        when(ship.getDriveGeneration()).thenReturn(3.4d);
        instance = instance.setExtraDriveStructure(extraDriveStructure).setShrink(shrink);
        assertEquals(expectedValue, instance.getDriveDamage());
    }

    private static Stream<Arguments> driveDamageProvider() {
        return Stream.of(
                arguments(0, 0, 16),
                arguments(0, 1, 19),
                arguments(0, 2, 26),
                arguments(0, 3, 35),
                arguments(0, 4, 48),
                arguments(0, 5, 63),
                arguments(0, 6, 82),
                arguments(0, 7, 104),
                arguments(0, 8, 129),
                arguments(0, 9, 157),
                arguments(2, 0, 24),
                arguments(2, 1, 29),
                arguments(2, 2, 38),
                arguments(2, 3, 52),
                arguments(2, 4, 71),
                arguments(2, 5, 95),
                arguments(2, 6, 123),
                arguments(2, 7, 156),
                arguments(2, 8, 193),
                arguments(2, 9, 236)
        );
    }

    @Test
    void getBasicSpacesUsed() {
        when(ship.getDriveSpacesWithoutArmor(anyDouble())).thenReturn(4d);
        int expectedValue = 4;
        assertEquals(expectedValue, instance.getBasicSpacesUsed());

        instance = instance.setExtraDriveStructure(2);
        expectedValue = 6;
        assertEquals(expectedValue, instance.getBasicSpacesUsed());
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

    @ParameterizedTest(name = "shrink {0} cost per space {1}")
    @MethodSource("costPerSpaceProvider")
    void getCostPerSpace(final int shrink, final double expectedValue) {
        when(ship.getDriveGeneration()).thenReturn(3.4d);
        instance = instance.setShrink(shrink);
        assertEquals(expectedValue, instance.getCostPerSpace(), tolerance);
    }

    private static Stream<Arguments> costPerSpaceProvider() {
        return Stream.of(
                arguments(0, 19.54282),
                arguments(1, 21.49710),
                arguments(2, 25.40566),
                arguments(3, 31.26851),
                arguments(4, 39.08563),
                arguments(5, 48.85704),
                arguments(6, 60.58273),
                arguments(7, 74.26271),
                arguments(8, 89.89696),
                arguments(9, 107.48556)
        );
    }

    @Test
    void getCostPerSpace() {
        when(ship.getDriveGeneration()).thenReturn(3.4d);
        double expectedValue = 19.54282d;
        assertEquals(expectedValue, instance.getCostPerSpace(), tolerance);

        // Should change when ship changes.
        when(ship.getDriveGeneration()).thenReturn(6.0d);
        expectedValue = 38.63617d;
        assertEquals(expectedValue, instance.getCostPerSpace(), tolerance);
    }

    @Test
    void getCrewPerSpace() {
        double expectedValue = 0.5;
        assertEquals(expectedValue, instance.getCrewPerSpace());
    }

    @ParameterizedTest(name = "shrink {0} actual spaces {1}")
    @MethodSource("actualSpacesUsedProvider")
    void getActualSpacesUsed(final int extraDriveStructure, final int shrink, final int expectedValue) {
        when(ship.getDriveSpacesWithoutArmor(anyDouble())).thenReturn(4d);
        instance = instance.setExtraDriveStructure(extraDriveStructure).setShrink(shrink);
        assertEquals(expectedValue, instance.getActualSpacesUsed());
    }

    private static Stream<Arguments> actualSpacesUsedProvider() {
        return Stream.of(
                arguments(0, 0, 4),
                arguments(0, 1, 4),
                arguments(0, 2, 4),
                arguments(0, 3, 4),
                arguments(0, 4, 4),
                arguments(0, 5, 4),
                arguments(0, 6, 4),
                arguments(0, 7, 4),
                arguments(0, 8, 4),
                arguments(0, 9, 4),
                arguments(2, 0, 6),
                arguments(2, 1, 6),
                arguments(2, 2, 6),
                arguments(2, 3, 6),
                arguments(2, 4, 6),
                arguments(2, 5, 6),
                arguments(2, 6, 6),
                arguments(2, 7, 6),
                arguments(2, 8, 6),
                arguments(2, 9, 6)
        );
    }

    @ParameterizedTest(name = "shrink {0} base cost {1}")
    @MethodSource("baseCostProvider")
    void getBaseCost(final int extraDriveStructure, final int shrink, final int expectedValue) {
        when(ship.getDriveSpacesWithoutArmor(anyDouble())).thenReturn(4d);
        when(ship.getDriveGeneration()).thenReturn(3.4d);
        instance = instance.setExtraDriveStructure(extraDriveStructure).setShrink(shrink);
        assertEquals(expectedValue, instance.getBaseCost());
    }

    private static Stream<Arguments> baseCostProvider() {
        return Stream.of(
                arguments(0, 0, 79),
                arguments(0, 1, 86),
                arguments(0, 2, 102),
                arguments(0, 3, 126),
                arguments(0, 4, 157),
                arguments(0, 5, 196),
                arguments(0, 6, 243),
                arguments(0, 7, 298),
                arguments(0, 8, 360),
                arguments(0, 9, 430),
                arguments(2, 0, 118),
                arguments(2, 1, 129),
                arguments(2, 2, 153),
                arguments(2, 3, 188),
                arguments(2, 4, 235),
                arguments(2, 5, 294),
                arguments(2, 6, 364),
                arguments(2, 7, 446),
                arguments(2, 8, 540),
                arguments(2, 9, 645)
        );
    }

    @ParameterizedTest(name = "shrink {0} enhanced cost {1}")
    @MethodSource("enhancedCostProvider")
    void getEnhancedCost(final int extraDriveStructure, final int shrink, final int expectedValue) {
        when(ship.getDriveSpacesWithoutArmor(anyDouble())).thenReturn(4d);
        when(ship.getDriveGeneration()).thenReturn(3.4d);
        instance = instance.setExtraDriveStructure(extraDriveStructure).setShrink(shrink);
        assertEquals(expectedValue, instance.getEnhancedCost());
    }

    private static Stream<Arguments> enhancedCostProvider() {
        return Stream.of(
                arguments(0, 0, 79),
                arguments(0, 1, 95),
                arguments(0, 2, 128),
                arguments(0, 3, 185),
                arguments(0, 4, 278),
                arguments(0, 5, 421),
                arguments(0, 6, 631),
                arguments(0, 7, 929),
                arguments(0, 8, 1340),
                arguments(0, 9, 1892),
                arguments(2, 0, 118),
                arguments(2, 1, 142),
                arguments(2, 2, 191),
                arguments(2, 3, 277),
                arguments(2, 4, 417),
                arguments(2, 5, 631),
                arguments(2, 6, 946),
                arguments(2, 7, 1393),
                arguments(2, 8, 2010),
                arguments(2, 9, 2838)
        );
    }

    @Test
    void getCrewRequired() {
        when(ship.getDriveSpacesWithoutArmor(anyDouble())).thenReturn(4d);
        double expectedValue = 2d;
        assertEquals(expectedValue, instance.getCrewRequirement());

        expectedValue = 3d;
        instance = instance.setExtraDriveStructure(2);
        assertEquals(expectedValue, instance.getCrewRequirement());
    }

    @ParameterizedTest(name = "shrink {0} duel cost {1}")
    @MethodSource("duelCostProvider")
    void getDuelCost(final int extraDriveStructure, final int shrink, final int expectedValue) {
        when(ship.getDriveSpacesWithoutArmor(anyDouble())).thenReturn(4d);
        when(ship.getDriveGeneration()).thenReturn(3.4d);
        instance = instance.setExtraDriveStructure(extraDriveStructure).setShrink(shrink);
        assertEquals(expectedValue, instance.getDuelCost());
    }

    private static Stream<Arguments> duelCostProvider() {
        return Stream.of(
                arguments(0, 0, 79),
                arguments(0, 1, 95),
                arguments(0, 2, 128),
                arguments(0, 3, 185),
                arguments(0, 4, 278),
                arguments(0, 5, 421),
                arguments(0, 6, 631),
                arguments(0, 7, 929),
                arguments(0, 8, 1340),
                arguments(0, 9, 1892),
                arguments(2, 0, 118),
                arguments(2, 1, 142),
                arguments(2, 2, 191),
                arguments(2, 3, 277),
                arguments(2, 4, 417),
                arguments(2, 5, 631),
                arguments(2, 6, 946),
                arguments(2, 7, 1393),
                arguments(2, 8, 2010),
                arguments(2, 9, 2838)
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
        when(ship.getDriveSpacesWithoutArmor(anyDouble())).thenReturn(4d);
        when(ship.getDriveGeneration()).thenReturn(3.4d);
        instance.setProductionLevel(productionLevel);
        assertEquals(expectedValue, instance.getEconomicCost());
    }

    private static Stream<Arguments> economicCostProvider() {
        return Stream.of(
                arguments(PROTOTYPE, 316),
                arguments(LIMITED, 158),
                arguments(STANDARD, 79),
                arguments(MASS, 40)
        );
    }

    @Test
    void getMaintenanceRate() {
        double expectedValue = 0.2d;
        assertEquals(expectedValue, instance.getMaintenanceRate());
    }

    @ParameterizedTest(name = "shrink {0} maintenance cost {1}")
    @MethodSource("maintenanceCostProvider")
    void getMaintenanceCost(final int shrink, final double expectedValue) {
        when(ship.getDriveSpacesWithoutArmor(anyDouble())).thenReturn(4d);
        when(ship.getDriveGeneration()).thenReturn(3.4d);
        instance = instance.setShrink(shrink);
        assertEquals(expectedValue, instance.getMaintenanceCost(), tolerance);
    }

    private static Stream<Arguments> maintenanceCostProvider() {
        return Stream.of(
                arguments(0, 15.8d),
                arguments(1, 19d),
                arguments(2, 25.6d),
                arguments(3, 37d),
                arguments(4, 55.6d),
                arguments(5, 84.2d),
                arguments(6, 126.2d),
                arguments(7, 185.8d),
                arguments(8, 268d),
                arguments(9, 378.4d)
        );
    }

}