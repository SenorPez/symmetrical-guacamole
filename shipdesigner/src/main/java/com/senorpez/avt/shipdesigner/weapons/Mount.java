package com.senorpez.avt.shipdesigner.weapons;

import com.senorpez.avt.shipdesigner.Ship;
import com.senorpez.avt.shipdesigner.enums.AVIDWindow;
import com.senorpez.avt.shipdesigner.enums.ArmorSegment;
import com.senorpez.avt.shipdesigner.enums.MountType;
import com.senorpez.avt.shipdesigner.systems.ProductionLevel;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.senorpez.avt.shipdesigner.enums.MountType.*;

class Mount {
    Ship ship;

    MountType mountType;
    ProductionLevel productionLevel;

    int mountArmor; // Component Armor
    int shutterArmor;
    
    int cgDrums;
    int cmDrums;

    List<Weapon> weapons;
    ArmorSegment location;
    List<AVIDWindow> firingArc;

    Coordinate mountLocation;
    Coordinate diamondLocation;
    
    Mount() {}

    int getMountTotalSpaces() {
        if (mountType.equals(PRIMARY)) {
            return ship
                    .getMountConfiguration()
                    .getPrimaryMountTotalSpaces(ship.getShape(), ship.getHullSize(), ship.getLaidDown());
        }
        if (mountType.equals(SECONDARY)) {
            return ship
                    .getMountConfiguration()
                    .getSecondaryMountTotalSpaces(ship.getShape(), ship.getHullSize(), ship.getLaidDown());
        }
        if (mountType.equals(TERTIARY)) {
            return ship
                    .getMountConfiguration()
                    .getTertiaryMountTotalSpaces(ship.getShape(), ship.getHullSize(), ship.getLaidDown());
        }
        return 0;
    }

    int getMountRows() {
        if (mountType.equals(PRIMARY)) {
            return ship
                    .getMountConfiguration()
                    .getPrimaryMountRows(ship.getShape(), ship.getHullSize(), ship.getLaidDown());
        }
        if (mountType.equals(SECONDARY)) {
            return ship
                    .getMountConfiguration()
                    .getSecondaryMountRows(ship.getShape(), ship.getHullSize(), ship.getLaidDown());
        }
        if (mountType.equals(TERTIARY)) {
            return ship
                    .getMountConfiguration()
                    .getTertiaryMountRows(ship.getShape(), ship.getHullSize(), ship.getLaidDown());
        }
        return 0;
    }

    int getMountBiggestWeaponSpaces() {
        if (mountType.equals(PRIMARY)) {
            return ship
                    .getMountConfiguration()
                    .getPrimaryMountBiggestWeaponSpaces(ship.getShape(), ship.getHullSize(), ship.getLaidDown());
        }
        if (mountType.equals(SECONDARY)) {
            return ship
                    .getMountConfiguration()
                    .getSecondaryMountBiggestWeaponSpaces(ship.getShape(), ship.getHullSize(), ship.getLaidDown());
        }
        if (mountType.equals(TERTIARY)) {
            return ship
                    .getMountConfiguration()
                    .getTertiaryMountBiggestWeaponSpaces(ship.getShape(), ship.getHullSize(), ship.getLaidDown());
        }
        return 0;
    }

    int getDuelCost(Ship ship) {
        return Double.valueOf(Math.ceil(getMountCost(ship))).intValue() + getWeaponCost();
    }

    int getEconomicCost(Ship ship) {
        return Double.valueOf(Math.ceil(getMountCost(ship) * getProductionLevel().getEconomicCostModifier())).intValue()
                + getWeaponCost();
    }

    int getHeatExchangers() {
        return weapons
                .stream()
                .filter(w -> w instanceof Laser)
                .map(l -> ((Laser) l).getHeatExchangers())
                .reduce(Integer::sum)
                .orElse(0);
    }

    int getFlashCoolers() {
        return weapons
                .stream()
                .filter(w -> w instanceof Laser)
                .map(l -> ((Laser) l).getHeatExchangers())
                .reduce(Integer::sum)
                .orElse(0);
    }

    private double getMountCost(Ship ship) {
        if (getWeaponSpacesUsed() <= 0) return 0;
        int large = getLargestWeaponSpacesUsed();
        int count = countLargestWeaponsInMount();
        int sum = getWeaponSpacesUsed();
        double mod = ship.getShape().getWeaponCostModifier();
        int fieldOfFire = firingArc.size();

        return (Math.pow(2, large) * (count + ((sum - (large * count)) / 5d)) * mod + 3 * (cgDrums + cmDrums)) * (0.9 + fieldOfFire / 10d)
                + shutterArmorCost();
    }

    private int shutterArmorCost() {
        List<Double> armorShrinkMultiplier = List.of(1d, 1.1d, 1.3d, 1.6d, 2d, 2.5d, 3.1d, 3.8d, 4.6d, 5.5d, 6.5d);

        return Double.valueOf(Math.ceil(shutterArmor * 2 * armorShrinkMultiplier.get(ship.getArmorShrink()))).intValue();

    }

    private int getLargestWeaponSpacesUsed() {
        return Collections.max(weapons.stream().map(Weapon::getSpacesUsed).toList());
    }

    private int getWeaponSpacesUsed() {
        return weapons.stream().map(Weapon::getSpacesUsed).reduce(Integer::sum).orElse(0);
    }

    private int countLargestWeaponsInMount() {
        return (int) weapons.stream().filter(w -> w.getSpacesUsed() == getLargestWeaponSpacesUsed()).count();
    }

    private int getWeaponCost() {
        return weapons.stream().map(Weapon::getDuelCost).reduce(Integer::sum).orElse(0);
    }

    // GETTERS & SETTERS
    Ship getShip() {
        return ship;
    }

    Mount setShip(Ship ship) {
        this.ship = ship;
        return this;
    }

    MountType getMountType() {
        return mountType;
    }

    Mount setMountType(MountType mountType) {
        this.mountType = mountType;
        return this;
    }

    int getMountArmor() {
        return mountArmor;
    }

    public Mount setMountArmor(int mountArmor) {
        this.mountArmor = mountArmor;
        return this;
    }

    int getShutterArmor() {
        return shutterArmor;
    }

    Mount setShutterArmor(int shutterArmor) {
        this.shutterArmor = shutterArmor;
        return this;
    }

    int getCgDrums() {
        return cgDrums;
    }

    Mount setCgDrums(int cgDrums) {
        this.cgDrums = cgDrums;
        return this;
    }

    int getCmDrums() {
        return cmDrums;
    }

    Mount setCmDrums(int cmDrums) {
        this.cmDrums = cmDrums;
        return this;
    }

    public Mount setProductionLevel(ProductionLevel productionLevel) {
        this.productionLevel = productionLevel;
        return this;
    }

    List<Weapon> getWeapons() {
        return weapons;
    }

    Mount setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
        return this;
    }

    public ProductionLevel getProductionLevel() {
        return productionLevel;
    }

    ArmorSegment getLocation() {
        return location;
    }

    Mount setLocation(ArmorSegment location) {
        this.location = location;
        return this;
    }

    List<AVIDWindow> getFiringArc() {
        return firingArc;
    }

    Mount setFiringArc(List<AVIDWindow> firingArc) {
        this.firingArc = firingArc;
        return this;
    }

    Coordinate getMountLocation() {
        return mountLocation;
    }

    Mount setMountLocation(Coordinate mountLocation) {
        this.mountLocation = mountLocation;
        return this;
    }

    Coordinate getDiamondLocation() {
        return diamondLocation;
    }

    Mount setDiamondLocation(Coordinate diamondLocation) {
        this.diamondLocation = diamondLocation;
        return this;
    }

    private static class Coordinate {
        private int x;
        private int y;

        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int getX() {
            return x;
        }

        Coordinate setX(int x) {
            this.x = x;
            return this;
        }

        int getY() {
            return y;
        }

        Coordinate setY(int y) {
            this.y = y;
            return this;
        }
    }
}
