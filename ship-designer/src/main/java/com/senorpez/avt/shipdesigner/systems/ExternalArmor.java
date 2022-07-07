package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

import static com.senorpez.avt.shipdesigner.systems.ProductionLevel.STANDARD;

class ExternalArmor extends System {
    // TODO: Add validation against Frame Reinforcement

    private ArmorShrink armorShrink;
    private ArmorProductionLevel armorProductionLevel;

    private final static String name = "External Armor";
    private final static int spacesPerSystem = 1;
    private final static double costPerSpace = 2d;
    private final static double crewPerSpace = 0d;
    private final static double maintenanceRate = 0.05d;

    ExternalArmor(final Ship ship,
                  final int quantity,
                  final ArmorShrink armorShrink,
                  final ArmorProductionLevel armorProductionLevel) {
        super(ship,
                quantity,
                0,
                STANDARD,
                name,
                spacesPerSystem,
                costPerSpace,
                crewPerSpace,
                maintenanceRate);
        this.armorShrink = armorShrink;
        this.armorProductionLevel = armorProductionLevel;
    }

    ArmorShrink getArmorShrink() {
        return armorShrink;
    }

    ExternalArmor setArmorShrink(final ArmorShrink armorShrink) {
        this.armorShrink = armorShrink;
        return this;
    }

    ArmorProductionLevel getArmorProductionLevel() {
        return armorProductionLevel;
    }

    ExternalArmor setArmorProductionLevel(final ArmorProductionLevel armorProductionLevel) {
        this.armorProductionLevel = armorProductionLevel;
        return this;
    }

    int getExternalArmorPoints() {
        final double val = (getQuantity() * 5) / (1 - 0.05d * getShrink());
        return Double.valueOf(Math.ceil(val)).intValue();
    }

    @Override
    int getShrink() {
        return getArmorShrink().getShrink();
    }

    @Override
    ProductionLevel getProductionLevel() {
        return getArmorProductionLevel().getProductionLevel();
    }

    @SuppressWarnings("unchecked")
    @Override
    ExternalArmor setShrink(final int shrink) {
        // Shrink is immutable; change via the ArmorShrink object.
        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    ExternalArmor setProductionLevel(final ProductionLevel productionLevel) {
        // Production level is immutable; change via the ArmorProductionLevel object.
        return this;
    }
}
