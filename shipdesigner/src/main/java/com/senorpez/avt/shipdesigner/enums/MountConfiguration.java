package com.senorpez.avt.shipdesigner.enums;

import static com.senorpez.avt.shipdesigner.enums.Shape.*;

public enum MountConfiguration {
    KEEL("Keel", 1) {
        @Override
        int getPrimaryMountTotalSpaces(final Shape shape, final int hullSpaces, final int year) {
            if (shape.equals(CYLINDER)) return getPrimaryMountTotalSpaces(0.38d, shape, hullSpaces, year);
            if (shape.equals(LONG_CYLINDER)) return getPrimaryMountTotalSpaces(0.38d, shape, hullSpaces, year);
            if (shape.equals(CONICAL)) return getPrimaryMountTotalSpaces(0.36d, shape, hullSpaces, year);
            return 0;
        }

        @Override
        int getPrimaryMountBiggestWeaponSpaces(Shape shape, int hullSpaces, int year) {
            final int primaryMountTotalSpaces = getPrimaryMountTotalSpaces(shape, hullSpaces, year);
            return Math.max(
                    Math.min(15, Double.valueOf(Math.round(getKeelWeaponPercentage(hullSpaces) * primaryMountTotalSpaces)).intValue()),
                    Math.min(3, primaryMountTotalSpaces));
        }

        @Override
        public int getPrimaryMountFieldOfFire(Shape shape) {
            return 1;
        }
    },
    SINGLE("Single", 1) {
        @Override
        int getPrimaryMountTotalSpaces(Shape shape, int hullSpaces, int year) {
            if (shape.equals(CYLINDER)) return getPrimaryMountTotalSpaces(0.34d, shape, hullSpaces, year);
            if (shape.equals(LONG_CYLINDER)) return getPrimaryMountTotalSpaces(0.35d, shape, hullSpaces, year);
            if (shape.equals(SPHEROID)) return getPrimaryMountTotalSpaces(0.31d, shape, hullSpaces, year);
            if (shape.equals(ELLIPSOID)) return getPrimaryMountTotalSpaces(0.31d, shape, hullSpaces, year);
            if (shape.equals(CONICAL)) return getPrimaryMountTotalSpaces(0.32d, shape, hullSpaces, year);
            if (shape.equals(HEMISPHEROID)) return getPrimaryMountTotalSpaces(0.28d, shape, hullSpaces, year);
            return 0;
        }

        @Override
        public int getPrimaryMountFieldOfFire(Shape shape) {
            if (shape.equals(CYLINDER)) return 9;
            if (shape.equals(LONG_CYLINDER)) return 9;
            if (shape.equals(SPHEROID)) return 12;
            if (shape.equals(ELLIPSOID)) return 12;
            if (shape.equals(CONICAL)) return 15;
            if (shape.equals(HEMISPHEROID)) return 15;
            return 0;
        }
    },
    DOUBLE("Double", 2) {
        @Override
        int getPrimaryMountTotalSpaces(Shape shape, int hullSpaces, int year) {
            if (shape.equals(CYLINDER)) return getPrimaryMountTotalSpaces(0.3d, shape, hullSpaces, year);
            if (shape.equals(SPHEROID)) return getPrimaryMountTotalSpaces(0.28d, shape, hullSpaces, year);
            if (shape.equals(ELLIPSOID)) return getPrimaryMountTotalSpaces(0.28d, shape, hullSpaces, year);
            if (shape.equals(CONICAL)) return getPrimaryMountTotalSpaces(0.28d, shape, hullSpaces, year);
            if (shape.equals(HEMISPHEROID)) return getPrimaryMountTotalSpaces(0.26d, shape, hullSpaces, year);
            return 0;
        }

        @Override
        public int getPrimaryMountFieldOfFire(Shape shape) {
            if (shape.equals(CYLINDER)) return 9;
            if (shape.equals(SPHEROID)) return 13;
            if (shape.equals(ELLIPSOID)) return 13;
            if (shape.equals(CONICAL)) return 16;
            if (shape.equals(HEMISPHEROID)) return 16;
            return 0;
        }
    },
    TRIPLE("Triple", 3) {
        @Override
        int getPrimaryMountTotalSpaces(Shape shape, int hullSpaces, int year) {
            if (shape.equals(SPHEROID)) return getPrimaryMountTotalSpaces(0.25d, shape, hullSpaces, year);
            if (shape.equals(ELLIPSOID)) return getPrimaryMountTotalSpaces(0.25d, shape, hullSpaces, year);
            if (shape.equals(CONICAL)) return getPrimaryMountTotalSpaces(0.24d, shape, hullSpaces, year);
            if (shape.equals(HEMISPHEROID)) return getPrimaryMountTotalSpaces(0.24d, shape, hullSpaces, year);
            return 0;
        }

        @Override
        public int getPrimaryMountFieldOfFire(Shape shape) {
            if (shape.equals(SPHEROID)) return 14;
            if (shape.equals(ELLIPSOID)) return 14;
            if (shape.equals(CONICAL)) return 17;
            if (shape.equals(HEMISPHEROID)) return 17;
            return 0;
        }
    },
    QUADRUPLE("Quadruple", 4) {
        @Override
        int getPrimaryMountTotalSpaces(Shape shape, int hullSpaces, int year) {
            if (shape.equals(SPHEROID)) return getPrimaryMountTotalSpaces(0.22d, shape, hullSpaces, year);
            if (shape.equals(ELLIPSOID)) return getPrimaryMountTotalSpaces(0.22d, shape, hullSpaces, year);
            if (shape.equals(CONICAL)) return getPrimaryMountTotalSpaces(0.2d, shape, hullSpaces, year);
            if (shape.equals(HEMISPHEROID)) return getPrimaryMountTotalSpaces(0.22d, shape, hullSpaces, year);
            return 0;
        }

        @Override
        public int getPrimaryMountFieldOfFire(Shape shape) {
            if (shape.equals(SPHEROID)) return 15;
            if (shape.equals(ELLIPSOID)) return 15;
            if (shape.equals(CONICAL)) return 18;
            if (shape.equals(HEMISPHEROID)) return 18;
            return 0;
        }
    },
    QUINTUPLE("Quintuple", 5) {
        @Override
        int getPrimaryMountTotalSpaces(Shape shape, int hullSpaces, int year) {
            if (shape.equals(SPHEROID)) return getPrimaryMountTotalSpaces(0.19d, shape, hullSpaces, year);
            if (shape.equals(ELLIPSOID)) return getPrimaryMountTotalSpaces(0.19d, shape, hullSpaces, year);
            if (shape.equals(HEMISPHEROID)) return getPrimaryMountTotalSpaces(0.2d, shape, hullSpaces, year);
            return 0;
        }

        @Override
        public int getPrimaryMountFieldOfFire(Shape shape) {
            if (shape.equals(SPHEROID)) return 15;
            if (shape.equals(ELLIPSOID)) return 15;
            if (shape.equals(HEMISPHEROID)) return 19;
            return 0;
        }
    },
    SEXTUPLE("Sextuple", 6) {
        @Override
        int getPrimaryMountTotalSpaces(Shape shape, int hullSpaces, int year) {
            if (shape.equals(HEMISPHEROID)) return getPrimaryMountTotalSpaces(0.1667d, shape, hullSpaces, year);
            return 0;
        }

        @Override
        public int getPrimaryMountFieldOfFire(Shape shape) {
            if (shape.equals(HEMISPHEROID)) return 20;
            return 0;
        }
    };

    private final String name;
    private final int primaryMountsTotalCount;

    MountConfiguration(String name, int primaryMountsTotalCount) {
        this.name = name;
        this.primaryMountsTotalCount = primaryMountsTotalCount;
    }

    public String getName() {
        return name;
    }

    abstract int getPrimaryMountTotalSpaces(final Shape shape, final int hullSpaces, final int year);

    final int getPrimaryMountTotalSpaces(final double multiplier, final Shape shape, final int hullSpaces, final int year) {
        return Double.valueOf(Math.round(multiplier * shape.getWeaponizableSpaces(hullSpaces, year))).intValue();
    }

    int getPrimaryMountBiggestWeaponSpaces(final Shape shape, final int hullSpaces, final int year) {
        final int primaryMountTotalSpaces = getPrimaryMountTotalSpaces(shape, hullSpaces, year);
        return Math.max(
                Math.min(15, Double.valueOf(Math.round(getStandardWeaponPercentage(hullSpaces) * primaryMountTotalSpaces)).intValue()),
                Math.min(3, primaryMountTotalSpaces));
    }

    final double getKeelWeaponPercentage(final int hullSpaces) {
        return 0.8 / Math.pow(hullSpaces / 25d, 1 / 2.95d);
    }

    private double getStandardWeaponPercentage(final int hullSpaces) {
        return 0.72 / Math.pow(hullSpaces / 25d, 1 / 2.95d);
    }

    public int getPrimaryMountsTotalCount() {
        return primaryMountsTotalCount;
    }

    public abstract int getPrimaryMountFieldOfFire(final Shape shape);

    int getPrimaryMountRows(final Shape shape, final int hullSpaces, final int year) {
        return Math.min(
                Double.valueOf(Math.ceil(Math.pow(getPrimaryMountTotalSpaces(shape, hullSpaces, year), 1 / 1.75d))).intValue(),
                10);
    }
}
