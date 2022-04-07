package com.senorpez.avt.shipdesigner.weapons;

import com.senorpez.avt.shipdesigner.Ship;
import com.senorpez.avt.shipdesigner.enums.MountConfiguration;
import com.senorpez.avt.shipdesigner.enums.MountType;
import com.senorpez.avt.shipdesigner.enums.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
    void getMountTotalSpaces() {
        when(ship.getHullSize()).thenReturn(106);
        when(ship.getLaidDown()).thenReturn(2186);
        when(ship.getShape()).thenReturn(Shape.CONICAL);
        when(ship.getMountConfiguration()).thenReturn(MountConfiguration.TRIPLE);

        instance = instance.setMountType(MountType.PRIMARY);
        int expectedValue = 5;
        assertEquals(expectedValue, instance.getMountTotalSpaces());

        instance.setMountType(MountType.SECONDARY);
        expectedValue = 2;
        assertEquals(expectedValue, instance.getMountTotalSpaces());

        instance.setMountType(MountType.TERTIARY);
        expectedValue = 1;
        assertEquals(expectedValue, instance.getMountTotalSpaces());
    }

    @Test
    void getMountRows() {
        when(ship.getHullSize()).thenReturn(106);
        when(ship.getLaidDown()).thenReturn(2186);
        when(ship.getShape()).thenReturn(Shape.CONICAL);
        when(ship.getMountConfiguration()).thenReturn(MountConfiguration.TRIPLE);

        instance = instance.setMountType(MountType.PRIMARY);
        int expectedValue = 3;
        assertEquals(expectedValue, instance.getMountRows());

        instance.setMountType(MountType.SECONDARY);
        expectedValue = 2;
        assertEquals(expectedValue, instance.getMountRows());

        instance.setMountType(MountType.TERTIARY);
        expectedValue = 1;
        assertEquals(expectedValue, instance.getMountRows());
    }

    @Test
    void getMountBiggestWeaponSpaces() {
        when(ship.getHullSize()).thenReturn(106);
        when(ship.getLaidDown()).thenReturn(2186);
        when(ship.getShape()).thenReturn(Shape.CONICAL);
        when(ship.getMountConfiguration()).thenReturn(MountConfiguration.TRIPLE);

        instance = instance.setMountType(MountType.PRIMARY);
        int expectedValue = 3;
        assertEquals(expectedValue, instance.getMountBiggestWeaponSpaces());

        instance.setMountType(MountType.SECONDARY);
        expectedValue = 2;
        assertEquals(expectedValue, instance.getMountBiggestWeaponSpaces());

        instance.setMountType(MountType.TERTIARY);
        expectedValue = 2;
        assertEquals(expectedValue, instance.getMountBiggestWeaponSpaces());
    }
}