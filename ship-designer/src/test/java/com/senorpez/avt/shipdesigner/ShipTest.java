package com.senorpez.avt.shipdesigner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.senorpez.avt.shipdesigner.validators.HullSpacesValidator.hullSpacesOutOfBounds;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

class ShipTest {
    Ship instance;

    private final double tolerance = 1e-5;

    @BeforeEach
    void setUp() {
        instance = spy(new Ship());
    }

    @DisplayName("Validate Hull Spaces")
    @ParameterizedTest(name = "{0} hull spaces => {1}")
    @MethodSource("hullSpacesProvider")
    void validateHullSpaces(final int hullSpaces, final boolean expectedValue) {
        instance = instance
                .setHullSpaces(hullSpaces)
                .build();

        assertEquals(expectedValue, instance.isValid());

        if (instance.isValid()) assertFalse(instance.getValidationErrors().contains(hullSpacesOutOfBounds));
        else assertTrue(instance.getValidationErrors().contains(hullSpacesOutOfBounds));
    }

    private static Stream<Arguments> hullSpacesProvider() {
        return IntStream.iterate(0, v -> v <= 3000, v -> v + 25).mapToObj(v -> arguments(v, v >= 25 && v <= 2500));
    }

    @Test
    void getDriveHullSpaces() {
        doReturn(94.92721d).when(instance).getDriveMass();

        double expectedValue = 3.79709d;
        assertEquals(expectedValue, instance.getDriveHullSpaces(), tolerance);
    }

    @Test
    void getDriveMass() {
        doReturn(73.51272d).when(instance).getLanternMass();
        doReturn(21.41449d).when(instance).getMastMass();

        double expectedValue = 94.92721d;
        assertEquals(expectedValue, instance.getDriveMass(), tolerance);
    }

    @Test
    void getLanternMass() {
        doReturn(73.51272d).when(instance).getLanternStructuralMass();
        doReturn(9.42478d).when(instance).getLanternArmorMass();

        double expectedValue = 82.93750d;
        assertEquals(expectedValue, instance.getLanternMass(), tolerance);
    }

    @ParameterizedTest(name = "{0} drive output => {1} lantern mass")
    @MethodSource("lanternStructuralMassProvider")
    void getLanternStructuralMass(double driveOutput, double expectedValue) {
        double tolerance = 1e-3;
        doReturn(driveOutput).when(instance).getDriveOutput();
        assertEquals(expectedValue, instance.getLanternStructuralMass(), tolerance);
    }

    private static Stream<Arguments> lanternStructuralMassProvider() {
        return Stream.of(
                arguments(0.54041d, 73.51272d),
                arguments(1.17089d, 117.08926d),
                arguments(4.32330d, 449.46145d)
        );
    }

    @ParameterizedTest(name = "{0} tons, {1} g, Gen {2}")
    @MethodSource("driveOutputProvider")
    void getDriveOutput(int shipMass, double shipMaxAcceleration, double driveGeneration, double expectedValue) {
        instance.shipMass = shipMass;
        instance.shipMaxAcceleration = shipMaxAcceleration;
        instance.driveGeneration = driveGeneration;

        assertEquals(expectedValue, instance.getDriveOutput(), tolerance);
    }

    private static Stream<Arguments> driveOutputProvider() {
        return Stream.of(
                arguments(625, 1.5d, 3.4d, 0.54041d),
                arguments(625, 3d, 3.4d, 1.08082d),
                arguments(625, 1.5d, 2.0d, 0.31789d),
                arguments(625, 3d, 2.0d, 0.63578d),
                arguments(2500, 1.5d, 3.4d, 2.16165d),
                arguments(2500, 3d, 3.4d, 4.32330d),
                arguments(2500, 1.5d, 2.0d, 1.27156d),
                arguments(2500, 3d, 2.0d, 2.54312d)
        );
    }

    @ParameterizedTest(name = "{0} lantern armor, {1} armor shrink => {2} lantern armor mass")
    @MethodSource("lanternArmorMassProvider")
    void getLanternArmorMass(final int lanternArmor, final int armorShrink, final double expectedValue) {
        instance.lanternArmor = lanternArmor;
        instance.armorShrink = armorShrink;
        doReturn(188.49556d).when(instance).getLanternCoverageSurface();

        assertEquals(expectedValue, instance.getLanternArmorMass(), tolerance);
    }

    private static Stream<Arguments> lanternArmorMassProvider() {
        return Stream.of(
                arguments(0, 0, 0d),
                arguments(0, 1, 0d),
                arguments(0, 2, 0d),
                arguments(1, 0, 9.42478d),
                arguments(1, 1, 8.97598d),
                arguments(1, 2, 8.56798d),
                arguments(2, 0, 18.84956d),
                arguments(2, 1, 17.95196d),
                arguments(2, 2, 17.13596d)
        );
    }

    @Test
    void getLanternCoverageSurface() {
        double tolerance = 1e-4;
        doReturn(10.95445d).when(instance).getLanternDiameter();

        double expectedValue = 188.49556d;
        assertEquals(expectedValue, instance.getLanternCoverageSurface(), tolerance);
    }

    @ParameterizedTest(name = "{0} tons, {1} g")
    @MethodSource("lanternDiameterProvider")
    void getLanternDiameter(int shipMass, double shipMaxAcceleration, double expectedValue) {
        instance.shipMass = shipMass;
        instance.shipMaxAcceleration = shipMaxAcceleration;

        assertEquals(expectedValue, instance.getLanternDiameter(), tolerance);
    }

    private static Stream<Arguments> lanternDiameterProvider() {
        return Stream.of(
                arguments(625, 1.5d, 10.95445d),
                arguments(625, 3d, 15.49193d),
                arguments(2500, 1.5d, 21.90890d),
                arguments(2500, 3d, 30.98387d)
        );
    }

    @Test
    void getMastMass() {
        doReturn(3.50936d).when(instance).getMastStructuralMass();
        doReturn(5.25249d).when(instance).getMastArmorMass();
        doReturn(24.05152d).when(instance).getShieldMass();

        double expectedValue = 32.81337d;
        assertEquals(expectedValue, instance.getMastMass(), tolerance);
    }

    @Test
    void getMastStructuralMass() {
        instance.shipMass = 625;
        instance.shipMaxAcceleration = 1.5d;
        doReturn(22.39590d).when(instance).getMastLength();
        doReturn(1.5d).when(instance).getMastMassModifier();

        double expectedValue = 3.50936d;
        assertEquals(expectedValue, instance.getMastStructuralMass(), tolerance);
    }
}