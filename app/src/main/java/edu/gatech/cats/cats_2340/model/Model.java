package edu.gatech.cats.cats_2340.model;

import android.util.Log;
import java.util.List;
import java.util.ArrayList;

import edu.gatech.cats.cats_2340.controllers.SQLController;


/**
 * Created by Blake on 9/21/2017.
 * Modified 9/21/17
 *
 * Basic model class
 */

public class Model {
    //Singleton instance
    private static final Model _instance = new Model();

    private RatSighting currentRat;

    private User currentUser;

    private List<RatSighting> reportList = new ArrayList<>();

    /**
     * Gets the instance of the model
     * @return The model
     */
    public static Model getInstance() {
        return _instance;
    }

    /**
     * Basic Model constructor. Empty right now but editable as needed
     */
    private Model() {
        //User u = new User("Elijah", "user", "pass", true);
        //userList.add(u);
        //RatSighting currentRat = new RatSighting(1, 1, LocationType.valueOf("place"), 23114, "add", "city", BoroughType.valueOf("b"), 10, 10);
    }

    /**
     * Basic logic for Log-In. Username/password in a list currently, will probably switch to a
     * Database soon
     * @param user The username entered
     * @param pass The password entered
     * @return Whether a user was found with the entered username/password
     */
    public boolean attemptLogin(String user, String pass) {
        if (currentUser != null) {
            Log.d("ERROR:", "User trying to login, but is already logged in?");
            return false;
        }
        User u = SQLController.getSQLController().getUser(user, pass);
        if (u == null) {
            return false;
        }
        currentUser = u;
        return true;
    }

    /**
     * Logs the user out of the application
     */
    public void logout() {
        if (currentUser == null) {
            Log.d("ERROR:", "User logged out but was not logged in!");
        }
        currentUser = null;
    }

    /**
     * Adds the user to a list of users. Will most likely utilize a database in the future
     * @param u User to be added
     *          @return Whether or not the user could log in
     */
    public boolean registerUser(User u) {
        return SQLController.getSQLController().addUser(u);
    }

    /**
     * Returns the current user
     * @return The use logged
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Adds a rat report to the report list
     * @param report The rat report to add
     */
    public void addReport(RatSighting report) {
        reportList.add(report);
    }

    /**
     * Returns the current rat
     * @return Null right now
     */
    public RatSighting getCurrentRat() {
        return null;
    }

    /**
     * Returns the array of rats
     * @return Null right now
     */
    public RatSighting[] getRatArray() {
        //return ((RatSighting[]) sqlController.getAllSightings().toArray());
        return null;
    }

    /**
     * Sets the current rat
     * @param rats The rat to set it to
     */
    public void setCurrentRat(RatSighting rats) {
        currentRat = rats;
    }
}
