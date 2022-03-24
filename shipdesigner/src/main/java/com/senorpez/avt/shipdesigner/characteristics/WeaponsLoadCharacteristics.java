package com.senorpez.avt.shipdesigner.characteristics;

import java.util.ArrayList;
import java.util.List;

record WeaponsLoadCharacteristics(ShipCharacteristics shipCharacteristics) {
    int getTotalNumberOfWeapons() {
        return Double.valueOf(Math.ceil(shipCharacteristics.getShipSpaces() * shipCharacteristics.getHullShape().getTotalWeaponSpaces(shipCharacteristics.getShipSpaces()))).intValue();
    }

    int getLargestWeaponAllowed() {
        return shipCharacteristics.getHullShape().getLargestWeaponAllowed(shipCharacteristics.getShipSpaces());
    }

    int getLargestWeaponsAllowed_Keel() {
        return shipCharacteristics.getHullShape().getLargestWeaponAllowed_Keel(shipCharacteristics.getShipSpaces());
    }

    List<WeaponMount> getLargestWeaponMountsAllowed() {
        final int shipSpaces = shipCharacteristics.getShipSpaces();
        final int spaces1 = Long.valueOf(Math.round(shipSpaces * shipCharacteristics.getHullShape().getLargestMountSpaces_Option1(shipSpaces))).intValue();
        final int spaces2 = Long.valueOf(Math.round(shipSpaces * shipCharacteristics.getHullShape().getLargestMountSpaces_Option2(shipSpaces))).intValue();

        final int lines1 = shipCharacteristics.getHullShape().getLargestMountLines_Option1();
        final int lines2 = shipCharacteristics.getHullShape().getLargestMountLines_Option2();

        final List<WeaponMount> list = new ArrayList<>();
        list.add(new WeaponMount(lines1, spaces1));
        list.add(new WeaponMount(lines2, spaces2));
        return list;
    }

    int getMaximumNumberOfLines() {
        return shipCharacteristics.getHullShape().getMaximumMountLines(shipCharacteristics.getShipSpaces());
    }

    record WeaponMount(int lines, int spaces) {

    }
}
