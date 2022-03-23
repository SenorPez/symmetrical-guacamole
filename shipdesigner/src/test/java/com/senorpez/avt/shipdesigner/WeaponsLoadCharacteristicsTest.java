package com.senorpez.avt.shipdesigner;

import com.senorpez.avt.shipdesigner.WeaponsLoadCharacteristics.WeaponMount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeaponsLoadCharacteristicsTest {
    private WeaponsLoadCharacteristics instance;

    @Test
    void getTotalNumberOfWeapons() {
        int expectedValue = 10;

        assertEquals(expectedValue, instance.getTotalNumberOfWeapons());
    }

    @Test
    void getLargestWeaponsAllowed() {
        int expectedValue = 2;

        assertEquals(expectedValue, instance.getLargestWeaponsAllowed());
    }

    @Test
    void getLargestWeaponsAllowed_Keel() {
        int expectedValue = 2;

        assertEquals(expectedValue, instance.getLargestWeaponsAllowed_Keel());
    }

    @Test
    void getLargestWeaponMountsAllowed() {
        WeaponMount expectedValueOption1 = new WeaponMount(2, 3);
        WeaponMount expectedValueOption2 = new WeaponMount(4, 2);

        assertEquals(expectedValueOption1, instance.getLargestWeaponMountsAllowed().get(0));
        assertEquals(expectedValueOption2, instance.getLargestWeaponMountsAllowed().get(1));
    }

    @Test
    void getMaximumNumberOfLines() {
        int expectedValue = 2;

        assertEquals(expectedValue, instance.getMaximumNumberOfLines());
    }
}
