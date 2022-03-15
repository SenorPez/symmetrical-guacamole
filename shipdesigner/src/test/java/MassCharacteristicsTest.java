import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MassCharacteristicsTest {
    @Test
    void getDriveMass() {
        Ship ship = new Ship()
                .setHullSize(55)
                .setShape(Shape.ELLIPSOID);
        ShipCharacteristics characteristics = new ShipCharacteristics(ship);
        MassCharacteristics instance = new MassCharacteristics(ship, characteristics);
        double expectedValue = 257.363868;
        assertEquals(expectedValue, instance.getDriveMass(), 0.01);
    }

    @Test
    void getHullMass() {
        Ship ship = new Ship()
                .setHullSize(55)
                .setShape(Shape.ELLIPSOID);
        ShipCharacteristics characteristics = new ShipCharacteristics(ship);
        MassCharacteristics instance = new MassCharacteristics(ship, characteristics);
        double expectedValue = 1092.6361;
        assertEquals(expectedValue, instance.getHullMass(), 0.1d);
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
        assertEquals(expectedValue, instance.getMastStructuralMass(), 0.01);
    }

    @Test
    void getDriveSpaces() {
        Ship ship = new Ship()
                .setHullSize(55)
                .setShape(Shape.ELLIPSOID);
        ShipCharacteristics characteristics = new ShipCharacteristics(ship);
        MassCharacteristics instance = new MassCharacteristics(ship, characteristics);
        double expectedValue = 10.29455d;
        assertEquals(expectedValue, instance.getDriveSpaces(), 0.1);
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
}
