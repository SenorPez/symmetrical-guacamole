import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShipTest {
    private int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

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

    @Test
    void fieldHullSize() {
        Integer expectedValue = 50;
        Ship instance = new Ship();
        instance.setHullSize(50);

        assertEquals(expectedValue, instance.getHullSize());
    }

    @Test
    void fieldHullSize_Minimum() {
        Integer expectedValue = 25;
        Ship instance = new Ship();
        instance.setHullSize(11);

        assertEquals(expectedValue, instance.getHullSize());
    }

    @Test
    void fieldSheetFormat() {
        SheetFormat expectedValue = SheetFormat.FOUR_UP;
        String expectedFormat = "4-Up";
        Ship instance = new Ship();
        instance.setSheetFormat(SheetFormat.FOUR_UP);

        assertEquals(expectedValue, instance.getSheetFormat());
        assertEquals(expectedFormat, instance.getSheetFormat().getFormat());
    }

    @Test
    void fieldLaidDown() {
        Integer expectedValue = 2259;
        Ship instance = new Ship();
        instance.setLaidDown(2259);

        assertEquals(expectedValue, instance.getLaidDown());
    }

    @Test
    void fieldBuildMode() {
        BuildMode expectedValue = BuildMode.QUICK;
        String expectedBuildModeName = "Quick and Dirty";
        Ship instance = new Ship();
        instance.setBuildMode(BuildMode.QUICK);

        assertEquals(expectedValue, instance.getBuildMode());
        assertEquals(expectedBuildModeName, instance.getBuildMode().getBuildModeName());
    }

    @Test
    void getShipClass_Gunboat() {
        int hullSize = getRandomNumber(25, 51);
        Ship instance = new Ship();
        String expectedValue = "Gunboat";
        assertEquals(expectedValue, instance.setHullSize(hullSize).getShipClass());
    }

    @Test
    void getShipClass_Corvette() {
        int hullSize = getRandomNumber(51, 151);
        Ship instance = new Ship();
        String expectedValue = "Corvette";
        assertEquals(expectedValue, instance.setHullSize(hullSize).getShipClass());
    }

    @Test
    void getShipClass_Frigate() {
        int hullSize = getRandomNumber(151, 251);
        Ship instance = new Ship();
        String expectedValue = "Frigate";
        assertEquals(expectedValue, instance.setHullSize(hullSize).getShipClass());
    }

    @Test
    void getShipClass_Light_Cruiser() {
        int hullSize = getRandomNumber(251, 351);
        Ship instance = new Ship();
        String expectedValue = "Light Cruiser";
        assertEquals(expectedValue, instance.setHullSize(hullSize).getShipClass());
    }

    @Test
    void getShipClass_Heavy_Cruiser() {
        int hullSize = getRandomNumber(351, 501);
        Ship instance = new Ship();
        String expectedValue = "Heavy Cruiser";
        assertEquals(expectedValue, instance.setHullSize(hullSize).getShipClass());
    }

    @Test
    void getShipClass_Battlecruiser() {
        int hullSize = getRandomNumber(501, 701);
        Ship instance = new Ship();
        String expectedValue = "Battlecruiser";
        assertEquals(expectedValue, instance.setHullSize(hullSize).getShipClass());
    }

    @Test
    void getShipClass_Battleship() {
        int hullSize = getRandomNumber(701, 1001);
        Ship instance = new Ship();
        String expectedValue = "Battleship";
        assertEquals(expectedValue, instance.setHullSize(hullSize).getShipClass());
    }

    @Test
    void getShipClass_Leviathan() {
        int hullSize = getRandomNumber(1001, 1801);
        Ship instance = new Ship();
        String expectedValue = "Leviathan";
        assertEquals(expectedValue, instance.setHullSize(hullSize).getShipClass());
    }

    @Test
    void getShipClass_Titan() {
        int hullSize = getRandomNumber(1801, 2500);
        Ship instance = new Ship();
        String expectedValue = "Titan";
        assertEquals(expectedValue, instance.setHullSize(hullSize).getShipClass());
    }

    @Test
    void getBuildTime_Standard() {
        Ship instance = new Ship();
        instance.setHullSize(275);
        instance.setBuildMode(BuildMode.STANDARD);
        Integer expectedValue = 29;
        assertEquals(expectedValue, instance.getBuildTime());
    }

    @Test
    void getBuildTime_Quick() {
        Ship instance = new Ship();
        instance.setHullSize(125);
        instance.setBuildMode(BuildMode.QUICK);
        Integer expectedValue = 7;
        assertEquals(expectedValue, instance.getBuildTime());
    }
}
