package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

import java.util.List;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;

public class StructuralSystems {
    private Hull hull;
    private Drive drive;
    private FrameReinforcement frameReinforcement;
    private DriveAndMastArmor driveAndMastArmor;
    private ExternalArmor externalArmor;
    private InternalArmor internalArmor;
    private Accessways accessways;

    final private List<System> systems;

    StructuralSystems(final Ship ship) {
        final ArmorShrink armorShrink = new ArmorShrink(0);
        final ArmorProductionLevel armorProductionLevel = new ArmorProductionLevel(ProductionLevel.STANDARD);

        this.hull = new Hull(ship, 0, ProductionLevel.STANDARD);
        this.drive = new Drive(ship, 0, ProductionLevel.STANDARD, 0);
        this.frameReinforcement = new FrameReinforcement(ship, 0, ProductionLevel.STANDARD);
        this.driveAndMastArmor = new DriveAndMastArmor(ship, 0, 0, armorShrink, armorProductionLevel);
        this.externalArmor = new ExternalArmor(ship, 0, armorShrink, armorProductionLevel);
        this.internalArmor = new InternalArmor(ship, 0, armorShrink, armorProductionLevel);
        this.accessways = new Accessways(ship, false);

        this.systems = List.of(
                this.hull,
                this.drive,
                this.frameReinforcement,
                this.driveAndMastArmor,
                this.externalArmor,
                this.internalArmor,
                this.accessways
        );
    }

    public StructuralSystems(final Hull hull,
                             final Drive drive,
                             final FrameReinforcement frameReinforcement,
                             final DriveAndMastArmor driveAndMastArmor,
                             final ExternalArmor externalArmor,
                             final InternalArmor internalArmor,
                             final Accessways accessways) {
        this.hull = hull;
        this.drive = drive;
        this.frameReinforcement = frameReinforcement;
        this.driveAndMastArmor = driveAndMastArmor;
        this.externalArmor = externalArmor;
        this.internalArmor = internalArmor;
        this.accessways = accessways;

        this.systems = List.of(
                this.hull,
                this.drive,
                this.frameReinforcement,
                this.driveAndMastArmor,
                this.externalArmor,
                this.internalArmor,
                this.accessways
        );
    }

    int getBasicSpacesUsed() {
        return systemsSum(System::getBasicSpacesUsed);
    }

    int getActualSpacesUsed() {
        return systemsSum(System::getActualSpacesUsed);
    }

    int getBaseCost() {
        return systemsSum(System::getBaseCost);
    }

    int getEnhancedCost() {
        return systemsSum(System::getEnhancedCost);
    }

    int getArmorPointsUsed() {
        return 0;
    }

    int getDuelCost() {
        return systemsSum(System::getDuelCost);
    }

    int getEconomicCost() {
        return systemsSum(System::getEconomicCost);
    }

    double getMaintenanceCost() {
        return systemsSum(System::getMaintenanceCost);
    }

    private int systemsSum(final ToIntFunction<System> f) {
        return getSystems().stream().mapToInt(f).sum();
    }

    private double systemsSum(final ToDoubleFunction<System> f) {
        return getSystems().stream().mapToDouble(f).sum();
    }

    Hull getHull() {
        return hull;
    }

    void setHull(final Hull hull) {
        this.hull = hull;
    }

    Drive getDrive() {
        return drive;
    }

    void setDrive(final Drive drive) {
        this.drive = drive;
    }

    FrameReinforcement getFrameReinforcement() {
        return frameReinforcement;
    }

    void setFrameReinforcement(final FrameReinforcement frameReinforcement) {
        this.frameReinforcement = frameReinforcement;
    }

    DriveAndMastArmor getDriveAndMastArmor() {
        return driveAndMastArmor;
    }

    void setDriveAndMastArmor(final DriveAndMastArmor driveAndMastArmor) {
        this.driveAndMastArmor = driveAndMastArmor;
    }

    ExternalArmor getExternalArmor() {
        return externalArmor;
    }

    void setExternalArmor(final ExternalArmor externalArmor) {
        this.externalArmor = externalArmor;
    }

    InternalArmor getInternalArmor() {
        return internalArmor;
    }

    void setInternalArmor(final InternalArmor internalArmor) {
        this.internalArmor = internalArmor;
    }

    Accessways getAccessways() {
        return accessways;
    }

    void setAccessways(final Accessways accessways) {
        this.accessways = accessways;
    }

    private List<System> getSystems() {
        return systems;
    }
}
