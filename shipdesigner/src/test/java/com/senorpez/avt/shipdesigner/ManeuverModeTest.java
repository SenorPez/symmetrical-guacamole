package com.senorpez.avt.shipdesigner;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManeuverModeTest {
    @Test
    void getMode() {
        assertEquals(ManeuverMode.H, ManeuverMode.getMode(11.35718d));
        assertEquals(ManeuverMode.C, ManeuverMode.getMode(55.13342d));
    }
}
