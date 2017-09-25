package edu.gatech.cats.cats_2340.model;

import android.util.Log;

/**
 * Created by acer_ on 9/21/2017.
 * Modified 9/21/17
 *
 * Basic model class
 */

public class Model {
    //Singleton instance
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

    //Basic logic for a log in implemented here. The username and password are hardcoded but will
    //obviously be replaced in the future
    public boolean attemptLogin(String user, String pass) {
        loggedIn = true;
        return (user.equals("user") && pass.equals("pass"));
    }

    public void logout() {
        if (!loggedIn) {
            Log.d("ERROR:", "User logged out but was not logged in!");
        }
        loggedIn = false;
    }
}
