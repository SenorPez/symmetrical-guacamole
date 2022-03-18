package com.senorpez.avt.shipdesigner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MassCharacteristicsTest {
    @Mock
    Ship ship;
    @Mock
    ShipCharacteristics characteristics;

    private MassCharacteristics instance;
    private final double tolerance = 0.001d;

    @BeforeEach
    void setUp() {
        instance = spy(new MassCharacteristics(ship, characteristics));
    }

    @Test
    void getHullMass() {
        doReturn(40.10843d).when(instance).getHullSpaces();
        double expectedValue = 1002.71086d;

        assertEquals(expectedValue, instance.getHullMass(), tolerance);
    }

    @Test
    void getHullSpaces() {
        when(ship.getHullSize()).thenReturn(55);
        doReturn(1).when(instance).getHullArmorSpaces();
        doReturn(13.89157d).when(instance).getOverallDriveSpaces_wArmor();
        double expectedValue = 40.10843d;

        assertEquals(expectedValue, instance.getHullSpaces(), tolerance);
    }

    @Test
    void getHullPercentage() {
        when(ship.getHullSize()).thenReturn(55);
        doReturn(40.10843d).when(instance).getHullSpaces();
        double expectedValue = 0.7292443d;

        assertEquals(expectedValue, instance.getHullPercentage(), tolerance);
    }

    @Test
    void getHullArmorMass() {
        doReturn(1).when(instance).getHullArmorSpaces();
        double expectedValue = 25.00000d;

        assertEquals(expectedValue, instance.getHullArmorMass(), tolerance);
    }

    @Test
    void getHullArmorSpaces() {
        when(ship.getHullArmor()).thenReturn(1);
        int expectedValue = 1;

        assertEquals(expectedValue, instance.getHullArmorSpaces());
    }

    @Test
    void getHullArmorPercentage() {
        when(ship.getHullSize()).thenReturn(55);
        doReturn(1).when(instance).getHullArmorSpaces();
        double expectedValue = 0.018181818;

        assertEquals(expectedValue, instance.getHullArmorPercentage(), tolerance);
    }

    @Test
    void getTotalHullMass() {
        doReturn(1002.71086d).when(instance).getHullMass();
        doReturn(25).when(instance).getHullArmorMass();
        double expectedValue = 1027.71086d;

        assertEquals(expectedValue, instance.getTotalHullMass(), tolerance);
    }

    @Test
    void getTotalHullSpaces() {
        doReturn(40.10843d).when(instance).getHullSpaces();
        doReturn(1).when(instance).getHullArmorSpaces();
        double expectedValue = 41.10843d;

        assertEquals(expectedValue, instance.getTotalHullSpaces(), tolerance);
    }

    @Test
    void getTotalHullPercentage() {
        when(ship.getHullSize()).thenReturn(55);
        doReturn(41.10843d).when(instance).getTotalHullSpaces();
        double expectedValue = 0.7474261d;

        assertEquals(expectedValue, instance.getTotalHullPercentage(), tolerance);
    }

    @Test
    void getDriveMass() {
        Ship ship = new Ship()
                .setHullSize(55)
                .setShape(Shape.ELLIPSOID);
        ShipCharacteristics characteristics = new ShipCharacteristics(ship);
        MassCharacteristics instance = new MassCharacteristics(ship, characteristics);
        double expectedValue = 257.363868;
        assertEquals(expectedValue, instance.getOverallDriveMass_wArmor(), 0.01);
    }

    @Test
    void getMastMass() {
        Ship ship = new Ship()
                .setHullSize(55)
                .setShape(Shape.ELLIPSOID);
        ShipCharacteristics characteristics = new ShipCharacteristics(ship);
        MassCharacteristics instance = new MassCharacteristics(ship, characteristics);
        double expectedValue = 58.63d;
        assertEquals(expectedValue, instance.getMastMass(), 0.01);
    }

    @Test
    void getMastArmorMass() {
        Ship ship = new Ship();
        ShipCharacteristics characteristics = new ShipCharacteristics(ship);
        MassCharacteristics instance = new MassCharacteristics(ship, characteristics);
        double expectedValue = 0d;
        assertEquals(expectedValue, instance.getMastArmorMass(), 0.01);
    }

    @Test
    void getMastMassModifier() {
        Ship ship = new Ship().setShape(Shape.ELLIPSOID);
        ShipCharacteristics characteristics = new ShipCharacteristics(ship);
        MassCharacteristics instance = new MassCharacteristics(ship, characteristics);
        double expectedValue = 1.25d;
        assertEquals(expectedValue, instance.getMastMassModifier());
    }

    @Test
    void getMastShieldMass() {
        Ship ship = new Ship();
        ShipCharacteristics characteristics = new ShipCharacteristics(ship);
        MassCharacteristics instance = new MassCharacteristics(ship, characteristics);
        double expectedValue = 40.53d;
        assertEquals(expectedValue, instance.getMastShieldMass(), 0.01);
    }

    @Test
    void getMastStructuralMass() {
        Ship ship = new Ship()
                .setHullSize(55)
                .setShape(Shape.ELLIPSOID);
        ShipCharacteristics characteristics = new ShipCharacteristics(ship);
        MassCharacteristics instance = new MassCharacteristics(ship, characteristics);
        double expectedValue = 18.10d;
        assertEquals(expectedValue, instance.getMastStructureMass(), 0.01);
    }

    @Test
    void getDriveSpaces() {
        Ship ship = new Ship()
                .setHullSize(55)
                .setShape(Shape.ELLIPSOID);
        ShipCharacteristics characteristics = new ShipCharacteristics(ship);
        MassCharacteristics instance = new MassCharacteristics(ship, characteristics);
        double expectedValue = 10.29455d;
        assertEquals(expectedValue, instance.getOverallDriveSpaces_wArmor(), 0.1);
    }

//    @Test
//    void getHullSpaces() {
//        com.senorpez.avt.shipdesigner.Ship ship = new com.senorpez.avt.shipdesigner.Ship().setHullSize(55);
//        com.senorpez.avt.shipdesigner.ShipCharacteristics characteristics = new com.senorpez.avt.shipdesigner.ShipCharacteristics(ship);
//        com.senorpez.avt.shipdesigner.MassCharacteristics instance = new com.senorpez.avt.shipdesigner.MassCharacteristics(ship, characteristics);
//        int expectedValue = 44;
//        assertEquals(expectedValue, instance.getHullSpaces());
//    }

    @Test
    void getFigureOfMerit() {
        Ship ship = new Ship()
                .setHullSize(55)
                .setShape(Shape.ELLIPSOID);
        ShipCharacteristics characteristics = new ShipCharacteristics(ship);
        MassCharacteristics instance = new MassCharacteristics(ship, characteristics);
        double expectedValue = 56.79d;
        assertEquals(expectedValue, instance.getFigureOfMerit(), 0.01);
    }

    @Test
    void getPivotAccel() {
        Ship ship = new Ship()
                .setHullSize(55)
                .setShape(Shape.ELLIPSOID);
        ShipCharacteristics characteristics = new ShipCharacteristics(ship);
        MassCharacteristics instance = new MassCharacteristics(ship, characteristics);
        double expectedValue = 14.61515d;
        assertEquals(expectedValue, instance.getPivotAccel(), 0.01);
    }

    @Test
    void getLanternDiameter() {
        Ship ship = new Ship()
                .setHullSize(55);
        ShipCharacteristics characteristics = new ShipCharacteristics(ship);
        MassCharacteristics instance = new MassCharacteristics(ship, characteristics);
        double expectedValue = 22d;
        assertEquals(expectedValue, instance.getLanternDiameter());
    }

    @Test
    void getActualDriveFraction() {
        Ship ship = new Ship()
                .setHullSize(55)
                .setShape(Shape.ELLIPSOID);
        ShipCharacteristics characteristics = new ShipCharacteristics(ship);
        MassCharacteristics instance = new MassCharacteristics(ship, characteristics);
        double expectedValue = 0.1871737d;
        assertEquals(expectedValue, instance.getActualDriveFraction(), 0.01);
    }

    @Test
    void getMainHullLength() {
        Ship ship = new Ship().setShape(Shape.ELLIPSOID);
        ShipCharacteristics characteristics = new ShipCharacteristics(ship);
        MassCharacteristics instance = new MassCharacteristics(ship, characteristics);
        double expectedValue = 32.84850d;
        assertEquals(expectedValue, instance.getMainHullLength());
    }

    @Test
    void getPivotThrust() {
        Ship ship = new Ship()
                .setHullSize(55)
                .setShape(Shape.ELLIPSOID);
        ShipCharacteristics characteristics = new ShipCharacteristics(ship);
        MassCharacteristics instance = new MassCharacteristics(ship, characteristics);
        double expectedValue = 177.04301d;
        assertEquals(expectedValue, instance.getPivotThrust(), 0.00001);
    }

    @Test
    void getNeutronFlux_MR_year() {
        Ship ship = new Ship()
                .setHullSize(55);
        ShipCharacteristics characteristics = new ShipCharacteristics(ship);
        MassCharacteristics instance = new MassCharacteristics(ship, characteristics);
        double expectedValue = 71987.64505d;
        assertEquals(expectedValue, instance.getNeutronFlux_MR_yr(), 0.0001);
    }

    @Test
    void getNeutronFlux_KR_hour() {
        Ship ship = new Ship()
                .setHullSize(55)
                .setMaximumThrust(11d);
        ShipCharacteristics characteristics = new ShipCharacteristics(ship);
        MassCharacteristics instance = new MassCharacteristics(ship, characteristics);
        double expectedValue = 8212.14294d;
        assertEquals(expectedValue, instance.getNeutronFlux_KR_hr(), 0.01);
    }

    @Test
    void getNewCombatPower() {
        Ship ship = new Ship()
                .setHullSize(55)
                .setMaximumThrust(11d)
                .setEngineGeneration(3.1d);
        ShipCharacteristics characteristics = new ShipCharacteristics(ship);
        MassCharacteristics instance = new MassCharacteristics(ship, characteristics);
        double expectedValue = 1.98734d;
        assertEquals(expectedValue, instance.getNewCombatPower(), 0.01);
    }
}
