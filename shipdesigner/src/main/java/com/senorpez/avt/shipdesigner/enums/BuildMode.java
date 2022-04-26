package com.senorpez.avt.shipdesigner.enums;

public enum BuildMode {
    STANDARD("Standard", 1.0d, 0),
    QUICK("Quick and Dirty", 0.5d, 0.6);

    private final String buildModeName;
    private final double buildTimeModifier;
    private final double maintenanceIncrease;

    BuildMode(String buildModeName, double buildTimeModifier, double maintenanceIncrease) {
        this.buildModeName = buildModeName;
        this.buildTimeModifier = buildTimeModifier;
        this.maintenanceIncrease = maintenanceIncrease;
    }

    public String getBuildModeName() {
        return buildModeName;
    }

    public double getBuildTimeModifier() {
        return buildTimeModifier;
    }

    public double getMaintenanceIncrease() {
        return maintenanceIncrease;
    }
}
