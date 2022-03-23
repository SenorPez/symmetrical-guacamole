package com.senorpez.avt.shipdesigner;

record FuelSpace(double exactTotalFuel, int fuelAtSpace) {
    FuelSpace(int space, double engineGeneration, int hullSpaces, int previousFuel) {
        this(calculateExactTotalFuel(engineGeneration, hullSpaces, space),
                calculateFuelAtSpace(space, engineGeneration, hullSpaces, previousFuel));
    }

    private static double calculateExactTotalFuel(double engineGeneration, int hullSpaces, int space) {
        return engineGeneration * 200 / (hullSpaces - (0.45 * space)) * space;
    }

    private static int calculateFuelAtSpace(int space, double engineGeneration, int hullSpaces, int previousFuel) {
        return Double.valueOf(Math.floor(calculateExactTotalFuel(engineGeneration, hullSpaces, space) - previousFuel)).intValue();
    }
}
