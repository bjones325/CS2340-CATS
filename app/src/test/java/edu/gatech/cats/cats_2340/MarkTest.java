package edu.gatech.cats.cats_2340;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.gatech.cats.cats_2340.model.LocationType;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mark Walker on 11/14/2017.
 * Tests getIndividualRatSighting(int key)
 */

public class MarkTest {
    private List<String> str = new ArrayList<>();
    private List<String> other = new ArrayList<>();
    private final List<LocationType> loc = new ArrayList<>();
    private LocationType currLoc;


    /**
     * Sets up 3 ArrayLists of varying types for tests
     */
    @Before
    public void setUp() {
        str = Arrays.asList("1-2 Family Dwelling",
                "3+ Family Apt. Building",
                "Vacant Building", "Parking Lot/Garage", "3+ Family Mixed Use Building",
                "Commercial Building", "Vacant Lot", "Public Garden", "Construction Site",
                "Hospital", "Catch Basin/Sewer", "Other");
        other = Arrays.asList("In my basement", "football field", "MANCHESTER", "double now");
        loc.add(LocationType.FAMILY_DWELLING);
        loc.add(LocationType.FAMILY_APARTMENT);
        loc.add(LocationType.BUILDING);
        loc.add(LocationType.PARKING_LOT_GARAGE);
        loc.add(LocationType.FAMILY_MIXED_USE_BUILDING);
        loc.add(LocationType.COMMERCIAL_BUILDING);
        loc.add(LocationType.VACANT_LOT);
        loc.add(LocationType.PUBLIC_GARDEN);
        loc.add(LocationType.CONSTRUCTION_SITE);
        loc.add(LocationType.HOSPITAL);
        loc.add(LocationType.CATCH_BASIN_SEWER);
        loc.add(LocationType.OTHER);
    }

    /**
     * Converts Strings into their relevant location types
     *  to test if they convert correctly
     */
    @Test
    public void testToLocationType() {
        for (int i = 0; i < str.size(); i++) {
            currLoc = LocationType.toLocationType(str.get(i));
            assertEquals(loc.get(i), currLoc);
        }
    }

    /**
     * Tests user generated entries that do not match any option,
     *  and should become of type other
     */
    @Test
    public void testOtherPossibilities() {
        for (int i = 0; i < other.size(); i++) {
            currLoc = LocationType.toLocationType(other.get(i));
            assertEquals(LocationType.OTHER, currLoc);
        }
    }

    /**
     * Tests null entry (which wouldn't normally be allowed to happen,
     *  as fields can't be empty inside the app)
     */
    @Test
    public void testNullEntry() {
        currLoc = LocationType.toLocationType(null);
        assertEquals(LocationType.OTHER, currLoc);
    }

}
