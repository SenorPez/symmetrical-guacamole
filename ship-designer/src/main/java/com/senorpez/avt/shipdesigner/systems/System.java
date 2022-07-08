package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

interface System {
    Ship getShip();
    String getName();
    int getQuantity();
    int getShrink();
    int getSpacesPerSystem();
    double getCostPerSpace();
    double getCrewPerSpace();
    ProductionLevel getProductionLevel();
    double getMaintenanceRate();

    void setQuantity(final int quantity);
    void setShrink(final int shrink);
    void setProductionLevel(final ProductionLevel productionLevel);

    default int getBasicSpacesUsed() {
        return getQuantity() * getSpacesPerSystem();
    }

    default int getActualSpacesUsed() {
        if (getBasicSpacesUsed() > 0) {
            return Math.max(1, Double.valueOf(Math.floor(getBasicSpacesUsed() * (1 - getShrink() * 0.05))).intValue());
        } else {
            return 0;
        }
    }

    default int getBaseCost() {
        return Double.valueOf(Math.ceil(getBasicSpacesUsed() * getCostPerSpace())).intValue();
    }

    default int getEnhancedCost() {
        double val = getBasicSpacesUsed() * getCostPerSpace() * ShrinkCost.getSystemShrinkModifier(getShrink());
        return Double.valueOf(Math.ceil(Math.round(val * 100000d) / 100000d)).intValue();
    }

    default double getCrewRequirement() {
        return getBasicSpacesUsed() * getCrewPerSpace();
    }

    default int getDuelCost() {
        return getEnhancedCost();
    }

    default int getEconomicCost() {
        return Double.valueOf(Math.ceil(getDuelCost() * getProductionLevel().getCostModifier())).intValue();
    }

    default double getMaintenanceCost() {
        return getEconomicCost() * getMaintenanceRate();
    }
}
