package com.senorpez.avt.shipdesigner.weapons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.internal.stubbing.defaultanswers.ReturnsDeepStubs;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.senorpez.avt.shipdesigner.systems.ProductionLevel.STANDARD;
import static com.senorpez.avt.shipdesigner.weapons.LaserSRLS.SRLS.SRLS3;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LaserSRLSTest {
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    Mount mount;

    LaserSRLS instance;

    @BeforeEach
    void setUp() {
        instance = new LaserSRLS(mount, SRLS3, 2, 1, 0, STANDARD, 3, 4);
    }

    @Test
    void getSpacesUsed() {
        int expectedValue = 3;
        assertEquals(expectedValue, instance.getSpacesUsed());
    }

    @Test
    void getDuelCost() {
        when(mount.getFiringArc().size()).thenReturn(7);
        int expectedValue = 177;
        assertEquals(expectedValue, instance.getDuelCost());
    }

    @Test
    void getEconomicCost() {
        when(mount.getFiringArc().size()).thenReturn(7);
        int expectedValue = 177;
        assertEquals(expectedValue, instance.getEconomicCost());
    }

    @Test
    void getRecycleTime() {
        int expectedValue = 4;
        assertEquals(expectedValue, instance.getRecycleTime());
    }

    @Test
    void getPower() {
        double expectedValue = 4.5;
        assertEquals(expectedValue, instance.getPower());
    }
}