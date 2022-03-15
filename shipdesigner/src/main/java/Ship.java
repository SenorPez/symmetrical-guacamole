class Ship {
    private String name;
    private Nation origin;
    private Shape shape;

    public String getName() {
        return name;
    }

    public Ship setName(String name) {
        this.name = name;
        return this;
    }

    public Nation getOrigin() {
        return origin;
    }

    public Ship setOrigin(Nation origin) {
        this.origin = origin;
        return this;
    }

    public Shape getShape() {
        return shape;
    }

    public Ship setShape(Shape shape) {
        this.shape = shape;
        return this;
    }
}
