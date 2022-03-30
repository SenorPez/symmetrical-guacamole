package com.senorpez.avt.shipdesigner.systems;

enum LifeSupportClass {
    MILITARY("Military"),
    EXTENDED("Extended"),
    GUNBOAT("Gunboat"); // TODO: This doesn't seem to be used anymore.

    private final String className;

    LifeSupportClass(String className) {
        this.className = className;
    }

    String getClassName() {
        return className;
    }
}
