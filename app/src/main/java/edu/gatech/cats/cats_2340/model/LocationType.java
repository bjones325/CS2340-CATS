package edu.gatech.cats.cats_2340.model;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Blake on 10/10/2017. Its an enum of Location types
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

    public static final List<String> type = Arrays.asList("1-2 Family Dwelling",
            "3+ Family Apt. Building",
            "Vacant Building", "Parking Lot/Garage", "3+ Family Mixed Use Building",
            "Commercial Building", "Vacant Lot", "Public Garden", "Construction Site",
            "Hospital", "Catch Basin/Sewer", "Other");

    private final String name;

    LocationType(String n) {
        name = n;
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Converts a string to a location enum
     * @param s The string
     * @return A LocationType corresponding to that
     */
    public static LocationType toLocationType(String s) {
        for (LocationType lt : LocationType.values()) {
            String ltString = lt.toString();
            if (ltString.equalsIgnoreCase(s)) {
                return lt;
            }
        }
        return LocationType.OTHER;
    }
}
