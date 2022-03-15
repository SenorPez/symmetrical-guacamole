enum BuildMode {
    STANDARD("Standard"),
    QUICK("Quick and Dirty");

    private final String buildModeName;

    BuildMode(String buildModeName) {
        this.buildModeName = buildModeName;
    }

    String getBuildModeName() {
        return buildModeName;
    }
}
