package com.senorpez.avt.shipdesigner.systems;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArmorShrinkTest {
    private ArmorShrink instance;

    @BeforeEach
    void setUp() {
        instance = new ArmorShrink(3);
    }

    @Test
    void getArmorShrink() {
        int expectedValue = 3;
        assertEquals(expectedValue, instance.getShrink());
    }

    @Test
    void setArmorShrink() {
        int expectedValue = 2;
        instance.setShrink(2);
        assertEquals(expectedValue, instance.getShrink());
    }
}