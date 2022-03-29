package com.senorpez.avt.shipdesigner.systems;

class ArmorProductionLevel {
    private ProductionLevel productionLevel;

    ArmorProductionLevel(ProductionLevel productionLevel) {
        this.productionLevel = productionLevel;
    }

    ProductionLevel getProductionLevel() {
        return productionLevel;
    }

    ArmorProductionLevel setProductionLevel(ProductionLevel productionLevel) {
        this.productionLevel = productionLevel;
        return this;
    }
}
