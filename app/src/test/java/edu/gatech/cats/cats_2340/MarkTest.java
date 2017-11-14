package edu.gatech.cats.cats_2340;

import org.junit.Before;

import edu.gatech.cats.cats_2340.controllers.SQLController;

/**
 * Created by Mark Walker on 11/14/2017.
 */

public class MarkTest {
    private SQLController sql;

    @Before
    public void setUp() {
        sql = SQLController.getSQLController();
        sql.initializeConnection();
    }


}
