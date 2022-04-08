package com.senorpez.avt.shipdesigner.weapons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.senorpez.avt.shipdesigner.systems.ProductionLevel.STANDARD;
import static com.senorpez.avt.shipdesigner.weapons.LaserSRLS.SRLS.SRLS3;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PodDockTest {
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    Mount mount;

    PodDock instance;

    @BeforeEach
    void setUp() {
        instance = new PodDock(mount, 0, STANDARD);
    }

    @Test
    void getSpacesUsed() {
        int expectedValue = 1;
        assertEquals(expectedValue, instance.getSpacesUsed());
    }

    @Test
    void getDuelCost() {
        when(mount.getFiringArc().size()).thenReturn(1);
        int expectedValue = 3;
        assertEquals(expectedValue, instance.getDuelCost());
    }

    @Test
    void getEconomicCost() {
        when(mount.getFiringArc().size()).thenReturn(1);
        int expectedValue = 3;
        assertEquals(expectedValue, instance.getEconomicCost());
    }
}