class MassCharacteristics {
    private Ship ship;

    MassCharacteristics(Ship ship) {
        this.ship = ship;
    }

    int getHullMass() {
        return getHullSpaces() * 25;
    }

    int getHullArmorMass() {
        return getHullArmorSpaces() * 25;
    }

    int getDriveSpaces() {
        return 10; // Placeholder
    }

    int getHullArmorSpaces() {
        return ship.getHullArmor();
    }

    int getHullSpaces() {
        return ship.getHullSize() - getHullArmorSpaces() - getDriveSpaces();
    }

//    int getMastCombinedMass() {
//        return getMastStructuralMass() + getMastArmorMass() + getBaseShieldMass();
//    }
//
//    int getMainHullMass() {
//        return getMainHullSpaces();
//    }
//
//    int getTotalDriveMass() {
//        return getMastCombinedMass() + getLanternMass() + getEngineArmorMass();
//    }
//
//    int getMainHullSpaces() {
//        return ship.getHullSize()
//                - getMainArmorSpaces()
//                - Double.valueOf(getTotalDriveSpaces_Armor()).intValue();
//    }
//
//    int getMainArmorSpaces() {
//        return ship.getHullArmor();
//    }
//
//    double getTotalDriveSpaces_Armor() {
//        return getTotalDriveMass() / 25d;
//    }
}
