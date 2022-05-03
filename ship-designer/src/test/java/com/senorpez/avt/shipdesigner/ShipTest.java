package com.senorpez.avt.shipdesigner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.senorpez.avt.shipdesigner.HullShape.SPHERE;
import static com.senorpez.avt.shipdesigner.validators.HullSpacesValidator.hullSpacesOutOfBounds;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
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
                .setHullShape(SPHERE)
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
        doReturn(94.92721d).when(instance).getDriveMass(anyDouble());

        double expectedValue = 3.79709d;
        assertEquals(expectedValue, instance.getDriveHullSpaces(), tolerance);
    }

    @Test
    void getDriveMass() {
        doReturn(73.51272d).when(instance).getLanternMass();
        doReturn(21.41449d).when(instance).getMastMass(anyDouble());

        double expectedValue = 94.92721d;
        assertEquals(expectedValue, instance.getDriveMass(22.39590d), tolerance);
    }

    @Test
    void getLanternMass() {
        doReturn(73.51272d).when(instance).getLanternStructureMass();
        doReturn(9.42478d).when(instance).getLanternArmorMass();

        double expectedValue = 82.93750d;
        assertEquals(expectedValue, instance.getLanternMass(), tolerance);
    }

    @ParameterizedTest(name = "{0} drive output => {1} lantern mass")
    @MethodSource("lanternStructuralMassProvider")
    void getLanternStructuralMass(double driveOutput, double expectedValue) {
        double tolerance = 1e-3;
        doReturn(driveOutput).when(instance).getDriveOutput();
        assertEquals(expectedValue, instance.getLanternStructureMass(), tolerance);
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
    void getDriveOutput(int hullSpaces, double shipMaxAcceleration, double driveGeneration, double expectedValue) {
        doReturn(22.39590d).when(instance).calculateMastLength();
        instance.setHullSpaces(hullSpaces).build();
        instance.maxAcceleration = shipMaxAcceleration;
        instance.driveGeneration = driveGeneration;

        assertEquals(expectedValue, instance.getDriveOutput(), tolerance);
    }

    private static Stream<Arguments> driveOutputProvider() {
        return Stream.of(
                arguments(25, 1.5d, 3.4d, 0.54041d),
                arguments(25, 3d, 3.4d, 1.08082d),
                arguments(25, 1.5d, 2.0d, 0.31789d),
                arguments(25, 3d, 2.0d, 0.63578d),
                arguments(100, 1.5d, 3.4d, 2.16165d),
                arguments(100, 3d, 3.4d, 4.32330d),
                arguments(100, 1.5d, 2.0d, 1.27156d),
                arguments(100, 3d, 2.0d, 2.54312d)
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
    void getLanternDiameter(int hullSpaces, double shipMaxAcceleration, double expectedValue) {
        doReturn(22.39590d).when(instance).calculateMastLength();
        instance.setHullSpaces(hullSpaces).build();
        instance.maxAcceleration = shipMaxAcceleration;

        assertEquals(expectedValue, instance.getLanternDiameter(), tolerance);
    }

    private static Stream<Arguments> lanternDiameterProvider() {
        return Stream.of(
                arguments(25, 1.5d, 10.95445d),
                arguments(25, 3d, 15.49193d),
                arguments(100, 1.5d, 21.90890d),
                arguments(100, 3d, 30.98387d)
        );
    }

    @Test
    void getMastMass() {
        double mastLength = 22.39590d;
        doReturn(3.50936d).when(instance).getMastStructureMass(anyDouble());
        doReturn(5.25249d).when(instance).getMastArmorMass();
        doReturn(24.05152d).when(instance).getShieldMass();

        double expectedValue = 32.81337d;
        assertEquals(expectedValue, instance.getMastMass(mastLength), tolerance);
    }

    @Test
    void getMastStructuralMass() {
        double mastLength = 22.39590d;
        doReturn(mastLength).when(instance).calculateMastLength();
        instance.setHullSpaces(25).build();
        instance.maxAcceleration = 1.5d;
        instance.mastLength = 22.39590d;
        doReturn(1.5d).when(instance).getMastMassModifier();

        double expectedValue = 3.50936d;
        assertEquals(expectedValue, instance.getMastStructureMass(mastLength), tolerance);
    }

    @Test
    void calculateMastLength() {
        // TODO: Need to test.
    }

    @Test
    void getPivotAcceleration() {
        double mastLength = 22.39590d;
        double tolerance = 1e-4;
        doReturn(151.25000d).when(instance).getPivotThrust();
        doReturn(0.1701217d).when(instance).getDriveFraction(anyDouble());
        doReturn(14.94895d).when(instance).getHullLength(anyDouble());
        doReturn(10.95445d).when(instance).getLanternDiameter();
        doReturn(124730.07776d).when(instance).getMomentOfInertia(anyDouble());

        double expectedValue = 56.57713d;
        assertEquals(expectedValue, instance.getPivotAccel(mastLength), tolerance);
    }

    @Test
    void getPivotThrust() {
        doReturn(22.39590d).when(instance).calculateMastLength();
        instance.setHullSpaces(25).build();
        doReturn(1.25d).when(instance).getPivotModifier();

        double expectedValue = 151.25000d;
        assertEquals(expectedValue, instance.getPivotThrust(), tolerance);
        instance.pivotThrustOverride = 42;
        expectedValue = 42d;
        assertEquals(expectedValue, instance.getPivotThrust(), tolerance);
    }

    @Test
    void getPivotModifier() {
        instance = instance
                .setHullShape(SPHERE)
                .build();

        double expectedValue = 1.25d;
        assertEquals(expectedValue, instance.getPivotModifier(), tolerance);
    }

    @Test
    void getDriveFraction() {
        double mastLength = 22.39590d;
        doReturn(22.39590d).when(instance).calculateMastLength();
        instance.setHullSpaces(25).build();
        doReturn(106.32609d).when(instance).getDriveMass(anyDouble());

        double expectedValue = 0.1701217d;
        assertEquals(expectedValue, instance.getDriveFraction(mastLength), tolerance);
    }

    @Test
    void getHullLength() {
        double mastLength = 22.39590d;
        instance = instance
                .setHullShape(SPHERE)
                .setHullSpaces(25)
                .build();
        doReturn(0.1600000d).when(instance).getArmorFraction();
        doReturn(0.1701217d).when(instance).getDriveFraction(anyDouble());

        double expectedValue = 14.73372d;
        assertEquals(expectedValue, instance.getHullLength(mastLength), tolerance);
    }

    @Test
    void getArmorFraction() {
        instance = instance.setHullSpaces(25);
        doReturn(4).when(instance).getHullArmor();

        double expectedValue = 0.1600000d;
        assertEquals(expectedValue, instance.getArmorFraction(), tolerance);
    }

    @Test
    void getHullArmor() {
        instance.externalArmor = 1;
        instance.internalArmor = 4;

        int expectedValue = 5;
        assertEquals(expectedValue, instance.getHullArmor());
    }

    @Test
    void getMomentOfInertia() {
        final double tolerance = 1e-1;
        final double mastLength = 22.39590d;
        doReturn(mastLength).when(instance).calculateMastLength();
        doReturn(0.6698783d).when(instance).getUsableFraction(anyDouble());
        doReturn(73.51272d).when(instance).getLanternMass();
        doReturn(10.95445d).when(instance).getLanternDiameter();
        doReturn(3.50936d).when(instance).getMastStructureMass(anyDouble());
        doReturn(5.25249d).when(instance).getMastArmorMass();
        doReturn(32.81337d).when(instance).getMastMass(anyDouble());
        instance.setHullSpaces(25)
                .setHullShape(SPHERE)
                .build();

        double expectedValue = 123728.46426d;
        assertEquals(expectedValue, instance.getMomentOfInertia(mastLength), tolerance);
    }

    @Test
    void getUseableFraction() {
        final double mastLength = 22.39590d;
        doReturn(0.1600000d).when(instance).getArmorFraction();
        doReturn(0.1701217d).when(instance).getDriveFraction(anyDouble());

        double expectedValue = 0.6698783d;
        assertEquals(expectedValue, instance.getUsableFraction(mastLength), tolerance);
    }
}