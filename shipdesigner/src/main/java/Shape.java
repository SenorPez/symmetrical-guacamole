enum Shape {
    CYLINDER("Cylinder", 1d, 52.85813d, 1d),
    SPHEROID("Spheroid", 1.5d, 20.69326d, 1.25d),
    LONG_CYLINDER("Long Cylinder", 0.75d, 83.90705d, 0.75d),
    HEMISPHEROID("Hemispheroid", 1.75d, 13.03594d, 1.375d),
    CONICAL("Conical", 1.375, 34.16382d, 1.1825d),
    ELLIPSOID("Ellipsoid", 1.25d, 32.84850d, 1.125d);

    private final String shapeName;
    private final double mastMassModifier;
    private final double hullLength; // TODO: Replace with calculated value.
    private final double pivotModifier;

    Shape(String shapeName, double mastMassModifier, double hullLength, double pivotModifier) {
        this.shapeName = shapeName;
        this.mastMassModifier = mastMassModifier;
        this.hullLength = hullLength;
        this.pivotModifier = pivotModifier;
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

    public double getPivotModifier() {
        return pivotModifier;
    }
}
