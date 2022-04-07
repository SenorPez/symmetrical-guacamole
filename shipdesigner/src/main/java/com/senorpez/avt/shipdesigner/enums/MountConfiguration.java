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
        public int getPrimaryMountTotalCount(Shape shape) {
            return shape.equals(HEMISPHEROID) ? 1 : super.getPrimaryMountTotalCount(shape);
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
        public int getPrimaryMountTotalCount(Shape shape) {
            return shape.equals(HEMISPHEROID) ? 2 : super.getPrimaryMountTotalCount(shape);
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
        public int getPrimaryMountTotalCount(Shape shape) {
            return shape.equals(HEMISPHEROID) ? 3 : super.getPrimaryMountTotalCount(shape);
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
        public int getPrimaryMountTotalCount(Shape shape) {
            return shape.equals(HEMISPHEROID) ? 4 : super.getPrimaryMountTotalCount(shape);
        }

        @Override
        public int getPrimaryMountFieldOfFire(Shape shape) {
            if (shape.equals(SPHEROID)) return 15;
            if (shape.equals(ELLIPSOID)) return 15;
            if (shape.equals(HEMISPHEROID)) return 19;
            return 0;
        }
    },
    SEXTUPLE("Sextuple", 4) {
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

    public int getPrimaryMountTotalCount(final Shape shape) {
        return primaryMountsTotalCount;
    }

    public abstract int getPrimaryMountFieldOfFire(final Shape shape);

    int getPrimaryMountRows(final Shape shape, final int hullSpaces, final int year) {
        return Math.min(
                Double.valueOf(Math.ceil(Math.pow(getPrimaryMountTotalSpaces(shape, hullSpaces, year), 1 / 1.75d))).intValue(),
                10);
    }

    int getSecondaryMountTotalSpaces(Shape shape, int hullSpaces, int year) {
        if (shape.equals(CYLINDER))
            return Math.max(
                    Double.valueOf(Math.round(0.09 * shape.getWeaponizableSpaces(hullSpaces, year))).intValue(),
                    1
            );
        if (shape.equals(LONG_CYLINDER))
            return Math.max(
                    Double.valueOf(Math.round(0.09 * shape.getWeaponizableSpaces(hullSpaces, year))).intValue(),
                    1
            );
        if (shape.equals(SPHEROID))
            return Double.valueOf(Math.round(0.6 * getPrimaryMountTotalSpaces(shape, hullSpaces, year))).intValue();
        if (shape.equals(ELLIPSOID))
            return Double.valueOf(Math.round(0.6 * getPrimaryMountTotalSpaces(shape, hullSpaces, year))).intValue();
        if (shape.equals(CONICAL))
            return Math.max(
                    Double.valueOf(Math.round(0.08 * shape.getWeaponizableSpaces(hullSpaces, year))).intValue(),
                    1
            );
        if (shape.equals(HEMISPHEROID))
            return Math.max(
                    Double.valueOf(Math.round(0.09 * shape.getWeaponizableSpaces(hullSpaces, year))).intValue(),
                    1
            );
        return 0;
    }

    int getSecondaryMountBiggestWeaponSpaces(final Shape shape, final int hullSpaces, final int year) {
        final int primary = getPrimaryMountBiggestWeaponSpaces(shape, hullSpaces, year);
        final int spaces = getSecondaryMountTotalSpaces(shape, hullSpaces, year);
        return Math.max(1, Math.max(
                Math.min(
                        Double.valueOf(Math.floor(primary * 0.83)).intValue(),
                        Double.valueOf(Math.round(0.6 * spaces)).intValue()
                ),
                Math.min(2, Math.min(
                        primary - 1,
                        spaces
                ))
        ));
    }

    int getSecondaryMountTotalCount(final Shape shape, final int hullSpaces, final int year) {
        if (shape.equals(CYLINDER))
            return Double.valueOf(Math.round(shape.getWeaponizableSpaces(hullSpaces, year) * 0.48d / getSecondaryMountTotalSpaces(shape, hullSpaces, year))).intValue();
        if (shape.equals(LONG_CYLINDER))
            return Double.valueOf(Math.round(shape.getWeaponizableSpaces(hullSpaces, year) * 0.33d / getSecondaryMountTotalSpaces(shape, hullSpaces, year))).intValue();
        if (shape.equals(SPHEROID)) return 8 - getPrimaryMountTotalCount(shape);
        if (shape.equals(ELLIPSOID)) return 8 - getPrimaryMountTotalCount(shape);
        if (shape.equals(CONICAL)) return 10 - getPrimaryMountTotalCount(shape);
        if (shape.equals(HEMISPHEROID)) return 9 - getPrimaryMountTotalCount(shape);
        return 0;
    }

    int getSecondaryMountRows(final Shape shape, final int hullSpaces, final int year) {
        return Math.min(
                Double.valueOf(Math.ceil(Math.pow(getSecondaryMountTotalSpaces(shape, hullSpaces, year), 1 / 1.65d))).intValue(),
                10
        );
    }

    int getTertiaryMountBiggestWeaponSpaces(final Shape shape, final int hullSpaces, final int year) {
        return Math.max(
                Double.valueOf(Math.round(Math.sqrt(getPrimaryMountBiggestWeaponSpaces(shape, hullSpaces, year)))).intValue(),
                1
        );
    }

    int getTertiaryMountTotalCount(final Shape shape, final int hullSpaces, final int year) {
        return 24 - getPrimaryMountTotalCount(shape) - getSecondaryMountTotalCount(shape, hullSpaces, year);
    }

    int getMountTotalSpaces(final Shape shape, final int hullSpaces, final int year) {
        return getPrimaryMountTotalSpaces(shape, hullSpaces, year) * getPrimaryMountTotalCount(shape)
                + getSecondaryMountTotalSpaces(shape, hullSpaces, year) * getSecondaryMountTotalCount(shape, hullSpaces, year)
                + shape.getTertiaryMountTotalSpaces(hullSpaces, year) * getTertiaryMountTotalCount(shape, hullSpaces, year);
    }
}
