package edu.gatech.cats.cats_2340;

import org.junit.Before;

import edu.gatech.cats.cats_2340.controllers.SQLController;

/**
 * Created by Mark Walker on 11/14/2017 for JUnit tests
 */

class MarkTest {

    @Before
    public void setUp() {
        SQLController sql = SQLController.getSQLController();
        sql.initializeConnection();
    }


}
