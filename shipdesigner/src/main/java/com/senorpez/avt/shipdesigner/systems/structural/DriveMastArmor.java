package com.senorpez.avt.shipdesigner.systems.structural;

import com.senorpez.avt.shipdesigner.Ship;
import com.senorpez.avt.shipdesigner.systems.ProductionLevel;
import com.senorpez.avt.shipdesigner.systems.System;

public class DriveMastArmor extends System {
    private final ArmorShrink armorShrink;
    private final ArmorProductionLevel armorProductionLevel;

    private final static String name = "Engine and Mast Armor";
    private final static int spacesPerSystem = 1;
    private final static double costPerSpace = 2d;
    private final static double crewRequiredPerSpace = 0d;
    private final static double maintenanceRate = 0.05d;

    DriveMastArmor(Ship ship,
                   ArmorShrink armorShrink,
                   ArmorProductionLevel armorProductionLevel) {
        super(ship,
                name,
                spacesPerSystem,
                costPerSpace,
                crewRequiredPerSpace,
                maintenanceRate,
                0,
                armorShrink.getShrinkEnhancement(),
                armorProductionLevel.getProductionLevel());
        this.armorShrink = armorShrink;
        this.armorProductionLevel = armorProductionLevel;
    }

    int getMastArmor() {
        return getShip().getMastArmor();
    }

    int getDriveArmor() {
        return getShip().getDriveArmor();
    }

    @Override
    public int getShrinkEnhancement() {
        return armorShrink.getShrinkEnhancement();
    }

    @Override
    public int getActualSpacesUsed() {
        return getBasicSpacesUsed();
    }

    @Override
    public ProductionLevel getProductionLevel() {
        return armorProductionLevel.getProductionLevel();
    }

    @SuppressWarnings("unchecked")
    @Override
    public DriveMastArmor setQuantity(int quantity) {
        // Hull quantity is immutable.
        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public DriveMastArmor setShrinkEnhancement(int shrinkEnhancement) {
        this.armorShrink.setShrinkEnhancement(shrinkEnhancement);
        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public DriveMastArmor setProductionLevel(ProductionLevel productionLevel) {
        this.armorProductionLevel.setProductionLevel(productionLevel);
        return this;
    }
}
