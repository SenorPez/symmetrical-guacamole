package com.senorpez.avt.shipdesigner;

class Ship {
    int hullSpaces;

    Ship build() {
        return this;
    }

    int getHullSpaces() {
        return hullSpaces;
    }

    Ship setHullSpaces(final int hullSpaces) {
        this.hullSpaces = hullSpaces;
        return this;
    }
}
