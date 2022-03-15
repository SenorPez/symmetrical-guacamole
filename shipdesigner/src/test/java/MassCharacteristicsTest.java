import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MassCharacteristicsTest {
    @Test
    void getHullMass() {
        Ship ship = new Ship().setHullSize(55);
        MassCharacteristics instance = new MassCharacteristics(ship);
        int expectedValue = 1093;
        assertEquals(expectedValue, instance.getHullMass());
    }

    @Test
    void getHullArmorMass() {
        Ship ship = new Ship();
        MassCharacteristics instance = new MassCharacteristics(ship);
        int expectedValue = 25;
        assertEquals(expectedValue, instance.getHullArmorMass());
    }

    @Test
    void getHullSpaces() {
        Ship ship = new Ship().setHullSize(55);
        MassCharacteristics instance = new MassCharacteristics(ship);
        int expectedValue = 44;
        assertEquals(expectedValue, instance.getHullSpaces());
    }

    @Test
    void getHullArmorSpaces() {
        Ship ship = new Ship();
        MassCharacteristics instance = new MassCharacteristics(ship);
        int expectedValue = 1;
        assertEquals(expectedValue, instance.getHullArmorSpaces());
    }
}
