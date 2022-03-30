package com.senorpez.avt.shipdesigner.systems.internal;

import com.senorpez.avt.shipdesigner.Ship;
import com.senorpez.avt.shipdesigner.systems.ArmoredSystem;
import com.senorpez.avt.shipdesigner.systems.ProductionLevel;
import com.senorpez.avt.shipdesigner.systems.structural.Drive;

public class MHDCaptureSystem extends ArmoredSystem {
    private ReactorArmor reactorArmor;

    private final static String name = "MHD Capture System";
    private final static double costPerSpace = 10d;
    private final static double crewRequiredPerSpace = 0.5d;
    private final static double maintenanceRate = 0.2d;

    MHDCaptureSystem(Ship ship,
                     Drive drive,
                     boolean hasMHDCaptureSystem,
                     int shrinkEnhancement,
                     ProductionLevel productionLevel,
                     ReactorArmor reactorArmor) {
        super(ship,
                name,
                Double.valueOf(Math.ceil(drive.getTotalDriveSpaces() / 10d + 10)).intValue(),
                costPerSpace,
                crewRequiredPerSpace,
                maintenanceRate,
                hasMHDCaptureSystem ? 1 : 0,
                shrinkEnhancement,
                productionLevel,
                reactorArmor.getArmorLevel());
        this.reactorArmor = reactorArmor;
    }

    @Override
    public int getArmorLevel() {
        return reactorArmor.getArmorLevel();
    }

    @Override
    public double getArmorPointsUsed() {
        return reactorArmor.getArmorPointsUsed();
    }
}
