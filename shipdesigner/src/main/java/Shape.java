enum Shape {
    CYLINDER("Cylinder", 1d, 52.85813d),
    SPHEROID("Spheroid", 1.5d, 20.69326d),
    LONG_CYLINDER("Long Cylinder", 0.75d, 83.90705d),
    HEMISPHEROID("Hemispheroid", 1.75d, 13.03594d),
    CONICAL("Conical", 1.375, 34.16382d),
    ELLIPSOID("Ellipsoid", 1.25d, 32.84850d);

    private final String shapeName;
    private final double mastMassModifier;
    private final double hullLength; // TODO: Replace with calculated value.

    Shape(String shapeName, double mastMassModifier, double hullLength) {
        this.shapeName = shapeName;
        this.mastMassModifier = mastMassModifier;
        this.hullLength = hullLength;
    }

    String getShapeName() {
        return shapeName;
    }

    double getMastMassModifier() {
        return mastMassModifier;
    }

    public double getHullLength() {
        return hullLength;
    }
}
