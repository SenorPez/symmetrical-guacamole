package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.systems.internal.*;
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
class InternalSystemsTest {
    @Mock
    private Quarters quarters;
    @Mock
    private CargoSupport cargoSupport;
    @Mock
    private CargoRepair cargoRepair;
    @Mock
    private CargoMagazine cargoMagazine;
    @Mock
    private CargoShuttle cargoShuttle;
    @Mock
    private CargoArmor cargoArmor;
    @Mock
    private Reactor reactor;
    @Mock
    private ReactorArmor reactorArmor;
    @Mock
    private Battery battery;
    @Mock
    private HeatSinkWater heatSinkWater;
    @Mock
    private HeatSinkSodium heatSinkSodium;
    @Mock
    private HeatSinkLithium heatSinkLithium;
    @Mock
    private HeatSinkArmor heatSinkArmor;
    @Mock
    private Radiator radiator;
    @Mock
    private Fuel fuel;

    InternalSystems instance;

    @BeforeEach
    void setUp() {
        instance = new InternalSystems(
                quarters,
                new ArrayList<>(List.of(cargoSupport, cargoRepair, cargoMagazine, cargoShuttle)),
                cargoArmor,
                new ArrayList<>(List.of(reactor)),
                reactorArmor,
                new ArrayList<>(List.of(battery)),
                new ArrayList<>(List.of(heatSinkWater, heatSinkSodium, heatSinkLithium)),
                heatSinkArmor,
                radiator,
                fuel
        );
    }

    @Test
    void getBasicSpacesUsed() {
        when(quarters.getBasicSpacesUsed()).thenReturn(5);
        when(cargoSupport.getBasicSpacesUsed()).thenReturn(8);
        when(cargoRepair.getBasicSpacesUsed()).thenReturn(1);
        when(cargoMagazine.getBasicSpacesUsed()).thenReturn(2);
        when(cargoShuttle.getBasicSpacesUsed()).thenReturn(6);
        when(reactor.getBasicSpacesUsed()).thenReturn(20);
        when(battery.getBasicSpacesUsed()).thenReturn(10);
        when(heatSinkWater.getBasicSpacesUsed()).thenReturn(5);
        when(heatSinkSodium.getBasicSpacesUsed()).thenReturn(4);
        when(heatSinkLithium.getBasicSpacesUsed()).thenReturn(1);
        when(radiator.getBasicSpacesUsed()).thenReturn(2);
        when(fuel.getBasicSpacesUsed()).thenReturn(11);

        int expectedValue = 75;
        assertEquals(expectedValue, instance.getBasicSpacesUsed());
    }

    @Test
    void getActualSpacesUsed() {
        when(quarters.getActualSpacesUsed()).thenReturn(5);
        when(cargoSupport.getActualSpacesUsed()).thenReturn(8);
        when(cargoRepair.getActualSpacesUsed()).thenReturn(1);
        when(cargoMagazine.getActualSpacesUsed()).thenReturn(2);
        when(cargoShuttle.getActualSpacesUsed()).thenReturn(6);
        when(reactor.getActualSpacesUsed()).thenReturn(18);
        when(battery.getActualSpacesUsed()).thenReturn(9);
        when(heatSinkWater.getActualSpacesUsed()).thenReturn(4);
        when(heatSinkSodium.getActualSpacesUsed()).thenReturn(3);
        when(heatSinkLithium.getActualSpacesUsed()).thenReturn(1);
        when(radiator.getActualSpacesUsed()).thenReturn(1);
        when(fuel.getActualSpacesUsed()).thenReturn(11);

        int expectedValue = 69;
        assertEquals(expectedValue, instance.getActualSpacesUsed());
    }

    @Test
    void getBaseCost() {
        when(quarters.getBaseCost()).thenReturn(5);
        when(cargoSupport.getBaseCost()).thenReturn(8);
        when(cargoRepair.getBaseCost()).thenReturn(1);
        when(cargoMagazine.getBaseCost()).thenReturn(2);
        when(cargoShuttle.getBaseCost()).thenReturn(6);
        when(reactor.getBaseCost()).thenReturn(60);
        when(battery.getBaseCost()).thenReturn(30);
        when(heatSinkWater.getBaseCost()).thenReturn(2);
        when(heatSinkSodium.getBaseCost()).thenReturn(16);
        when(heatSinkLithium.getBaseCost()).thenReturn(60);
        when(radiator.getBaseCost()).thenReturn(2);
        when(fuel.getBaseCost()).thenReturn(3);

        int expectedValue = 195;
        assertEquals(expectedValue, instance.getBaseCost());
    }

    @Test
    void getEnhancedCost() {
        when(quarters.getEnhancedCost()).thenReturn(5);
        when(cargoSupport.getEnhancedCost()).thenReturn(8);
        when(cargoRepair.getEnhancedCost()).thenReturn(1);
        when(cargoMagazine.getEnhancedCost()).thenReturn(2);
        when(cargoShuttle.getEnhancedCost()).thenReturn(6);
        when(reactor.getEnhancedCost()).thenReturn(75);
        when(battery.getEnhancedCost()).thenReturn(33);
        when(heatSinkWater.getEnhancedCost()).thenReturn(2);
        when(heatSinkSodium.getEnhancedCost()).thenReturn(18);
        when(heatSinkLithium.getEnhancedCost()).thenReturn(66);
        when(radiator.getEnhancedCost()).thenReturn(3);
        when(fuel.getEnhancedCost()).thenReturn(3);

        int expectedValue = 222;
        assertEquals(expectedValue, instance.getEnhancedCost());
    }

    @Test
    void getArmorPointsUsed() {
        when(quarters.getArmorPointsUsed()).thenReturn(2.5d);
        when(cargoArmor.getArmorPointsUsed()).thenReturn(8.5d);
        when(reactorArmor.getArmorPointsUsed()).thenReturn(18d);
        when(heatSinkArmor.getArmorPointsUsed()).thenReturn(8d);
        when(battery.getArmorPointsUsed()).thenReturn(9d);
        when(fuel.getArmorPointsUsed()).thenReturn(11d);

        int expectedValue = 57;
        assertEquals(expectedValue, instance.getArmorPointsUsed());
    }

    @Test
    void getDuelCost() {
        when(quarters.getDuelCost()).thenReturn(5);
        when(cargoSupport.getDuelCost()).thenReturn(8);
        when(cargoRepair.getDuelCost()).thenReturn(1);
        when(cargoMagazine.getDuelCost()).thenReturn(2);
        when(cargoShuttle.getDuelCost()).thenReturn(6);
        when(reactor.getDuelCost()).thenReturn(75);
        when(battery.getDuelCost()).thenReturn(33);
        when(heatSinkWater.getDuelCost()).thenReturn(2);
        when(heatSinkSodium.getDuelCost()).thenReturn(18);
        when(heatSinkLithium.getDuelCost()).thenReturn(66);
        when(radiator.getDuelCost()).thenReturn(3);
        when(fuel.getDuelCost()).thenReturn(3);

        int expectedValue = 222;
        assertEquals(expectedValue, instance.getDuelCost());
    }

    @Test
    void getEconomicCost() {
        when(quarters.getEconomicCost()).thenReturn(5);
        when(cargoSupport.getEconomicCost()).thenReturn(8);
        when(cargoRepair.getEconomicCost()).thenReturn(1);
        when(cargoMagazine.getEconomicCost()).thenReturn(2);
        when(cargoShuttle.getEconomicCost()).thenReturn(6);
        when(reactor.getEconomicCost()).thenReturn(75);
        when(battery.getEconomicCost()).thenReturn(33);
        when(heatSinkWater.getEconomicCost()).thenReturn(2);
        when(heatSinkSodium.getEconomicCost()).thenReturn(18);
        when(heatSinkLithium.getEconomicCost()).thenReturn(66);
        when(radiator.getEconomicCost()).thenReturn(3);
        when(fuel.getEconomicCost()).thenReturn(3);

        int expectedValue = 222;
        assertEquals(expectedValue, instance.getEconomicCost());
    }

    @Test
    void getMaintenanceCostPerYear() {
        when(quarters.getMaintenanceCostPerYear()).thenReturn(0.25d);
        when(cargoSupport.getMaintenanceCostPerYear()).thenReturn(0.4d);
        when(cargoRepair.getMaintenanceCostPerYear()).thenReturn(0.05d);
        when(cargoMagazine.getMaintenanceCostPerYear()).thenReturn(0.1d);
        when(cargoShuttle.getMaintenanceCostPerYear()).thenReturn(0.3d);
        when(reactor.getMaintenanceCostPerYear()).thenReturn(15d);
        when(battery.getMaintenanceCostPerYear()).thenReturn(6.6d);
        when(heatSinkWater.getMaintenanceCostPerYear()).thenReturn(0.3d);
        when(heatSinkSodium.getMaintenanceCostPerYear()).thenReturn(3.6d);
        when(heatSinkLithium.getMaintenanceCostPerYear()).thenReturn(13.2d);
        when(radiator.getMaintenanceCostPerYear()).thenReturn(0.6);
        when(fuel.getMaintenanceCostPerYear()).thenReturn(0d);

        double expectedValue = 40.4d;
        assertEquals(expectedValue, instance.getMaintenanceCostPerYear(), 0.001d);
    }
}
