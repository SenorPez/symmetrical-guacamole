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
        Ship instance = new Ship().setName("Ship Name");
        String expectedValue = "Ship Name";
        assertEquals(expectedValue, instance.getName());
    }

    @Test
    void fieldOrigin() {
        Ship instance = new Ship().setOrigin(Nation.OLYMPIAN_REPUBLIC);

        Nation expectedValue = Nation.OLYMPIAN_REPUBLIC;
        String expectedNationName = "Olympian Republic";
        assertEquals(expectedValue, instance.getOrigin());
        assertEquals(expectedNationName, instance.getOrigin().getNationName());
    }

    @Test
    void fieldShape() {
        Ship instance = new Ship().setShape(Shape.SPHEROID);

        Shape expectedValue = Shape.SPHEROID;
        String expectedShapeName = "Spheroid";
        assertEquals(expectedValue, instance.getShape());
        assertEquals(expectedShapeName, instance.getShape().getShapeName());
    }

    @Test
    void fieldHullSize() {
        Ship instance = new Ship().setHullSize(50);
        Integer expectedValue = 50;
        assertEquals(expectedValue, instance.getHullSize());
    }

    @Test
    void fieldHullSize_Minimum() {
        Ship instance = new Ship().setHullSize(11);
        Integer expectedValue = 25;
        assertEquals(expectedValue, instance.getHullSize());
    }

    @Test
    void fieldSheetFormat() {
        Ship instance = new Ship().setSheetFormat(SheetFormat.FOUR_UP);

        SheetFormat expectedValue = SheetFormat.FOUR_UP;
        String expectedFormat = "4-Up";
        assertEquals(expectedValue, instance.getSheetFormat());
        assertEquals(expectedFormat, instance.getSheetFormat().getFormat());
    }

    @Test
    void fieldLaidDown() {
        Ship instance = new Ship().setLaidDown(2259);
        Integer expectedValue = 2259;
        assertEquals(expectedValue, instance.getLaidDown());
    }

    @Test
    void fieldBuildMode() {
        Ship instance = new Ship().setBuildMode(BuildMode.QUICK);

        BuildMode expectedValue = BuildMode.QUICK;
        String expectedBuildModeName = "Quick and Dirty";
        assertEquals(expectedValue, instance.getBuildMode());
        assertEquals(expectedBuildModeName, instance.getBuildMode().getBuildModeName());
    }

    @Test
    void getShipClass_Gunboat() {
        int hullSize = getRandomNumber(25, 51);
        Ship instance = new Ship().setHullSize(hullSize);
        String expectedValue = "Gunboat";
        assertEquals(expectedValue, instance.getShipClass());
    }

    @Test
    void getShipClass_Corvette() {
        int hullSize = getRandomNumber(51, 151);
        Ship instance = new Ship().setHullSize(hullSize);
        String expectedValue = "Corvette";
        assertEquals(expectedValue, instance.getShipClass());
    }

    @Test
    void getShipClass_Frigate() {
        int hullSize = getRandomNumber(151, 251);
        Ship instance = new Ship().setHullSize(hullSize);
        String expectedValue = "Frigate";
        assertEquals(expectedValue, instance.getShipClass());
    }

    @Test
    void getShipClass_Light_Cruiser() {
        int hullSize = getRandomNumber(251, 351);
        Ship instance = new Ship().setHullSize(hullSize);
        String expectedValue = "Light Cruiser";
        assertEquals(expectedValue, instance.getShipClass());
    }

    @Test
    void getShipClass_Heavy_Cruiser() {
        int hullSize = getRandomNumber(351, 501);
        Ship instance = new Ship().setHullSize(hullSize);
        String expectedValue = "Heavy Cruiser";
        assertEquals(expectedValue, instance.getShipClass());
    }

    @Test
    void getShipClass_Battlecruiser() {
        int hullSize = getRandomNumber(501, 701);
        Ship instance = new Ship().setHullSize(hullSize);
        String expectedValue = "Battlecruiser";
        assertEquals(expectedValue, instance.getShipClass());
    }

    @Test
    void getShipClass_Battleship() {
        int hullSize = getRandomNumber(701, 1001);
        Ship instance = new Ship().setHullSize(hullSize);
        String expectedValue = "Battleship";
        assertEquals(expectedValue, instance.getShipClass());
    }

    @Test
    void getShipClass_Leviathan() {
        int hullSize = getRandomNumber(1001, 1801);
        Ship instance = new Ship().setHullSize(hullSize);
        String expectedValue = "Leviathan";
        assertEquals(expectedValue, instance.getShipClass());
    }

    @Test
    void getShipClass_Titan() {
        int hullSize = getRandomNumber(1801, 2500);
        Ship instance = new Ship().setHullSize(hullSize);
        String expectedValue = "Titan";
        assertEquals(expectedValue, instance.getShipClass());
    }

    @Test
    void getBuildTime_Standard() {
        Ship instance = new Ship()
                .setHullSize(275)
                .setBuildMode(BuildMode.STANDARD);
        Integer expectedValue = 29;
        assertEquals(expectedValue, instance.getBuildTime());
    }

    @Test
    void getBuildTime_Quick() {
        Ship instance = new Ship()
                .setHullSize(125)
                .setBuildMode(BuildMode.QUICK);
        Integer expectedValue = 7;
        assertEquals(expectedValue, instance.getBuildTime());
    }

    @Test
    void getMonetaryCost_Standard() {
        Ship instance = new Ship().setBuildMode(BuildMode.STANDARD);
        Double expectedValue = 61d;
        assertEquals(expectedValue, instance.getMonetaryCost());
    }

    @Test
    void getMonetaryCost_Quick() {
        Ship instance = new Ship().setBuildMode(BuildMode.QUICK);
        Double expectedValue = 76.25d;
        assertEquals(expectedValue, instance.getMonetaryCost());
    }

    @Test
    void fieldPercentOfficers() {
        int percentOfficers = getRandomNumber(10, 22);
        Ship instance = new Ship().setPercentOfficers(percentOfficers);
        Integer expectedValue = percentOfficers;
        assertEquals(expectedValue, instance.getPercentOfficers());
    }

    @Test
    void fieldPercentOfficers_Low() {
        int percentOfficers = getRandomNumber(1, 10);
        Ship instance = new Ship().setPercentOfficers(percentOfficers);
        Integer expectedValue = 10;
        assertEquals(expectedValue, instance.getPercentOfficers());
    }

    @Test
    void fieldPercentOfficers_High() {
        int percentOfficers = getRandomNumber(23, 100);
        Ship instance = new Ship().setPercentOfficers(percentOfficers);
        Integer expectedValue = 22;
        assertEquals(expectedValue, instance.getPercentOfficers());
    }

    @Test
    void getDuelCost() {
        Ship instance = new Ship();
        Integer expectedValue = 644;
        assertEquals(expectedValue, instance.getDuelCost());
    }

    @Test
    void getEconomicCost() {
        Ship instance = new Ship();
        Integer expectedValue = 610;
        assertEquals(expectedValue, instance.getEconomicCost());
    }

    @Test
    void getMaintenanceCost() {
        Ship instance = new Ship();
        Integer expectedValue = 185;
        assertEquals(expectedValue, instance.getMaintenanceCost());
    }

    @Test
    void getBoxes() {
        Ship instance = new Ship();
        Integer expectedValue = 61;
        assertEquals(expectedValue, instance.getBoxes());
    }

    @Test
    void getMinimumCrew() {
        Ship instance = new Ship();
        Integer expectedValue = 26;
        assertEquals(expectedValue, instance.getMinimumCrew());
    }

    @Test
    void getOfficers() {
        Ship instance = new Ship().setPercentOfficers(20);
        Integer expectedValue = 5;
        assertEquals(expectedValue, instance.getOfficers());
    }

    @Test
    void getEnlisted() {
        Ship instance = new Ship().setPercentOfficers(20);
        Integer expectedValue = 21;
        assertEquals(expectedValue, instance.getEnlisted());
    }

    @Test
    void getGunboatCrew() {
        Ship instance = new Ship();
        Integer expectedValue = 10;
        assertEquals(expectedValue, instance.getGunboatCrew());
    }
}
