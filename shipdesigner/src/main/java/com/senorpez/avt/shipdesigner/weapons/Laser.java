package com.senorpez.avt.shipdesigner.weapons;

import com.senorpez.avt.shipdesigner.systems.ProductionLevel;

import java.util.List;
import java.util.Optional;

abstract class Laser extends Weapon {
    private int armorPiercing;
    private Integer durabilityEnhancement;
    private int heatExchangers;
    private int flashCoolers;

    // TODO: This is used in multiple places
    private final static List<Double> enhancementTable = List.of(1d, 1.1d, 1.25d, 1.475d, 1.775d, 2.15d, 2.6d, 3.125d, 3.725d, 4.4d, 5.15d, 5.975d, 6.875d, 7.85d, 8.9d, 10.025d, 11.225d, 12.5d, 13.85d, 15.275, 16.775d, 18.35);

    Laser(final Mount mount,
          final int armorPiercing,
          final int shrinkEnhancement,
          final Integer durabilityEnhancement,
          final ProductionLevel productionLevel,
          final int heatExchangers,
          final int flashCoolers) {
        super(mount, shrinkEnhancement, productionLevel);
        this.armorPiercing = armorPiercing;
        this.durabilityEnhancement = durabilityEnhancement; // Set to null if "Fragile"
        this.heatExchangers = heatExchangers;
        this.flashCoolers = flashCoolers;
    }

    @Override
    int getDuelCost() {
        final int enhancements = getArmorPiercing() + getShrinkEnhancement() + Optional.ofNullable(durabilityEnhancement).orElse(0);
        final double enhancementCost = enhancementTable.get(enhancements);
        final int baseCost = getBaseCost();
        final double fragileDiscount = durabilityEnhancement == null ? 0.1 : 1;
        final double fieldOfFireCost = 0.9 + getMount().getFiringArc().size() / 10d;
        return Double.valueOf(Math.ceil(enhancementCost * baseCost * fragileDiscount * fieldOfFireCost)).intValue();
    }

    int getRecycleTime() {
        List<Integer> heatExchangerData = List.of(600, 797, 960, 1093, 1200, 1286, 1355, 1411, 1458, 1501);
        final int recycleTimeDivisor = getHeatExchangers() <= 9 ? heatExchangerData.get(getHeatExchangers())
                : 1501 + ((getHeatExchangers() - 9) * 25);
        return Double.valueOf(Math.ceil(getWasteHeat() / recycleTimeDivisor)).intValue();
    }

    @Override
    int getBaseCost() {
        return 10 * (getHeatExchangers() + getFlashCoolers()) + getModelCost();
    }

    abstract int getModelCost();

    abstract double getWasteHeat();
    
    abstract double getPower();

    // GETTERS & SETTERS
    int getArmorPiercing() {
        return armorPiercing;
    }

    Laser setArmorPiercing(int armorPiercing) {
        this.armorPiercing = armorPiercing;
        return this;
    }

    Integer getDurabilityEnhancement() {
        return durabilityEnhancement;
    }

    Laser setDurabilityEnhancement(Integer durabilityEnhancement) {
        this.durabilityEnhancement = durabilityEnhancement;
        return this;
    }

    int getHeatExchangers() {
        return heatExchangers;
    }

    Laser setHeatExchangers(int heatExchangers) {
        this.heatExchangers = heatExchangers;
        return this;
    }

    int getFlashCoolers() {
        return flashCoolers;
    }

    Laser setFlashCoolers(int flashCoolers) {
        this.flashCoolers = flashCoolers;
        return this;
    }
}
