package com.senorpez.avt.shipdesigner.systems;

public class ArmorProductionLevel {
    private ProductionLevel productionLevel;

    public ArmorProductionLevel(final ProductionLevel productionLevel) {
        this.productionLevel = productionLevel;
    }

    ProductionLevel getProductionLevel() {
        return productionLevel;
    }

    void setProductionLevel(final ProductionLevel productionLevel) {
        this.productionLevel = productionLevel;
    }
}
