package com.senorpez.avt.shipdesigner;

import java.util.List;

public class WeaponsLoadCharacteristics {
    private final ShipCharacteristics shipCharacteristics;

    public WeaponsLoadCharacteristics(ShipCharacteristics shipCharacteristics) {
        this.shipCharacteristics = shipCharacteristics;
    }

    int getTotalNumberOfWeapons() {
        return Double.valueOf(Math.ceil(shipCharacteristics.getShipSpaces() * TotalWeaponsSpaces_10Worlds.getTotalWeaponsSpaces(shipCharacteristics.getShipSpaces()))).intValue();
    }

    int getLargestWeaponAllowed() {
        return shipCharacteristics.getHullShape().getLargestWeaponMountable(shipCharacteristics.getShipSpaces());
    }

    int getLargestWeaponsAllowed_Keel() {
        // TODO: Placeholder
        return 0;
    }

    List<WeaponMount> getLargestWeaponMountsAllowed() {
        // TODO: Placeholder
        return null;
    }

    int getMaximumNumberOfLines() {
        // TODO: Placeholder
        return 0;
    }

    record WeaponMount(int lines, int spaces) {

    }
}
