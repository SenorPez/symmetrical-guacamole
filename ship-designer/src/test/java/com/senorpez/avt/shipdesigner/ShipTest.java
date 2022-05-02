package com.senorpez.avt.shipdesigner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {
    Ship instance;

    @BeforeEach
    void setUp() {
        instance = new Ship();
    }

    @Test
    void getHullSpaces() {
        // Trivial test, but using to set up GitHub actions.
        instance = instance
                .setHullSpaces(25)
                .build();

        int expectedValue = 25;
        assertEquals(expectedValue, instance.getHullSpaces());
    }
}