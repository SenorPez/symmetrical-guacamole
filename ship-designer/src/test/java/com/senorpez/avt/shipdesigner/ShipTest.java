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
//        instance.setHullSpaces(25)
//                .setHullShape(SPHERE)
//                .setDriveGeneration(3.4)
//                .setThrust(6)
//                .build();
//        instance.internalArmor = 4;
//        instance.mastArmor = 1;
//        instance.lanternArmor = 1;
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
        doReturn(115.82646d).when(instance).getDriveMass(anyDouble(), anyDouble());

        double expectedValue = 4.63306d;
        assertEquals(expectedValue, instance.getDriveHullSpaces(21.42413d, 0.6546777d), tolerance);
    }

    @Test
    void getDriveMass() {
        doReturn(82.93750d).when(instance).getLanternMass();
        doReturn(32.88896d).when(instance).getMastMass(anyDouble(), anyDouble());

        double expectedValue = 115.82646d;
        assertEquals(expectedValue, instance.getDriveMass(21.42413d, 0.6546777d), tolerance);
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
        doReturn(3.35708d).when(instance).getMastStructureMass(anyDouble());
        doReturn(4.80657d).when(instance).getMastArmorMass(anyDouble());
        doReturn(24.72531d).when(instance).getShieldMass(anyDouble(), anyDouble());

        double expectedValue = 32.88896d;
        assertEquals(expectedValue, instance.getMastMass(21.42413d, 0.6546777d), tolerance);
    }

    @Test
    void getMastStructuralMass() {
        instance.setHullSpaces(25).setThrust(6);
        doReturn(1.5d).when(instance).getMastMassModifier();

        double expectedValue = 3.35708d;
        assertEquals(expectedValue, instance.getMastStructureMass(21.42413d), tolerance);
    }

    @Test
    void calculateMastLength() {
        // TODO: Need to test.
    }

    @Test
    void getPivotAcceleration() {
        double tolerance = 1e-4;
        doReturn(151.25000d).when(instance).getPivotThrust();
        doReturn(0.1853223d).when(instance).getDriveFraction(anyDouble());
        doReturn(14.62142d).when(instance).getHullLength(anyDouble());
        doReturn(10.95445d).when(instance).getLanternDiameter();
        doReturn(124065.29666d).when(instance).getMomentOfInertia(anyDouble(), anyDouble());

        double expectedValue = 53.39362d;
        assertEquals(expectedValue, instance.getPivotAccel(21.42413d, 0.6546777d), tolerance);
    }

    @Test
    void getPivotThrust() {
        instance.setHullSpaces(25);
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
        instance.setHullSpaces(25);
        doReturn(0.1600000d).when(instance).getArmorFraction();
        doReturn(115.82646d).when(instance).getDriveMass(anyDouble(), anyDouble());

        double expectedValue = 0.1853223d;
        assertEquals(expectedValue, instance.getDriveFraction(21.42413d), tolerance);
    }

    @Test
    void getHullLength() {
        doReturn(0.1600000d).when(instance).getArmorFraction();
        doReturn(0.1853223d).when(instance).getDriveFraction(anyDouble());

        double expectedValue = 14.62142d;
        assertEquals(expectedValue, instance.getHullLength(21.42413d), tolerance);
    }

    @Test
    void getHullDiameter() {
        doReturn(0.1600000d).when(instance).getArmorFraction();
        doReturn(0.1853223d).when(instance).getDriveFraction(anyDouble());

        double expectedValue = 14.62142d;
        assertEquals(expectedValue, instance.getHullDiameter(21.42413d), tolerance);
    }

    @Test
    void getArmorFraction() {
        instance.setHullSpaces(25);
        doReturn(4).when(instance).getHullArmor();

        double expectedValue = 0.1600000d;
        assertEquals(expectedValue, instance.getArmorFraction(), tolerance);
    }

    @Test
    void getHullArmor() {
        instance.externalArmor = 1;
        instance.internalArmor = 1;

        int expectedValue = 2;
        assertEquals(expectedValue, instance.getHullArmor());
    }

    @Test
    void getMomentOfInertia() {
        final double tolerance = 1e-1;

        doReturn(0.6546777d).when(instance).getUsableFraction(anyDouble());
        doReturn(0.1853223d).when(instance).getDriveFraction(anyDouble());
        doReturn(82.93750d).when(instance).getLanternMass();
        doReturn(10.95445d).when(instance).getLanternDiameter();
        doReturn(3.35708d).when(instance).getMastStructureMass(anyDouble());
        doReturn(4.80657d).when(instance).getMastArmorMass(anyDouble());
        doReturn(32.88896d).when(instance).getMastMass(anyDouble(), anyDouble());
        instance.setHullSpaces(25)
                .setHullShape(SPHERE)
                .build();

        double expectedValue = 124065.29666d;
        assertEquals(expectedValue, instance.getMomentOfInertia(21.42413d, 0.6546777d), tolerance);
    }

    @Test
    void getUsableFraction() {
        final double mastLength = 22.39590d;
        doReturn(0.1600000d).when(instance).getArmorFraction();
        doReturn(0.1853223d).when(instance).getDriveFraction(anyDouble());

        double expectedValue = 0.6546777d;
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
        instance.mastArmor = 1;
        double expectedValue = 4.80657d;
        assertEquals(expectedValue, instance.getMastArmorMass(21.42413d), tolerance);
    }

    @Test
    void getShieldMass() {
        final double tolerance = 1e-4;
        doReturn(78954.19135d).when(instance).getNeutronFluxAtShield();
        doReturn(24.12276d).when(instance).getRadReductionDueToMast(anyDouble());
        doReturn(4.50952d).when(instance).getShieldCrossSection(anyDouble(), anyDouble());

        double expectedValue = 24.72531d;
        assertEquals(expectedValue, instance.getShieldMass(21.42413d, 0.6546777d), tolerance);
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
        doReturn(10.95445d).when(instance).getLanternDiameter();

        double expectedValue = 24.12276d;
        assertEquals(expectedValue, instance.getRadReductionDueToMast(21.42413d), tolerance);
    }

    @Test
    void getShieldCrossSection() {
        final double tolerance = 1e-4;
        doReturn(2.39618d).when(instance).getShieldMinDiameter(anyDouble(), anyDouble());

        double expectedValue = 4.50952d;
        assertEquals(expectedValue, instance.getShieldCrossSection(21.42413d, 0.6546777d), tolerance);
    }

    @Test
    void getShieldDiameter() {
        doReturn(10.95445d).when(instance).getLanternDiameter();

        double expectedValue = 2.39618d;
        assertEquals(expectedValue, instance.getShieldMinDiameter(21.42413d, 0.6546777d), tolerance);
    }
}