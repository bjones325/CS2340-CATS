package edu.gatech.cats.cats_2340.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import com.mysql.jdbc.Driver;

import android.content.Intent;
import android.util.Log;

import edu.gatech.cats.cats_2340.model.BuroughType;
import edu.gatech.cats.cats_2340.model.LocationType;
import edu.gatech.cats.cats_2340.model.RatSighting;
import edu.gatech.cats.cats_2340.model.SearchCriteria;
import edu.gatech.cats.cats_2340.model.User;

/**
 * Created by acer_ on 10/5/2017.
 */

public class SQLController {

    private static String username = "acer";
    private static String password = "cs2340CATS";
    private static String dbName = "cs2340";
    private static String serverName = "10.0.2.2";
    private static int portNumber = 3306;

    private static Connection SQLconnection;

    /**
     * Initializes connection to the database, setting SQLconnection.
     */
    public static boolean initializeConnection(){
        Log.d("INFO", "Initializing Connection to Database");
        Properties connectionProps = new Properties();
        connectionProps.put("user", username);
        connectionProps.put("password", password);
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://" +
                            serverName +
                            ":" + portNumber +
                            "/" + dbName + "?autoReconnect=true&useSSL=false",
                    connectionProps);

            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            SQLconnection = conn;
            Log.d("INFO", "Initialized Connection to Database");
            return true;
        } catch (Exception e){
            Log.d("ERROR", "Failed to Connect to Database!");
            Log.d("ERROR", "MSG: " + e.getMessage());
            return false;
        }
    }

    /**
     * Disconnects from the SQL database, if connected at all.
     */
    public static void disconnect() {
        if (SQLconnection != null) {
            try {
                SQLconnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Checks if the SQLDatabase connection has been established.
     * If not, will attempt to reinitialize connection.
     * @return If the database is connected, or has been reconnected.
     */
    private static boolean isSQLInitialized() {
        if (SQLconnection == null) {
            Log.d("ERROR", "Not connected to Database! Attempted to reinitialize");
            return initializeConnection();
        }
        return true;
    }

    private static void closeStatement(Statement state) {
        if (state == null) return;
        try {
            state.close();
        } catch (SQLException e) {
            Log.d("ERROR:", "Error closing statement " + state.toString());
        }
    }

    private boolean executeStatement(String statementString) {
        if (!isSQLInitialized()) return false;
        Statement statement = null;
        try {
            statement = SQLconnection.createStatement();
            statement.execute(statementString);
            return  true;
        } catch(SQLException e) {
            Log.d("ERROR:", "Error executing statement: " + statementString);
            return false;
        } finally {
            closeStatement(statement);
        }
    }

    public ArrayList<RatSighting> getAllSightings() {
        ArrayList<RatSighting> ratData = new ArrayList<>();

        // Test Code
        ratData.add(new RatSighting(1, "2", LocationType.BUILDING, 23114, "add", "city1", BuroughType.BRONX, 2, 3));
        ratData.add(new RatSighting(2, "3", LocationType.COMMERCIAL_BUILDING, 30309, "add2", "cit1", BuroughType.MANHATTAN, 4, 5));
        return ratData;
    }

    public ArrayList<RatSighting> getFilteredSightings(SearchCriteria sc) {
        return null;
    }

    public RatSighting getIndividualRatSighting(int key) {
        return null;
    }

    public boolean addRatSighting() {
        return false;
    }

    public boolean updateRatSighting(int key, RatSighting newSighting) {
        return false;
    }

    public boolean removeRatSighting() {
        return false;
    }

    public User getUser(int key) {
        return null;
    }

    public boolean updateUser(int key, User newUser) {
        return false;
    }

    public boolean removeUser() {
        return false;
    }

}
