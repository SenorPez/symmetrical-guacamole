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
}