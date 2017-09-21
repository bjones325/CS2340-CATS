package edu.gatech.cats.cats_2340.model;

/**
 * Created by acer_ on 9/21/2017.
 * Modified 9/21/17
 *
 * Basic model class
 */

public class Model {
    private static final Model _instance = new Model();

    private String user;
    private String pass;

    private boolean loggedIn = false;

    public static Model getInstance() {
        return _instance;
    }

    private Model() {
        user = "user";
        pass = "pass";
    }

    public boolean attemptLogin(String user, String pass) {
        return (user.equals("user") && pass.equals("pass"));
    }
}
