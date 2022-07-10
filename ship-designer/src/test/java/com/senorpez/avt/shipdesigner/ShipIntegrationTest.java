package com.senorpez.avt.shipdesigner;

import com.senorpez.avt.shipdesigner.systems.ProductionLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.senorpez.avt.shipdesigner.HullShape.SPHERE;
import static com.senorpez.avt.shipdesigner.systems.ProductionLevel.STANDARD;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShipIntegrationTest {
    @Nested
    @DisplayName("York (Implicit")
    class YorkImplicitTest {
        Ship instance;
        final double tolerance = 1e-5;

        @BeforeEach
        void setUp() {
            instance = new Ship()
                    .setDriveGeneration(3.4)
                    .setThrust(6)
                    .setInternalArmor(4)
                    .setLifeSupport(1, 0, 2, STANDARD)
                    .setBridge(1, 0, 3, STANDARD)
                    .build();
        }

        // TODO: Add tests as more methods become public.
        @Test
        void getDriveGeneration() {
            double expectedValue = 3.4d;
            assertEquals(expectedValue, instance.getDriveGeneration());
        }

        @Test
        void getDriveSpacesWithoutArmor() {
            double expectedValue = 3.79574d;
            assertEquals(expectedValue, instance.getDriveSpacesWithoutArmor(), tolerance);
        }

        @Test
        void getHullDiameter() {
            double expectedValue = 14.86663d;
            assertEquals(expectedValue, instance.getHullDiameter(), tolerance);
        }

        @Test
        void getHullShape() {
            HullShape expectedValue = SPHERE;
            assertEquals(expectedValue, instance.getHullShape());
        }

        @Test
        void getHullSpaces() {
            int expectedValue = 25;
            assertEquals(expectedValue, instance.getHullSpaces());
        }

        @Test
        void getLanternDiameter() {
            double expectedValue = 10.95445d;
            assertEquals(expectedValue, instance.getLanternDiameter(), tolerance);
        }

        @Test
        void getMastDiameter() {
            double expectedValue = 1.86988d;
            assertEquals(expectedValue, instance.getMastDiameter(), tolerance);
        }

        @Test
        void getMastLength() {
            double expectedValue = 28.04820d;
            assertEquals(expectedValue, instance.getMastLength(), tolerance);
        }

        @Test
        void getShieldMaxDiameter() {
            double expectedValue = 2.99819d;
            assertEquals(expectedValue, instance.getShieldMaxDiameter(), tolerance);
        }

        @Test
        void getShieldMinDiameter() {
            double expectedValue = 2.02162d;
            assertEquals(expectedValue, instance.getShieldMinDiameter(), tolerance);
        }

        @Test
        void getShieldThickness() {
            double expectedValue = 2.64586d;
            assertEquals(expectedValue, instance.getShieldThickness(), tolerance);
        }

        @Test
        void getThrust() {
            double expectedValue = 6d;
            assertEquals(expectedValue, instance.getThrust());
        }
    }

    @Nested
    @DisplayName("York (Explicit)")
    class YorkExplicitTest {
        Ship instance;
        final double tolerance = 1e-5;

        @BeforeEach
        void setUp() {
            instance = new Ship()
                    .setHullShape(SPHERE)
                    .setHullSpaces(25)
                    .setDriveGeneration(3.4)
                    .setThrust(6)
                    .setArmorShrink(0)
                    .setArmorProductionLevel(STANDARD)
                    .setHull(0, STANDARD)
                    .setDrive(0, 0, STANDARD)
                    .setFrameReinforcement(0, STANDARD)
                    .setDriveAndMastArmor(0, 0)
                    .setExternalArmor(0)
                    .setInternalArmor(4)
                    .setAccessways(false)
                    .setLifeSupport(1, 0, 2, STANDARD)
                    .setBridge(1, 0, 3, STANDARD)
                    .setFlagBridge(0, 0, 0, STANDARD)
                    .setBetaHyperdrive(0, 0, 0, STANDARD)
                    .setGammaHyperdrive(0, 0, 0, STANDARD)
                    .setDeltaHyperdrive(0, 0, 0, STANDARD)
                    .setEpsilonHyperdrive(0, 0, 0, STANDARD)
                    .setZetaHyperdrive(0, 0, 0, STANDARD)
                    .setECM(0, 0, STANDARD)
                    .setECCM(0, 0, STANDARD)
                    .setHIRTS(0, STANDARD)
                    .build();
        }

        // TODO: Add tests as more methods become public.
        @Test
        void getDriveGeneration() {
            double expectedValue = 3.4d;
            assertEquals(expectedValue, instance.getDriveGeneration());
        }

        @Test
        void getDriveSpacesWithoutArmor() {
            double expectedValue = 3.79574d;
            assertEquals(expectedValue, instance.getDriveSpacesWithoutArmor(), tolerance);
        }

        @Test
        void getHullDiameter() {
            double expectedValue = 14.86663d;
            assertEquals(expectedValue, instance.getHullDiameter(), tolerance);
        }

        @Test
        void getHullShape() {
            HullShape expectedValue = SPHERE;
            assertEquals(expectedValue, instance.getHullShape());
        }

        @Test
        void getHullSpaces() {
            int expectedValue = 25;
            assertEquals(expectedValue, instance.getHullSpaces());
        }

        @Test
        void getLanternDiameter() {
            double expectedValue = 10.95445d;
            assertEquals(expectedValue, instance.getLanternDiameter(), tolerance);
        }

        @Test
        void getMastDiameter() {
            double expectedValue = 1.86988d;
            assertEquals(expectedValue, instance.getMastDiameter(), tolerance);
        }

        @Test
        void getMastLength() {
            double expectedValue = 28.04820d;
            assertEquals(expectedValue, instance.getMastLength(), tolerance);
        }

        @Test
        void getShieldMaxDiameter() {
            double expectedValue = 2.99819d;
            assertEquals(expectedValue, instance.getShieldMaxDiameter(), tolerance);
        }

        @Test
        void getShieldMinDiameter() {
            double expectedValue = 2.02162d;
            assertEquals(expectedValue, instance.getShieldMinDiameter(), tolerance);
        }

        @Test
        void getShieldThickness() {
            double expectedValue = 2.64586d;
            assertEquals(expectedValue, instance.getShieldThickness(), tolerance);
        }

        @Test
        void getThrust() {
            double expectedValue = 6d;
            assertEquals(expectedValue, instance.getThrust());
        }
    }
}
