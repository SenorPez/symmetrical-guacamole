package com.senorpez.avt.shipdesigner.enums;

public enum BuildMode {
    STANDARD("Standard", 1.0d),
    QUICK("Quick and Dirty", 0.5d);

    private final String buildModeName;
    private final double buildTimeModifier;

    BuildMode(String buildModeName, double buildTimeModifier) {
        this.buildModeName = buildModeName;
        this.buildTimeModifier = buildTimeModifier;
    }

    public String getBuildModeName() {
        return buildModeName;
    }

    public double getBuildTimeModifier() {
        return buildTimeModifier;
    }
}
