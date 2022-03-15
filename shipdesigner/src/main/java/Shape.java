enum Shape {
    CYLINDER("Cylinder"),
    SPHEROID("Spheroid"),
    LONG_CYLINDER("Long Cylinder"),
    HEMISPHEROID("Hemispheroid"),
    CONICAL("Conical"),
    ELLIPSOID("Ellipsoid");

    private final String shapeName;

    Shape(String shapeName) {
        this.shapeName = shapeName;
    }

    String getShapeName() {
        return shapeName;
    }
}
