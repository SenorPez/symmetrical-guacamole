package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.systems.internal.*;

import java.util.List;

public class InternalSystems {
    private Quarters quarters;
    private List<Cargo> cargos;
    private CargoArmor cargoArmor;
    private List<Reactor> reactors;
    private ReactorArmor reactorArmor;
    private List<Battery> batteries;
    private List<HeatSink> heatSinks;
    private HeatSinkArmor heatSinkArmor;
    private Radiator radiator;
    private Fuel fuel;

    public InternalSystems(Quarters quarters,
                           List<Cargo> cargos,
                           CargoArmor cargoArmor,
                           List<Reactor> reactors,
                           ReactorArmor reactorArmor,
                           List<Battery> batteries,
                           List<HeatSink> heatSinks,
                           HeatSinkArmor heatSinkArmor,
                           Radiator radiator,
                           Fuel fuel) {
        this.quarters = quarters;
        this.cargos = cargos;
        this.reactors = reactors;
        this.batteries = batteries;
        this.heatSinks = heatSinks;
        this.radiator = radiator;
        this.fuel = fuel;

        this.cargoArmor = cargoArmor;
        this.reactorArmor = reactorArmor;
        this.heatSinkArmor = heatSinkArmor;
    }

    int getBasicSpacesUsed() {
        return quarters.getBasicSpacesUsed()
                + radiator.getBasicSpacesUsed()
                + fuel.getBasicSpacesUsed()
                + cargos.stream().map(System::getBasicSpacesUsed).reduce(Integer::sum).orElse(0)
                + reactors.stream().map(System::getBasicSpacesUsed).reduce(Integer::sum).orElse(0)
                + batteries.stream().map(System::getBasicSpacesUsed).reduce(Integer::sum).orElse(0)
                + heatSinks.stream().map(System::getBasicSpacesUsed).reduce(Integer::sum).orElse(0);
    }
    
    int getActualSpacesUsed() {
        return quarters.getActualSpacesUsed()
                + radiator.getActualSpacesUsed()
                + fuel.getActualSpacesUsed()
                + cargos.stream().map(System::getActualSpacesUsed).reduce(Integer::sum).orElse(0)
                + reactors.stream().map(System::getActualSpacesUsed).reduce(Integer::sum).orElse(0)
                + batteries.stream().map(System::getActualSpacesUsed).reduce(Integer::sum).orElse(0)
                + heatSinks.stream().map(System::getActualSpacesUsed).reduce(Integer::sum).orElse(0);
    }
    
    int getBaseCost() {
        return quarters.getBaseCost()
                + radiator.getBaseCost()
                + fuel.getBaseCost()
                + cargos.stream().map(System::getBaseCost).reduce(Integer::sum).orElse(0)
                + reactors.stream().map(System::getBaseCost).reduce(Integer::sum).orElse(0)
                + batteries.stream().map(System::getBaseCost).reduce(Integer::sum).orElse(0)
                + heatSinks.stream().map(System::getBaseCost).reduce(Integer::sum).orElse(0);
    }
    
    int getEnhancedCost() {
        return quarters.getEnhancedCost()
                + radiator.getEnhancedCost()
                + fuel.getEnhancedCost()
                + cargos.stream().map(System::getEnhancedCost).reduce(Integer::sum).orElse(0)
                + reactors.stream().map(System::getEnhancedCost).reduce(Integer::sum).orElse(0)
                + batteries.stream().map(System::getEnhancedCost).reduce(Integer::sum).orElse(0)
                + heatSinks.stream().map(System::getEnhancedCost).reduce(Integer::sum).orElse(0);
    }
    
    public double getCrewRequirement() {
        return quarters.getCrewRequirement()
                + radiator.getCrewRequirement()
                + fuel.getCrewRequirement()
                + cargos.stream().map(System::getCrewRequirement).reduce(Double::sum).orElse(0d)
                + reactors.stream().map(System::getCrewRequirement).reduce(Double::sum).orElse(0d)
                + batteries.stream().map(System::getCrewRequirement).reduce(Double::sum).orElse(0d)
                + heatSinks.stream().map(System::getCrewRequirement).reduce(Double::sum).orElse(0d);
    }
    
    double getArmorPointsUsed() {
        return quarters.getArmorPointsUsed()
                + fuel.getArmorPointsUsed()
                + cargoArmor.getArmorPointsUsed()
                + reactorArmor.getArmorPointsUsed()
                + heatSinkArmor.getArmorPointsUsed()
                + batteries.stream().map(ArmoredSystem::getArmorPointsUsed).reduce(Double::sum).orElse(0d);
    }
    
    int getDuelCost() {
        return quarters.getDuelCost()
                + radiator.getDuelCost()
                + fuel.getDuelCost()
                + cargos.stream().map(System::getDuelCost).reduce(Integer::sum).orElse(0)
                + reactors.stream().map(System::getDuelCost).reduce(Integer::sum).orElse(0)
                + batteries.stream().map(System::getDuelCost).reduce(Integer::sum).orElse(0)
                + heatSinks.stream().map(System::getDuelCost).reduce(Integer::sum).orElse(0);
    }
    
    int getEconomicCost() {
        return quarters.getEconomicCost()
                + radiator.getEconomicCost()
                + fuel.getEconomicCost()
                + cargos.stream().map(System::getEconomicCost).reduce(Integer::sum).orElse(0)
                + reactors.stream().map(System::getEconomicCost).reduce(Integer::sum).orElse(0)
                + batteries.stream().map(System::getEconomicCost).reduce(Integer::sum).orElse(0)
                + heatSinks.stream().map(System::getEconomicCost).reduce(Integer::sum).orElse(0);
    }
    
    double getMaintenanceCostPerYear() {
        return quarters.getMaintenanceCostPerYear()
                + radiator.getMaintenanceCostPerYear()
                + fuel.getMaintenanceCostPerYear()
                + cargos.stream().map(System::getMaintenanceCostPerYear).reduce(Double::sum).orElse(0d)
                + reactors.stream().map(System::getMaintenanceCostPerYear).reduce(Double::sum).orElse(0d)
                + batteries.stream().map(System::getMaintenanceCostPerYear).reduce(Double::sum).orElse(0d)
                + heatSinks.stream().map(System::getMaintenanceCostPerYear).reduce(Double::sum).orElse(0d);
    }
}
