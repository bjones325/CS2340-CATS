package edu.gatech.cats.cats_2340.model;

import android.util.Log;
import java.util.List;
import java.util.ArrayList;

import edu.gatech.cats.cats_2340.controllers.SQLController;

/**
 * Created by acer_ on 9/21/2017.
 * Modified 9/21/17
 *
 * Basic model class
 */

public class Model {
    //Singleton instance
    private static final Model _instance = new Model();

    private RatSighting currentRat;

    private List<User> userList = new ArrayList<User>();

    private boolean loggedIn = false;

    private SQLController sqlController;

    public static Model getInstance() {
        return _instance;
    }

    /**
     * Basic Model constructor. Empty right now but editable as needed
     */
    private Model() {
        //User u = new User("Elijah", "user", "pass", true);
        //userList.add(u);
        RatSighting currentRat = new RatSighting(1, 1, LocationType.BUILDING, 23114, "add", "city", BuroughType.BRONX, 10, 10);
    }

    /**
     * Basic logic for Log-In. Username/password in a list currently, will probably switch to a
     * Database soon
     * @param user The username entered
     * @param pass The password entered
     * @return Whether a user was found with the entered username/password
     */
    public boolean attemptLogin(String user, String pass) {
        boolean foundUser = false;


        for (User u : userList) {
            if (u.getId().equals(user)) {
                foundUser = true;

                if (u.getPass().equals(pass)) {
                    loggedIn = true;
                    return true;
                } else {
                    Log.d("ERROR:", "Incorrect password"); //TODO: Add in user facing
                    Log.d("ERROR:", "Entered : " + pass + "Looking for : " + u.getPass());
                    return false;
                }
            }
        }

        if (!foundUser) {
            Log.d("ERROR:", "Username not found"); //TODO: Add in user facing
        }

        return false;
    }

    /**
     * Logs the user out of the application
     */
    public void logout() {
        if (!loggedIn) {
            Log.d("ERROR:", "User logged out but was not logged in!");
        }
        loggedIn = false;
    }

    /**
     * Adds the user to a list of users. Will most likely utilize a database in the future
     * @param u User to be added
     */
    public void register(User u) {
        userList.add(u);
    }

    public RatSighting getCurrentRat() {
        return  new RatSighting(1, 1, LocationType.BUILDING, 23114, "add", "city", BuroughType.BRONX, 10, 10);
    }

    public RatSighting[] getRatArray() {
        return ((RatSighting[]) sqlController.getAllSightings().toArray());

    }
}
