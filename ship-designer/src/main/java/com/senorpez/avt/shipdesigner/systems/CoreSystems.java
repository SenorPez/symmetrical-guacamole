package com.senorpez.avt.shipdesigner.systems;

import com.senorpez.avt.shipdesigner.Ship;

import java.util.List;

import static com.senorpez.avt.shipdesigner.systems.ProductionLevel.STANDARD;

public class CoreSystems implements SystemsSummary {
    private LifeSupport lifeSupport;
    private Bridge bridge;
    private FlagBridge flagBridge;
    private BetaHyperdrive betaHyperdrive;
    private GammaHyperdrive gammaHyperdrive;
    private DeltaHyperdrive deltaHyperdrive;
    private EpsilonHyperdrive epsilonHyperdrive;
    private ZetaHyperdrive zetaHyperdrive;
    private ECM ecm;
    private ECCM eccm;
    private HIRTS hirts;

    final private List<System> systems;

    CoreSystems(final Ship ship) {
        this(new LifeSupport(ship, 1, 0, 0, STANDARD),
                new Bridge(ship, 1, 0, 0, STANDARD),
                new FlagBridge(ship, 0, 0, 0, STANDARD),
                new BetaHyperdrive(ship, 0, 0, 0, STANDARD),
                new GammaHyperdrive(ship, 0, 0, 0, STANDARD),
                new DeltaHyperdrive(ship, 0, 0, 0, STANDARD),
                new EpsilonHyperdrive(ship, 0, 0, 0, STANDARD),
                new ZetaHyperdrive(ship, 0, 0, 0, STANDARD),
                new ECM(ship, 0, 0, STANDARD),
                new ECCM(ship, 0, 0, STANDARD),
                new HIRTS(ship, 0, STANDARD));
    }

    public CoreSystems(final LifeSupport lifeSupport,
                final Bridge bridge,
                final FlagBridge flagBridge,
                final BetaHyperdrive betaHyperdrive,
                final GammaHyperdrive gammaHyperdrive,
                final DeltaHyperdrive deltaHyperdrive,
                final EpsilonHyperdrive epsilonHyperdrive,
                final ZetaHyperdrive zetaHyperdrive,
                final ECM ecm,
                final ECCM eccm,
                final HIRTS hirts) {
        this.lifeSupport = lifeSupport;
        this.bridge = bridge;
        this.flagBridge = flagBridge;
        this.betaHyperdrive = betaHyperdrive;
        this.gammaHyperdrive = gammaHyperdrive;
        this.deltaHyperdrive = deltaHyperdrive;
        this.epsilonHyperdrive = epsilonHyperdrive;
        this.zetaHyperdrive = zetaHyperdrive;
        this.ecm = ecm;
        this.eccm = eccm;
        this.hirts = hirts;

        this.systems = List.of(
                this.lifeSupport,
                this.bridge,
                this.flagBridge,
                this.betaHyperdrive,
                this.gammaHyperdrive,
                this.deltaHyperdrive,
                this.epsilonHyperdrive,
                this.zetaHyperdrive,
                this.ecm,
                this.eccm,
                this.hirts
        );
    }

    LifeSupport getLifeSupport() {
        return lifeSupport;
    }

    void setLifeSupport(final LifeSupport lifeSupport) {
        this.lifeSupport = lifeSupport;
    }

    Bridge getBridge() {
        return bridge;
    }

    void setBridge(final Bridge bridge) {
        this.bridge = bridge;
    }

    FlagBridge getFlagBridge() {
        return flagBridge;
    }

    void setFlagBridge(final FlagBridge flagBridge) {
        this.flagBridge = flagBridge;
    }

    BetaHyperdrive getBetaHyperdrive() {
        return betaHyperdrive;
    }

    void setBetaHyperdrive(final BetaHyperdrive betaHyperdrive) {
        this.betaHyperdrive = betaHyperdrive;
    }

    GammaHyperdrive getGammaHyperdrive() {
        return gammaHyperdrive;
    }

    void setGammaHyperdrive(final GammaHyperdrive gammaHyperdrive) {
        this.gammaHyperdrive = gammaHyperdrive;
    }

    DeltaHyperdrive getDeltaHyperdrive() {
        return deltaHyperdrive;
    }

    void setDeltaHyperdrive(final DeltaHyperdrive deltaHyperdrive) {
        this.deltaHyperdrive = deltaHyperdrive;
    }

    EpsilonHyperdrive getEpsilonHyperdrive() {
        return epsilonHyperdrive;
    }

    void setEpsilonHyperdrive(final EpsilonHyperdrive epsilonHyperdrive) {
        this.epsilonHyperdrive = epsilonHyperdrive;
    }

    ZetaHyperdrive getZetaHyperdrive() {
        return zetaHyperdrive;
    }

    void setZetaHyperdrive(final ZetaHyperdrive zetaHyperdrive) {
        this.zetaHyperdrive = zetaHyperdrive;
    }

    ECM getECM() {
        return ecm;
    }

    void setECM(final ECM ecm) {
        this.ecm = ecm;
    }

    ECCM getECCM() {
        return eccm;
    }

    void setECCM(final ECCM eccm) {
        this.eccm = eccm;
    }

    HIRTS getHIRTS() {
        return hirts;
    }

    void setHIRTS(final HIRTS hirts) {
        this.hirts = hirts;
    }

    public List<System> getSystems() {
        return systems;
    }
}
