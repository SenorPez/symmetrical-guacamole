package com.senorpez.avt.shipdesigner;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShipCharacteristicsTest {
    @Test
    void getShipMass() {
        Ship ship = new Ship().setHullSize(55);
        ShipCharacteristics instance = new ShipCharacteristics(ship);
        Integer expectedValue = 1375;
        assertEquals(expectedValue, instance.getShipMass());
    }

    @Test
    void getShipAcceleration() {
        Ship ship = new Ship().setMaximumThrust(11d);
        ShipCharacteristics instance = new ShipCharacteristics(ship);
        Double expectedValue = 2.75;
        assertEquals(expectedValue, instance.getShipAcceleration());
    }

    @Test
    void getMainHullArmor() {
        Ship ship = new Ship();
        ShipCharacteristics instance = new ShipCharacteristics(ship);
        Integer expectedValue = 1;
        assertEquals(expectedValue, instance.getMainHullArmor());
    }

    @Test
    void getMastArmor() {
        Ship ship = new Ship().setMastArmor(4);
        ShipCharacteristics instance = new ShipCharacteristics(ship);
        Integer expectedValue = 4;
        assertEquals(expectedValue, instance.getMastArmor());
    }

    @Test
    void getEngineArmor() {
        Ship ship = new Ship().setEngineArmor(4);
        ShipCharacteristics instance = new ShipCharacteristics(ship);
        Integer expectedValue = 4;
        assertEquals(expectedValue, instance.getEngineArmor());
    }

    @Test
    void getArmorShrink() {
        Ship ship = new Ship();
        ShipCharacteristics instance = new ShipCharacteristics(ship);
        Integer expectedValue = 0;
        assertEquals(expectedValue, instance.getArmorShrink());
    }
}
