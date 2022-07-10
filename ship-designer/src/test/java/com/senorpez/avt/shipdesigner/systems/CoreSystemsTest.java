package com.senorpez.avt.shipdesigner.systems;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CoreSystemsTest {
    @Mock
    LifeSupport lifeSupport;
    @Mock
    Bridge bridge;
    @Mock
    FlagBridge flagBridge;
    @Mock
    BetaHyperdrive betaHyperdrive;
    @Mock
    GammaHyperdrive gammaHyperdrive;
    @Mock
    DeltaHyperdrive deltaHyperdrive;
    @Mock
    EpsilonHyperdrive epsilonHyperdrive;
    @Mock
    ZetaHyperdrive zetaHyperdrive;
    @Mock
    ECM ecm;
    @Mock
    ECCM eccm;
    @Mock
    HIRTS hirts;

    private CoreSystems instance;

    @Test
    void initialShipTest() {
        // Mock out as if a blank ship.
        when(lifeSupport.getBasicSpacesUsed()).thenReturn(1);
        when(lifeSupport.getActualSpacesUsed()).thenReturn(1);
        when(lifeSupport.getBaseCost()).thenReturn(1);
        when(lifeSupport.getEnhancedCost()).thenReturn(1);
        when(lifeSupport.getDuelCost()).thenReturn(1);
        when(lifeSupport.getEconomicCost()).thenReturn(1);
        when(lifeSupport.getMaintenanceCost()).thenReturn(0.2d);
        when(lifeSupport.getArmorPointsUsed()).thenReturn(0d);

        when(bridge.getBasicSpacesUsed()).thenReturn(1);
        when(bridge.getActualSpacesUsed()).thenReturn(1);
        when(bridge.getBaseCost()).thenReturn(3);
        when(bridge.getEnhancedCost()).thenReturn(3);
        when(bridge.getDuelCost()).thenReturn(3);
        when(bridge.getEconomicCost()).thenReturn(3);
        when(bridge.getMaintenanceCost()).thenReturn(0.75d);
        when(bridge.getArmorPointsUsed()).thenReturn(0d);

        when(flagBridge.getBasicSpacesUsed()).thenReturn(0);
        when(flagBridge.getActualSpacesUsed()).thenReturn(0);
        when(flagBridge.getBaseCost()).thenReturn(0);
        when(flagBridge.getEnhancedCost()).thenReturn(0);
        when(flagBridge.getDuelCost()).thenReturn(0);
        when(flagBridge.getEconomicCost()).thenReturn(0);
        when(flagBridge.getMaintenanceCost()).thenReturn(0d);
        when(flagBridge.getArmorPointsUsed()).thenReturn(0d);

        when(betaHyperdrive.getBasicSpacesUsed()).thenReturn(0);
        when(betaHyperdrive.getActualSpacesUsed()).thenReturn(0);
        when(betaHyperdrive.getBaseCost()).thenReturn(0);
        when(betaHyperdrive.getEnhancedCost()).thenReturn(0);
        when(betaHyperdrive.getDuelCost()).thenReturn(0);
        when(betaHyperdrive.getEconomicCost()).thenReturn(0);
        when(betaHyperdrive.getMaintenanceCost()).thenReturn(0d);
        when(betaHyperdrive.getArmorPointsUsed()).thenReturn(0d);

        when(gammaHyperdrive.getBasicSpacesUsed()).thenReturn(0);
        when(gammaHyperdrive.getActualSpacesUsed()).thenReturn(0);
        when(gammaHyperdrive.getBaseCost()).thenReturn(0);
        when(gammaHyperdrive.getEnhancedCost()).thenReturn(0);
        when(gammaHyperdrive.getDuelCost()).thenReturn(0);
        when(gammaHyperdrive.getEconomicCost()).thenReturn(0);
        when(gammaHyperdrive.getMaintenanceCost()).thenReturn(0d);
        when(gammaHyperdrive.getArmorPointsUsed()).thenReturn(0d);

        when(deltaHyperdrive.getBasicSpacesUsed()).thenReturn(0);
        when(deltaHyperdrive.getActualSpacesUsed()).thenReturn(0);
        when(deltaHyperdrive.getBaseCost()).thenReturn(0);
        when(deltaHyperdrive.getEnhancedCost()).thenReturn(0);
        when(deltaHyperdrive.getDuelCost()).thenReturn(0);
        when(deltaHyperdrive.getEconomicCost()).thenReturn(0);
        when(deltaHyperdrive.getMaintenanceCost()).thenReturn(0d);
        when(deltaHyperdrive.getArmorPointsUsed()).thenReturn(0d);

        when(epsilonHyperdrive.getBasicSpacesUsed()).thenReturn(0);
        when(epsilonHyperdrive.getActualSpacesUsed()).thenReturn(0);
        when(epsilonHyperdrive.getBaseCost()).thenReturn(0);
        when(epsilonHyperdrive.getEnhancedCost()).thenReturn(0);
        when(epsilonHyperdrive.getDuelCost()).thenReturn(0);
        when(epsilonHyperdrive.getEconomicCost()).thenReturn(0);
        when(epsilonHyperdrive.getMaintenanceCost()).thenReturn(0d);
        when(epsilonHyperdrive.getArmorPointsUsed()).thenReturn(0d);

        when(zetaHyperdrive.getBasicSpacesUsed()).thenReturn(0);
        when(zetaHyperdrive.getActualSpacesUsed()).thenReturn(0);
        when(zetaHyperdrive.getBaseCost()).thenReturn(0);
        when(zetaHyperdrive.getEnhancedCost()).thenReturn(0);
        when(zetaHyperdrive.getDuelCost()).thenReturn(0);
        when(zetaHyperdrive.getEconomicCost()).thenReturn(0);
        when(zetaHyperdrive.getMaintenanceCost()).thenReturn(0d);
        when(zetaHyperdrive.getArmorPointsUsed()).thenReturn(0d);

        when(ecm.getBasicSpacesUsed()).thenReturn(0);
        when(ecm.getActualSpacesUsed()).thenReturn(0);
        when(ecm.getBaseCost()).thenReturn(0);
        when(ecm.getEnhancedCost()).thenReturn(0);
        when(ecm.getDuelCost()).thenReturn(0);
        when(ecm.getEconomicCost()).thenReturn(0);
        when(ecm.getMaintenanceCost()).thenReturn(0d);

        when(eccm.getBasicSpacesUsed()).thenReturn(0);
        when(eccm.getActualSpacesUsed()).thenReturn(0);
        when(eccm.getBaseCost()).thenReturn(0);
        when(eccm.getEnhancedCost()).thenReturn(0);
        when(eccm.getDuelCost()).thenReturn(0);
        when(eccm.getEconomicCost()).thenReturn(0);
        when(eccm.getMaintenanceCost()).thenReturn(0d);

        when(hirts.getBasicSpacesUsed()).thenReturn(0);
        when(hirts.getActualSpacesUsed()).thenReturn(0);
        when(hirts.getBaseCost()).thenReturn(0);
        when(hirts.getEnhancedCost()).thenReturn(0);
        when(hirts.getDuelCost()).thenReturn(0);
        when(hirts.getEconomicCost()).thenReturn(0);
        when(hirts.getMaintenanceCost()).thenReturn(0d);

        instance = new CoreSystems(lifeSupport,
                bridge,
                flagBridge,
                betaHyperdrive,
                gammaHyperdrive,
                deltaHyperdrive,
                epsilonHyperdrive,
                zetaHyperdrive,
                ecm,
                eccm,
                hirts);

        assertEquals(2, instance.getBasicSpacesUsed());
        assertEquals(2, instance.getActualSpacesUsed());
        assertEquals(4, instance.getBaseCost());
        assertEquals(4, instance.getEnhancedCost());
        assertEquals(0, instance.getArmorPointsUsed());
        assertEquals(4, instance.getDuelCost());
        assertEquals(4, instance.getEconomicCost());
        assertEquals(0.95d, instance.getMaintenanceCost());
    }

    @Test
    void yorkTest() {
        // Mock out as if a York.
        when(lifeSupport.getBasicSpacesUsed()).thenReturn(1);
        when(lifeSupport.getActualSpacesUsed()).thenReturn(1);
        when(lifeSupport.getBaseCost()).thenReturn(1);
        when(lifeSupport.getEnhancedCost()).thenReturn(1);
        when(lifeSupport.getDuelCost()).thenReturn(1);
        when(lifeSupport.getEconomicCost()).thenReturn(1);
        when(lifeSupport.getMaintenanceCost()).thenReturn(0.2d);
        when(lifeSupport.getArmorPointsUsed()).thenReturn(1d);

        when(bridge.getBasicSpacesUsed()).thenReturn(1);
        when(bridge.getActualSpacesUsed()).thenReturn(1);
        when(bridge.getBaseCost()).thenReturn(3);
        when(bridge.getEnhancedCost()).thenReturn(3);
        when(bridge.getDuelCost()).thenReturn(3);
        when(bridge.getEconomicCost()).thenReturn(3);
        when(bridge.getMaintenanceCost()).thenReturn(0.75d);
        when(bridge.getArmorPointsUsed()).thenReturn(2d);

        when(flagBridge.getBasicSpacesUsed()).thenReturn(0);
        when(flagBridge.getActualSpacesUsed()).thenReturn(0);
        when(flagBridge.getBaseCost()).thenReturn(0);
        when(flagBridge.getEnhancedCost()).thenReturn(0);
        when(flagBridge.getDuelCost()).thenReturn(0);
        when(flagBridge.getEconomicCost()).thenReturn(0);
        when(flagBridge.getMaintenanceCost()).thenReturn(0d);
        when(flagBridge.getArmorPointsUsed()).thenReturn(0d);

        when(betaHyperdrive.getBasicSpacesUsed()).thenReturn(0);
        when(betaHyperdrive.getActualSpacesUsed()).thenReturn(0);
        when(betaHyperdrive.getBaseCost()).thenReturn(0);
        when(betaHyperdrive.getEnhancedCost()).thenReturn(0);
        when(betaHyperdrive.getDuelCost()).thenReturn(0);
        when(betaHyperdrive.getEconomicCost()).thenReturn(0);
        when(betaHyperdrive.getMaintenanceCost()).thenReturn(0d);
        when(betaHyperdrive.getArmorPointsUsed()).thenReturn(0d);

        when(gammaHyperdrive.getBasicSpacesUsed()).thenReturn(0);
        when(gammaHyperdrive.getActualSpacesUsed()).thenReturn(0);
        when(gammaHyperdrive.getBaseCost()).thenReturn(0);
        when(gammaHyperdrive.getEnhancedCost()).thenReturn(0);
        when(gammaHyperdrive.getDuelCost()).thenReturn(0);
        when(gammaHyperdrive.getEconomicCost()).thenReturn(0);
        when(gammaHyperdrive.getMaintenanceCost()).thenReturn(0d);
        when(gammaHyperdrive.getArmorPointsUsed()).thenReturn(0d);

        when(deltaHyperdrive.getBasicSpacesUsed()).thenReturn(0);
        when(deltaHyperdrive.getActualSpacesUsed()).thenReturn(0);
        when(deltaHyperdrive.getBaseCost()).thenReturn(0);
        when(deltaHyperdrive.getEnhancedCost()).thenReturn(0);
        when(deltaHyperdrive.getDuelCost()).thenReturn(0);
        when(deltaHyperdrive.getEconomicCost()).thenReturn(0);
        when(deltaHyperdrive.getMaintenanceCost()).thenReturn(0d);
        when(deltaHyperdrive.getArmorPointsUsed()).thenReturn(0d);

        when(epsilonHyperdrive.getBasicSpacesUsed()).thenReturn(0);
        when(epsilonHyperdrive.getActualSpacesUsed()).thenReturn(0);
        when(epsilonHyperdrive.getBaseCost()).thenReturn(0);
        when(epsilonHyperdrive.getEnhancedCost()).thenReturn(0);
        when(epsilonHyperdrive.getDuelCost()).thenReturn(0);
        when(epsilonHyperdrive.getEconomicCost()).thenReturn(0);
        when(epsilonHyperdrive.getMaintenanceCost()).thenReturn(0d);
        when(epsilonHyperdrive.getArmorPointsUsed()).thenReturn(0d);

        when(zetaHyperdrive.getBasicSpacesUsed()).thenReturn(0);
        when(zetaHyperdrive.getActualSpacesUsed()).thenReturn(0);
        when(zetaHyperdrive.getBaseCost()).thenReturn(0);
        when(zetaHyperdrive.getEnhancedCost()).thenReturn(0);
        when(zetaHyperdrive.getDuelCost()).thenReturn(0);
        when(zetaHyperdrive.getEconomicCost()).thenReturn(0);
        when(zetaHyperdrive.getMaintenanceCost()).thenReturn(0d);
        when(zetaHyperdrive.getArmorPointsUsed()).thenReturn(0d);

        when(ecm.getBasicSpacesUsed()).thenReturn(0);
        when(ecm.getActualSpacesUsed()).thenReturn(0);
        when(ecm.getBaseCost()).thenReturn(0);
        when(ecm.getEnhancedCost()).thenReturn(0);
        when(ecm.getDuelCost()).thenReturn(0);
        when(ecm.getEconomicCost()).thenReturn(0);
        when(ecm.getMaintenanceCost()).thenReturn(0d);

        when(eccm.getBasicSpacesUsed()).thenReturn(0);
        when(eccm.getActualSpacesUsed()).thenReturn(0);
        when(eccm.getBaseCost()).thenReturn(0);
        when(eccm.getEnhancedCost()).thenReturn(0);
        when(eccm.getDuelCost()).thenReturn(0);
        when(eccm.getEconomicCost()).thenReturn(0);
        when(eccm.getMaintenanceCost()).thenReturn(0d);

        when(hirts.getBasicSpacesUsed()).thenReturn(0);
        when(hirts.getActualSpacesUsed()).thenReturn(0);
        when(hirts.getBaseCost()).thenReturn(0);
        when(hirts.getEnhancedCost()).thenReturn(0);
        when(hirts.getDuelCost()).thenReturn(0);
        when(hirts.getEconomicCost()).thenReturn(0);
        when(hirts.getMaintenanceCost()).thenReturn(0d);

        instance = new CoreSystems(lifeSupport,
                bridge,
                flagBridge,
                betaHyperdrive,
                gammaHyperdrive,
                deltaHyperdrive,
                epsilonHyperdrive,
                zetaHyperdrive,
                ecm,
                eccm,
                hirts);

        assertEquals(2, instance.getBasicSpacesUsed());
        assertEquals(2, instance.getActualSpacesUsed());
        assertEquals(4, instance.getBaseCost());
        assertEquals(4, instance.getEnhancedCost());
        assertEquals(3, instance.getArmorPointsUsed());
        assertEquals(4, instance.getDuelCost());
        assertEquals(4, instance.getEconomicCost());
        assertEquals(0.95d, instance.getMaintenanceCost());
    }
}