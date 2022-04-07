package com.senorpez.avt.shipdesigner.enums;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.senorpez.avt.shipdesigner.enums.MountConfiguration.*;
import static com.senorpez.avt.shipdesigner.enums.Shape.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class MountConfigurationTest {
    static List<TestData> testDataList = new ArrayList<>();

    @BeforeAll
    static void beforeAll() {
        testDataList.add(new TestData(CYLINDER, KEEL, 92, 35, 10, 1, 1, 8, 8, 5, 6, 12, 4, 5, 3, 17, 26, 4, 168));
        testDataList.add(new TestData(CYLINDER, SINGLE, 92, 31, 8, 1, 9, 8, 8, 5, 6, 12, 4, 5, 3, 17, 26, 4, 164));
        testDataList.add(new TestData(CYLINDER, DOUBLE, 92, 28, 7, 2, 9, 7, 8, 5, 6, 12, 4, 5, 3, 16, 26, 4, 184));
        testDataList.add(new TestData(LONG_CYLINDER, KEEL, 100, 38, 10, 1, 1, 8, 9, 5, 4, 12, 4, 5, 3, 19, 26, 4, 169));
        testDataList.add(new TestData(LONG_CYLINDER, SINGLE, 100, 35, 9, 1, 9, 8, 9, 5, 4, 12, 4, 5, 3, 19, 26, 4, 166));
        testDataList.add(new TestData(SPHEROID, SINGLE, 83, 26, 6, 1, 12, 7, 16, 4, 7, 21, 6, 4, 2, 16, 26, 3, 202));
        testDataList.add(new TestData(SPHEROID, DOUBLE, 83, 23, 6, 2, 13, 6, 14, 4, 6, 21, 5, 4, 2, 16, 26, 3, 194));
        testDataList.add(new TestData(SPHEROID, TRIPLE, 83, 21, 5, 3, 14, 6, 13, 4, 5, 21, 5, 4, 2, 16, 26, 3, 192));
        testDataList.add(new TestData(SPHEROID, QUADRUPLE, 83, 18, 4, 4, 15, 6, 11, 3, 4, 21, 5, 4, 2, 16, 26, 3, 180));
        testDataList.add(new TestData(SPHEROID, QUINTUPLE, 83, 16, 4, 5, 15, 5, 10, 3, 3, 21, 5, 4, 2, 16, 26, 3, 174));
        testDataList.add(new TestData(ELLIPSOID, SINGLE, 85, 26, 6, 1, 12, 7, 16, 4, 7, 21, 6, 4, 2, 16, 26, 3, 202));
        testDataList.add(new TestData(ELLIPSOID, DOUBLE, 85, 24, 6, 2, 13, 7, 14, 4, 6, 21, 5, 4, 2, 16, 26, 3, 196));
        testDataList.add(new TestData(ELLIPSOID, TRIPLE, 85, 21, 5, 3, 14, 6, 13, 4, 5, 21, 5, 4, 2, 16, 26, 3, 192));
        testDataList.add(new TestData(ELLIPSOID, QUADRUPLE, 85, 19, 5, 4, 15, 6, 11, 4, 4, 21, 5, 4, 2, 16, 26, 3, 184));
        testDataList.add(new TestData(ELLIPSOID, QUINTUPLE, 85, 16, 4, 5, 15, 5, 10, 3, 3, 21, 5, 4, 2, 16, 26, 3, 174));
        testDataList.add(new TestData(CONICAL, KEEL, 87, 31, 8, 1, 1, 8, 7, 4, 9, 23, 4, 4, 3, 14, 26, 3, 150));
        testDataList.add(new TestData(CONICAL, SINGLE, 87, 28, 7, 1, 15, 7, 7, 4, 9, 23, 4, 4, 3, 14, 26, 3, 147));
        testDataList.add(new TestData(CONICAL, DOUBLE, 87, 24, 6, 2, 16, 7, 7, 4, 8, 23, 4, 4, 2, 14, 26, 3, 160));
        testDataList.add(new TestData(CONICAL, TRIPLE, 87, 21, 5, 3, 17, 6, 7, 4, 7, 23, 4, 4, 2, 14, 26, 3, 168));
        testDataList.add(new TestData(CONICAL, QUADRUPLE, 87, 17, 4, 4, 18, 6, 7, 3, 6, 23, 4, 4, 2, 14, 26, 3, 166));
        testDataList.add(new TestData(HEMISPHEROID, SINGLE, 81, 23, 6, 1, 15, 6, 7, 4, 8, 24, 4, 4, 2, 15, 26, 3, 139));
        testDataList.add(new TestData(HEMISPHEROID, DOUBLE, 81, 21, 5, 1, 16, 6, 7, 4, 8, 24, 4, 4, 2, 15, 26, 3, 137));
        testDataList.add(new TestData(HEMISPHEROID, TRIPLE, 81, 19, 5, 2, 17, 6, 7, 4, 7, 24, 4, 4, 2, 15, 26, 3, 147));
        testDataList.add(new TestData(HEMISPHEROID, QUADRUPLE, 81, 18, 4, 3, 18, 6, 7, 3, 6, 24, 4, 4, 2, 15, 26, 3, 156));
        testDataList.add(new TestData(HEMISPHEROID, QUINTUPLE, 81, 16, 4, 4, 19, 5, 7, 3, 5, 24, 4, 4, 2, 15, 26, 3, 159));
        testDataList.add(new TestData(HEMISPHEROID, SEXTUPLE, 81, 14, 3, 4, 20, 5, 7, 2, 5, 24, 4, 4, 2, 15, 26, 3, 151));
    }

    @ParameterizedTest
    @MethodSource("provider")
    void getWeaponizableSpaces(TestData testData) {
        final int hullSpaces = 600;
        final int year = 2186;
        Shape shape = testData.shape;
        MountConfiguration mountConfiguration = testData.mountConfiguration;

        int expectedValue = testData.weaponizableSpaces;
        assertEquals(expectedValue, mountConfiguration.getWeaponizableSpaces(shape, hullSpaces, year));
    }

    @ParameterizedTest
    @MethodSource("provider")
    void getPrimaryMountTotalSpaces(TestData testData) {
        final int hullSpaces = 600;
        final int year = 2186;
        Shape shape = testData.shape;
        MountConfiguration mountConfiguration = testData.mountConfiguration;

        int expectedValue = testData.primaryMountTotalSpaces;
        assertEquals(expectedValue, mountConfiguration.getPrimaryMountTotalSpaces(shape, hullSpaces, year));
    }

    @ParameterizedTest
    @MethodSource("provider")
    void getPrimaryMountBiggestWeaponSpaces(TestData testData) {
        final int hullSpaces = 600;
        final int year = 2186;
        Shape shape = testData.shape;
        MountConfiguration mountConfiguration = testData.mountConfiguration;

        int expectedValue = testData.primaryMountBiggestWeaponSpaces;
        assertEquals(expectedValue, mountConfiguration.getPrimaryMountBiggestWeaponSpaces(shape, hullSpaces, year));
    }

    @ParameterizedTest
    @MethodSource("provider")
    void getPrimaryMountTotalCount(TestData testData) {
        Shape shape = testData.shape;
        MountConfiguration mountConfiguration = testData.mountConfiguration;

        int expectedValue = testData.primaryMountTotalCount;
        assertEquals(expectedValue, mountConfiguration.getPrimaryMountTotalCount(shape));
    }

    @ParameterizedTest
    @MethodSource("provider")
    void getPrimaryMountFieldOfFire(TestData testData) {
        Shape shape = testData.shape;
        MountConfiguration mountConfiguration = testData.mountConfiguration;

        int expectedValue = testData.primaryMountFieldOfFire;
        assertEquals(expectedValue, mountConfiguration.getPrimaryMountFieldOfFire(shape));
    }

    @ParameterizedTest
    @MethodSource("provider")
    void getPrimaryMountRows(TestData testData) {
        final int hullSpaces = 600;
        final int year = 2186;
        Shape shape = testData.shape;
        MountConfiguration mountConfiguration = testData.mountConfiguration;

        int expectedValue = testData.primaryMountRows;
        assertEquals(expectedValue, mountConfiguration.getPrimaryMountRows(shape, hullSpaces, year));
    }

    @ParameterizedTest
    @MethodSource("provider")
    void getSecondaryMountTotalSpaces(TestData testData) {
        final int hullSpaces = 600;
        final int year = 2186;
        Shape shape = testData.shape;
        MountConfiguration mountConfiguration = testData.mountConfiguration;

        int expectedValue = testData.secondaryMountTotalSpaces;
        assertEquals(expectedValue, mountConfiguration.getSecondaryMountTotalSpaces(shape, hullSpaces, year));
    }

    @ParameterizedTest
    @MethodSource("provider")
    void getSecondaryMountBiggestWeaponSpaces(TestData testData) {
        final int hullSpaces = 600;
        final int year = 2186;
        Shape shape = testData.shape;
        MountConfiguration mountConfiguration = testData.mountConfiguration;

        int expectedValue = testData.secondaryMountBiggestWeaponSpaces;
        assertEquals(expectedValue, mountConfiguration.getSecondaryMountBiggestWeaponSpaces(shape, hullSpaces, year));
    }

    @ParameterizedTest
    @MethodSource("provider")
    void getSecondaryMountTotalCount(TestData testData) {
        final int hullSpaces = 600;
        final int year = 2186;
        Shape shape = testData.shape;
        MountConfiguration mountConfiguration = testData.mountConfiguration;

        int expectedValue = testData.secondaryMountTotalCount;
        assertEquals(expectedValue, mountConfiguration.getSecondaryMountTotalCount(shape, hullSpaces, year));
    }

    @ParameterizedTest
    @MethodSource("provider")
    void getSecondaryMountFieldOfFire(TestData testData) {
        Shape shape = testData.shape;
        MountConfiguration mountConfiguration = testData.mountConfiguration;

        int expectedValue = testData.secondaryMountFieldOfFire;
        assertEquals(expectedValue, mountConfiguration.getSecondaryMountFieldOfFire(shape));
    }

    @ParameterizedTest
    @MethodSource("provider")
    void getSecondaryMountRows(TestData testData) {
        final int hullSpaces = 600;
        final int year = 2186;
        Shape shape = testData.shape;
        MountConfiguration mountConfiguration = testData.mountConfiguration;

        int expectedValue = testData.secondaryMountRows;
        assertEquals(expectedValue, mountConfiguration.getSecondaryMountRows(shape, hullSpaces, year));
    }

    @ParameterizedTest
    @MethodSource("provider")
    void getTertiaryMountTotalSpaces(TestData testData) {
        final int hullSpaces = 600;
        final int year = 2186;
        Shape shape = testData.shape;
        MountConfiguration mountConfiguration = testData.mountConfiguration;

        int expectedValue = testData.tertiaryMountTotalSpaces;
        assertEquals(expectedValue, mountConfiguration.getTertiaryMountTotalSpaces(shape, hullSpaces, year));
    }

    @ParameterizedTest
    @MethodSource("provider")
    void getTertiaryMountBiggestWeaponSpaces(TestData testData) {
        final int hullSpaces = 600;
        final int year = 2186;
        Shape shape = testData.shape;
        MountConfiguration mountConfiguration = testData.mountConfiguration;

        int expectedValue = testData.tertiaryMountBiggestWeaponSpaces;
        assertEquals(expectedValue, mountConfiguration.getTertiaryMountBiggestWeaponSpaces(shape, hullSpaces, year));
    }

    @ParameterizedTest
    @MethodSource("provider")
    void getTertiaryMountTotalCount(TestData testData) {
        final int hullSpaces = 600;
        final int year = 2186;
        Shape shape = testData.shape;
        MountConfiguration mountConfiguration = testData.mountConfiguration;

        int expectedValue = testData.tertiaryMountTotalCount;
        assertEquals(expectedValue, mountConfiguration.getTertiaryMountTotalCount(shape, hullSpaces, year));
    }

    @ParameterizedTest
    @MethodSource("provider")
    void getTertiaryMountFieldOfFire(TestData testData) {
        Shape shape = testData.shape;
        MountConfiguration mountConfiguration = testData.mountConfiguration;

        int expectedValue = testData.tertiaryMountFieldOfFire;
        assertEquals(expectedValue, mountConfiguration.getTertiaryMountFieldOfFire(shape));
    }

    @ParameterizedTest
    @MethodSource("provider")
    void getTertiaryMountRows(TestData testData) {
        final int hullSpaces = 600;
        final int year = 2186;
        Shape shape = testData.shape;
        MountConfiguration mountConfiguration = testData.mountConfiguration;

        int expectedValue = testData.tertiaryMountRows;
        assertEquals(expectedValue, mountConfiguration.getTertiaryMountRows(shape, hullSpaces, year));
    }

    @ParameterizedTest
    @MethodSource("provider")
    void getMountTotalSpaces(TestData testData) {
        final int hullSpaces = 600;
        final int year = 2186;
        Shape shape = testData.shape;
        MountConfiguration mountConfiguration = testData.mountConfiguration;

        int expectedValue = testData.totalMountSpaces;
        assertEquals(expectedValue, mountConfiguration.getMountTotalSpaces(shape, hullSpaces, year));
    }

    private static Stream<TestData> provider() {
        return testDataList.stream();
    }

    private record TestData(Shape shape,
                            MountConfiguration mountConfiguration,
                            int weaponizableSpaces,
                            int primaryMountTotalSpaces,
                            int primaryMountBiggestWeaponSpaces,
                            int primaryMountTotalCount,
                            int primaryMountFieldOfFire,
                            int primaryMountRows,
                            int secondaryMountTotalSpaces,
                            int secondaryMountBiggestWeaponSpaces,
                            int secondaryMountTotalCount,
                            int secondaryMountFieldOfFire,
                            int secondaryMountRows,
                            int tertiaryMountTotalSpaces,
                            int tertiaryMountBiggestWeaponSpaces,
                            int tertiaryMountTotalCount,
                            int tertiaryMountFieldOfFire,
                            int tertiaryMountRows,
                            int totalMountSpaces) {
    }
}