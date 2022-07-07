package com.senorpez.avt.shipdesigner.systems;

class ArmorProductionLevel {
    private ProductionLevel productionLevel;

    ArmorProductionLevel(final ProductionLevel productionLevel) {
        this.productionLevel = productionLevel;
    }

    ProductionLevel getProductionLevel() {
        return productionLevel;
    }

    ArmorProductionLevel setProductionLevel(final ProductionLevel productionLevel) {
        this.productionLevel = productionLevel;
        return this;
    }
}
