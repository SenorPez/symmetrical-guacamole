package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

public class SupportCargo extends CargoSystem {
    private int cruiseDuration;

    private final static String name = "Support Cargo";
    private final static int spacesPerSystem = 1;

    SupportCargo(final Ship ship,
                 final int cruiseDuration,
                 final CargoArmorLevel cargoArmorLevel,
                 final ProductionLevel productionLevel) {
        super(ship,
                calculateQuantity(ship, cruiseDuration),
                productionLevel,
                name,
                spacesPerSystem,
                cargoArmorLevel);
        this.cruiseDuration = cruiseDuration;
    }

    private static int calculateQuantity(final Ship ship, final int cruiseDuration) {
        if (cruiseDuration == 4) {
            return 0;
        } else {
            double supply = Math.floor(ship.getNonSupplyCrew() * cruiseDuration / 80d);
            double totalCrew = Math.max(10, Math.ceil(ship.getNonSupplyCrew() + supply * 0.125));
            double backcheck = Math.ceil(supply * 80 / totalCrew);

            while (backcheck < cruiseDuration) {
                supply += 1;
                totalCrew = Math.max(10, Math.ceil(ship.getNonSupplyCrew() + supply * 0.125));
                backcheck = Math.ceil(supply * 80 / totalCrew);
            }

            return Double.valueOf(supply).intValue();
        }
    }

    int getCruiseDuration() {
        return cruiseDuration;
    }

    void setCruiseDuration(final int cruiseDuration) {
        this.cruiseDuration = cruiseDuration;
    }

    @Override
    public int getQuantity() {
        return calculateQuantity(getShip(), getCruiseDuration());
    }

    @Override
    public void setQuantity(final int quantity) {
        // Quantity is immutable; change cruise duration instead
    }
}
