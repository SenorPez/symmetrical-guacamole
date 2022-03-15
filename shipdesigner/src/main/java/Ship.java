import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

class Ship {
    // Inputs
    private String name = "Class Name";
    private Nation origin = Nation.OLYMPIAN_REPUBLIC;
    private Shape shape = Shape.SPHEROID;
    private Integer hullSize = 25;
    private SheetFormat sheetFormat = SheetFormat.ONE_UP;
    private Integer laidDown = 2215;
    private BuildMode buildMode = BuildMode.STANDARD;

    private static final Logger logger = LogManager.getLogger(Ship.class);
    private static final Map<Integer, String> shipClasses = new HashMap<>();

    public Ship() {
        // Initialize ship classes
        shipClasses.put(25, "Gunboat");
        shipClasses.put(51, "Corvette");
        shipClasses.put(151, "Frigate");
        shipClasses.put(251, "Light Cruiser");
        shipClasses.put(351, "Heavy Cruiser");
        shipClasses.put(501, "Battlecruiser");
        shipClasses.put(701, "Battleship");
        shipClasses.put(1001, "Leviathan");
        shipClasses.put(1801, "Titan");
    }

    public String getShipClass() {
        return shipClasses.entrySet()
                .stream()
                .filter(entry -> entry.getKey() <= this.getHullSize())
                .max(Comparator.comparingInt(Map.Entry::getKey))
                .map(Map.Entry::getValue)
                .stream().findFirst()
                .orElseThrow(() -> {
                    logger.error("Set hull size before finding ship class.");
                    return new NoSuchElementException("Set hull size before finding ship class.");
                });
    }

    Integer getBuildTime() {
        double baseTime = (
                0.00000000003d * Math.pow(this.getHullSize(), 3)
                        - 0.0000002d * Math.pow(this.getHullSize(), 2)
                        + 0.0002d * this.getHullSize()
                        + 1.0363d)
                * (
                0.00000000003d * Math.pow(this.getHullSize(), 4)
                        - 0.0000001d * Math.pow(this.getHullSize(), 3)
                        + 0.0001d * Math.pow(this.getHullSize(), 2)
                        + 0.0711d * this.getHullSize()
                        + 1.687d);
        double rawBuildTime = baseTime * this.getBuildMode().getBuildTimeModifier();
        double ceilBuildTime = Math.ceil(rawBuildTime);
        return Double.valueOf(ceilBuildTime).intValue();
    }

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

    public SheetFormat getSheetFormat() {
        return sheetFormat;
    }

    public Ship setSheetFormat(SheetFormat sheetFormat) {
        logger.info(String.format("Setting sheet format to %s", sheetFormat.name()));
        this.sheetFormat = sheetFormat;
        return this;
    }

    public Integer getLaidDown() {
        return laidDown;
    }

    public Ship setLaidDown(Integer laidDown) {
        this.laidDown = laidDown;
        return this;
    }

    public BuildMode getBuildMode() {
        return buildMode;
    }

    public Ship setBuildMode(BuildMode buildMode) {
        this.buildMode = buildMode;
        return this;
    }
}
