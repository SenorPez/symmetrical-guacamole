enum Shape {
    CYLINDER("Cylinder", 1d),
    SPHEROID("Spheroid", 1.5d),
    LONG_CYLINDER("Long Cylinder", 0.75d),
    HEMISPHEROID("Hemispheroid", 1.75d),
    CONICAL("Conical", 1.375),
    ELLIPSOID("Ellipsoid", 1.25d);

    private final String shapeName;
    private final double mastMassModifier;

    Shape(String shapeName, double mastMassModifier) {
        this.shapeName = shapeName;
        this.mastMassModifier = mastMassModifier;
    }

    String getShapeName() {
        return shapeName;
    }

    double getMastMassModifier() {
        return mastMassModifier;
    }
}
