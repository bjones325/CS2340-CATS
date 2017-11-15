package edu.gatech.cats.cats_2340.model;

import android.support.annotation.Nullable;
import android.util.Log;

import edu.gatech.cats.cats_2340.controllers.SQLController;


/**
 * Created by Blake on 9/21/2017.
 * Modified 9/21/17
 *
 * Basic model class
 */

public final class Model {
    //Singleton instance
    private static final Model _instance = new Model();

    @Nullable
    private User currentUser;

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
        //RatSighting currentRat =
    }

    /**
     * Basic logic for Log-In. Username/password in a list currently, will probably switch to a
     * Database soon
     * @param user The username entered
     * @param pass The password entered
     * @return Whether a user was found with the entered username/password
     */
    public boolean attemptLogin(String user, String pass) {
        //Log.d("Login", "Model attempting log in");
        if (currentUser != null) {
            //Log.d("ERROR:", "User trying to login, but is already logged in?");
            //Log.d("Login", "Duplicate logins");
            return false;
        }
        SQLController sql = SQLController.getSQLController();
        User u = sql.getUser(user, pass);
        if (u == null) {
            //Log.d("Login", "SQL controller null");
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

        /* Set to null for clarity's sake */
        currentUser = null;
    }

    /**
     * Adds the user to a list of users. Will most likely utilize a database in the future
     * @param u User to be added
     *          @return Whether or not the user could log in
     */
    public boolean registerUser(User u) {
        //Clean one liner
        SQLController controller = SQLController.getSQLController();
        return controller.addUser(u);
    }

    /**
     * Returns the current user
     * @return The use logged
     */
    @Nullable
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * I don't know what this does
     * @param u The current user
     */
    public void setCurrentUser(@Nullable User u) {currentUser = u;}
}
