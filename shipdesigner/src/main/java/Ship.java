import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.IOException;
import java.util.*;

class Ship {
    private String name;
    private Nation origin;
    private Shape shape;
    private Integer hullSize = 0;
    private SheetFormat sheetFormat;

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
                .filter(entry -> entry.getKey() < this.getHullSize())
                .max(Comparator.comparingInt(Map.Entry::getKey))
                .map(Map.Entry::getValue)
                .stream().findFirst()
                .orElseThrow(() -> {
                    logger.error("Set hull size before finding ship class.");
                    return new NoSuchElementException("Set hull size before finding ship class.");
                });
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
}
