package com.senorpez.avt.shipdesigner.systems;

class StructuralSummary {
    private Hull hull;
    private Drive drive;
    private FrameReinforcement frameReinforcement;
    private DriveMastArmor driveMastArmor;
    private ExternalArmor externalArmor;
    private InternalArmor internalArmor;
    private ImprovedAccessways improvedAccessways;

    StructuralSummary(Hull hull,
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
    
    int getDuelCost() {
        return hull.getDuelCost()
                + drive.getDuelCost()
                + frameReinforcement.getDuelCost()
                + driveMastArmor.getDuelCost()
                + externalArmor.getDuelCost()
                + internalArmor.getDuelCost()
                + improvedAccessways.getDuelCost();
    }
    
    int getEconomicCost() {
        return hull.getEconomicCost()
                + drive.getEconomicCost()
                + frameReinforcement.getEconomicCost()
                + driveMastArmor.getEconomicCost()
                + externalArmor.getEconomicCost()
                + internalArmor.getEconomicCost()
                + improvedAccessways.getEconomicCost();
    }
    
    double getMaintenanceCostPerYear() {
        return hull.getMaintenanceCostPerYear()
                + drive.getMaintenanceCostPerYear()
                + frameReinforcement.getMaintenanceCostPerYear()
                + driveMastArmor.getMaintenanceCostPerYear()
                + externalArmor.getMaintenanceCostPerYear()
                + internalArmor.getMaintenanceCostPerYear()
                + improvedAccessways.getMaintenanceCostPerYear();
    }
}
