package com.senorpez.avt.shipdesigner.systems;

class ArmorShrink {
    private int shrinkEnhancement;

    ArmorShrink(int shrinkEnhancement) {
        this.shrinkEnhancement = shrinkEnhancement;
    }

    int getShrinkEnhancement() {
        return shrinkEnhancement;
    }

    ArmorShrink setShrinkEnhancement(int shrinkEnhancement) {
        this.shrinkEnhancement = shrinkEnhancement;
        return this;
    }
}
