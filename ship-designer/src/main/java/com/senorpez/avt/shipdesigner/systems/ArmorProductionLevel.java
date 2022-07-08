package com.senorpez.avt.shipdesigner.systems;

class ArmorProductionLevel {
    private ProductionLevel productionLevel;

    ArmorProductionLevel(final ProductionLevel productionLevel) {
        this.productionLevel = productionLevel;
    }

    ProductionLevel getProductionLevel() {
        return productionLevel;
    }

    void setProductionLevel(final ProductionLevel productionLevel) {
        this.productionLevel = productionLevel;
    }
}
