package edu.gatech.cats.cats_2340;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.gatech.cats.cats_2340.controllers.SQLController;
import edu.gatech.cats.cats_2340.model.User;

import static org.junit.Assert.assertEquals;

/**
 * Created by Blake on 11/8/2017. Tests SQLController AddUser
 */

public class BlakeTest {
    private SQLController sql;
    private User testUser;

    @Before
    public void setUp() {
        sql = SQLController.getSQLController();
        sql.initializeConnection();
        testUser = new User("Test", "dog", false);
        sql.removeUser(testUser.getName());
        for (int i = 0; i < 20; i++) {
            User newUser = new User("Test" + i, "dog", false);
            sql.removeUser(newUser.getName());
        }
    }

    @Test
    public void addNewUser() {
        assertEquals(true, sql.addUser(testUser));
    }

    @Test
    public void addExistingUser() {
        assertEquals(false, sql.addUser(testUser));

        User passwordUser = new User("Test", "1234", false);
        User adminUser = new User("Test", "dog", true);
        User adminPassUser = new User("Test", "1234", true);
        assertEquals(false, sql.addUser(passwordUser));
        assertEquals(false, sql.addUser(adminUser));
        assertEquals(false, sql.addUser(adminPassUser));
    }

    @Test
    public void addUsersQuickly() {
        for (int i = 0; i < 20; i++) {
            User newUser = new User("Test" + i, "dog", false);
            assertEquals(true, sql.addUser(newUser));
        }
    }

    @After
    public void cleanUp() {
        sql.removeUser(testUser.getName());
        for (int i = 0; i < 20; i++) {
            User newUser = new User("Test" + i, "dog", false);
            sql.removeUser(newUser.getName());
        }
    }


}
