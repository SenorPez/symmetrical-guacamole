package com.senorpez.avt.shipdesigner.systems;

class ArmorShrink {
    private int shrink;

    ArmorShrink(final int shrink) {
        this.shrink = shrink;
    }

    int getShrink() {
        return shrink;
    }

    ArmorShrink setShrink(final int shrink) {
        this.shrink = shrink;
        return this;
    }
}
