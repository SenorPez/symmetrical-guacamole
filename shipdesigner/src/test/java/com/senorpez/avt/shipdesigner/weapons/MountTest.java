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

import java.util.List;

import static com.senorpez.avt.shipdesigner.enums.AVIDWindow.*;
import static com.senorpez.avt.shipdesigner.systems.ProductionLevel.LIMITED;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MountTest {
    @Mock
    Ship ship;
    @Mock
    List<Weapon> weapons;
    @Mock
    Weapon weapon;

    Mount instance;

    @BeforeEach
    void setUp() {
        weapons = List.of(weapon);
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

    @Test
    void getDuelCost() {
        when(ship.getArmorShrink()).thenReturn(1);
        when(ship.getShape()).thenReturn(Shape.CONICAL);
        when(weapon.getSpacesUsed()).thenReturn(3);
        when(weapon.getDuelCost()).thenReturn(9);

        instance = instance
                .setWeapons(weapons)
                .setShutterArmor(0)
                .setFiringArc(List.of(
                        GREEN_UP_NOSE,
                        BLUE_UP_NOSE_PORT, BLUE_UP_NOSE, BLUE_UP_NOSE_STARBOARD,
                        YELLOW_NOSE_PORT, YELLOW_NOSE, YELLOW_NOSE_STARBOARD));
        int expectedValue = 21;
        assertEquals(expectedValue, instance.getDuelCost());
    }

    @Test
    void getEconomicCost() {
        when(ship.getArmorShrink()).thenReturn(1);
        when(ship.getShape()).thenReturn(Shape.CONICAL);
        when(weapon.getSpacesUsed()).thenReturn(3);
        when(weapon.getDuelCost()).thenReturn(9);

        instance = instance
                .setWeapons(weapons)
                .setProductionLevel(LIMITED)
                .setShutterArmor(0)
                .setFiringArc(List.of(
                        GREEN_UP_NOSE,
                        BLUE_UP_NOSE_PORT, BLUE_UP_NOSE, BLUE_UP_NOSE_STARBOARD,
                        YELLOW_NOSE_PORT, YELLOW_NOSE, YELLOW_NOSE_STARBOARD));
        int expectedValue = 33;
        assertEquals(expectedValue, instance.getEconomicCost(ship));
    }

    @Test
    void getHeatExchangers() {
        instance = instance
                .setWeapons(weapons);
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getHeatExchangers());
    }

    @Test
    void getFlashCoolers() {
        instance = instance
                .setWeapons(weapons);
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getFlashCoolers());
    }
}