package com.senorpez.avt.shipdesigner.weapons;

import com.senorpez.avt.shipdesigner.systems.ProductionLevel;

class ZoneDefenseZDCM extends ZoneDefense {
    private ZDCM model;

    ZoneDefenseZDCM(Mount mount, ZDCM model, int shrinkEnhancement, ProductionLevel productionLevel) {
        super(mount, shrinkEnhancement, productionLevel);
        this.model = model;
    }

    @Override
    int getSpacesUsed() {
        return model.getSpaces();
    }

    @Override
    int getDuelCost() {
        final double enhancementCost = enhancementTable.get(getShrinkEnhancement());
        final int baseCost = getBaseCost();
        final double fieldOfFireCost = 0.9 + getMount().getFiringArc().size() / 10d;
        return Double.valueOf(Math.ceil(enhancementCost * baseCost * fieldOfFireCost)).intValue();
    }

    @Override
    int getRecycleTime() {
        return 1;
    }

    @Override
    double getPower() {
        return 0;
    }

    @Override
    int getBaseCost() {
        return model.getCost();
    }

    ZDCM getModel() {
        return model;
    }

    ZoneDefenseZDCM setModel(ZDCM model) {
        this.model = model;
        return this;
    }

    enum ZDCM {
        ZDCM_GEN1("ZDCM", 1, 7),
        ZDCM_GEN2("ZDCM+", 1, 9),
        ZDCM_GEN3("ZDCM++", 1, 11);
        
        private final String name;
        private final int spaces;
        private final int cost;

        ZDCM(String name, int spaces, int cost) {
            this.name = name;
            this.spaces = spaces;
            this.cost = cost;
        }

        String getName() {
            return name;
        }

        int getSpaces() {
            return spaces;
        }

        int getCost() {
            return cost;
        }
    }
}
