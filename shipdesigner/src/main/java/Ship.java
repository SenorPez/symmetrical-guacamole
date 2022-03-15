import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

class Ship {
    private String name;
    private Nation origin;
    private Shape shape;
    private Integer hullSize;

    private static final Logger logger = LogManager.getLogger(Ship.class);

    public String getName() {
        return name;
    }

    public Ship setName(String name) {
        logger.info(String.format("Setting origin to %s", name));
        this.name = name;
        return this;
    }

    public Nation getOrigin() {
        return origin;
    }

    public Ship setOrigin(Nation origin) {
        logger.info(String.format("Setting origin to %s", origin.name()));
        this.origin = origin;
        return this;
    }

    public Shape getShape() {
        return shape;
    }

    public Ship setShape(Shape shape) {
        logger.info(String.format("Setting shape to %s", shape.name()));
        this.shape = shape;
        return this;
    }

    public Integer getHullSize() {
        return hullSize;
    }

    public Ship setHullSize(Integer hullSize) {
        if (hullSize < 25) logger.error("Hull size under 25; setting to 25");
        logger.info(String.format("Setting hull size to %d", hullSize));
        this.hullSize = Math.max(hullSize, 25);
        return this;
    }
}
