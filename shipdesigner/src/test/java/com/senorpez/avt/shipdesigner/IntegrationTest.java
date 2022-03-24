package com.senorpez.avt.shipdesigner;

import com.senorpez.avt.shipdesigner.enums.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegrationTest {
    private static Ship ship;

    @BeforeAll
    static void beforeAll() {
        ship = new Ship()
                .setName("Mansur Mk 1")
                .setOrigin(Nation.MEDINA)
                .setShape(Shape.CYLINDER)
                .setHullSize(402)
                .setSheetFormat(SheetFormat.ONE_UP)
                .setLaidDown(2190)
                .setBuildMode(BuildMode.STANDARD)
                .setPercentOfficers(20)
                .setDriveGeneration(3.8d)
                .setMaximumThrust(3)
                .setDriveArmor(1)
                .setMastArmor(1)
                .build();
    }

    @Test
    void getShipClass() {
        String expectedValue = "Heavy Cruiser";
        assertEquals(expectedValue, ship.getShipClass());
    }

    @Test
    void getBuildTime() {
        int expectedValue = 45;
        assertEquals(expectedValue, ship.getBuildTime());
    }

    @Test
    void getMonetaryCost() {
        double expectedValue = 264.2d;
        assertEquals(expectedValue, ship.getMonetaryCost());
    }

    @Test
    void getDuelCost() {
        int expectedValue = 2191;
        assertEquals(expectedValue, ship.getDuelCost());
    }

    @Test
    void getEconomicCost() {
        int expectedValue = 2642;
        assertEquals(expectedValue, ship.getEconomicCost());
    }

    @Test
    void getMaintenanceCost() {
        int expectedValue = 444;
        assertEquals(expectedValue, ship.getMaintenanceCost());
    }

    @Test
    void getBoxes() {
        int expectedValue = 290;
        assertEquals(expectedValue, ship.getBoxes());
    }

    @Test
    void getOfficers() {
        int expectedValue = 24;
        assertEquals(expectedValue, ship.getOfficers());
    }

    @Test
    void getEnlisted() {
        int expectedValue = 97;
        assertEquals(expectedValue, ship.getEnlisted());
    }

    @Test
    void getGunboatCrew() {
        int expectedValue = 0;
        assertEquals(expectedValue, ship.getGunboatCrew());
    }

    @Test
    void getExtraBerths() {
        int expectedValue = 9;
        assertEquals(expectedValue, ship.getExtraBerths());
    }

    @Test
    void getCruiseDuration() {
        int expectedValue = 28;
        assertEquals(expectedValue, ship.getCruiseDuration());
    }

    @Test
    void getNoseHullDepth() {
        int expectedValue = 74;
        assertEquals(expectedValue, ship.getNoseHullDepth());
    }

    @Test
    void getNoseSurfaceArea() {
        int expectedValue = 32;
        assertEquals(expectedValue, ship.getNoseSurfaceArea());
    }

    @Test
    void getLateralHullDepth() {
        int expectedValue = 15;
        assertEquals(expectedValue, ship.getLateralHullDepth());
    }

    @Test
    void getLateralSurfaceArea() {
        int expectedValue = 23;
        assertEquals(expectedValue, ship.getLateralSurfaceArea());
    }

    @Test
    void getAftHullDepth() {
        int expectedValue = 74;
        assertEquals(expectedValue, ship.getAftHullDepth());
    }

    @Test
    void getAftSurfaceArea() {
        int expectedValue = 32;
        assertEquals(expectedValue, ship.getAftSurfaceArea());
    }

    @Test
    void getHyperdriveCost() {
        Map<String, Integer> expectedValue = new HashMap<>();
        expectedValue.put("Gamma", 16);
        expectedValue.put("Delta", 24);
        expectedValue.put("Epsilon", 32);

        assertEquals(expectedValue, ship.getHyperdriveCost());
    }

    @Test
    void getPodHyperdriveSurcharge() {
        Map<String, Double> expectedValue = new HashMap<>();
        expectedValue.put("Gamma", 1d);
        expectedValue.put("Delta", 1.5d);
        expectedValue.put("Epsilon", 2d);

        assertEquals(expectedValue, ship.getPodHyperdriveSurcharge());
    }

    @Test
    void getBatteryCapacity() {
        int expectedValue = 40;
        assertEquals(expectedValue, ship.getBatteryCapacity());
    }

    @Test
    void getPowerPerSegment() {
        int expectedValue = 8;
        assertEquals(expectedValue, ship.getPowerPerSegment());
    }

    @Test
    void getPowerPerTurn() {
        int expectedValue = 0;
        assertEquals(expectedValue, ship.getPowerPerTurn());
    }

    @Test
    void getSolarPowerPerTurn() {
        int expectedValue = 0;
        assertEquals(expectedValue, ship.getSolarPowerPerTurn());
    }

    @Test
    void getChemicalBatteryPowerPerSegment() {
        int expectedValue = 0;
        assertEquals(expectedValue, ship.getChemicalBatteryPowerPerSegment());
    }

    @Test
    void hasMHD() {
        boolean expectedValue = false;
        assertEquals(expectedValue, ship.hasMHD());
    }

    @Test
    void getHeatSinkCapacity() {
        int expectedValue = 40;
        assertEquals(expectedValue, ship.getHeatSinkCapacity());
    }

    @Test
    void getRadiators() {
        double expectedValue = 1.6d;
        assertEquals(expectedValue, ship.getRadiators());
    }

    @Test
    void getFlexPoints() {
        int expectedValue = 2;
        assertEquals(expectedValue, ship.getFlexPoints());
    }

    @Test
    void getFlagPoints() {
        int expectedValue = 1;
        assertEquals(expectedValue, ship.getFlagPoints());
    }

    @Test
    void hasImprovedAccessways() {
        boolean expectedValue = false;
        assertEquals(expectedValue, ship.hasImprovedAccessways());
    }

    @Test
    void getStructuralIntegrity() {
        int expectedValue = 18;
        assertEquals(expectedValue, ship.getStructuralIntegrity());
    }

    @Test
    void getHullLength() {
        double expectedValue = 101.57902d;
        assertEquals(expectedValue, ship.getHullLength());
    }

    @Test
    void getHullDiameter() {
        double expectedValue = 20.31580d;
        assertEquals(expectedValue, ship.getHullDiameter());
    }

    @Test
    void getMastLength() {
        // Probably going to be wrong because of formula error I found.
        double expectedValue = 72.32566;
        assertEquals(expectedValue, ship.getMastLength());
    }

    @Test
    void getMastDiameter() {
        double expectedValue = 4.82171;
        assertEquals(expectedValue, ship.getMastDiameter());
    }

    @Test
    void getShieldDiameter() {
        double expectedValue = 3.59128d;
        assertEquals(expectedValue, ship.getShieldDiameter());
    }

    @Test
    void getShieldThickness() {
        double expectedValue = 2.69531d;
        assertEquals(expectedValue, ship.getShieldThickness());
    }

    @Test
    void getDriveDiameter() {
        double expectedValue = 31.06123d;
        assertEquals(expectedValue, ship.getDriveDiameter());
    }

    @Test
    void getTotalShipLength() {
        double expectedValue = 189.43530d;
        assertEquals(expectedValue, ship.getTotalShipLength());
    }

    @Test
    void getDriveOutput() {
        double expectedValue = 4.85608d;
        assertEquals(expectedValue, ship.getDriveOutput());
    }

    @Test
    void getDriveFlux() {
        double expectedValue = 8.04000d;
        assertEquals(expectedValue, ship.getDriveFlux());
    }

    @Test
    void getDriveDamage() {
        int expectedValue = 12;
        assertEquals(expectedValue, ship.getDriveDamage());
    }

    @Test
    void getDriveHullDepth() {
        int expectedValue = 4;
        assertEquals(expectedValue, ship.getDriveHullDepth());
    }

    @Test
    void getMastHullDepth() {
        int expectedValue = 3;
        assertEquals(expectedValue, ship.getMastHullDepth());
    }

    @Test
    void getPivotMode() {
        ManeuverMode expectedValue = ManeuverMode.M;
        assertEquals(expectedValue, ship.getPivotMode());
    }

    @Test
    void getRollMode() {
        ManeuverMode expectedValue = ManeuverMode.I;
        assertEquals(expectedValue, ship.getRollMode());
    }

    @Test
    void getPinwheelBoxes() {
        int expectedValue = 2;
        assertEquals(expectedValue, ship.getPinwheelBoxes());
    }

    @Test
    void getMastTrack() {
        int expectedValue = 13;
        assertEquals(expectedValue, ship.getMastTrack());
    }

    @Test
    void getEngineTrack() {
        int expectedValue = 9;
        assertEquals(expectedValue, ship.getEngineTrack());
    }

    @Test
    void getFuelSpaces() {
        int expectedValue = 65;
        assertEquals(expectedValue, ship.getFuelSpaces());
    }

    @Test
    void getFuelDotsPerSpace() {
        double expectedValue = 1.90000d;
        assertEquals(expectedValue, ship.getFuelDotsPerSpace());
    }

    @Test
    void getFuelDots() {
        int expectedValue = 132;
        assertEquals(expectedValue, ship.getFuelDots());
    }

    @Test
    void getSpacesToDeltaThrust() {
        int expectedValue = 57;
        assertEquals(expectedValue, ship.getSpacesToDeltaThrust());
    }

    @Test
    void getFuelBySpace() {
        List<FuelSpace> expectedValue = new ArrayList<>();
        expectedValue.add(new FuelSpace(1.89267d, 1));
        assertEquals(expectedValue, ship.getFuelBySpace());
    }

    @Test
    void getTotalExternalArmor() {
        int expectedValue = 206;
        assertEquals(expectedValue, ship.getTotalExternalArmor());
    }

    @Test
    void getAvailableExternalArmor() {
        int expectedValue = 0;
        assertEquals(expectedValue, ship.getAvailableExternalArmor());
    }

    @Test
    void getUsedExternalArmor() {
        int expectedValue = 206;
        assertEquals(expectedValue, ship.getUsedExternalArmor());
    }

    @Test
    void getMaximumNoseArmor() {
        int expectedValue = 18;
        assertEquals(expectedValue, ship.getMaximumNoseArmor());
    }

    @Test
    void getMaximumLateralArmor() {
        int expectedValue = 26;
        assertEquals(expectedValue, ship.getMaximumLateralArmor());
    }

    @Test
    void getMaximumAftArmor() {
        int expectedValue = 18;
        assertEquals(expectedValue, ship.getMaximumAftArmor());
    }

    @Test
    void getTotalNoseArmor() {
        int expectedValue = 12;
        assertEquals(expectedValue, ship.getTotalNoseArmor());
    }

    @Test
    void getTotalAftArmor() {
        int expectedValue = 0;
        assertEquals(expectedValue, ship.getTotalNoseArmor());
    }

    @Test
    void getTotalTopArmor() {
        int expectedValue = 9;
        assertEquals(expectedValue, ship.getTotalNoseArmor());
    }

    @Test
    void getTotalBottomArmor() {
        int expectedValue = 34;
        assertEquals(expectedValue, ship.getTotalNoseArmor());
    }

    @Test
    void getTotalPortArmor() {
        int expectedValue = 15;
        assertEquals(expectedValue, ship.getTotalNoseArmor());
    }

    @Test
    void getTotalStarboardArmor() {
        int expectedValue = 15;
        assertEquals(expectedValue, ship.getTotalNoseArmor());
    }
}
