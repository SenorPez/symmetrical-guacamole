class ShipCharacteristics {
    private Ship ship;
    private int shipMass;
    private double shipAcceleration;

    public ShipCharacteristics(Ship ship) {
        this.ship = ship;
        this.shipMass = ship.getHullSize() * 25;
        this.shipAcceleration = ship.getMaximumThrust() / 4d;
    }

    public int getShipMass() {
        return shipMass;
    }

    public double getShipAcceleration() {
        return shipAcceleration;
    }

    public int getMainHullArmor() {
        return ship.getMainHullArmor();
    }

    public int getMastArmor() {
        return ship.getMastArmor();
    }

    public int getEngineArmor() {
        return ship.getEngineArmor();
    }

    public int getArmorShrink() {
        return ship.getArmorShrink();
    }
}
