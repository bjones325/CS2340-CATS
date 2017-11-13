package edu.gatech.cats.cats_2340;

import org.junit.Before;
import org.junit.Test;

import edu.gatech.cats.cats_2340.controllers.SQLController;
import edu.gatech.cats.cats_2340.model.User;

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
        User testUser = new User("Test", "dog", false);
        sql.removeUser(testUser.getName());
        for (int i = 0; i < 20; i++) {
            User newUser = new User("Test" + i, "dog", false);
            sql.removeUser(newUser.getName());
        }
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
        for (int i = 0; i < 100; i++) {
            assertNotNull(sql.getIndividualRatSighting(i));
        }
    }

}
