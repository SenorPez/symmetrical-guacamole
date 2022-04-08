package com.senorpez.avt.shipdesigner.weapons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.senorpez.avt.shipdesigner.systems.ProductionLevel.STANDARD;
import static com.senorpez.avt.shipdesigner.weapons.LaserSRLS.SRLS.SRLS3;
import static com.senorpez.avt.shipdesigner.weapons.ZoneDefenseZDCM.ZDCM.ZDCM_GEN1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ZoneDefenseZDCMTest {
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    Mount mount;

    ZoneDefenseZDCM instance;

    @BeforeEach
    void setUp() {
        instance = new ZoneDefenseZDCM(mount, ZDCM_GEN1, 0, STANDARD);
    }

    @Test
    void getSpacesUsed() {
        int expectedValue = 1;
        assertEquals(expectedValue, instance.getSpacesUsed());
    }

    @Test
    void getDuelCost() {
        when(mount.getFiringArc().size()).thenReturn(9);
        int expectedValue = 13;
        assertEquals(expectedValue, instance.getDuelCost());
    }

    @Test
    void getEconomicCost() {
        when(mount.getFiringArc().size()).thenReturn(9);
        int expectedValue = 13;
        assertEquals(expectedValue, instance.getEconomicCost());
    }

    @Test
    void getRecycleTime() {
        int expectedValue = 1;
        assertEquals(expectedValue, instance.getRecycleTime());
    }

    @Test
    void getPower() {
        double expectedValue = 0;
        assertEquals(expectedValue, instance.getPower());
    }
}