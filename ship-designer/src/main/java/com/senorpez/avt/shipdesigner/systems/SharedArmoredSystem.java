package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

abstract class SharedArmoredSystem extends StandardArmoredSystem implements SharedArmorSystem {
    private ArmorLevel armorLevel;

    SharedArmoredSystem(final Ship ship,
                        final int quantity,
                        final int shrink,
                        final ProductionLevel productionLevel,
                        final String name,
                        final int spacesPerSystem,
                        final double costPerSpace,
                        final double crewPerSpace,
                        final double maintenanceRate,
                        final ArmorLevel armorLevel) {
        super(ship,
                quantity,
                shrink,
                productionLevel,
                name,
                spacesPerSystem,
                costPerSpace,
                crewPerSpace,
                maintenanceRate,
                0);
        this.armorLevel = armorLevel;
    }

    @Override
    public ArmorLevel getSharedArmorLevel() {
        return armorLevel;
    }

    @Override
    public void setSharedArmorLevel(final ArmorLevel armorLevel) {
        this.armorLevel = armorLevel;
    }

    @Override
    public int getArmorLevel() {
        return getSharedArmorLevel().getArmorLevel();
    }

    @Override
    public void setArmorLevel(final int armorLevel) {
        // Armor level is immutable; change ArmorLevel object instead
    }

    @Override
    public double getArmorPointsUsed() {
        return getSharedArmorLevel().getActualSpacesUsed() * ArmorPoints.getArmorPointsUsed(getSharedArmorLevel().getArmorLevel());
    }
}
