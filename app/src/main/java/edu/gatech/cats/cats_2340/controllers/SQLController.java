package edu.gatech.cats.cats_2340.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

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

    private Connection SQLconnection;

    private static SQLController singleton = new SQLController();

    private SQLController() {

    }

    public static SQLController getSQLController() {
        return singleton;
    }

    /**
     * Initializes connection to the database, setting SQLconnection.
     */
    public boolean initializeConnection(){
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
    public void disconnect() {
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
    private boolean isSQLInitialized() {
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

    private ResultSet executeStatement(String statementString) {
        if (!isSQLInitialized()) return null;
        Statement statement = null;
        try {
            statement = SQLconnection.createStatement();
            return statement.executeQuery(statementString);
        } catch(SQLException e) {
            Log.d("ERROR:", "Error executing statement: " + statementString);
            return null;
        } finally {
            closeStatement(statement);
        }
    }

    public RatSighting[] getAllSightings() {
        String statement = "SELECT * FROM `cs2340`.`rat_sighting`;";
        ResultSet result = executeStatement(statement);
        if (result == null) return null;
        try {
            result.beforeFirst();
            ArrayList<RatSighting> list = new ArrayList<RatSighting>();
            while (result.next()) {
                RatSighting newSight = new RatSighting(result.getInt(1), result.getString(2),
                        LocationType.toLocationType(result.getString(3)), result.getInt(4), result.getString(5), result.getString(6),
                        BuroughType.toBuroughType(result.getString(7)), result.getFloat(8), result.getFloat(9));
                list.add(newSight);
            }
            return (RatSighting[]) list.toArray();
        } catch (Exception e) {
            Log.d("ERROR:", "Failed GetAllSightings");
            return null;
        }
    }

    public ArrayList<RatSighting> getFilteredSightings(SearchCriteria sc) {
        return null;
    }

    public RatSighting getIndividualRatSighting(int key) {
        return null;
    }

    public boolean addRatSighting(RatSighting rs) {
        String statement = "INSERT INTO `cs2340`.`rat_sighting` VALUES(" +
                rs.getKey() + "," +
                rs.getCreated() + "," +
                rs.getLocationType().toString() + "," +
                rs.getZip() + "," +
                rs.getAddr() + "," +
                rs.getCity() + "," +
                rs.getBorough().toString() + "," +
                rs.getLat() + "," +
                rs.getLong() + ");";
        if (executeStatement(statement) == null) {
            return false;
        }
        return true;
    }

    public boolean clearRatTable() {
        String safeStatement = "SET sql_safe_updates=0";
        executeStatement(safeStatement);
        String statement = "DELETE FROM `cs2340`.`rat_sighting`";
        if (executeStatement(statement) == null) {
            return false;
        }
        return true;
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
