package com.senorpez.avt.shipdesigner.weapons;

import com.senorpez.avt.shipdesigner.Ship;
import com.senorpez.avt.shipdesigner.enums.AVIDWindow;
import com.senorpez.avt.shipdesigner.enums.ArmorSegment;

import java.util.List;

class Mount {
    Ship ship;

    MountType mountType;

    List<Weapon> weapons;
    ArmorSegment location;
    List<AVIDWindow> firingArc;

    Coordinate mountLocation;
    Coordinate diamondLocation;
    
    Mount() {}

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

    List<Weapon> getWeapons() {
        return weapons;
    }

    Mount setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
        return this;
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

    private enum MountType {
        PRIMARY,
        SECONDARY,
        TERTIARY
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
