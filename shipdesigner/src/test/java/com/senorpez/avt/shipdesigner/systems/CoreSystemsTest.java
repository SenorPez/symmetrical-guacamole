package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.systems.core.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CoreSystemsTest {
    @Mock
    private LifeSupport lifeSupport;
    @Mock
    private Bridge bridge;
    @Mock
    private FlagBridge flagBridge;
    @Mock
    private BetaHyperdrive betaHyperdrive;
    @Mock
    private GammaHyperdrive gammaHyperdrive;
    @Mock
    private DeltaHyperdrive deltaHyperdrive;
    @Mock
    private EpsilonHyperdrive epsilonHyperdrive;
    @Mock
    private ZetaHyperdrive zetaHyperdrive;
    @Mock
    private ECM ecm;
    @Mock
    private ECCM eccm;
    @Mock
    private HIRTS hirts;

    CoreSystems instance;

    @BeforeEach
    void setUp() {
        instance = new CoreSystems(
                lifeSupport,
                bridge,
                flagBridge,
                new ArrayList<>(List.of(betaHyperdrive, gammaHyperdrive, deltaHyperdrive, epsilonHyperdrive, zetaHyperdrive)),
                ecm,
                eccm,
                hirts
        );
    }

    @Test
    void getBasicSpacesUsed() {
        when(lifeSupport.getBasicSpacesUsed()).thenReturn(1);
        when(bridge.getBasicSpacesUsed()).thenReturn(1);
        when(flagBridge.getBasicSpacesUsed()).thenReturn(3);
        when(betaHyperdrive.getBasicSpacesUsed()).thenReturn(2);
        when(gammaHyperdrive.getBasicSpacesUsed()).thenReturn(4);
        when(deltaHyperdrive.getBasicSpacesUsed()).thenReturn(8);
        when(epsilonHyperdrive.getBasicSpacesUsed()).thenReturn(12);
        when(zetaHyperdrive.getBasicSpacesUsed()).thenReturn(20);
        when(ecm.getBasicSpacesUsed()).thenReturn(4);
        when(eccm.getBasicSpacesUsed()).thenReturn(10);
        when(hirts.getBasicSpacesUsed()).thenReturn(1);

        int expectedValue = 66;
        assertEquals(expectedValue, instance.getBasicSpacesUsed());
    }

    @Test
    void getActualSpacesUsed() {
        when(lifeSupport.getActualSpacesUsed()).thenReturn(1);
        when(bridge.getActualSpacesUsed()).thenReturn(1);
        when(flagBridge.getActualSpacesUsed()).thenReturn(3);
        when(betaHyperdrive.getActualSpacesUsed()).thenReturn(2);
        when(gammaHyperdrive.getActualSpacesUsed()).thenReturn(3);
        when(deltaHyperdrive.getActualSpacesUsed()).thenReturn(7);
        when(epsilonHyperdrive.getActualSpacesUsed()).thenReturn(12);
        when(zetaHyperdrive.getActualSpacesUsed()).thenReturn(20);
        when(ecm.getActualSpacesUsed()).thenReturn(4);
        when(eccm.getActualSpacesUsed()).thenReturn(10);
        when(hirts.getActualSpacesUsed()).thenReturn(1);

        int expectedValue = 64;
        assertEquals(expectedValue, instance.getActualSpacesUsed());
    }

    @Test
    void getBaseCost() {
        when(lifeSupport.getBaseCost()).thenReturn(1);
        when(bridge.getBaseCost()).thenReturn(3);
        when(flagBridge.getBaseCost()).thenReturn(9);
        when(betaHyperdrive.getBaseCost()).thenReturn(6);
        when(gammaHyperdrive.getBaseCost()).thenReturn(16);
        when(deltaHyperdrive.getBaseCost()).thenReturn(40);
        when(epsilonHyperdrive.getBaseCost()).thenReturn(84);
        when(zetaHyperdrive.getBaseCost()).thenReturn(200);
        when(ecm.getBaseCost()).thenReturn(200);
        when(eccm.getBaseCost()).thenReturn(169);
        when(hirts.getBaseCost()).thenReturn(1);

        int expectedValue = 729;
        assertEquals(expectedValue, instance.getBaseCost());
    }

    @Test
    void getEnhancedCost() {
        when(lifeSupport.getEnhancedCost()).thenReturn(1);
        when(bridge.getEnhancedCost()).thenReturn(3);
        when(flagBridge.getEnhancedCost()).thenReturn(9);
        when(betaHyperdrive.getEnhancedCost()).thenReturn(6);
        when(gammaHyperdrive.getEnhancedCost()).thenReturn(18);
        when(deltaHyperdrive.getEnhancedCost()).thenReturn(44);
        when(epsilonHyperdrive.getEnhancedCost()).thenReturn(84);
        when(zetaHyperdrive.getEnhancedCost()).thenReturn(200);
        when(ecm.getEnhancedCost()).thenReturn(200);
        when(eccm.getEnhancedCost()).thenReturn(169);
        when(hirts.getEnhancedCost()).thenReturn(1);

        int expectedValue = 735;
        assertEquals(expectedValue, instance.getEnhancedCost());
    }

    @Test
    void getArmorPointsUsed() {
        when(lifeSupport.getArmorPointsUsed()).thenReturn(0.5d);
        when(bridge.getArmorPointsUsed()).thenReturn(1d);
        when(flagBridge.getArmorPointsUsed()).thenReturn(6d);
        when(betaHyperdrive.getArmorPointsUsed()).thenReturn(7d);
        when(gammaHyperdrive.getArmorPointsUsed()).thenReturn(15d);
        when(deltaHyperdrive.getArmorPointsUsed()).thenReturn(49d);
        when(epsilonHyperdrive.getArmorPointsUsed()).thenReturn(108d);
        when(zetaHyperdrive.getArmorPointsUsed()).thenReturn(180d);

        double expectedValue = 366.5d;
        assertEquals(expectedValue, instance.getArmorPointsUsed());
    }

    @Test
    void getDuelCost() {
        when(lifeSupport.getDuelCost()).thenReturn(1);
        when(bridge.getDuelCost()).thenReturn(3);
        when(flagBridge.getDuelCost()).thenReturn(9);
        when(betaHyperdrive.getDuelCost()).thenReturn(6);
        when(gammaHyperdrive.getDuelCost()).thenReturn(18);
        when(deltaHyperdrive.getDuelCost()).thenReturn(44);
        when(epsilonHyperdrive.getDuelCost()).thenReturn(84);
        when(zetaHyperdrive.getDuelCost()).thenReturn(200);
        when(ecm.getDuelCost()).thenReturn(200);
        when(eccm.getDuelCost()).thenReturn(169);
        when(hirts.getDuelCost()).thenReturn(1);

        int expectedValue = 735;
        assertEquals(expectedValue, instance.getDuelCost());
    }

    @Test
    void getEconomicCost() {
        when(lifeSupport.getEconomicCost()).thenReturn(1);
        when(bridge.getEconomicCost()).thenReturn(3);
        when(flagBridge.getEconomicCost()).thenReturn(9);
        when(betaHyperdrive.getEconomicCost()).thenReturn(6);
        when(gammaHyperdrive.getEconomicCost()).thenReturn(18);
        when(deltaHyperdrive.getEconomicCost()).thenReturn(44);
        when(epsilonHyperdrive.getEconomicCost()).thenReturn(84);
        when(zetaHyperdrive.getEconomicCost()).thenReturn(200);
        when(ecm.getEconomicCost()).thenReturn(200);
        when(eccm.getEconomicCost()).thenReturn(169);
        when(hirts.getEconomicCost()).thenReturn(1);

        int expectedValue = 735;
        assertEquals(expectedValue, instance.getEconomicCost());
    }

    @Test
    void getMaintenanceCostPerYear() {
        when(lifeSupport.getMaintenanceCostPerYear()).thenReturn(0.2d);
        when(bridge.getMaintenanceCostPerYear()).thenReturn(0.75d);
        when(flagBridge.getMaintenanceCostPerYear()).thenReturn(2.25d);
        when(betaHyperdrive.getMaintenanceCostPerYear()).thenReturn(1.2d);
        when(gammaHyperdrive.getMaintenanceCostPerYear()).thenReturn(3.6d);
        when(deltaHyperdrive.getMaintenanceCostPerYear()).thenReturn(8.8d);
        when(epsilonHyperdrive.getMaintenanceCostPerYear()).thenReturn(16.8d);
        when(zetaHyperdrive.getMaintenanceCostPerYear()).thenReturn(40d);
        when(ecm.getMaintenanceCostPerYear()).thenReturn(50d);
        when(eccm.getMaintenanceCostPerYear()).thenReturn(42.25d);
        when(hirts.getMaintenanceCostPerYear()).thenReturn(0.25d);

        double expectedValue = 166.1d;
        assertEquals(expectedValue, instance.getMaintenanceCostPerYear(), 0.001d);
    }
}
