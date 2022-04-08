package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.systems.core.*;

import java.util.List;

public class CoreSystems {
    private final LifeSupport lifeSupport;
    private final Bridge bridge;
    private final FlagBridge flagBridge;
    private final List<Hyperdrive> hyperdrives;
    private final ECM ecm;
    private final ECCM eccm;
    private final HIRTS hirts;

    public CoreSystems(LifeSupport lifeSupport,
                       Bridge bridge,
                       FlagBridge flagBridge,
                       List<Hyperdrive> hyperdrives,
                       ECM ecm,
                       ECCM eccm,
                       HIRTS hirts) {
        this.lifeSupport = lifeSupport;
        this.bridge = bridge;
        this.flagBridge = flagBridge;
        this.hyperdrives = hyperdrives;
        this.ecm = ecm;
        this.eccm = eccm;
        this.hirts = hirts;
    }

    int getBasicSpacesUsed() {
        return lifeSupport.getBasicSpacesUsed()
                + bridge.getBasicSpacesUsed()
                + flagBridge.getBasicSpacesUsed()
                + ecm.getBasicSpacesUsed()
                + eccm.getBasicSpacesUsed()
                + hirts.getBasicSpacesUsed()
                + hyperdrives.stream().map(System::getBasicSpacesUsed).reduce(Integer::sum).orElse(0);
    }
    
    int getActualSpacesUsed() {
        return lifeSupport.getActualSpacesUsed()
                + bridge.getActualSpacesUsed()
                + flagBridge.getActualSpacesUsed()
                + ecm.getActualSpacesUsed()
                + eccm.getActualSpacesUsed()
                + hirts.getActualSpacesUsed()
                + hyperdrives.stream().map(System::getActualSpacesUsed).reduce(Integer::sum).orElse(0);
    }
    
    int getBaseCost() {
        return lifeSupport.getBaseCost()
                + bridge.getBaseCost()
                + flagBridge.getBaseCost()
                + ecm.getBaseCost()
                + eccm.getBaseCost()
                + hirts.getBaseCost()
                + hyperdrives.stream().map(System::getBaseCost).reduce(Integer::sum).orElse(0);
    }
    
    int getEnhancedCost() {
        return lifeSupport.getEnhancedCost()
                + bridge.getEnhancedCost()
                + flagBridge.getEnhancedCost()
                + ecm.getEnhancedCost()
                + eccm.getEnhancedCost()
                + hirts.getEnhancedCost()
                + hyperdrives.stream().map(System::getEnhancedCost).reduce(Integer::sum).orElse(0);
    }
    
    public double getCrewRequirement() {
        return lifeSupport.getCrewRequirement()
                + bridge.getCrewRequirement()
                + flagBridge.getCrewRequirement()
                + ecm.getCrewRequirement()
                + eccm.getCrewRequirement()
                + hirts.getCrewRequirement()
                + hyperdrives.stream().map(System::getCrewRequirement).reduce(Double::sum).orElse(0d);
    }
    
    double getArmorPointsUsed() {
        return lifeSupport.getArmorPointsUsed()
                + bridge.getArmorPointsUsed()
                + flagBridge.getArmorPointsUsed()
                + hyperdrives.stream().map(ArmoredSystem::getArmorPointsUsed).reduce(Double::sum).orElse(0d);
    }
    
    public int getDuelCost() {
        return lifeSupport.getDuelCost()
                + bridge.getDuelCost()
                + flagBridge.getDuelCost()
                + ecm.getDuelCost()
                + eccm.getDuelCost()
                + hirts.getDuelCost()
                + hyperdrives.stream().map(System::getDuelCost).reduce(Integer::sum).orElse(0);
    }
    
    int getEconomicCost() {
        return lifeSupport.getEconomicCost()
                + bridge.getEconomicCost()
                + flagBridge.getEconomicCost()
                + ecm.getEconomicCost()
                + eccm.getEconomicCost()
                + hirts.getEconomicCost()
                + hyperdrives.stream().map(System::getEconomicCost).reduce(Integer::sum).orElse(0);
    }
    
    double getMaintenanceCostPerYear() {
        return lifeSupport.getMaintenanceCostPerYear()
                + bridge.getMaintenanceCostPerYear()
                + flagBridge.getMaintenanceCostPerYear()
                + ecm.getMaintenanceCostPerYear()
                + eccm.getMaintenanceCostPerYear()
                + hirts.getMaintenanceCostPerYear()
                + hyperdrives.stream().map(System::getMaintenanceCostPerYear).reduce(Double::sum).orElse(0d);
    }
}
