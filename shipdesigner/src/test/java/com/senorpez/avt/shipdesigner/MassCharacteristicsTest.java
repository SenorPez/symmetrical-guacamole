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
    ShipCharacteristics characteristics;

    private MassCharacteristics instance;
    private final double tolerance = 0.00002d;

    @BeforeEach
    void setUp() {
        instance = spy(new MassCharacteristics(characteristics, 31.21665d));
    }

    @Test
    void getHullMass() {
        doReturn(40.10843d).when(instance).getHullSpaces(anyDouble());
        double expectedValue = 1002.71086d;

        assertEquals(expectedValue, instance.getHullMass(), tolerance * 10);
    }

    @Test
    void getHullSpaces() {
        when(characteristics.getShipSpaces()).thenReturn(55);
        doReturn(1).when(instance).getHullArmorSpaces();
        doReturn(13.89157d).when(instance).getOverallDriveSpaces_wArmor(anyDouble());
        double expectedValue = 40.10843d;

        assertEquals(expectedValue, instance.getHullSpaces(), tolerance);
    }

    @Test
    void getHullPercentage() {
        when(characteristics.getShipSpaces()).thenReturn(55);
        doReturn(40.10843d).when(instance).getHullSpaces(anyDouble());
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
        when(characteristics.getHullArmor()).thenReturn(1);
        int expectedValue = 1;

        assertEquals(expectedValue, instance.getHullArmorSpaces());
    }

    @Test
    void getHullArmorPercentage() {
        when(characteristics.getShipSpaces()).thenReturn(55);
        doReturn(1).when(instance).getHullArmorSpaces();
        double expectedValue = 0.018181818;

        assertEquals(expectedValue, instance.getHullArmorPercentage(), tolerance);
    }

    @Test
    void getTotalHullMass() {
        doReturn(1002.71086d).when(instance).getHullMass(anyDouble());
        doReturn(25).when(instance).getHullArmorMass();
        double expectedValue = 1027.71086d;

        assertEquals(expectedValue, instance.getTotalHullMass(), tolerance);
    }

    @Test
    void getTotalHullSpaces() {
        doReturn(40.10843d).when(instance).getHullSpaces(anyDouble());
        doReturn(1).when(instance).getHullArmorSpaces();
        double expectedValue = 41.10843d;

        assertEquals(expectedValue, instance.getTotalHullSpaces(), tolerance);
    }

    @Test
    void getTotalHullPercentage() {
        when(characteristics.getShipSpaces()).thenReturn(55);
        doReturn(41.10843d).when(instance).getTotalHullSpaces(anyDouble());
        double expectedValue = 0.7474261d;

        assertEquals(expectedValue, instance.getTotalHullPercentage(), tolerance);
    }

    @Test
    void getMastStructureMass() {
        when(characteristics.getShipMass()).thenReturn(1375);
        when(characteristics.getShipAcceleration()).thenReturn(2.75d);
        when(characteristics.getHullShape()).thenReturn(Shape.ELLIPSOID);

        double expectedValue = 16.44100d;
        assertEquals(expectedValue, instance.getMastStructureMass(), tolerance);

        expectedValue = 16.96768d;
        assertEquals(expectedValue, instance.getMastStructureMass(32.21665d), tolerance);
    }

    @Test
    void getMastStructureSpaces() {
        doReturn(16.44100d).when(instance).getMastStructureMass(anyDouble());
        double expectedValue = 0.65764d;

        assertEquals(expectedValue, instance.getMastStructureSpaces(), tolerance);
    }

    @Test
    void getMastStructurePercentage() {
        when(characteristics.getShipSpaces()).thenReturn(55);
        doReturn(0.65764d).when(instance).getMastStructureSpaces(anyDouble());
        double expectedValue = 0.0119571d;

        assertEquals(expectedValue, instance.getMastStructurePercentage(), tolerance);
    }

    @Test
    void getMastArmorMass() {
        when(characteristics.getMastArmor()).thenReturn(1);
        when(characteristics.getArmorShrink()).thenReturn(0);

        double expectedValue = 10.20473d;
        assertEquals(expectedValue, instance.getMastArmorMass(), tolerance);

        expectedValue = 10.86900d;
        assertEquals(expectedValue, instance.getMastArmorMass(32.21665d), tolerance);
    }

    @Test
    void getMastArmorSpaces() {
        doReturn(10.20473d).when(instance).getMastArmorMass(anyDouble());
        double expectedValue = 0.40819d;

        assertEquals(expectedValue, instance.getMastArmorSpaces(), tolerance);
    }

    @Test
    void getMastArmorPercentage() {
        when(characteristics.getShipSpaces()).thenReturn(55);
        doReturn(0.40819d).when(instance).getMastArmorSpaces(anyDouble());
        double expectedValue = 0.0074216d;

        assertEquals(expectedValue, instance.getMastArmorPercentage(), tolerance);
    }

    @Test
    void getMastShieldMass() {
        when(characteristics.getHullArmor()).thenReturn(1);
        when(characteristics.getHullShape()).thenReturn(Shape.ELLIPSOID);
        when(characteristics.getShipAcceleration()).thenReturn(2.75d);
        when(characteristics.getShipMass()).thenReturn(1375);
        when(characteristics.getShipSpaces()).thenReturn(55);
        when(characteristics.getDriveGeneration()).thenReturn(3.1d);

        double expectedValue = 45.88301d;
        assertEquals(expectedValue, instance.getMastShieldMass(), tolerance);

        expectedValue = 44.07349d;
        assertEquals(expectedValue, instance.getMastShieldMass(32.21665d), tolerance);
    }

    @Test
    void getMastShieldSpaces() {
        doReturn(45.88301d).when(instance).getMastShieldMass(anyDouble());
        double expectedValue = 1.83532d;

        assertEquals(expectedValue, instance.getMastShieldSpaces(), tolerance);
    }

    @Test
    void getMastShieldPercentage() {
        when(characteristics.getShipSpaces()).thenReturn(55);
        doReturn(1.83532d).when(instance).getMastShieldSpaces(anyDouble());
        double expectedValue = 0.0333695d;

        assertEquals(expectedValue, instance.getMastShieldPercentage(), tolerance);
    }

    @Test
    void getDriveMass() {
        when(characteristics.getShipMass()).thenReturn(1375);
        when(characteristics.getShipAcceleration()).thenReturn(2.75d);
        when(characteristics.getDriveGeneration()).thenReturn(3.1d);
        double expectedValue = 198.73386d;

        assertEquals(expectedValue, instance.getDriveMass(), tolerance);
    }

    @Test
    void getDriveMass_Big() {
        when(characteristics.getShipMass()).thenReturn(2000);
        when(characteristics.getShipAcceleration()).thenReturn(4.00d);
        when(characteristics.getDriveGeneration()).thenReturn(3.1d);
        double expectedValue = 431.08181d;

        assertEquals(expectedValue, instance.getDriveMass(), tolerance);
    }

    @Test
    void getDriveMass_Small() {
        when(characteristics.getShipMass()).thenReturn(1375);
        when(characteristics.getShipAcceleration()).thenReturn(0.50d);
        when(characteristics.getDriveGeneration()).thenReturn(3.1d);
        double expectedValue = 60.11109d;

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
        when(characteristics.getShipSpaces()).thenReturn(55);
        doReturn(7.94935d).when(instance).getDriveSpaces();
        double expectedValue = 0.1445337d;

        assertEquals(expectedValue, instance.getDrivePercentage(), tolerance);
    }

    @Test
    void getDriveArmorMass() {
        when(characteristics.getDriveArmor()).thenReturn(2);
        when(characteristics.getArmorShrink()).thenReturn(0);
        when(characteristics.getShipMass()).thenReturn(1375);
        when(characteristics.getShipAcceleration()).thenReturn(2.75d);
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
        when(characteristics.getShipSpaces()).thenReturn(55);
        doReturn(3.04106d).when(instance).getDriveArmorSpaces();
        double expectedValue = 0.0552920d;

        assertEquals(expectedValue, instance.getDriveArmorPercentage(), tolerance);
    }

    @Test
    void getOverallDriveMass_wArmor() {
        doReturn(16.44100d).when(instance).getMastStructureMass(anyDouble());
        doReturn(10.20473d).when(instance).getMastArmorMass(anyDouble());
        doReturn(45.88301d).when(instance).getMastShieldMass(anyDouble());
        doReturn(198.73386d).when(instance).getDriveMass();
        doReturn(76.02654d).when(instance).getDriveArmorMass();
        double expectedValue = 347.28914d;

        assertEquals(expectedValue, instance.getOverallDriveMass_wArmor(), tolerance);
    }

    @Test
    void getOverallDriveSpaces_wArmor() {
        doReturn(347.28914d).when(instance).getOverallDriveMass_wArmor(anyDouble());
        double expectedValue = 13.89157d;

        assertEquals(expectedValue, instance.getOverallDriveSpaces_wArmor(), tolerance);
    }

    @Test
    void getOverallDrivePercentage_wArmor() {
        when(characteristics.getShipSpaces()).thenReturn(55);
        doReturn(13.89157d).when(instance).getOverallDriveSpaces_wArmor(anyDouble());
        double expectedValue = 0.2525739d;

        assertEquals(expectedValue, instance.getOverallDrivePercentage_wArmor(), tolerance);
    }

    @Test
    void getOverallDriveMass_noArmor() {
        doReturn(16.44100d).when(instance).getMastStructureMass(anyDouble());
        doReturn(45.88301d).when(instance).getMastShieldMass(anyDouble());
        doReturn(198.73386d).when(instance).getDriveMass();
        double expectedValue = 261.05788d;

        assertEquals(expectedValue, instance.getOverallDriveMass_noArmor(), tolerance);
    }

    @Test
    void getOverallDriveSpaces_noArmor() {
        doReturn(261.05788d).when(instance).getOverallDriveMass_noArmor(anyDouble());
        double expectedValue = 10.44232d;

        assertEquals(expectedValue, instance.getOverallDriveSpaces_noArmor(), tolerance);
    }

    @Test
    void getOverallDrivePercentage_noArmor() {
        when(characteristics.getShipSpaces()).thenReturn(55);
        doReturn(10.44232d).when(instance).getOverallDriveSpaces_noArmor(anyDouble());
        double expectedValue = 0.1898603d;

        assertEquals(expectedValue, instance.getOverallDrivePercentage_noArmor(), tolerance);
    }

    @Test
    void getTotalShipArmorMass() {
        doReturn(25).when(instance).getHullArmorMass();
        doReturn(10.20473d).when(instance).getMastArmorMass(anyDouble());
        doReturn(76.02654d).when(instance).getDriveArmorMass();
        double expectedValue = 111.23127d;

        assertEquals(expectedValue, instance.getTotalShipArmorMass(), tolerance);
    }

    @Test
    void getTotalShipArmorSpaces() {
        doReturn(111.23127d).when(instance).getTotalShipArmorMass(anyDouble());
        double expectedValue = 4.44925d;

        assertEquals(expectedValue, instance.getTotalShipArmorSpaces(), tolerance);
    }

    @Test
    void getTotalShipArmorPercentage() {
        when(characteristics.getShipSpaces()).thenReturn(55);
        doReturn(4.44925d).when(instance).getTotalShipArmorSpaces(anyDouble());
        double expectedValue = .0808955d;

        assertEquals(expectedValue, instance.getTotalShipArmorPercentage(), tolerance);
    }

    @Test
    void getTotalShipMass() {
        when(characteristics.getShipMass()).thenReturn(1375);
        doReturn(1027.71086d).when(instance).getTotalHullMass(anyDouble());
        doReturn(347.28914d).when(instance).getOverallDriveMass_wArmor(anyDouble());
        double expectedValue = 1375d;

        assertEquals(expectedValue, instance.getTotalShipMass());
        assertEquals(characteristics.getShipMass(), instance.getTotalShipMass());
    }

    @Test
    void getTotalShipSpaces() {
        when(characteristics.getShipSpaces()).thenReturn(55);
        doReturn(41.10843d).when(instance).getTotalHullSpaces(anyDouble());
        doReturn(13.89157d).when(instance).getOverallDriveSpaces_wArmor(anyDouble());
        double expectedValue = 55d;

        assertEquals(expectedValue, instance.getTotalShipSpaces());
        assertEquals(characteristics.getShipSpaces(), instance.getTotalShipSpaces());
    }

    @Test
    void getTotalShipPercentage() {
        doReturn(0.7474251d).when(instance).getTotalHullPercentage(anyDouble());
        doReturn(0.2525739d).when(instance).getOverallDrivePercentage_wArmor(anyDouble());
        double expectedValue = 1d;

        assertEquals(expectedValue, instance.getTotalShipPercentage(), tolerance);
    }

    @Test
    void fullResultsTest() {
        when(characteristics.getShipSpaces()).thenReturn(55);
        when(characteristics.getShipMass()).thenReturn(1375);
//        when(characteristics.getShipThrust()).thenReturn(11d);
        when(characteristics.getShipAcceleration()).thenReturn(2.75d);
        when(characteristics.getHullShape()).thenReturn(Shape.ELLIPSOID);
        when(characteristics.getDriveGeneration()).thenReturn(3.1d);
        when(characteristics.getHullArmor()).thenReturn(1);
        when(characteristics.getMastArmor()).thenReturn(1);
        when(characteristics.getDriveArmor()).thenReturn(2);
        when(characteristics.getArmorShrink()).thenReturn(0);

        instance = new MassCharacteristics(characteristics);

        assertEquals(999.07916d, instance.getHullMass(), tolerance * 20);
        assertEquals(39.96318d, instance.getHullSpaces(), tolerance);
        assertEquals(0.7266033d, instance.getHullPercentage(), tolerance);
        assertEquals(25.00000d, instance.getHullArmorMass(), tolerance);
        assertEquals(1.00000d, instance.getHullArmorSpaces(), tolerance);
        assertEquals(0.0181818d, instance.getHullArmorPercentage(), tolerance);
        assertEquals(1024.07949d, instance.getTotalHullMass(), tolerance);
        assertEquals(40.96318d, instance.getTotalHullSpaces(), tolerance);
        assertEquals(0.7447851d, instance.getTotalHullPercentage(), tolerance);
        assertEquals(14.45141d, instance.getMastStructureMass(), tolerance);
        assertEquals(0.57806d, instance.getMastStructureSpaces(), tolerance);
        assertEquals(0.0105101d, instance.getMastStructurePercentage(), tolerance);
        assertEquals(7.88434d, instance.getMastArmorMass(), tolerance);
        assertEquals(0.31537d, instance.getMastArmorSpaces(), tolerance);
        assertEquals(0.0057341d, instance.getMastArmorPercentage(), tolerance);
        assertEquals(53.82436d, instance.getMastShieldMass(), tolerance);
        assertEquals(2.15297d, instance.getMastShieldSpaces(), tolerance);
        assertEquals(0.0391450d, instance.getMastShieldPercentage(), tolerance);
        assertEquals(198.73386, instance.getDriveMass(), tolerance);
        assertEquals(7.94935d, instance.getDriveSpaces(), tolerance);
        assertEquals(0.1445337d, instance.getDrivePercentage(), tolerance);
        assertEquals(76.02654d, instance.getDriveArmorMass(), tolerance);
        assertEquals(3.04106d, instance.getDriveArmorSpaces(), tolerance);
        assertEquals(0.0552920d, instance.getDriveArmorPercentage(), tolerance);
        assertEquals(350.92051d, instance.getOverallDriveMass_wArmor(), tolerance);
        assertEquals(14.03682d, instance.getOverallDriveSpaces_wArmor(), tolerance);
        assertEquals(0.2552149d, instance.getOverallDrivePercentage_wArmor(), tolerance);
        assertEquals(267.00963d, instance.getOverallDriveMass_noArmor(), tolerance);
        assertEquals(10.68039d, instance.getOverallDriveSpaces_noArmor(), tolerance);
        assertEquals(0.1941888d, instance.getOverallDrivePercentage_noArmor(), tolerance);
        assertEquals(108.91088d, instance.getTotalShipArmorMass(), tolerance);
        assertEquals(4.35644d, instance.getTotalShipArmorSpaces(), tolerance);
        assertEquals(0.0792079d, instance.getTotalShipArmorPercentage(), tolerance);
        assertEquals(1375d, instance.getTotalShipMass());
        assertEquals(55d, instance.getTotalShipSpaces());
        assertEquals(1, instance.getTotalShipPercentage());
    }
}
