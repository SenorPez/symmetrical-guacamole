import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MassCharacteristicsTest {
    @Test
    void getDriveMass() {
        Ship ship = new Ship();
        ShipCharacteristics characteristics = new ShipCharacteristics(ship);
        MassCharacteristics instance = new MassCharacteristics(ship, characteristics);
        int expectedValue = 257;
        assertEquals(expectedValue, instance.getDriveMass());
    }

    @Test
    void getHullMass() {
        Ship ship = new Ship().setHullSize(55);
        ShipCharacteristics characteristics = new ShipCharacteristics(ship);
        MassCharacteristics instance = new MassCharacteristics(ship, characteristics);
        int expectedValue = 1093;
        assertEquals(expectedValue, instance.getHullMass());
    }

    @Test
    void getHullArmorMass() {
        Ship ship = new Ship();
        ShipCharacteristics characteristics = new ShipCharacteristics(ship);
        MassCharacteristics instance = new MassCharacteristics(ship, characteristics);
        int expectedValue = 25;
        assertEquals(expectedValue, instance.getHullArmorMass());
    }

    @Test
    void getMastMass() {
        Ship ship = new Ship();
        ShipCharacteristics characteristics = new ShipCharacteristics(ship);
        MassCharacteristics instance = new MassCharacteristics(ship, characteristics);
        double expectedValue = 58.63d;
        assertEquals(expectedValue, instance.getMastMass());
    }

    @Test
    void getMastArmorMass() {
        Ship ship = new Ship().setHullSize(55);
        ShipCharacteristics characteristics = new ShipCharacteristics(ship);
        MassCharacteristics instance = new MassCharacteristics(ship, characteristics);
        double expectedValue = 0d;
        assertEquals(expectedValue, instance.getMastArmorMass(), 0.01);
    }

    @Test
    void getMastShieldMass() {
        Ship ship = new Ship().setHullSize(55);
        ShipCharacteristics characteristics = new ShipCharacteristics(ship);
        MassCharacteristics instance = new MassCharacteristics(ship, characteristics);
        double expectedValue = 40.53d;
        assertEquals(expectedValue, instance.getMastShieldMass(), 0.01);
    }

    @Test
    void getMastStructuralMass() {
        Ship ship = new Ship().setHullSize(55);
        ShipCharacteristics characteristics = new ShipCharacteristics(ship);
        MassCharacteristics instance = new MassCharacteristics(ship, characteristics);
        double expectedValue = 18.10d;
        assertEquals(expectedValue, instance.getMastStructuralMass(), 0.01);
    }

    @Test
    void getHullSpaces() {
        Ship ship = new Ship().setHullSize(55);
        ShipCharacteristics characteristics = new ShipCharacteristics(ship);
        MassCharacteristics instance = new MassCharacteristics(ship, characteristics);
        int expectedValue = 44;
        assertEquals(expectedValue, instance.getHullSpaces());
    }

    @Test
    void getHullArmorSpaces() {
        Ship ship = new Ship();
        ShipCharacteristics characteristics = new ShipCharacteristics(ship);
        MassCharacteristics instance = new MassCharacteristics(ship, characteristics);
        int expectedValue = 1;
        assertEquals(expectedValue, instance.getHullArmorSpaces());
    }
}
