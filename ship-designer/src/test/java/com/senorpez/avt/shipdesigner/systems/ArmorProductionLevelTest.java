package com.senorpez.avt.shipdesigner.systems;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.senorpez.avt.shipdesigner.systems.ProductionLevel.PROTOTYPE;
import static com.senorpez.avt.shipdesigner.systems.ProductionLevel.STANDARD;
import static org.junit.jupiter.api.Assertions.*;

class ArmorProductionLevelTest {
    private ArmorProductionLevel instance;

    @BeforeEach
    void setUp() {
        instance = new ArmorProductionLevel(STANDARD);
    }

    @Test
    void getArmorShrink() {
        ProductionLevel expectedValue = STANDARD;
        assertEquals(expectedValue, instance.getProductionLevel());
    }

    @Test
    void setArmorShrink() {
        ProductionLevel expectedValue = PROTOTYPE;
        instance = instance.setProductionLevel(expectedValue);
        assertEquals(expectedValue, instance.getProductionLevel());
    }
}