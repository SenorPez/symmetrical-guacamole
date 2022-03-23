package com.senorpez.avt.shipdesigner;

import com.senorpez.avt.shipdesigner.WeaponsLoadCharacteristics.WeaponMount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeaponsLoadCharacteristicsTest {
    @Mock
    ShipCharacteristics shipCharacteristics;

    private WeaponsLoadCharacteristics instance;

    @BeforeEach
    void setUp() {
        instance = new WeaponsLoadCharacteristics(shipCharacteristics);
    }

    @Test
    void getTotalNumberOfWeapons() {
        when(shipCharacteristics.getHullShape()).thenReturn(Shape.ELLIPSOID);
        when(shipCharacteristics.getShipSpaces()).thenReturn(55);
        int expectedValue = 10;

        assertEquals(expectedValue, instance.getTotalNumberOfWeapons());
    }

    @Test
    void getLargestWeaponAllowed() {
        when(shipCharacteristics.getHullShape()).thenReturn(Shape.ELLIPSOID);
        when(shipCharacteristics.getShipSpaces()).thenReturn(55);
        int expectedValue = 2;

        assertEquals(expectedValue, instance.getLargestWeaponAllowed());
    }

    @Test
    void getLargestWeaponsAllowed_Keel() {
        when(shipCharacteristics.getHullShape()).thenReturn(Shape.ELLIPSOID);
        when(shipCharacteristics.getShipSpaces()).thenReturn(55);
        int expectedValue = 2;

        assertEquals(expectedValue, instance.getLargestWeaponsAllowed_Keel());
    }

    @Test
    void getLargestWeaponMountsAllowed() {
        when(shipCharacteristics.getHullShape()).thenReturn(Shape.ELLIPSOID);
        when(shipCharacteristics.getShipSpaces()).thenReturn(55);

        WeaponMount expectedValueOption1 = new WeaponMount(2, 3);
        WeaponMount expectedValueOption2 = new WeaponMount(4, 2);

        assertEquals(expectedValueOption1, instance.getLargestWeaponMountsAllowed().get(0));
        assertEquals(expectedValueOption2, instance.getLargestWeaponMountsAllowed().get(1));
    }

    @Test
    void getMaximumNumberOfLines() {
        when(shipCharacteristics.getHullShape()).thenReturn(Shape.ELLIPSOID);
        when(shipCharacteristics.getShipSpaces()).thenReturn(55);
        int expectedValue = 2;

        assertEquals(expectedValue, instance.getMaximumNumberOfLines());
    }
}
