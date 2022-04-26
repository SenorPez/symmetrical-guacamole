package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.systems.structural.*;

public class StructuralSystems {
    private final Hull hull;
    private final Drive drive;
    private final FrameReinforcement frameReinforcement;
    private final DriveMastArmor driveMastArmor;
    private final ExternalArmor externalArmor;
    private final InternalArmor internalArmor;
    private final ImprovedAccessways improvedAccessways;

    StructuralSystems(Hull hull,
                      Drive drive,
                      FrameReinforcement frameReinforcement,
                      DriveMastArmor driveMastArmor,
                      ExternalArmor externalArmor,
                      InternalArmor internalArmor,
                      ImprovedAccessways improvedAccessways) {
        this.hull = hull;
        this.drive = drive;
        this.frameReinforcement = frameReinforcement;
        this.driveMastArmor = driveMastArmor;
        this.externalArmor = externalArmor;
        this.internalArmor = internalArmor;
        this.improvedAccessways = improvedAccessways;
    }
    
    int getBasicSpacesUsed() {
        return hull.getBasicSpacesUsed()
                + drive.getBasicSpacesUsed()
                + frameReinforcement.getBasicSpacesUsed()
                + driveMastArmor.getBasicSpacesUsed()
                + externalArmor.getBasicSpacesUsed()
                + internalArmor.getBasicSpacesUsed()
                + improvedAccessways.getBasicSpacesUsed();
    }
    
    int getActualSpacesUsed() {
        return hull.getActualSpacesUsed()
                + drive.getActualSpacesUsed()
                + frameReinforcement.getActualSpacesUsed()
                + driveMastArmor.getActualSpacesUsed()
                + externalArmor.getActualSpacesUsed()
                + internalArmor.getActualSpacesUsed()
                + improvedAccessways.getActualSpacesUsed();
    }
    
    int getBaseCost() {
        return hull.getBaseCost()
                + drive.getBaseCost()
                + frameReinforcement.getBaseCost()
                + driveMastArmor.getBaseCost()
                + externalArmor.getBaseCost()
                + internalArmor.getBaseCost()
                + improvedAccessways.getBaseCost();
    }
    
    int getEnhancedCost() {
        return hull.getEnhancedCost()
                + drive.getEnhancedCost()
                + frameReinforcement.getEnhancedCost()
                + driveMastArmor.getEnhancedCost()
                + externalArmor.getEnhancedCost()
                + internalArmor.getEnhancedCost()
                + improvedAccessways.getEnhancedCost();
    }
    
    public double getCrewRequirement() {
        return hull.getCrewRequirement()
                + drive.getCrewRequirement()
                + frameReinforcement.getCrewRequirement()
                + driveMastArmor.getCrewRequirement()
                + externalArmor.getCrewRequirement()
                + internalArmor.getCrewRequirement()
                + improvedAccessways.getCrewRequirement();
    }
    
    public int getDuelCost() {
        return hull.getDuelCost()
                + drive.getDuelCost()
                + frameReinforcement.getDuelCost()
                + driveMastArmor.getDuelCost()
                + externalArmor.getDuelCost()
                + internalArmor.getDuelCost()
                + improvedAccessways.getDuelCost();
    }
    
    public int getEconomicCost() {
        return hull.getEconomicCost()
                + drive.getEconomicCost()
                + frameReinforcement.getEconomicCost()
                + driveMastArmor.getEconomicCost()
                + externalArmor.getEconomicCost()
                + internalArmor.getEconomicCost()
                + improvedAccessways.getEconomicCost();
    }
    
    public double getMaintenanceCostPerYear() {
        return hull.getMaintenanceCostPerYear()
                + drive.getMaintenanceCostPerYear()
                + frameReinforcement.getMaintenanceCostPerYear()
                + driveMastArmor.getMaintenanceCostPerYear()
                + externalArmor.getMaintenanceCostPerYear()
                + internalArmor.getMaintenanceCostPerYear()
                + improvedAccessways.getMaintenanceCostPerYear();
    }

    public Hull getHull() {
        return hull;
    }

    public boolean hasImprovedAccessways() {
        return improvedAccessways.hasImprovedAccessways();
    }
}
