package edu.gatech.cats.cats_2340;

import org.junit.Before;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.Test;

import edu.gatech.cats.cats_2340.controllers.SQLController;
import edu.gatech.cats.cats_2340.model.BoroughType;
import edu.gatech.cats.cats_2340.model.LocationType;
import edu.gatech.cats.cats_2340.model.RatSighting;
import edu.gatech.cats.cats_2340.model.SearchCriteria;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


/**
 * Created by Ruchi Banerjee on 11/12/2017.
 */

public class RuchiTest {
    private SQLController sql = new SQLController().getSQLController();

    private SearchCriteria emptySC = new SearchCriteria();
    ArrayList<RatSighting> list = new ArrayList<>();

    private Date ratDate1 = new Date(0,0,0);
    private Date ratDate2 = new Date(1,11,1);
    private SearchCriteria locSC = new SearchCriteria(LocationType.toLocationType("Building"));
    private SearchCriteria borSC = new SearchCriteria(BoroughType.toBoroughType("Manhattan"));
    private RatSighting rs1 = new RatSighting(123, ratDate1, LocationType.toLocationType("Building"),
            876, "246 Lucky Rd", "New York City", BoroughType.toBoroughType("Manhattan"),
            42.0f, -72.3f);

    @Before
    public void setUp() {
        SQLController.initializeConnection();
    }

    @Test
    public void testEmptySearchCriteria() {
        RatSighting[] test = sql.getFilteredSightings((emptySC));
        assertArrayEquals(list.toArray(new RatSighting[0]), test);
    }


    @Test
    public void testLocSearchCriteria() {
        for (RatSighting rs : sql.getFilteredSightings(locSC)) {
            assertEquals(rs1.getLocationType(), rs.getLocationType());
        }
    }

    @Test
    public void testBorSearchCriteria() {
        for (RatSighting rs : sql.getFilteredSightings(borSC)) {
            assertEquals(rs1.getBorough(), rs.getBorough());
        }
    }

    @Test
    public void testStartDateSearchCriteria() {
        ArrayList<BoroughType> borType = new ArrayList<>();
        ArrayList<LocationType> locType = new ArrayList<>();
        borType.add(0, BoroughType.MANHATTAN);
        locType.add(0, LocationType.BUILDING);
        SearchCriteria dateSC = new SearchCriteria(borType, locType, ratDate1, ratDate2);

        for (RatSighting rs : sql.getFilteredSightings(dateSC)) {
            assertEquals(rs1.getCreated(), rs.getCreated());
        }
    }

}
