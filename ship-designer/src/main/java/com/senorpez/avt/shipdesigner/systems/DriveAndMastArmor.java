package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

public class DriveAndMastArmor extends StandardSystem {
    private int driveArmor;
    private int mastArmor;
    private ArmorShrink armorShrink;
    private ArmorProductionLevel armorProductionLevel;

    private final static String name = "Drive and Mast Armor";
    private final static int spacesPerSystem = 1;
    private final static double costPerSpace = 2d;
    private final static double crewPerSpace = 0d;
    private final static double maintenanceRate = 0.05d;

    public DriveAndMastArmor(final Ship ship,
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

    public int getDriveArmor() {
        return driveArmor;
    }

    void setDriveArmor(final int driveArmor) {
        this.driveArmor = driveArmor;
    }

    public int getMastArmor() {
        return mastArmor;
    }

    void setMastArmor(final int mastArmor) {
        this.mastArmor = mastArmor;
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
    public void setQuantity(final int quantity) {
        // Quantity is immutable; set Drive Armor and Mast Armor
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
