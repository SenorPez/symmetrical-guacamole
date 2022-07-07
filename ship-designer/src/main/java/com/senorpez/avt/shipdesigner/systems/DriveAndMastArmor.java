package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

public class DriveAndMastArmor extends System {
    private int driveArmor;
    private int mastArmor;
    private ArmorShrink armorShrink;
    private ArmorProductionLevel armorProductionLevel;

    private final static String name = "Drive and Mast Armor";
    private final static int spacesPerSystem = 1;
    private final static double costPerSpace = 2d;
    private final static double crewPerSpace = 0d;
    private final static double maintenanceRate = 0.05d;

    DriveAndMastArmor(final Ship ship,
                      final int driveArmor,
                      final int mastArmor,
                      final ArmorShrink armorShrink,
                      final ArmorProductionLevel armorProductionLevel) {
        super(ship,
                0,
                0,
                null,
                name,
                spacesPerSystem,
                costPerSpace,
                crewPerSpace,
                maintenanceRate);
        this.driveArmor = driveArmor;
        this.mastArmor = mastArmor;
        this.armorShrink = armorShrink;
        this.armorProductionLevel = armorProductionLevel;
    }

    int getDriveArmor() {
        return driveArmor;
    }

    DriveAndMastArmor setDriveArmor(final int driveArmor) {
        this.driveArmor = driveArmor;
        return this;
    }

    int getMastArmor() {
        return mastArmor;
    }

    DriveAndMastArmor setMastArmor(final int mastArmor) {
        this.mastArmor = mastArmor;
        return this;
    }

    @Override
    int getShrink() {
        return armorShrink.getShrink();
    }

    DriveAndMastArmor setArmorShrink(final ArmorShrink armorShrink) {
        this.armorShrink = armorShrink;
        return this;
    }

    @Override
    ProductionLevel getProductionLevel() {
        return armorProductionLevel.getProductionLevel();
    }

    DriveAndMastArmor setArmorProductionLevel(final ArmorProductionLevel armorProductionLevel) {
        this.armorProductionLevel = armorProductionLevel;
        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    DriveAndMastArmor setQuantity(final int quantity) {
        // Quantity is immutable.
        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    DriveAndMastArmor setShrink(final int shrink) {
        // Shrink is immutable; change via the ArmorShrink object.
        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    DriveAndMastArmor setProductionLevel(final ProductionLevel productionLevel) {
        // Production level is immutable; change via the ArmorProductionLevel object.
        return this;
    }
}
