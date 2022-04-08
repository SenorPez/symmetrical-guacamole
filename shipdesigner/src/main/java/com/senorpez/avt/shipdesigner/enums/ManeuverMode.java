package com.senorpez.avt.shipdesigner.enums;

import java.util.Comparator;
import java.util.stream.Stream;

public enum ManeuverMode {
    Z(0.00048828d),
    Y(0.00097656d),
    X(0.00195313d),
    W(0.00390625d),
    V(0.0078125d),
    U(0.015625d),
    T(0.03125d),
    S(0.0625d),
    R(0.125d),
    Q(0.25d),
    P(0.5d),
    O(1d),
    N(2d),
    M(3d),
    L(4d),
    K(5d),
    J(6d),
    I(8d),
    H(12d),
    G(16d),
    F(20d),
    E(24d),
    D(32d),
    C(48d),
    B(64d),
    A(96d),
    AA(128d),
    AAA(192d),
    AAAA(256d);

    private final double maneuverAccel;

    ManeuverMode(double maneuverAccel) {
        this.maneuverAccel = maneuverAccel;
    }

    public double getManeuverAccel() {
        return maneuverAccel;
    }

    public static ManeuverMode getMode(double maneuverAccel) {
        // TODO: This is probably incorrect due to how VLOOKUP in Excel works.
        return Stream
                .of(ManeuverMode.values())
                .min(Comparator.comparingDouble(m -> Math.abs(m.getManeuverAccel() - maneuverAccel)))
                .orElse(AAAA);
    }
}
