package edu.gatech.cats.cats_2340;

import org.junit.Before;
import org.junit.Test;

import edu.gatech.cats.cats_2340.controllers.SQLController;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Elena on 11/9/17. Tests SQLController getIndividualRatSighting
 */

public class ElenaTest {
    private SQLController sql;

    @Before
    public void setUp() {
        sql = SQLController.getSQLController();
        sql.initializeConnection();
    }
    @Test
    public void testGetNullRatSighting() {
        assertEquals(null, sql.getIndividualRatSighting(-15000));
    }

    @Test
    public void testGetInvalidRatSighting() {
        assertEquals(null, sql.getIndividualRatSighting(23000));
    }

    @Test
    public void testGetValidRatSighting() {
        for (int i = 1; i < 100; i++) {
            assertNotNull(sql.getIndividualRatSighting(i));
        }
    }

}
