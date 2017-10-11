package edu.gatech.cats.cats_2340.model;

/**
 * Created by Blake on 10/10/2017.
 */

public enum LocationType {

    FAMILY_DWELLING("Family Dwelling"),
    FAMILY_APARTMENT("Family Apartment"),
    BUILDING("Building"),
    FAMILY_MIXED_USE_BUILDING("Family Mixed Use Building"),
    COMMERCIAL_BUILDING("Commercial Building"),
    VACANT_LOT("Vacant Lot"),
    CONSTRUCTION_SITE("Construction Site"),
    HOSPITAL("Hospital"),
    CATCH_BASIN_SEWER("Catch Basin/Sewer");

    private String name;

    private LocationType(String n) {
        name = n;
    }

    @Override
    public String toString() {
        return name;
    }
}
