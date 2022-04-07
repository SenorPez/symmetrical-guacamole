package com.senorpez.avt.shipdesigner;

import com.senorpez.avt.shipdesigner.characteristics.*;
import com.senorpez.avt.shipdesigner.enums.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.IntStream;

public class Ship {
    // Inputs
    private String name = "Class Name";
    private Nation origin = Nation.OLYMPIAN_REPUBLIC;
    private Shape shape = Shape.SPHEROID;
    private int hullSize = 25;
    private SheetFormat sheetFormat = SheetFormat.ONE_UP;

    private int laidDown = 2215;
    private BuildMode buildMode = BuildMode.STANDARD;
    private int percentOfficers = 20;

    private double driveGeneration = 3.0d;
    private double maximumThrust = 11d;

    private int driveArmor = 0;
    private int mastArmor = 0;

    private List<Integer> noseArmor = new ArrayList<>();
    private List<Integer> aftArmor = new ArrayList<>();
    private List<Integer> topArmor = new ArrayList<>();
    private List<Integer> bottomArmor = new ArrayList<>();
    private List<Integer> portArmor = new ArrayList<>();
    private List<Integer> starboardArmor = new ArrayList<>();

    private ShipCharacteristics shipCharacteristics;
    private MassCharacteristics massCharacteristics;
    private DimensionCharacteristics dimensionCharacteristics;
    private DriveCharacteristics driveCharacteristics;
    private SurfaceCharacteristics surfaceCharacteristics;

    private MountConfiguration mountConfiguration;

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

        // Initialize armor arrays. Need to figure out how to record which is which.
        noseArmor.addAll(IntStream.range(0, 5).map(i -> 0).boxed().toList());
        aftArmor.addAll(IntStream.range(0, 5).map(i -> 0).boxed().toList());
        topArmor.addAll(IntStream.range(0, 5).map(i -> 0).boxed().toList());
        bottomArmor.addAll(IntStream.range(0, 5).map(i -> 0).boxed().toList());
        portArmor.addAll(IntStream.range(0, 5).map(i -> 0).boxed().toList());
        starboardArmor.addAll(IntStream.range(0, 5).map(i -> 0).boxed().toList());
    }

    Ship build() {
        shipCharacteristics = new ShipCharacteristics(this);
        massCharacteristics = new MassCharacteristics(shipCharacteristics);
        dimensionCharacteristics = new DimensionCharacteristics(shipCharacteristics, massCharacteristics);
        driveCharacteristics = new DriveCharacteristics(shipCharacteristics, massCharacteristics);
        surfaceCharacteristics = new SurfaceCharacteristics(shipCharacteristics, massCharacteristics);
        return this;
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
        return 0;
    }

    int getEconomicCost() {
        // TODO: Compute economic cost
        return 0;
    }

    int getMaintenanceCost() {
        // TODO: Compute maintenance cost
        return 0;
    }

    int getBoxes() {
        // TODO: Compute boxes
        return 0;
    }

    public int getMinimumCrew() {
        // TODO: Compute minimum crew
        return 0;
    }

    int getOfficers() {
        return Long.valueOf(
                Math.round(this.getMinimumCrew() * this.getPercentOfficers() / 100d)
        ).intValue();
    }

    int getEnlisted() {
        return this.getMinimumCrew() - this.getOfficers();
    }

    int getGunboatCrew() {
        return getGunboatDocks() * 10;
    }

    int getExtraBerths() {
        int extraQuarters = this.getQuartersSystems() * 10 - this.getMinimumCrew() - getGunboatCrew();
        int extraLifeSupport = this.getLifeSupportSystems() * 200 - this.getMinimumCrew() - getGunboatCrew();
        int extraBerths = Math.min(extraQuarters, extraLifeSupport);
        return Math.max(0, extraBerths);
    }

    int getCruiseDuration() {
        // TODO: Indefinite duration doesn't seem to work in spreadsheet?
        return getEnduranceSystems();
    }

    int getNoseHullDepth() {
        return surfaceCharacteristics.getAxialHullDepth();
    }

    int getNoseSurfaceArea() {
        return Long.valueOf(Math.round(surfaceCharacteristics.getFrontArmorArea() / 10)).intValue();
    }

    int getLateralHullDepth() {
        return surfaceCharacteristics.getLateralHullDepth();
    }

    int getLateralSurfaceArea() {
        return Long.valueOf(Math.round(surfaceCharacteristics.getLateralArmorArea() / 10)).intValue();
    }

    int getAftHullDepth() {
        return surfaceCharacteristics.getAxialHullDepth();
    }

    int getAftSurfaceArea() {
        return Long.valueOf(Math.round(surfaceCharacteristics.getRearArmorArea() / 10)).intValue();
    }

    Map<String, Integer> getHyperdriveCost() {
        // TODO: Placeholder
        return null;
    }

    Map<String, Double> getPodHyperdriveSurcharge() {
        // TODO: Placeholder
        return null;
    }

    int getBatteryCapacity() {
        // TODO: Probably move into a 'Systems' object.
        return 0;
    }

    int getPowerPerSegment() {
        // TODO: Probably move into a 'Systems' object.
        return 0;
    }

    int getPowerPerTurn() {
        // TODO: Probably move into a 'Systems' object.
        return 0;
    }

    int getSolarPowerPerTurn() {
        // TODO: Probably move into a 'Systems' object.
        return 0;
    }

    int getChemicalBatteryPowerPerSegment() {
        // TODO: Probably move into a 'Systems' object.
        return 0;
    }

    boolean hasMHD() {
        // TODO: Probably move into a 'Systems' object.
        return false;
    }

    int getHeatSinkCapacity() {
        // TODO: Probably move into a 'Systems' object.
        return 0;
    }

    double getRadiators() {
        // TODO: Probably move into a 'Systems' object.
        return 0;
    }

    int getFlexPoints() {
        // TODO: Probably move into a 'Systems' object.
        return 0;
    }

    int getFlagPoints() {
        // TODO: Probably move into a 'Systems' object.
        return 0;
    }

    boolean hasImprovedAccessways() {
        // TODO: Probably move into a 'Systems' object.
        return false;
    }

    int getStructuralIntegrity() {
        // TODO: Calculate. Different than DimensionCharacteristics.getStructuralIntegrity.
        return 0;
    }

    double getHullLength() {
        return dimensionCharacteristics.getHullLength();
    }

    double getHullDiameter() {
        return dimensionCharacteristics.getHullDiameter();
    }

    double getMastLength() {
        return dimensionCharacteristics.getMastLength();
    }

    double getMastDiameter() {
        return dimensionCharacteristics.getMastDiameter();
    }

    double getShieldDiameter() {
        return dimensionCharacteristics.getShieldDiameter();
    }

    double getShieldThickness() {
        return dimensionCharacteristics.getShieldThickness();
    }

    double getDriveDiameter() {
        return dimensionCharacteristics.getDriveDiameter();
    }

    double getTotalShipLength() {
        return dimensionCharacteristics.getTotalShipLength();
    }

    double getDriveOutput() {
        return driveCharacteristics.getDriveOutput_Combat();
    }

    double getDriveFlux() {
        // Different than DriveCharacteristics.getDriveFlux, which seems to be used nowhere.
        return getHullSize() / 50d;
    }

    int getDriveDamage() {
        return driveCharacteristics.getDriveDamage();
    }

    public int getHullArmor() {
        // TODO: Probably move into a 'Systems' object. Exists now only to support
        //       com.senorpez.avt.shipdesigner.characteristics.ShipCharacteristics.getHullArmor
        return 1;
    }

    public int getArmorShrink() {
        // TODO: Probably move into a 'Systems' object. Exists now only to support
        //       com.senorpez.avt.shipdesigner.characteristics.ShipCharacteristics.getArmorShrink
        return 0;
    }

    int getDriveHullDepth() {
        return surfaceCharacteristics.getDriveHullDepth();
    }

    int getMastHullDepth() {
        return surfaceCharacteristics.getMastHullDepth();
    }

    ManeuverMode getPivotMode() {
        return driveCharacteristics.getPivotMode();
    }

    ManeuverMode getRollMode() {
        return driveCharacteristics.getRollMode();
    }

    int getPinwheelBoxes() {
        return Long.valueOf(
                Math.max(
                        1,
                        Math.round((massCharacteristics.getMastShieldSpaces() + massCharacteristics.getDriveSpaces()) / 2)
                )
        ).intValue();
    }

    int getMastTrack() {
        final double sphereFactor = getShape() == Shape.SPHEROID ? 1.5d : 1d;
        return Double.valueOf(
                Math.ceil((((getHullSize() * 25 * getMaximumThrust() / 4) / 70000) * 7.8)
                        * massCharacteristics.getMastLength()
                        * sphereFactor)
        ).intValue();
    }

    int getEngineTrack() {
        // TODO: Move into a 'Systems' object
        return 0;
    }

    int getThrustTrack() {
        // TODO: Move into a 'Systems' object
        return 0;
    }

    int getFuelSpaces() {
        // TODO: Probably move into a 'Systems' object.
        return 10;
    }

    double getFuelDotsPerSpace() {
        return Long.valueOf(Math.round(200d / getHullSize() * getDriveGeneration())).intValue();
    }

    int getFuelDots() {
        return Double.valueOf(
                Math.floor((200d / getHullSize() * getDriveGeneration()) * getFuelSpaces() / (1 - 0.5 * (getFuelSpaces() * 0.9 / getHullSize())))
        ).intValue();
    }

    int getSpacesToDeltaThrust() {
        return Long.valueOf(
                Math.round(getSpacesForThrustChange(1) - getSpacesForThrustChange(0))
        ).intValue();
    }

    List<FuelSpace> getFuelBySpace() {
        int space = 1;
        int previousFuel = 0;
        final double driveGeneration = getDriveGeneration();
        final int hullSize = getHullSize();
        List<FuelSpace> fuelSpaces = new ArrayList<>();
        fuelSpaces.add(new FuelSpace(space, driveGeneration, hullSize, previousFuel));

        while (space <= getFuelSpaces()) {
            space += 1;
            previousFuel = fuelSpaces.stream()
                    .map(FuelSpace::fuelAtSpace)
                    .reduce(0, Integer::sum);
            fuelSpaces.add(new FuelSpace(space, driveGeneration, hullSize, previousFuel));
        }

        return fuelSpaces;
    }

    int getTotalExternalArmor() {
        // TODO: Probably move into a 'Systems' object.
        return 0;
    }

    int getAvailableExternalArmor() {
        return getTotalExternalArmor() - getUsedExternalArmor();
    }

    int getUsedExternalArmor() {
        final double noseSurface = getNoseSurfaceArea() / 10d;
        final double lateralSurface = getLateralSurfaceArea() / 10d;
        final double aftSurface = getAftSurfaceArea() / 10d;

        return Long.valueOf(Math.round(
                noseSurface * getTotalNoseArmor()
                        + aftSurface * getTotalAftArmor()
                        + lateralSurface * getTotalTopArmor()
                        + lateralSurface * getTotalBottomArmor()
                        + lateralSurface * getTotalPortArmor()
                        + lateralSurface * getTotalStarboardArmor())).intValue();
    }

    int getMaximumNoseArmor() {
        // TODO: Probably move into a 'Systems' object.
        return 0;
    }

    int getMaximumLateralArmor() {
        // TODO: Probably move into a 'Systems' object.
        return 0;
    }

    int getMaximumAftArmor() {
        // TODO: Probably move into a 'Systems' object.
        return 0;
    }

    int getTotalNoseArmor() {
        return getNoseArmor().stream().reduce(Integer::sum).orElse(0);
    }

    int getTotalAftArmor() {
        return getAftArmor().stream().reduce(Integer::sum).orElse(0);
    }

    int getTotalTopArmor() {
        return getTopArmor().stream().reduce(Integer::sum).orElse(0);
    }

    int getTotalBottomArmor() {
        return getBottomArmor().stream().reduce(Integer::sum).orElse(0);
    }

    int getTotalPortArmor() {
        return getPortArmor().stream().reduce(Integer::sum).orElse(0);
    }

    int getTotalStarboardArmor() {
        return getStarboardArmor().stream().reduce(Integer::sum).orElse(0);
    }

    private int getGunboatDocks() {
        // TODO: Probably move into a 'Weapons' object. Exists now only to support
        //       this.getGunboatCrew() and this.getExtraBerths()
        return 0;
    }

    private int getLifeSupportSystems() {
        // TODO: Probably move into a 'Systems' object. Exists now only to support
        //       this.getExtraBerths()
        return 0;
    }

    private int getQuartersSystems() {
        // TODO: Probably move into a 'Systems' object. Exists now only to support
        //       this.getExtraBerths()
        return 0;
    }

    private int getEnduranceSystems() {
        // TODO: Probably move into a 'Systems' object. Exists now only to support
        //       this.getCruiseDuration()
        return 0;
    }

    private double getSpacesForThrustChange(final int index) {
        return getHullSize() - (getDriveOutput() * 943.7244398d / getDriveGeneration() / (getMaximumThrust() + (index * 0.5)));
    }

    int getPrimaryMountsAvailable() {
        return mountConfiguration.getPrimaryMountsTotalCount();
    }

    int getPrimaryMountFieldOfFire() {
        return mountConfiguration.getPrimaryMountFieldOfFire(shape);
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

    public Shape getShape() {
        return shape;
    }

    Ship setShape(Shape shape) {
        logger.info(String.format("Setting shape to %s", shape.name()));
        this.shape = shape;
        return this;
    }

    public int getHullSize() {
        return hullSize;
    }

    Ship setHullSize(int hullSize) {
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

    int getLaidDown() {
        return laidDown;
    }

    Ship setLaidDown(int laidDown) {
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

    int getPercentOfficers() {
        return percentOfficers;
    }

    Ship setPercentOfficers(int percentOfficers) {
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

    public double getDriveGeneration() {
        return driveGeneration;
    }

    Ship setDriveGeneration(double driveGeneration) {
        this.driveGeneration = Math.round(driveGeneration * 10d) / 10d;
        return this;
    }

    public double getMaximumThrust() {
        return maximumThrust;
    }

    Ship setMaximumThrust(double maximumThrust) {
        this.maximumThrust = Math.round(maximumThrust * 2d) / 2d;
        return this;
    }

    public int getDriveArmor() {
        return driveArmor;
    }

    Ship setDriveArmor(int driveArmor) {
        this.driveArmor = driveArmor;
        return this;
    }

    public int getMastArmor() {
        return mastArmor;
    }

    Ship setMastArmor(int mastArmor) {
        this.mastArmor = mastArmor;
        return this;
    }

    List<Integer> getNoseArmor() {
        return noseArmor;
    }

    Ship setNoseArmor(List<Integer> noseArmor) {
        this.noseArmor = noseArmor;
        return this;
    }

    List<Integer> getAftArmor() {
        return aftArmor;
    }

    Ship setAftArmor(List<Integer> aftArmor) {
        this.aftArmor = aftArmor;
        return this;
    }

    List<Integer> getTopArmor() {
        return topArmor;
    }

    Ship setTopArmor(List<Integer> topArmor) {
        this.topArmor = topArmor;
        return this;
    }

    List<Integer> getBottomArmor() {
        return bottomArmor;
    }

    Ship setBottomArmor(List<Integer> bottomArmor) {
        this.bottomArmor = bottomArmor;
        return this;
    }

    List<Integer> getPortArmor() {
        return portArmor;
    }

    Ship setPortArmor(List<Integer> portArmor) {
        this.portArmor = portArmor;
        return this;
    }

    List<Integer> getStarboardArmor() {
        return starboardArmor;
    }

    Ship setStarboardArmor(List<Integer> starboardArmor) {
        this.starboardArmor = starboardArmor;
        return this;
    }

    MountConfiguration getMountConfiguration() {
        return mountConfiguration;
    }

    Ship setMountConfiguration(MountConfiguration mountConfiguration) {
        this.mountConfiguration = mountConfiguration;
        return this;
    }
}
