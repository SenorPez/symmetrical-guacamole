package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.senorpez.avt.shipdesigner.HullShape.SPHERE;
import static com.senorpez.avt.shipdesigner.systems.ProductionLevel.PROTOTYPE;
import static com.senorpez.avt.shipdesigner.systems.ProductionLevel.STANDARD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccesswaysTest {
    @Mock
    Ship ship;

    private Accessways instance;

    @BeforeEach
    void setUp() {
        instance = new Accessways(ship, false);
    }

    @Test
    void getShip() {
        Ship expectedValue = ship;
        assertEquals(expectedValue, instance.getShip());
    }

    @Test
    void getName() {
        String expectedValue = "Accessways";
        assertEquals(expectedValue, instance.getName());
    }

    @Test
    void getQuantity() {
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getQuantity());

        instance = instance.setImprovedAccessways(true);
        expectedValue = 1;
        assertEquals(expectedValue, instance.getQuantity());

        instance = instance.setQuantity(42);
        // Should be immutable
        assertEquals(expectedValue, instance.getQuantity());
    }

    @Test
    void getBasicSpacesUsed() {
        when(ship.getHullSpaces()).thenReturn(25);
        when(ship.getHullShape()).thenReturn(SPHERE);
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getBasicSpacesUsed());

        instance = instance.setImprovedAccessways(true);
        expectedValue = 1;
        assertEquals(expectedValue, instance.getBasicSpacesUsed());
    }

    @Test
    void getShrink() {
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getShrink());

        instance = instance.setImprovedAccessways(true);
        assertEquals(expectedValue, instance.getShrink());

        instance = instance.setShrink(9);
        // Should be immutable
        assertEquals(expectedValue, instance.getShrink());
    }

    @Test
    void getSpacesPerSystem() {
        when(ship.getHullSpaces()).thenReturn(25);
        when(ship.getHullShape()).thenReturn(SPHERE);
        int expectedValue = 1;
        assertEquals(expectedValue, instance.getSpacesPerSystem());

        instance = instance.setImprovedAccessways(true);
        assertEquals(expectedValue, instance.getSpacesPerSystem());

        // TODO: Parameterize once other hull shapes are implemented.
    }

    @Test
    void getCostPerSpace() {
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getCostPerSpace());

        instance = instance.setImprovedAccessways(true);
        assertEquals(expectedValue, instance.getCostPerSpace());
    }

    @Test
    void getCrewPerSpace() {
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getCrewPerSpace());

        instance = instance.setImprovedAccessways(true);
        assertEquals(expectedValue, instance.getCrewPerSpace());
    }

    @Test
    void getActualSpacesUsed() {
        when(ship.getHullSpaces()).thenReturn(25);
        when(ship.getHullShape()).thenReturn(SPHERE);
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getActualSpacesUsed());

        instance = instance.setImprovedAccessways(true);
        expectedValue = 1;
        assertEquals(expectedValue, instance.getActualSpacesUsed());
    }

    @Test
    void getBaseCost() {
        when(ship.getHullSpaces()).thenReturn(25);
        when(ship.getHullShape()).thenReturn(SPHERE);
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getBaseCost());

        instance = instance.setImprovedAccessways(true);
        assertEquals(expectedValue, instance.getBaseCost());
    }

    @Test
    void getEnhancedCost() {
        when(ship.getHullSpaces()).thenReturn(25);
        when(ship.getHullShape()).thenReturn(SPHERE);
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getEnhancedCost());

        instance = instance.setImprovedAccessways(true);
        assertEquals(expectedValue, instance.getEnhancedCost());
    }

    @Test
    void getCrewRequirement() {
        when(ship.getHullSpaces()).thenReturn(25);
        when(ship.getHullShape()).thenReturn(SPHERE);
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getCrewRequirement());

        instance = instance.setImprovedAccessways(true);
        assertEquals(expectedValue, instance.getCrewRequirement());
    }

    @Test
    void getDuelCost() {
        when(ship.getHullSpaces()).thenReturn(25);
        when(ship.getHullShape()).thenReturn(SPHERE);
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getDuelCost());

        instance = instance.setImprovedAccessways(true);
        assertEquals(expectedValue, instance.getDuelCost());
    }

    @Test
    void getProductionLevel() {
        ProductionLevel expectedValue = STANDARD;
        assertEquals(expectedValue, instance.getProductionLevel());

        instance = instance.setImprovedAccessways(true);
        assertEquals(expectedValue, instance.getProductionLevel());


        instance = instance.setProductionLevel(PROTOTYPE);
        // Should be immutable
        assertEquals(expectedValue, instance.getProductionLevel());
    }

    @Test
    void getEconomicCost() {
        when(ship.getHullSpaces()).thenReturn(25);
        when(ship.getHullShape()).thenReturn(SPHERE);
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getEconomicCost());

        instance = instance.setImprovedAccessways(true);
        assertEquals(expectedValue, instance.getEconomicCost());
    }

    @Test
    void getMaintenanceRate() {
        double expectedValue = 0d;
        assertEquals(expectedValue, instance.getMaintenanceRate());

        instance = instance.setImprovedAccessways(true);
        assertEquals(expectedValue, instance.getMaintenanceRate());
    }

    @Test
    void getMaintenanceCost() {
        when(ship.getHullSpaces()).thenReturn(25);
        when(ship.getHullShape()).thenReturn(SPHERE);
        double expectedValue = 0d;
        assertEquals(expectedValue, instance.getMaintenanceCost());

        instance = instance.setImprovedAccessways(true);
        assertEquals(expectedValue, instance.getMaintenanceCost());
    }
}