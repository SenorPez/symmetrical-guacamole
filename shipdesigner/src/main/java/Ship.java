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
    private Integer percentOfficers = 20;

    private static final Logger logger = LogManager.getLogger(Ship.class);
    private static final Map<Integer, String> shipClasses = new HashMap<>();

    Ship() {
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

    String getShipClass() {
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

    int getBuildTime() {
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
        double modifiedBuildTime = baseTime * this.getBuildMode().getBuildTimeModifier();
        double ceilingBuildTime = Math.ceil(modifiedBuildTime);
        return Double.valueOf(ceilingBuildTime).intValue();
    }

    double getMonetaryCost() {
        double baseCost = this.getEconomicCost() * 0.1;
        double surcharge = this.getBuildMode() == BuildMode.QUICK ? this.getEconomicCost() * 0.025 : 0;
        return baseCost + surcharge;
    }

    int getDuelCost() {
        // TODO: Compute duel cost
        return 644;
    }

    int getEconomicCost() {
        // TODO: Compute economic cost
        return 610;
    }

    // GETTERS & SETTERS
    String getName() {
        return name;
    }

    Ship setName(String name) {
        logger.info(String.format("Setting origin to %s", name));
        this.name = name;
        return this;
    }

    Nation getOrigin() {
        return origin;
    }

    Ship setOrigin(Nation origin) {
        logger.info(String.format("Setting origin to %s", origin.name()));
        this.origin = origin;
        return this;
    }

    Shape getShape() {
        return shape;
    }

    Ship setShape(Shape shape) {
        logger.info(String.format("Setting shape to %s", shape.name()));
        this.shape = shape;
        return this;
    }

    Integer getHullSize() {
        return hullSize;
    }

    Ship setHullSize(Integer hullSize) {
        if (hullSize < 25) logger.error("Hull size under 25; setting to 25");
        logger.info(String.format("Setting hull size to %d", hullSize));
        this.hullSize = Math.max(hullSize, 25);
        return this;
    }

    SheetFormat getSheetFormat() {
        return sheetFormat;
    }

    Ship setSheetFormat(SheetFormat sheetFormat) {
        logger.info(String.format("Setting sheet format to %s", sheetFormat.name()));
        this.sheetFormat = sheetFormat;
        return this;
    }

    Integer getLaidDown() {
        return laidDown;
    }

    Ship setLaidDown(Integer laidDown) {
        this.laidDown = laidDown;
        return this;
    }

    BuildMode getBuildMode() {
        return buildMode;
    }

    Ship setBuildMode(BuildMode buildMode) {
        this.buildMode = buildMode;
        return this;
    }

    public Integer getPercentOfficers() {
        return percentOfficers;
    }

    public Ship setPercentOfficers(Integer percentOfficers) {
        if (percentOfficers < 10) {
            logger.error("Officer percentage less than 10%; setting to 10%");
            this.percentOfficers = 10;
        } else if (percentOfficers > 22) {
            logger.error("Officer percentage greater than 22%; setting to 22%");
            this.percentOfficers = 22;
        } else {
            logger.info(String.format("Setting hull size to %d%%", percentOfficers));
            this.percentOfficers = percentOfficers;
        }
        return this;
    }
}
