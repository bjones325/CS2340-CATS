package edu.gatech.cats.cats_2340.model;

import java.util.Arrays;
import java.util.List;

/**
 * User class to store information about specific users of our application
 */

public class User {
    private String _name;
    private String _pass;
    private boolean _isAdmin;
    private static List<String> type = Arrays.asList("User", "Admin");

    /**
     * Constructor for user. The application only proceeds when all fields filled so we can
     * safely make a 4 param version for now
     * @param name Name submitted
     * @param pass Password submitted
     * @param isAdmin Whether or not they are an admin
     */
    public User(String name, String pass, boolean isAdmin) {
        _name = name;
        _pass = pass;
        _isAdmin = isAdmin;
    }

    /**
     * Getter for name
     * @return name
     */
    public String getName() {
        return _name;
    }


    /**
     * Getter for pass
     * @return pass
     */
    public String getPass() {
        return _pass;
    }

    /**
     * Getter for whether or not they are an admin user type
     * @return Whether or not they are an admin
     */
    public boolean getIsAdmin() {
        return _isAdmin;
    }

}
