import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MassCharacteristicsTest {
    @Test
    void getHullArmorMass() {
        Ship ship = new Ship();
        MassCharacteristics instance = new MassCharacteristics(ship);
        int expectedValue = 25;
        assertEquals(expectedValue, instance.getHullArmorMass());
    }

    @Test
    void getHullArmorSpaces() {
        Ship ship = new Ship();
        MassCharacteristics instance = new MassCharacteristics(ship);
        int expectedValue = 1;
        assertEquals(expectedValue, instance.getHullArmorSpaces());
    }
}
