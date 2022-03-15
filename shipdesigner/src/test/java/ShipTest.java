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

    @Test
    void fieldOrigin() {
        Nation expectedValue = Nation.OLYMPIAN_REPUBLIC;
        String expectedNationName = "Olympian Republic";
        Ship instance = new Ship();
        instance.setOrigin(Nation.OLYMPIAN_REPUBLIC);

        assertEquals(expectedValue, instance.getOrigin());
        assertEquals(expectedNationName, instance.getOrigin().getNationName());
    }

    @Test
    void fieldShape() {
        Shape expectedValue = Shape.SPHEROID;
        String expectedShapeName = "Spheroid";
        Ship instance = new Ship();
        instance.setShape(Shape.SPHEROID);

        assertEquals(expectedValue, instance.getShape());
        assertEquals(expectedShapeName, instance.getShape().getShapeName());
    }
}
