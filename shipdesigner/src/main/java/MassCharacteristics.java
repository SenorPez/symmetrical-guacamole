class MassCharacteristics {
    private Ship ship;

    MassCharacteristics(Ship ship) {
        this.ship = ship;
    }

    int getHullArmorMass() {
        return getHullArmorSpaces() * 25;
    }

    int getHullArmorSpaces() {
        return ship.getHullArmor();
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
