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
    void getMastStructureMass() {
        when(characteristics.getShipMass()).thenReturn(1375);
        when(characteristics.getShipAcceleration()).thenReturn(2.75d);
        doReturn(31.21665d).when(instance).getMastLength();
        doReturn(1.25d).when(instance).getMastMassModifier();
        double expectedValue = 16.44100d;

        assertEquals(expectedValue, instance.getMastStructureMass(), tolerance);
    }

    @Test
    void getMastStructureSpaces() {
        doReturn(16.44100d).when(instance).getMastStructureMass();
        double expectedValue = 0.65764d;

        assertEquals(expectedValue, instance.getMastStructureSpaces(), tolerance);
    }

    @Test
    void getMastStructurePercentage() {
        when(ship.getHullSize()).thenReturn(55);
        doReturn(0.65764d).when(instance).getMastStructureSpaces();
        double expectedValue = 0.0119571d;

        assertEquals(expectedValue, instance.getMastStructurePercentage(), tolerance);
    }

    @Test
    void getMastArmorMass() {
        when(characteristics.getMastArmor()).thenReturn(1);
        when(characteristics.getArmorShrink()).thenReturn(0);
        doReturn(31.21665d).when(instance).getMastLength();
        double expectedValue = 10.20473d;

        assertEquals(expectedValue, instance.getMastArmorMass(), tolerance);
    }

    @Test
    void getMastArmorSpaces() {
        doReturn(10.20473d).when(instance).getMastArmorMass();
        double expectedValue = 0.40819d;

        assertEquals(expectedValue, instance.getMastArmorSpaces(), tolerance);
    }

    @Test
    void getMastArmorPercentage() {
        when(ship.getHullSize()).thenReturn(55);
        doReturn(0.40819d).when(instance).getMastArmorSpaces();
        double expectedValue = 0.0074216d;

        assertEquals(expectedValue, instance.getMastArmorPercentage(), tolerance);
    }

    @Test
    void getMastShieldMass() {
        doReturn(0.63000d).when(instance).getTenXRadReduction();
        doReturn(71987.64505d).when(instance).getNeutronFlux_MR_yr();
        doReturn(14.72931d).when(instance).getRadReductionDueToMast();
        doReturn(8.08955d).when(instance).getShieldCrossSection();
        double expectedValue = 45.88301d;

        assertEquals(expectedValue, instance.getMastShieldMass(), tolerance);
    }

    @Test
    void getMastShieldSpaces() {
        doReturn(45.88301d).when(instance).getMastShieldMass();
        double expectedValue = 1.83532d;

        assertEquals(expectedValue, instance.getMastShieldSpaces(), tolerance);
    }

    @Test
    void getMastShieldPercentage() {
        when(ship.getHullSize()).thenReturn(55);
        doReturn(1.83532d).when(instance).getMastShieldSpaces();
        double expectedValue = 0.0333695d;

        assertEquals(expectedValue, instance.getMastShieldPercentage(), tolerance);
    }

    @Test
    void getDriveMass() {
        double expectedValue = 198.73386d;

        assertEquals(expectedValue, instance.getDriveMass(), tolerance);
    }

    @Test
    void getDriveSpaces() {
        doReturn(198.73386d).when(instance).getDriveMass();
        double expectedValue = 7.94935d;

        assertEquals(expectedValue, instance.getDriveSpaces(), tolerance);
    }

    @Test
    void getDrivePercentage() {
        when(ship.getHullSize()).thenReturn(55);
        doReturn(7.94935d).when(instance).getDriveSpaces();
        double expectedValue = 0.1445337d;

        assertEquals(expectedValue, instance.getDrivePercentage(), tolerance);
    }

    @Test
    void getDriveArmorMass() {
        double expectedValue = 76.02654d;
        
        assertEquals(expectedValue, instance.getDriveArmorMass(), tolerance);
    }

    @Test
    void getDriveArmorSpaces() {
        doReturn(76.02654d).when(instance).getDriveArmorMass();
        double expectedValue = 3.04106d;

        assertEquals(expectedValue, instance.getDriveArmorSpaces(), tolerance);
    }

    @Test
    void getDriveArmorPercentage() {
        when(ship.getHullSize()).thenReturn(55);
        doReturn(3.04106d).when(instance).getDriveArmorSpaces();
        double expectedValue = 0.0552920d;

        assertEquals(expectedValue, instance.getDriveArmorPercentage(), tolerance);
    }

    @Test
    void getOverallDriveMass_wArmor() {
        doReturn(16.44100d).when(instance).getMastStructureMass();
        doReturn(10.20473d).when(instance).getMastArmorMass();
        doReturn(45.88301d).when(instance).getMastShieldMass();
        doReturn(198.73386d).when(instance).getDriveMass();
        doReturn(76.02654d).when(instance).getDriveArmorMass();
        double expectedValue = 347.28914d;

        assertEquals(expectedValue, instance.getOverallDriveMass_wArmor(), tolerance);
    }

    @Test
    void getOverallDriveSpaces_wArmor() {
        doReturn(347.28914d).when(instance).getOverallDriveMass_wArmor();
        double expectedValue = 13.89157d;

        assertEquals(expectedValue, instance.getOverallDriveSpaces_wArmor(), tolerance);
    }

    @Test
    void getOverallDrivePercentage_wArmor() {
        when(ship.getHullSize()).thenReturn(55);
        doReturn(13.89157d).when(instance).getOverallDriveSpaces_wArmor();
        double expectedValue = 0.2525739d;

        assertEquals(expectedValue, instance.getOverallDrivePercentage_wArmor(), tolerance);
    }

    @Test
    void getOverallDriveMass_noArmor() {
        doReturn(16.44100d).when(instance).getMastStructureMass();
        doReturn(45.88301d).when(instance).getMastShieldMass();
        doReturn(198.73386d).when(instance).getDriveMass();
        double expectedValue = 261.05788d;

        assertEquals(expectedValue, instance.getOverallDriveMass_noArmor(), tolerance);
    }

    @Test
    void getOverallDriveSpaces_noArmor() {
        doReturn(261.05788d).when(instance).getOverallDriveMass_noArmor();
        double expectedValue = 10.44232d;

        assertEquals(expectedValue, instance.getOverallDriveSpaces_noArmor(), tolerance);
    }

    @Test
    void getOverallDrivePercentage_noArmor() {
        when(ship.getHullSize()).thenReturn(55);
        doReturn(10.44232d).when(instance).getOverallDriveSpaces_noArmor();
        double expectedValue = 0.1898603d;

        assertEquals(expectedValue, instance.getOverallDrivePercentage_noArmor(), tolerance);
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
    void getMastMassModifier() {
        Ship ship = new Ship().setShape(Shape.ELLIPSOID);
        ShipCharacteristics characteristics = new ShipCharacteristics(ship);
        MassCharacteristics instance = new MassCharacteristics(ship, characteristics);
        double expectedValue = 1.25d;
        assertEquals(expectedValue, instance.getMastMassModifier());
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
