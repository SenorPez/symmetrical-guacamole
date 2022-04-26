package com.senorpez.avt.shipdesigner;

import com.senorpez.avt.shipdesigner.enums.*;
import com.senorpez.avt.shipdesigner.systems.CoreSystems;
import com.senorpez.avt.shipdesigner.systems.InternalSystems;
import com.senorpez.avt.shipdesigner.systems.StructuralSystems;
import com.senorpez.avt.shipdesigner.weapons.Mount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static com.senorpez.avt.shipdesigner.enums.BuildMode.QUICK;
import static com.senorpez.avt.shipdesigner.enums.BuildMode.STANDARD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShipTest {
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    StructuralSystems structuralSystems;
    @Mock
    CoreSystems coreSystems;
    @Mock
    InternalSystems internalSystems;
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    List<Mount> mounts;

    Ship instance;

    private int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    @BeforeEach
    void setUp() {
        instance = spy(new Ship());
    }

    @Test
    void getDuelCost() {
        instance = instance
                .setMaximumThrust(10)
                .setStructuralSystems(structuralSystems)
                .setCoreSystems(coreSystems)
                .setInternalSystems(internalSystems)
                .setMounts(mounts)
                .build();
        when(structuralSystems.getDuelCost()).thenReturn(324);
        when(coreSystems.getDuelCost()).thenReturn(66);
        when(internalSystems.getDuelCost()).thenReturn(147);
        when(mounts.stream().map(any()).reduce(any())).thenReturn(Optional.of(107));

        doReturn(ManeuverMode.I).when(instance).getPivotMode();
        doReturn(43).when(instance).getMinimumCrew();

        int expectedValue = 724;
        assertEquals(expectedValue, instance.getDuelCost());
    }

    @Test
    void getEconomicCost() {
        instance = instance
                .setMaximumThrust(10)
                .setStructuralSystems(structuralSystems)
                .setCoreSystems(coreSystems)
                .setInternalSystems(internalSystems)
                .setMounts(mounts)
                .build();
        when(structuralSystems.getEconomicCost()).thenReturn(324);
        when(coreSystems.getEconomicCost()).thenReturn(66);
        when(internalSystems.getEconomicCost()).thenReturn(147);
        when(mounts.stream().map(any()).reduce(any())).thenReturn(Optional.of(107));

        doReturn(43).when(instance).getMinimumCrew();

        int expectedValue = 730;
        assertEquals(expectedValue, instance.getEconomicCost());
    }

    @Test
    void getMaintenanceCostPerYear() {
        instance = instance
                .setMaximumThrust(10)
                .setStructuralSystems(structuralSystems)
                .setCoreSystems(coreSystems)
                .setInternalSystems(internalSystems)
                .setMounts(mounts)
                .setBuildMode(STANDARD)
                .build();
        when(structuralSystems.hasImprovedAccessways()).thenReturn(false);
        when(structuralSystems.getMaintenanceCostPerYear()).thenReturn(57.45d);
        when(coreSystems.getMaintenanceCostPerYear()).thenReturn(13.35d);
        when(internalSystems.getMaintenanceCostPerYear()).thenReturn(27.05d);
        when(mounts.stream().map(any()).reduce(any())).thenReturn(Optional.of(107));

        int expectedValue = 120;
        assertEquals(expectedValue, instance.getMaintenanceCostPerYear());

        instance = instance.setBuildMode(QUICK);
        expectedValue = 191;
        assertEquals(expectedValue, instance.getMaintenanceCostPerYear());

        when(structuralSystems.hasImprovedAccessways()).thenReturn(true);
        expectedValue = 179;
        assertEquals(expectedValue, instance.getMaintenanceCostPerYear());

        instance = instance.setBuildMode(STANDARD);
        expectedValue = 108;
        assertEquals(expectedValue, instance.getMaintenanceCostPerYear());
    }

    @Test
    void getMinimumCrew() {
        instance = instance
                .setStructuralSystems(structuralSystems)
                .setCoreSystems(coreSystems)
                .setInternalSystems(internalSystems)
                .setMounts(mounts)
                .build();
        when(structuralSystems.getCrewRequirement()).thenReturn(8.5d);
        when(coreSystems.getCrewRequirement()).thenReturn(7.5d);
        when(internalSystems.getCrewRequirement()).thenReturn(20.25d);
        when(mounts.stream().map(any()).reduce(any())).thenReturn(Optional.of(6.5));
        when(structuralSystems.getHull().getShrinkEnhancement()).thenReturn(0);

        int expectedValue = 43;
        assertEquals(expectedValue, instance.getMinimumCrew());
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
        Ship instance = new Ship().setBuildMode(QUICK);

        BuildMode expectedValue = QUICK;
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
                .setBuildMode(STANDARD);
        Integer expectedValue = 29;
        assertEquals(expectedValue, instance.getBuildTime());
    }

    @Test
    void getBuildTime_Quick() {
        Ship instance = new Ship()
                .setHullSize(125)
                .setBuildMode(QUICK);
        Integer expectedValue = 7;
        assertEquals(expectedValue, instance.getBuildTime());
    }

    @Test
    void getMonetaryCost_Standard() {
        Ship instance = new Ship().setBuildMode(STANDARD);
        Double expectedValue = 61d;
        assertEquals(expectedValue, instance.getMonetaryCost());
    }

    @Test
    void getMonetaryCost_Quick() {
        Ship instance = new Ship().setBuildMode(QUICK);
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
    void getMaintenanceCost() {
        Ship instance = new Ship();
        Integer expectedValue = 185;
        assertEquals(expectedValue, instance.getMaintenanceCostPerYear());
    }

    @Test
    void getBoxes() {
        Ship instance = new Ship();
        Integer expectedValue = 61;
        assertEquals(expectedValue, instance.getBoxes());
    }

    @Test
    void getOfficers() {
        Ship instance = new Ship().setPercentOfficers(20);
        Integer expectedValue = 7;
        assertEquals(expectedValue, instance.getOfficers());
    }

    @Test
    void getEnlisted() {
        Ship instance = new Ship().setPercentOfficers(20);
        Integer expectedValue = 29;
        assertEquals(expectedValue, instance.getEnlisted());
    }

    @Test
    void getGunboatCrew() {
        Ship instance = new Ship();
        Integer expectedValue = 10;
        assertEquals(expectedValue, instance.getGunboatCrew());
    }

    @Test
    void getExtraBerths() {
        Ship instance = new Ship();
        Integer expectedValue = 4;
        assertEquals(expectedValue, instance.getExtraBerths());
    }

    @Test
    void getCruiseDuration() {
        Ship instance = new Ship();
        Integer expectedValue = 4;
        assertEquals(expectedValue, instance.getCruiseDuration());
    }

    @Test
    void fieldDriveGeneration() {
        Ship instance = new Ship().setDriveGeneration(3.0d);
        Double expectedValue = 3d;
        assertEquals(expectedValue, instance.getDriveGeneration());
    }

    @Test
    void fieldDriveGeneration_Round() {
        Ship instance = new Ship().setDriveGeneration(2.34d);
        Double expectedValue = 2.3d;
        assertEquals(expectedValue, instance.getDriveGeneration());
    }

    @Test
    void fieldMaximumThrust() {
        Ship instance = new Ship().setMaximumThrust(11d);
        Double expectedValue = 11d;
        assertEquals(expectedValue, instance.getMaximumThrust());
    }

    @Test
    void fieldMaximumThrust_Round() {
        Ship instance = new Ship().setMaximumThrust(4.7d);
        Double expectedValue = 4.5d;
        assertEquals(expectedValue, instance.getMaximumThrust());
    }

    @Test
    void fieldDriveArmor() {
        Ship instance = new Ship().setDriveArmor(4);
        Integer expectedValue = 4;
        assertEquals(expectedValue, instance.getDriveArmor());
    }

    @Test
    void fieldMastArmor() {
        Ship instance = new Ship().setMastArmor(4);
        Integer expectedValue = 4;
        assertEquals(expectedValue, instance.getMastArmor());
    }

    @Test
    void getMountConfiguration() {
        Ship instance = new Ship().setMountConfiguration(MountConfiguration.TRIPLE);
        MountConfiguration expectedValue = MountConfiguration.TRIPLE;
        assertEquals(expectedValue, instance.getMountConfiguration());
    }
}
