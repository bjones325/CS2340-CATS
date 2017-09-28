package edu.gatech.cats.cats_2340.model;

import android.util.Log;
import java.util.List;
import java.util.ArrayList;

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

    private List<User> userList = new ArrayList<User>();

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
        boolean foundUser = false;
        for (User u : userList) {
            if (u.getName() == user) {
                foundUser = true;

                if (u.getPass() == pass) {
                    loggedIn = true;
                    return true;
                } else {
                    Log.d("ERROR:", "Incorrect password"); //TODO: Add in user facing
                    return false;
                }
            }
        }

        if (!foundUser) {
            Log.d("ERROR:", "Username not found"); //TODO: Add in user facing
        }

        return false;
    }

    public void logout() {
        if (!loggedIn) {
            Log.d("ERROR:", "User logged out but was not logged in!");
        }
        loggedIn = false;
    }

    public void register(User u) {
        userList.add(u);
    }
}
