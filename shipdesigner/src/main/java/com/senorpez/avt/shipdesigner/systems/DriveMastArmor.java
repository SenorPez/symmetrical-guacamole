package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

class DriveMastArmor extends System {
    private ArmorShrink armorShrink;
    private ArmorProductionLevel armorProductionLevel;

    private final int costPerSpace = 2;

    private final static String name = "Engine and Mast Armor";
    private final static int spacesPerSystem = 1;
    private final static double crewRequiredPerSpace = 0d;
    private final static double maintenanceRate = 0.05d;

    public DriveMastArmor(Ship ship, ArmorShrink armorShrink, ArmorProductionLevel armorProductionLevel) {
        super(ship, name, spacesPerSystem, crewRequiredPerSpace, maintenanceRate, armorProductionLevel.getProductionLevel(), armorShrink.getShrinkEnhancement());
    }

    @Override
    int getQuantity() {
        return 0;
    }

    int getMastArmor() {
        return getShip().getMastArmor();
    }

    int getDriveArmor() {
        return getShip().getDriveArmor();
    }

    @Override
    int getBasicSpacesUsed() {
        return 0;
    }

    @Override
    double getCostPerSpace() {
        return costPerSpace;
    }
}
