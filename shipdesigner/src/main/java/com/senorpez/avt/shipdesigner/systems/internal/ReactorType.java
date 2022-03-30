package com.senorpez.avt.shipdesigner.systems.internal;

enum ReactorType {
    SOLID_CORE_FUSION("Solid Core Fusion", 0.5d, 6, 0.80000d, 20, 10, 0.5d),
    LIQUID_CORE_FUSION("Liquid Core Fusion", 1d, 8, 0.80000d, 30, 10, 0.625d),
    GAS_CORE_FUSION("Gas Core Fusion", 1d, 5, 0.80000d, 60, 15, 0.75d),
    D_T_FUSION("D-T Fusion", 4d, 14, 1 / 1.75d, 100, 5, 0.5d),
    D_3HE_FUSION("D-3He Fusion", 3, 10, 0.5d, 100, 5, 0.5d);

    private final String name;
    private final double powerPerSpace;
    private final int baseReactorSize;
    private final double powerFactor;
    private final int baseReactorCost;
    private final int additionalReactorCost;
    private final double baseReactorCrew;

    ReactorType(String name, double powerPerSpace, int baseReactorSize, double powerFactor, int baseReactorCost, int additionalReactorCost, double baseReactorCrew) {
        this.name = name;
        this.powerPerSpace = powerPerSpace;
        this.baseReactorSize = baseReactorSize;
        this.powerFactor = powerFactor;
        this.baseReactorCost = baseReactorCost;
        this.additionalReactorCost = additionalReactorCost;
        this.baseReactorCrew = baseReactorCrew;
    }

    String getName() {
        return name;
    }

    double getPowerPerSpace() {
        return powerPerSpace;
    }

    int getBaseReactorSize() {
        return baseReactorSize;
    }

    double getPowerFactor() {
        return powerFactor;
    }

    int getBaseReactorCost() {
        return baseReactorCost;
    }

    int getAdditionalReactorCost() {
        return additionalReactorCost;
    }

    double getBaseReactorCrew() {
        return baseReactorCrew;
    }

    int getSpacesPerSystem(final int powerGeneration) {
        return Double.valueOf(Math.floor(getBaseReactorSize() * Math.pow(powerGeneration / getPowerPerSpace(), powerFactor))).intValue();
    }

    double getCostPerSpace(final int powerGeneration) {
        return Double.valueOf(Math.ceil(getBaseReactorCost() + powerGeneration * getAdditionalReactorCost())).intValue() / (double) getSpacesPerSystem(powerGeneration);
    }

    double getMaintenanceCost() {
        return getBaseReactorCrew() > 0.5 ? 0.25d : 0.2d;
    }
}
