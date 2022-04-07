package com.senorpez.avt.shipdesigner.weapons;

import com.senorpez.avt.shipdesigner.Ship;
import com.senorpez.avt.shipdesigner.enums.MountConfiguration;
import com.senorpez.avt.shipdesigner.enums.MountType;
import com.senorpez.avt.shipdesigner.enums.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MountTest {
    @Mock
    Ship ship;

    Mount instance;

    @BeforeEach
    void setUp() {
        instance = new Mount().setShip(ship);
    }

    @Test
    void getMaximumMountSpaces() {
        when(ship.getHullSize()).thenReturn(106);
        when(ship.getLaidDown()).thenReturn(2186);
        when(ship.getShape()).thenReturn(Shape.CONICAL);
        when(ship.getMountConfiguration()).thenReturn(MountConfiguration.TRIPLE);

        instance = instance.setMountType(MountType.PRIMARY);
        int expectedValue = 5;
        assertEquals(expectedValue, instance.getMaximumMountSpaces());

        instance.setMountType(MountType.SECONDARY);
        expectedValue = 2;
        assertEquals(expectedValue, instance.getMaximumMountSpaces());

        instance.setMountType(MountType.TERTIARY);
        expectedValue = 1;
        assertEquals(expectedValue, instance.getMaximumMountSpaces());
    }
}