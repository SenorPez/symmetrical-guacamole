class Ship {
    private String name;
    private Nation origin;

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
}
