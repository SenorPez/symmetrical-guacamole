package com.senorpez.avt.shipdesigner;

import com.senorpez.avt.shipdesigner.systems.*;

import java.util.Optional;

public class Ship {
    private HullShape hullShape = null;
    private Integer hullSpaces = null;
    private Double driveGeneration = null;
    private Double thrust = null;

    // Combined Shrink and Production Level objects
    private ArmorShrink armorShrink = null;
    private ArmorProductionLevel armorProductionLevel = null;

    // Structural Systems
    private StructuralSystems structuralSystems = null;
    private Hull hull = null;
    private Drive drive = null;
    private FrameReinforcement frameReinforcement = null;
    private DriveAndMastArmor driveAndMastArmor = null;
    private ExternalArmor externalArmor = null;
    private InternalArmor internalArmor = null;
    private Accessways accessways = null;

    // Core Systems
    private CoreSystems coreSystems = null;
    private LifeSupport lifeSupport = null;
    private Bridge bridge = null;
    private FlagBridge flagBridge = null;
    private BetaHyperdrive betaHyperdrive = null;
    private GammaHyperdrive gammaHyperdrive = null;
    private DeltaHyperdrive deltaHyperdrive = null;
    private EpsilonHyperdrive epsilonHyperdrive = null;
    private ZetaHyperdrive zetaHyperdrive = null;
    private ECM ecm = null;
    private ECCM eccm = null;
    private HIRTS hirts = null;

    // Constants
    private static final int DRIVE_MASS_POWER = 1;
    private static final int PIVOT_ACCEL_POWER = 1;
    private static final double PIVOT_SCALING_FACTOR = 14.1522458529503d;
    private static final double RAD_REDUCTION = 0.630000d;
    private static final int RADIANT_DEFLECTION = 96; // Hand-waved "lensing" to reduce heat load.

    double pivotThrustOverride = 0; // TODO: Create Setter?

    // Calculated Value
    private double mastLength;

    // Build Sequence
    Ship() {
    }

    Ship setHullShape(final HullShape hullShape) {
        this.hullShape = hullShape;
        return this;
    }

    Ship setHullSpaces(final Integer hullSpaces) {
        this.hullSpaces = hullSpaces;
        return this;
    }

    Ship setDriveGeneration(final double driveGeneration) {
        this.driveGeneration = driveGeneration;
        return this;
    }

    Ship setThrust(final double thrust) {
        this.thrust = thrust;
        return this;
    }

    Ship setArmorShrink(final ArmorShrink armorShrink) {
        this.armorShrink = armorShrink;
        return this;
    }

    Ship setArmorShrink(final int armorShrink) {
        this.armorShrink = new ArmorShrink(0);
        return this;
    }

    Ship setArmorProductionLevel(final ArmorProductionLevel armorProductionLevel) {
        this.armorProductionLevel = armorProductionLevel;
        return this;
    }

    Ship setArmorProductionLevel(final ProductionLevel productionLevel) {
        this.armorProductionLevel = new ArmorProductionLevel(productionLevel);
        return this;
    }

    Ship setHull(final Hull hull) {
        this.hull = hull;
        return this;
    }

    Ship setHull(final int shrink, final ProductionLevel productionLevel) {
        this.hull = new Hull(this, shrink, productionLevel);
        return this;
    }

    Ship setDrive(final Drive drive) {
        this.drive = drive;
        return this;
    }

    Ship setDrive(final int extraDriveStructure, final int shrink, final ProductionLevel productionLevel) {
        this.drive = new Drive(this, shrink, productionLevel, extraDriveStructure);
        return this;
    }

    Ship setFrameReinforcement(final FrameReinforcement frameReinforcement) {
        this.frameReinforcement = frameReinforcement;
        return this;
    }

    Ship setFrameReinforcement(final int quantity, final ProductionLevel productionLevel) {
        this.frameReinforcement = new FrameReinforcement(this, quantity, productionLevel);
        return this;
    }

    Ship setDriveAndMastArmor(final DriveAndMastArmor driveAndMastArmor) {
        this.driveAndMastArmor = driveAndMastArmor;
        return this;
    }

    Ship setDriveAndMastArmor(final int driveArmor, final int mastArmor) {
        ArmorShrink armorShrink = Optional.ofNullable(this.armorShrink).orElse(new ArmorShrink(0));
        ArmorProductionLevel armorProductionLevel = Optional.ofNullable(this.armorProductionLevel).orElse(new ArmorProductionLevel(ProductionLevel.STANDARD));
        this.driveAndMastArmor = new DriveAndMastArmor(this, driveArmor, mastArmor, armorShrink, armorProductionLevel);
        return this;
    }

    Ship setExternalArmor(final ExternalArmor externalArmor) {
        this.externalArmor = externalArmor;
        return this;
    }

    Ship setExternalArmor(final int externalArmor) {
        ArmorShrink armorShrink = Optional.ofNullable(this.armorShrink).orElse(new ArmorShrink(0));
        ArmorProductionLevel armorProductionLevel = Optional.ofNullable(this.armorProductionLevel).orElse(new ArmorProductionLevel(ProductionLevel.STANDARD));
        this.externalArmor = new ExternalArmor(this, externalArmor, armorShrink, armorProductionLevel);
        return this;
    }

    Ship setInternalArmor(final InternalArmor internalArmor) {
        this.internalArmor = internalArmor;
        return this;
    }

    Ship setInternalArmor(final int internalArmor) {
        ArmorShrink armorShrink = Optional.ofNullable(this.armorShrink).orElse(new ArmorShrink(0));
        ArmorProductionLevel armorProductionLevel = Optional.ofNullable(this.armorProductionLevel).orElse(new ArmorProductionLevel(ProductionLevel.STANDARD));
        this.internalArmor = new InternalArmor(this, internalArmor, armorShrink, armorProductionLevel);
        return this;
    }

    Ship setAccessways(final Accessways accessways) {
        this.accessways = accessways;
        return this;
    }

    Ship setAccessways(final boolean improvedAccessways) {
        this.accessways = new Accessways(this, improvedAccessways);
        return this;
    }

    Ship setLifeSupport(final LifeSupport lifeSupport) {
        this.lifeSupport = lifeSupport;
        return this;
    }

    Ship setLifeSupport(final int quantity, final int shrink, final int armorLevel, final ProductionLevel productionLevel) {
        this.lifeSupport = new LifeSupport(this, quantity, shrink, armorLevel, productionLevel);
        return this;
    }

    Ship setBridge(final Bridge bridge) {
        this.bridge = bridge;
        return this;
    }

    Ship setBridge(final int quantity, final int shrink, final int armorLevel, final ProductionLevel productionLevel) {
        this.bridge = new Bridge(this, quantity, shrink, armorLevel, productionLevel);
        return this;
    }

    Ship setFlagBridge(final FlagBridge flagBridge) {
        this.flagBridge = flagBridge;
        return this;
    }

    Ship setFlagBridge(final int quantity, final int shrink, final int armorLevel, final ProductionLevel productionLevel) {
        this.flagBridge = new FlagBridge(this, quantity, shrink, armorLevel, productionLevel);
        return this;
    }

    Ship setBetaHyperdrive(final BetaHyperdrive betaHyperdrive) {
        this.betaHyperdrive = betaHyperdrive;
        return this;
    }

    Ship setBetaHyperdrive(final int quantity, final int shrink, final int armorLevel, final ProductionLevel productionLevel) {
        this.betaHyperdrive = new BetaHyperdrive(this, quantity, shrink, armorLevel, productionLevel);
        return this;
    }

    Ship setGammaHyperdrive(final GammaHyperdrive gammaHyperdrive) {
        this.gammaHyperdrive = gammaHyperdrive;
        return this;
    }

    Ship setGammaHyperdrive(final int quantity, final int shrink, final int armorLevel, final ProductionLevel productionLevel) {
        this.gammaHyperdrive = new GammaHyperdrive(this, quantity, shrink, armorLevel, productionLevel);
        return this;
    }

    Ship setDeltaHyperdrive(final DeltaHyperdrive deltaHyperdrive) {
        this.deltaHyperdrive = deltaHyperdrive;
        return this;
    }

    Ship setDeltaHyperdrive(final int quantity, final int shrink, final int armorLevel, final ProductionLevel productionLevel) {
        this.deltaHyperdrive = new DeltaHyperdrive(this, quantity, shrink, armorLevel, productionLevel);
        return this;
    }

    Ship setEpsilonHyperdrive(final EpsilonHyperdrive epsilonHyperdrive) {
        this.epsilonHyperdrive = epsilonHyperdrive;
        return this;
    }

    Ship setEpsilonHyperdrive(final int quantity, final int shrink, final int armorLevel, final ProductionLevel productionLevel) {
        this.epsilonHyperdrive = new EpsilonHyperdrive(this, quantity, shrink, armorLevel, productionLevel);
        return this;
    }

    Ship setZetaHyperdrive(final ZetaHyperdrive zetaHyperdrive) {
        this.zetaHyperdrive = zetaHyperdrive;
        return this;
    }

    Ship setZetaHyperdrive(final int quantity, final int shrink, final int armorLevel, final ProductionLevel productionLevel) {
        this.zetaHyperdrive = new ZetaHyperdrive(this, quantity, shrink, armorLevel, productionLevel);
        return this;
    }

    Ship setECM(final ECM ecm) {
        this.ecm = ecm;
        return this;
    }

    Ship setECM(final int quantity, final int shrink, final ProductionLevel productionLevel) {
        this.ecm = new ECM(this, quantity, shrink, productionLevel);
        return this;
    }

    Ship setECCM(final ECCM eccm) {
        this.eccm = eccm;
        return this;
    }

    Ship setECCM(final int quantity, final int shrink, final ProductionLevel productionLevel) {
        this.eccm = new ECCM(this, quantity, shrink, productionLevel);
        return this;
    }

    Ship setHIRTS(final HIRTS hirts) {
        this.hirts = hirts;
        return this;
    }

    Ship setHIRTS(final int shrink, final ProductionLevel productionLevel) {
        this.hirts = new HIRTS(this, shrink, productionLevel);
        return this;
    }

    public Ship build() {
        this.hullShape = Optional.ofNullable(hullShape).orElse(HullShape.SPHERE);
        this.hullSpaces = Optional.ofNullable(hullSpaces).orElse(25);
        this.driveGeneration = Optional.ofNullable(driveGeneration).orElse(1.0d);
        this.thrust = Optional.ofNullable(thrust).orElse(0.5d);

        this.armorShrink = Optional.ofNullable(armorShrink).orElse(new ArmorShrink(0));
        this.armorProductionLevel = Optional.ofNullable(armorProductionLevel).orElse(new ArmorProductionLevel(ProductionLevel.STANDARD));

        this.hull = Optional.ofNullable(hull).orElse(new Hull(this, 0, ProductionLevel.STANDARD));
        this.drive = Optional.ofNullable(drive).orElse(new Drive(this, 0, ProductionLevel.STANDARD, 0));
        this.frameReinforcement = Optional.ofNullable(frameReinforcement).orElse(new FrameReinforcement(this, 0, ProductionLevel.STANDARD));
        this.driveAndMastArmor = Optional.ofNullable(driveAndMastArmor).orElse(new DriveAndMastArmor(this, 0, 0, armorShrink, armorProductionLevel));
        this.externalArmor = Optional.ofNullable(externalArmor).orElse(new ExternalArmor(this, 0, armorShrink, armorProductionLevel));
        this.internalArmor = Optional.ofNullable(internalArmor).orElse(new InternalArmor(this, 0, armorShrink, armorProductionLevel));
        this.accessways = Optional.ofNullable(accessways).orElse(new Accessways(this, false));

        this.mastLength = calculateMastLength();

        this.structuralSystems = new StructuralSystems(
                hull,
                drive,
                frameReinforcement,
                driveAndMastArmor,
                externalArmor,
                internalArmor,
                accessways
        );

        return this;
    }

    public HullShape getHullShape() {
        return hullShape;
    }

    public int getHullSpaces() {
        return hullSpaces;
    }

    public double getDriveGeneration() {
        return driveGeneration;
    }

    public double getThrust() {
        return thrust;
    }

    // Derived values
    int getMass() {
        return getHullSpaces() * 25;
    }

    double getAcceleration() {
        return getThrust() / 4d;
    }

    // Object Getters
    private ArmorShrink getArmorShrinkObject() {
        return armorShrink;
    }

    int getArmorShrink() {
        return getArmorShrinkObject().getShrink();
    }

    private DriveAndMastArmor getDriveAndMastArmor() {
        return driveAndMastArmor;
    }

    int getDriveArmor() {
        return getDriveAndMastArmor().getDriveArmor();
    }

    int getMastArmor() {
        return getDriveAndMastArmor().getMastArmor();
    }

    private ExternalArmor getExternalArmorObject() {
        return externalArmor;
    }

    int getExternalArmor() {
        return getExternalArmorObject().getQuantity();
    }

    private InternalArmor getInternalArmorObject() {
        return internalArmor;
    }

    int getInternalArmor() {
        return getInternalArmorObject().getQuantity();
    }

    // This weird thing I've not decided what to do with yet.
    private double getPivotThrustOverride() {
        return pivotThrustOverride;
    }

    // Calculated values: Lantern
    public double getLanternDiameter() {
        return Math.sqrt(getMass() * getAcceleration() / 125) * 20 / Math.sqrt(100 / (100d - RADIANT_DEFLECTION));
    }

    double getLanternMass() {
        return getLanternStructureMass() + getLanternArmorMass();
    }

    double getLanternStructureMass() {
        double driveOutput = getDriveOutput();
        if (driveOutput > 4) {
            return 400 * Math.pow(driveOutput / 4d, 1.5);
        } else if (driveOutput < 1) {
            return 100 * Math.pow(driveOutput, 0.5);
        } else {
            return driveOutput * 100;
        }
    }

    double getLanternArmorMass() {
        return getDriveArmor() * 50 * getLanternCoverageSurface() / (1000 + 50 * getArmorShrink());
    }

    double getLanternCoverageSurface() {
        return 4 * Math.PI * Math.pow(getLanternDiameter() / 2, 2) / 2;
    }

    // Calculated values: Shield
    public double getShieldMinDiameter() {
        return getShieldMinDiameter(getMastLength(), getUsableFraction(getMastLength()));
    }

    public double getShieldMaxDiameter() {
        return getShieldMaxDiameter(getMastLength(), getUsableFraction(getMastLength()));
    }

    public double getShieldThickness() {
        return getShieldThickness(getMastLength(), getUsableFraction(getMastLength()));
    }

    double getShieldMinDiameter(final double mastLength, final double usableFraction) {
        return getHullShape().getShieldMinDiameter(getHullSpaces(),
                usableFraction,
                getLanternDiameter(),
                mastLength);
    }

    double getShieldMaxDiameter(final double mastLength, final double usableFraction) {
        return getHullShape().getShieldMaxDiameter(getHullSpaces(),
                usableFraction,
                getLanternDiameter(),
                mastLength,
                getShieldThickness(mastLength, usableFraction));
    }

    double getShieldThickness(final double mastLength, final double usableFraction) {
        return getShieldMass(mastLength, usableFraction) / getShieldCrossSection(mastLength, usableFraction) * 0.5;
    }

    double getShieldMass(final double mastLength, final double usableFraction) {
        return (RAD_REDUCTION * (Math.log10(getNeutronFluxAtShield()) + 6) - Math.log10(getRadReductionDueToMast(mastLength))) * getShieldCrossSection(mastLength, usableFraction);
    }

    double getNeutronFluxAtShield() {
        final double flux_kr_per_hour = (getDriveOutput() * 500000) / Math.pow(getLanternDiameter() / 2, 2);
        return flux_kr_per_hour * 24 * 365.25 / 1000;
    }

    double getRadReductionDueToMast(final double mastLength) {
        return Math.pow((mastLength + getLanternDiameter() / 2) / (getLanternDiameter() / 2), 2);
    }

    double getShieldCrossSection(final double mastLength, final double usableFraction) {
        return 0.25 * Math.PI * Math.pow(getShieldMinDiameter(mastLength, usableFraction), 2);
    }

    // Calculated values: Mast
    public double getMastDiameter() {
        return getMastLength() / 15;
    }

    public double getMastLength() {
        return mastLength;
    }

    double getMastMass(final double mastLength, final double usableFraction) {
        return getMastStructureMass(mastLength) + getMastArmorMass(mastLength) + getShieldMass(mastLength, usableFraction);
    }

    double getMastStructureMass(final double mastLength) {
        return getMass() * getAcceleration() / 70000 * 7.8 * mastLength * getMastMassModifier();
    }

    double getMastMassModifier() {
        return getHullShape().getMastMassModifier();
    }

    double getMastArmorMass(final double mastLength) {
        return ((1 / 15d) * Math.PI * Math.pow(mastLength, 2)) * getMastArmor() * 50 / (1000 + 50 * getArmorShrink());
    }

    // Calculated values: Drive
    public double getDriveSpacesWithoutArmor() {
        return getDriveSpacesWithoutArmor(mastLength);
    }

    double getDriveSpacesWithoutArmor(final double mastLength) {
        return getDriveMassWithoutArmor(mastLength) / 25d;
    }

    double getDriveMass(final double mastLength, final double usableFraction) {
        return getMastMass(mastLength, usableFraction) + getLanternMass();
    }

    double getDriveOutput() { // TODO: Add validation
        return 0.5d * getMass() * 1000 * getAcceleration() * 9.765625d * getDriveGeneration() * 34722 / Math.pow(10, 12);
    }

    double getDriveFraction(final double mastLength) {
        double driveFraction = 0d;
        double actualDriveFraction = 0.14d;
        double driveMass = 0d;

        while (Math.abs(driveFraction - actualDriveFraction) > 1e-9) {
            driveFraction = actualDriveFraction;
            final double usableHullFraction = 1 - driveFraction - getArmorFraction();
            driveMass = getDriveMass(mastLength, usableHullFraction);
            actualDriveFraction = driveMass / getMass();
        }

        return driveMass / getMass();
    }

    double getDriveMassWithoutArmor(final double mastLength) {
        return getMastStructureMass(mastLength) + getShieldMass(mastLength, getUsableFraction(mastLength)) + getLanternStructureMass();
    }

    // Calculated values: Hull
    public double getHullDiameter() {
        return getHullDiameter(mastLength);
    }

    private double getHullDiameter(final double mastLength) {
        return getHullShape().getHullDiameter(getHullSpaces(), 1 - getArmorFraction() - getDriveFraction(mastLength));
    }

    double getUsableFraction(final double mastLength) {
        return 1 - getArmorFraction() - getDriveFraction(mastLength);
    }

    double getHullLength(final double mastLength) {
        return getHullShape().getHullLength(getHullSpaces(), 1 - getArmorFraction() - getDriveFraction(mastLength));
    }

    double getArmorFraction() {
        return getHullArmor() / (double) getHullSpaces();
    }

    int getHullArmor() {
        return getExternalArmor() + getInternalArmor();
    }

    // Mast length calculation
    private double calculateMastLength() {
        double mastLength;
        if (getHullSpaces() < 100) mastLength = 25d;
        else if (getHullSpaces() < 400) mastLength = 50d;
        else if (getHullSpaces() < 1000) mastLength = 75d;
        else mastLength = 100d;

        double diff = getDifference(mastLength);
        double step = 1;
        final double target = 0.001d;
        while (Math.abs(diff) > target) {
            if (diff > 0) {
                step *= -1;
                while (diff > 0 && Math.abs(diff) > target) {
                    mastLength += step;
                    diff = getDifference(mastLength);
                }
                step *= -1;
            } else {
                while (diff < 0 && Math.abs(diff) > target) {
                    mastLength += step;
                    diff = getDifference(mastLength);
                }
            }
            step /= 10;
        }
        return mastLength;
    }

    private double getDifference(final double mastLength) {
        return 1000000 * getFigureOfMerit(mastLength, getUsableFraction(mastLength)) - 1000000 * getFigureOfMerit(mastLength + 0.1, getUsableFraction(mastLength));
    }

    private double getFigureOfMerit(final double mastLength, final double usableFraction) {
        return Math.pow(getPivotAcceleration(mastLength, usableFraction), PIVOT_ACCEL_POWER) / Math.pow(getDriveMass(mastLength, usableFraction), DRIVE_MASS_POWER);
    }

    double getPivotAcceleration(final double mastLength, final double usableFraction) {
        return (getPivotThrust() * 1000) * ((1 - getDriveFraction(mastLength)) * (mastLength + getHullLength(mastLength) / 2) - (getDriveFraction(mastLength)) * (getLanternDiameter() / 2)) / (getMomentOfInertia(mastLength, usableFraction) * 1000) * ((3 / Math.PI) * 128 * 16);
    }

    double getPivotThrust() {
        return getPivotThrustOverride() != 0 ? getPivotThrustOverride()
                : PIVOT_SCALING_FACTOR * Math.pow(getMass(), 1 / 3d) * getPivotModifier();
    }

    double getPivotModifier() {
        return getHullShape().getPivotModifier();
    }

    double getMomentOfInertia(final double mastLength, final double usableFraction) {
        return getHullShape().getMomentOfInertia(getHullSpaces(),
                usableFraction,
                getDriveFraction(mastLength),
                getLanternMass(),
                getLanternDiameter(),
                mastLength,
                getMastStructureMass(mastLength),
                getMastArmorMass(mastLength),
                getMastMass(mastLength, usableFraction));
    }

    public int getMinimumCrew() {
        // TODO: Calculate once all systems are in place.
        return 0;
    }

    public double getNonSupplyCrew() {
        // TODO: Calculate once all systems are in place.
        return 0;
    }
}
