package com.senorpez.avt.shipdesigner.weapons;

import com.senorpez.avt.shipdesigner.systems.ProductionLevel;

class LaserSRLS extends Laser {
    private SRLS model;

    LaserSRLS(final Mount mount,
              final SRLS model,
              final int armorPiercing,
              final int shrinkEnhancement,
              final int durabilityEnhancement,
              final ProductionLevel productionLevel,
              final int heatExchangers,
              final int flashCoolers) {
        super(mount, armorPiercing, shrinkEnhancement, durabilityEnhancement, productionLevel, heatExchangers, flashCoolers);
        this.model = model;
    }

    @Override
    int getSpacesUsed() {
        return model.getWeaponSize();
    }

    @Override
    double getPower() {
        return model.getPower();
    }

    @Override
    int getModelCost() {
        return model.getCost();
    }

    @Override
    double getWasteHeat() {
        return model.getWasteHeat();
    }

    SRLS getModel() {
        return model;
    }

    LaserSRLS setModel(SRLS model) {
        this.model = model;
        return this;
    }

    enum SRLS {
        SRLS2(2, 2),
        SRLS3(3, 5),
        SRLS4(4, 13),
        SRLS5(5, 26),
        SRLS6(6, 48),
        SRLS7(7, 71),
        SRLS8(8, 112);

        private final int weaponSize;
        private final int cost;

        private final static int wasteHeat = 1500;
        private final static double wasteHeatModifier = 0.2d;

        SRLS(int weaponSize, int cost) {
            this.weaponSize = weaponSize;
            this.cost = cost;
        }

        double getWasteHeat() {
            // TODO: LRLS has an additional 250 wasteHeat in here (that's not multiplied by the size)
            return (weaponSize * wasteHeat + 0) * (1 - wasteHeatModifier);
        }

        double getPower() {
            // TODO: LRLS has an additional 250 here.
            return Double.valueOf(Math.round((2 * (0 + (weaponSize * wasteHeat))) / 1000d)).intValue() / 2d;
        }

        int getWeaponSize() {
            return weaponSize;
        }

        int getCost() {
            return cost;
        }
    }
}
