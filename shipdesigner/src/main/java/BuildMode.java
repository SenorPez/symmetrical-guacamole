enum BuildMode {
    STANDARD("Standard", 1.0d),
    QUICK("Quick and Dirty", 0.5d);

    private final String buildModeName;
    private final Double buildTimeModifier;

    BuildMode(String buildModeName, double buildTimeModifier) {
        this.buildModeName = buildModeName;
        this.buildTimeModifier = buildTimeModifier;
    }

    String getBuildModeName() {
        return buildModeName;
    }

    Double getBuildTimeModifier() {
        return buildTimeModifier;
    }
}
