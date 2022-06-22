package com.senorpez.avt.shipdesigner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.senorpez.avt.shipdesigner.HullShape.SPHERE;
import static com.senorpez.avt.shipdesigner.validators.DriveGenerationValidator.driveGenerationInvalid;
import static com.senorpez.avt.shipdesigner.validators.DriveGenerationValidator.driveGenerationOutOfBounds;
import static com.senorpez.avt.shipdesigner.validators.HullSpacesValidator.hullSpacesOutOfBounds;
import static com.senorpez.avt.shipdesigner.validators.ThrustValidator.thrustInvalid;
import static com.senorpez.avt.shipdesigner.validators.ThrustValidator.thrustOutOfBounds;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

@ExtendWith(MockitoExtension.class)
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
        final double mastLength = 28.20816d;
        doReturn(mastLength).when(instance).calculateMastLength();
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

    @DisplayName("Validate Thrust")
    @ParameterizedTest(name = "{0} thrust => {1} with {2} errors")
    @MethodSource("thrustProvider")
    void validateThrust(final double thrust, final boolean expectedValue, final int errorCount) {
        final double mastLength = 28.20816d;
        doReturn(mastLength).when(instance).calculateMastLength();
        instance = instance
                .setHullSpaces(25)
                .setHullShape(SPHERE)
                .setThrust(thrust)
                .build();

        assertEquals(expectedValue, instance.isValid());
        assertEquals(instance.getValidationErrors().size(), errorCount);

        if (instance.isValid()) assertFalse(instance.getValidationErrors().contains(thrustOutOfBounds));
        if (instance.isValid()) assertFalse(instance.getValidationErrors().contains(thrustInvalid));
    }

    private static Stream<Arguments> thrustProvider() {
        return Stream.of(
                arguments(0.3, false, 2),
                arguments(0.5, true, 0),
                arguments(1.1, false, 1),
                arguments(15.5, true, 0),
                arguments(16, true, 0),
                arguments(17, false, 1)
        );
    }

    @DisplayName("Validate Drive Generation")
    @ParameterizedTest(name = "{0} generation -> {1} with {2} errors")
    @MethodSource("driveGenerationProvider")
    void validateDriveGeneration(final double driveGeneration, final boolean expectedValue, final int errorCount) {
        final double mastLength = 28.20816d;
        doReturn(mastLength).when(instance).calculateMastLength();
        instance = instance
                .setDriveGeneration(driveGeneration)
                .build();

        instance.getValidationErrors().forEach(e -> System.out.printf("%2.1f and %s\n", driveGeneration, e));

        assertEquals(expectedValue, instance.isValid());
        assertEquals(instance.getValidationErrors().size(), errorCount);

        if (instance.isValid()) assertFalse(instance.getValidationErrors().contains(driveGenerationOutOfBounds));
        if (instance.isValid()) assertFalse(instance.getValidationErrors().contains(driveGenerationInvalid));
    }

    private static Stream<Arguments> driveGenerationProvider() {
        return Stream.of(
                arguments(0.5, false, 1),
                arguments(1.0, true, 0),
                arguments(1.1, true, 0),
                arguments(9.0, true, 0),
                arguments(9.1, false, 1)
        );
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
    void getDriveOutput(int hullSpaces, double acceleration, double driveGeneration, double expectedValue) {
        doReturn(22.39590d).when(instance).calculateMastLength();
        instance.setHullSpaces(hullSpaces)
                .setThrust(acceleration * 4)
                .setDriveGeneration(driveGeneration)
                .build();

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
    void getLanternDiameter(int hullSpaces, double acceleration, double expectedValue) {
        doReturn(22.39590d).when(instance).calculateMastLength();
        instance.setHullSpaces(hullSpaces)
                .setThrust(acceleration * 4)
                .build();

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
        doReturn(5.25249d).when(instance).getMastArmorMass(anyDouble());
        doReturn(24.05152d).when(instance).getShieldMass(anyDouble());

        double expectedValue = 32.81337d;
        assertEquals(expectedValue, instance.getMastMass(mastLength), tolerance);
    }

    @Test
    void getMastStructuralMass() {
        double mastLength = 22.39590d;
        doReturn(mastLength).when(instance).calculateMastLength();
        instance.setHullSpaces(25)
                .setThrust(6d)
                .setMastLength(22.39590d)
                .build();
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
        doReturn(14.94895d).when(instance).getHullLength(mastLength);
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
        instance.setHullShape(SPHERE);

        double expectedValue = 1.25d;
        assertEquals(expectedValue, instance.getPivotModifier(), tolerance);
    }

    @Test
    void getDriveFraction() {
        double mastLength = 22.39590d;
        doReturn(0.1600000d).when(instance).getArmorFraction();
        doReturn(97.61132d).when(instance).getDriveMass(anyDouble(), anyDouble());
        instance.setHullSpaces(25).build();

        double expectedValue = 0.1561781d;
        assertEquals(expectedValue, instance.getDriveFraction(mastLength), tolerance);
    }

    @Test
    void getHullLength() {
        double mastLength = 22.39590d;
        doReturn(22.39590d).when(instance).calculateMastLength();
        doReturn(0.1600000d).when(instance).getArmorFraction();
        doReturn(0.1518835d).when(instance).getDriveFraction(anyDouble());
        instance = instance
                .setHullShape(SPHERE)
                .setHullSpaces(25)
                .build();

        double expectedValue = 14.86624d;
        assertEquals(expectedValue, instance.getHullLength(mastLength), tolerance);
    }

    @Test
    void getHullDiameter() {
        double mastLength = 22.39590d;
        doReturn(22.39590d).when(instance).calculateMastLength();
        doReturn(0.1600000d).when(instance).getArmorFraction();
        doReturn(0.1518835d).when(instance).getDriveFraction(anyDouble());
        instance = instance
                .setHullShape(SPHERE)
                .setHullSpaces(25)
                .build();

        double expectedValue = 14.86624d;
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
        final double mastLength = 28.20816d;

        doReturn(0.6882117d).when(instance).getUsableFraction(anyDouble());
        doReturn(0.1522773d).when(instance).getDriveFraction(anyDouble());
        doReturn(73.51272d).when(instance).getLanternMass();
        doReturn(10.95445d).when(instance).getLanternDiameter();
        doReturn(4.39897d).when(instance).getMastStructureMass(anyDouble());
        doReturn(0d).when(instance).getMastArmorMass(anyDouble());
        doReturn(21.35499d).when(instance).getMastMass(anyDouble());
        instance.setHullSpaces(25)
                .setHullShape(SPHERE)
                .build();

        double expectedValue = 150370.46005d;
        assertEquals(expectedValue, instance.getMomentOfInertia(mastLength), tolerance);
    }

    @Test
    void getUsableFraction() {
        final double mastLength = 22.39590d;
        doReturn(0.1600000d).when(instance).getArmorFraction();
        doReturn(0.1701217d).when(instance).getDriveFraction(anyDouble());

        double expectedValue = 0.6698783d;
        assertEquals(expectedValue, instance.getUsableFraction(mastLength), tolerance);
    }

    @Test
    void getMastMassModifier() {
        instance.setHullShape(SPHERE);

        double expectedValue = 1.5d;
        assertEquals(expectedValue, instance.getMastMassModifier(), tolerance);
    }

    @Test
    void getMastArmorMass() {
        final double mastLength = 16.61676d;
        instance.mastArmor = 1;
        instance.armorShrink = 1;

        double expectedValue = 2.75380d;
        assertEquals(expectedValue, instance.getMastArmorMass(mastLength), tolerance);
    }

    @Test
    void getShieldMass() {
        final double tolerance = 1e-4;
        final double mastLength = 28.20816d;
        doReturn(78954.19135d).when(instance).getNeutronFluxAtShield();
        doReturn(37.82351d).when(instance).getRadReductionDueToMast(anyDouble());
        doReturn(3.21401d).when(instance).getShieldCrossSection(anyDouble());

        double expectedValue = 16.99437d;
        assertEquals(expectedValue, instance.getShieldMass(mastLength), tolerance);
    }

    @Test
    void getNeutronFluxAtShield() {
        final double tolerance = 1e0;
        doReturn(0.54041d).when(instance).getDriveOutput();
        doReturn(10.95445d).when(instance).getLanternDiameter();

        double expectedValue = 78954.19135d;
        assertEquals(expectedValue, instance.getNeutronFluxAtShield(), tolerance);
    }

    @Test
    void getRadReductionDueToMast() {
        final double mastLength = 28.20816d;
        doReturn(10.95445d).when(instance).getLanternDiameter();

        double expectedValue = 37.82351d;
        assertEquals(expectedValue, instance.getRadReductionDueToMast(mastLength), tolerance);
    }

    @Test
    void getShieldCrossSection() {
        final double mastLength = 28.20816d;
        doReturn(2.02292d).when(instance).getShieldMinDiameter(anyDouble());

        double expectedValue = 3.21401d;
        assertEquals(expectedValue, instance.getShieldCrossSection(mastLength), tolerance);
    }

    @Test
    void getShieldDiameter() {
        final double mastLength = 28.20816d;
        doReturn(mastLength).when(instance).calculateMastLength();
        doReturn(0.1600000d).when(instance).getArmorFraction();
        doReturn(0.1518835d).when(instance).getDriveFraction(anyDouble());
        doReturn(10.95445d).when(instance).getLanternDiameter();
        instance.setHullSpaces(25)
                .setHullShape(SPHERE)
                .build();

        double expectedValue = 2.01344d;
        assertEquals(expectedValue, instance.getShieldMinDiameter(mastLength), tolerance);
    }
}