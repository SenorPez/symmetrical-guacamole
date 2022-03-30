package com.senorpez.avt.shipdesigner.systems.structural;

import com.senorpez.avt.shipdesigner.Ship;
import com.senorpez.avt.shipdesigner.characteristics.DriveCharacteristics;
import com.senorpez.avt.shipdesigner.systems.ProductionLevel;
import com.senorpez.avt.shipdesigner.systems.System;

public class ImprovedAccessways extends System {
    private boolean hasImprovedAccessways;
    private DriveCharacteristics driveCharacteristics;

    private final static double costPerSpace = 0d;
    private final static double crewRequiredPerSpace = 0d;
    private final static double maintenanceRate = 0d;

    ImprovedAccessways(Ship ship,
                       boolean hasImprovedAccessways,
                       DriveCharacteristics driveCharacteristics) {
        super(ship,
                "",
                0,
                costPerSpace,
                crewRequiredPerSpace,
                maintenanceRate,
                0,
                0,
                ProductionLevel.STANDARD);
        this.hasImprovedAccessways = hasImprovedAccessways;
        this.driveCharacteristics = driveCharacteristics;
    }

    public String getName() {
        return hasImprovedAccessways ? "Improved Accessways" : "No Improved Accessways";
    }

    public int getBasicSpacesUsed() {
        return getSpacesPerSystem();
    }

    public int getSpacesPerSystem() {
        return hasImprovedAccessways ? driveCharacteristics.getImprovedAccesswayRequirement() : 0;
    }

    @Override
    public int getActualSpacesUsed() {
        return getBasicSpacesUsed();
    }

    ImprovedAccessways setHasImprovedAccessways(boolean hasImprovedAccessways) {
        this.hasImprovedAccessways = hasImprovedAccessways;
        return this;
    }

    ImprovedAccessways setDriveCharacteristics(DriveCharacteristics driveCharacteristics) {
        this.driveCharacteristics = driveCharacteristics;
        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ImprovedAccessways setQuantity(int quantity) {
        // Quantity is immutable.
        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ImprovedAccessways setShrinkEnhancement(int quantity) {
        // Shrink is immutable.
        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ImprovedAccessways setProductionLevel(ProductionLevel productionLevel) {
        // Production level is immutable.
        return this;
    }
}
