package edu.gatech.cats.cats_2340.model;

/**
 * User class to store information about specific users of our application
 */

public class User {
    private String _name;
    private String _id;
    private String _pass;
    private boolean _isAdmin;

    public User(String name, String id, String pass, boolean isAdmin) {
        _name = name;
        _id = id;
        _pass = pass;
        _isAdmin = isAdmin;
    }

    public String getName() {
        return _name;
    }

    public String getId() {
        return _id;
    }

    public String getPass() {
        return _pass;
    }

    public boolean getIsAdmin() {
        return _isAdmin;
    }
}
