import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShipTest {
    @Test
    void fieldName() {
        String expectedValue = "Ship Name";
        Ship instance = new Ship();
        instance.setName("Ship Name");

        assertEquals(expectedValue, instance.getName());
    }
}
