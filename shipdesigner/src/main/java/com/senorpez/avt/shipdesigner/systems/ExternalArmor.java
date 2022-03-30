package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

class ExternalArmor extends System {
    private final ArmorShrink armorShrink;
    private final ArmorProductionLevel armorProductionLevel;

    private final static String name = "External Armor";
    private final static int spacesPerSystem = 1;
    private final static double costPerSpace = 2d;
    private final static double crewRequiredPerSpace = 0d;
    private final static double maintenanceRate = 0.05d;

    ExternalArmor(Ship ship,
                  int quantity,
                  ArmorShrink armorShrink,
                  ArmorProductionLevel armorProductionLevel) {
        super(ship,
                name,
                spacesPerSystem,
                costPerSpace,
                crewRequiredPerSpace,
                maintenanceRate,
                quantity,
                armorShrink.getShrinkEnhancement(),
                armorProductionLevel.getProductionLevel());
        this.armorShrink = armorShrink;
        this.armorProductionLevel = armorProductionLevel;
    }

    int getExternalArmorPoints() {
        return Double.valueOf(Math.ceil((getQuantity() * 5) / (1 - 0.05 * getShrinkEnhancement()))).intValue();
    }

    @Override
    int getShrinkEnhancement() {
        return armorShrink.getShrinkEnhancement();
    }

    @Override
    ProductionLevel getProductionLevel() {
        return armorProductionLevel.getProductionLevel();
    }

    @SuppressWarnings("unchecked")
    @Override
    ExternalArmor setShrinkEnhancement(int shrinkEnhancement) {
        this.armorShrink.setShrinkEnhancement(shrinkEnhancement);
        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    ExternalArmor setProductionLevel(ProductionLevel productionLevel) {
        this.armorProductionLevel.setProductionLevel(productionLevel);
        return this;
    }
}
