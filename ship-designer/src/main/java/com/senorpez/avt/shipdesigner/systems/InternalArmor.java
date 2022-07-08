package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

class InternalArmor extends StandardSystem {
    // TODO: Add validation against Frame Reinforcement

    private ArmorShrink armorShrink;
    private ArmorProductionLevel armorProductionLevel;

    private final static String name = "Internal Armor";
    private final static int spacesPerSystem = 1;
    private final static double costPerSpace = 2d;
    private final static double crewPerSpace = 0d;
    private final static double maintenanceRate = 0.05d;

    InternalArmor(final Ship ship,
                  final int quantity,
                  final ArmorShrink armorShrink,
                  final ArmorProductionLevel armorProductionLevel) {
        super(ship,
                quantity,
                0,
                ProductionLevel.STANDARD,
                name,
                spacesPerSystem,
                costPerSpace,
                crewPerSpace,
                maintenanceRate);
        this.armorShrink = armorShrink;
        this.armorProductionLevel = armorProductionLevel;
    }

    int getInternalArmorPoints() {
        final double val = (getQuantity() * 5) / (1 - 0.05d * getShrink());
        return Double.valueOf(Math.ceil(val)).intValue();
    }

    @Override
    public int getShrink() {
        return armorShrink.getShrink();
    }


    void setArmorShrink(final ArmorShrink armorShrink) {
        this.armorShrink = armorShrink;
    }

    @Override
    public ProductionLevel getProductionLevel() {
        return armorProductionLevel.getProductionLevel();
    }

    void setArmorProductionLevel(final ArmorProductionLevel armorProductionLevel) {
        this.armorProductionLevel = armorProductionLevel;
    }

    @Override
    public void setShrink(final int shrink) {
        // Shrink is immutable; change via the ArmorShrink object
    }

    @Override
    public void setProductionLevel(final ProductionLevel productionLevel) {
        // Production Level is immutable; change via the ArmorProductionLevel object
    }
}
