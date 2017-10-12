package edu.gatech.cats.cats_2340.model;

/**
 * Created by Blake on 10/10/2017.
 */

public enum LocationType {

    FAMILY_DWELLING("1-2 Family Dwelling"),
    FAMILY_APARTMENT("3+ Family Apt. Building"),
    BUILDING("Vacant Building"),
    PARKING_LOT_GARAGE("Parking Lot/Garage"),
    FAMILY_MIXED_USE_BUILDING("3+ Family Mixed Use Building"),
    COMMERCIAL_BUILDING("Commercial Building"),
    VACANT_LOT("Vacant Lot"),
    PUBLIC_GARDEN("Public Garden"),
    CONSTRUCTION_SITE("Construction Site"),
    HOSPITAL("Hospital"),
    CATCH_BASIN_SEWER("Catch Basin/Sewer"),
    OTHER("Other (Explain Below)");

    private String name;

    private LocationType(String n) {
        name = n;
    }

    @Override
    public String toString() {
        return name;
    }

    public static LocationType toLocationType(String s) {
        for (LocationType lt : LocationType.values()) {
            if (lt.toString().equalsIgnoreCase(s)) {
                return lt;
            }
        }
        return null;
    }
}
