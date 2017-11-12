package edu.gatech.cats.cats_2340.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import android.util.Log;

import edu.gatech.cats.cats_2340.model.BoroughType;
import edu.gatech.cats.cats_2340.model.LocationType;
import edu.gatech.cats.cats_2340.model.RatSighting;
import edu.gatech.cats.cats_2340.model.SearchCriteria;
import edu.gatech.cats.cats_2340.model.User;

/**
 * Created by Blake on 10/5/2017. This is the SQL controller
 */

public class SQLController {

    private static final  String username = "cs2340user";
    private static  final  String password = "cs2340pass";
    private static final String dbName = "cs2340db"; //cs2340db
    private static final String serverName = "cs2340cats.cypdijxckqjj.us-east-2.rds.amazonaws.com";
    private static final int portNumber = 3306;

    private Connection SQLconnection;

    private static final SQLController singleton = new SQLController();

    SQLController() {

    }

    /**
     * Returns singleton of SQLController
     * @return The sql controller
     */
    public static SQLController getSQLController() {
        return singleton;
    }

    /**
     * Initializes connection to the database, setting SQLconnection.
     * @return Success of connection
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
            Log.d("INFO", "Success! Initialized to Database our Connection has!");
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

    /**
     * Attempts to close the SQL
     * @param state The statement
     */
    private static void closeStatement(Statement state) {
        if (state == null) {
            return;
        }
        try {
            state.close();
        } catch (SQLException e) {
            Log.d("ERROR:", "Error closing statement " + state.toString());
        }
    }
    /**
     * Executes statementString
     * @return If the database is executed, or if it has failed to execute
     */
    private boolean executeInsert(String statementString) {
        if (!isSQLInitialized()) {
            return false;
        }
        Statement statement = null;
        try {
            statement = SQLconnection.createStatement();
            statement.execute(statementString);
            return true;
        } catch(SQLException e) {
            Log.d("ERROR:", "Error executing statement: " + statementString);
            Log.d("ERROR:", "MSG: " + e.getMessage());
            return false;
        }
    }

    private ResultSet executeRetrieval(String statementString) {
        if (!isSQLInitialized()) {
            return null;
        }
        PreparedStatement statement = null;
        try {
            statement = SQLconnection.prepareStatement(statementString);
            return statement.executeQuery();
        } catch(SQLException e) {
            Log.d("ERROR:", "Error executing statement: " + statementString);
            Log.d("ERROR:", "MSG: " + e.getMessage());
            return null;
        }
    }

    /**
     * creates an ArrayList full of all the rat sightings from the csv
     * @return ArrayList<RatSightings> full of all the rat sighting in the csv
     */
    public RatSighting[] getAllSightings() {
        return getFilteredSightings(new SearchCriteria());
    }

    /**
     * creates an ArrayList full of all the rat sightings from the csv given specific criteria
     * @param sc search criteria
     * @return ArrayList<RatSightings> full of all the rat sighting with the criteria
     */
    public RatSighting[] getFilteredSightings(SearchCriteria sc) {
        String statement = getStatementMessage(sc);
        ResultSet result = executeRetrieval(statement);
        ArrayList<RatSighting> list = new ArrayList<>();
        if (result == null) {
            return list.toArray(new RatSighting[0]);
        }
        try {
            result.beforeFirst();
            while (result.next()) {
                RatSighting newSight = new RatSighting(result.getInt(1), result.getDate(2),
                        LocationType.values()[result.getInt(3)], result.getInt(4), result.getString(5), result.getString(6),
                        BoroughType.values()[result.getInt(7)], result.getFloat(8), result.getFloat(9));
                list.add(newSight);
            }
            RatSighting[] rats = new RatSighting[list.size()];
            rats = list.toArray(rats);
            return rats;
        } catch (Exception e) {
            Log.d("ERROR:", "Failed GetAllSightings");
            Log.d("ERROR:", "MSG: " + e.getMessage());
            return new RatSighting[0];
        }
    }

    /**
     * Gets a filtered count by month of rat sightings based on a search criteria
     * @param sc Search criteria
     * @return A list of 3 element arrays containing year, month, count
     */
    public ArrayList<Integer[]> getFilteredCounts(SearchCriteria sc) {

        String statement = getStatementMessageCount(sc);

        Log.d("SQL", statement);

        ResultSet result = executeRetrieval(statement);



        ArrayList<Integer[]> list = new ArrayList<>();
        if (result == null) {
            Integer[] elem = new Integer[] {0, 0, 0};
            list.add(elem);
        }
        try {
            result.beforeFirst();
            while (result.next()) {
                Integer[] row = {result.getInt(1), result.getInt(2), result.getInt(3)};
                list.add(row);
                Log.d("", String.valueOf(result.getInt(1)));
                Log.d("", String.valueOf(result.getInt(2)));

            }
            return list;
        } catch (Exception e) {
            Log.d("ERROR:", "Failed getRatCount");
            Log.d("ERROR:", "MSG: " + e.getMessage());

            return null;
        }
    }

    /**
     * Gets rat count grouped by month
     * @return See above
     */
    public List<int[]> getRatCount() {
        ResultSet result = executeRetrieval(
                "SELECT EXTRACT(month FROM dateCreated) AS 'Month', EXTRACT(year FROM dateCreated) AS 'Year', count(*) AS 'Count' FROM `cs2340db`.`rat_sighting` GROUP BY EXTRACT(month FROM dateCreated) ORDER BY EXTRACT(month FROM dateCreated)");

        try {
            List<int[]> resultList = new ArrayList<>();
            result.beforeFirst();
            while (result.next()) {
                int[] row = {result.getInt(1), result.getInt(2), result.getInt(3)};
                resultList.add(row);
                Log.d("", String.valueOf(result.getInt(1)));
                Log.d("", String.valueOf(result.getInt(2)));

            }
            return resultList;
        } catch (Exception e) {
            Log.d("ERROR:", "Failed getRatCount");
            Log.d("ERROR:", "MSG: " + e.getMessage());

            return null;
        }
    }

    private String getStatementMessageCount(SearchCriteria sc) {
        StringBuilder string = new StringBuilder("SELECT EXTRACT(month FROM dateCreated) AS 'Month', EXTRACT(year FROM dateCreated) AS 'Year', count(*) AS 'Count' FROM `cs2340db`.`rat_sighting`");
        boolean insertedWhere = false;

        if ((sc.getStartDate() != null) && (sc.getEndDate() != null)) {
            //string.append(" AND ");
            string.append(" WHERE ");
            string.append(" `dateCreated` BETWEEN '" + sc.getStartDate().toString() + "' AND '" + sc.getEndDate().toString() + "'");
        }
        string.append("GROUP BY EXTRACT(month FROM dateCreated) ORDER BY EXTRACT(month FROM dateCreated);");
        return string.toString();
    }

    private String getStatementMessage(SearchCriteria sc) {
        StringBuilder string = new StringBuilder("SELECT * FROM `cs2340db`.`rat_sighting`");
        boolean insertedWhere = false;
        if (sc.getBuroughs() != null) {
            insertedWhere = true;
            string.append(" WHERE `borough` = " + sc.getBuroughs().get(0).ordinal());
            for (int i = 1; i < sc.getBuroughs().size(); i++) {
                string.append(" OR `borough` = " + sc.getBuroughs().get(i).ordinal());
            }
        }
        if (sc.getLocations() != null) {
            if (insertedWhere) {
                string.append(" AND ");
            } else {
                string.append(" WHERE ");
                insertedWhere = true;
            }
            string.append("`locationType` = " + sc.getLocations().get(0).ordinal());
            for (int i = 1; i < sc.getLocations().size(); i++) {
                string.append(" OR `locationType` = " + sc.getLocations().get(i).ordinal());
            }
        }
        if ((sc.getStartDate() != null) && (sc.getEndDate() != null)) {
            if (insertedWhere) {
                string.append(" AND ");
            } else {
                string.append(" WHERE ");
            }
            string.append(" `dateCreated` BETWEEN '" + sc.getStartDate().toString() + "' AND '" + sc.getEndDate().toString() + "'");
        }
        string.append(";");
        return string.toString();
    }

    /**
     * searches through ArrayList of rat sightings for the specified key
     * @param key unique key of the rat sighting
     * @return rat sighting information that matches passed in key
     */
    public RatSighting getIndividualRatSighting(int key) {
        String statement = "SELECT * FROM `cs2340db`.`rat_sighting` WHERE `key` = " + key + ";";
        ResultSet result = executeRetrieval(statement);
        if (result == null) {
            return null;
        }
        try {
            result.beforeFirst();
            result.next();
            return new RatSighting(result.getInt(1), result.getDate(2),
                    LocationType.values()[result.getInt(3)], result.getInt(4), result.getString(5), result.getString(6),
                    BoroughType.values()[result.getInt(7)], result.getFloat(8), result.getFloat(9));
        } catch (Exception e) {
            Log.d("ERROR:", "Failed GetIndividualSighting for Key-" + key);
            Log.d("ERROR:", "MSG: " + e.getMessage());
            return null;
        }
    }

    /**
     * adds rat sighting to the database
     * @param rs The rat sighting to add
     * @param user The user adding
     * @return boolean if adding a rat sighting was successful or not
     */
    public boolean addRatSighting(RatSighting rs, User user) {
        rs.setKey(getNextRatKey());
        String statement = "INSERT INTO `cs2340db`.`rat_sighting` VALUES(" +
                rs.getKey() + ",'" +
                rs.getCreated() + "','" +
                rs.getLocationType().ordinal() + "'," +
                rs.getZip() + ",'" +
                rs.getAddr() + "','" +
                rs.getCity() + "','" +
                rs.getBorough().ordinal() + "'," +
                rs.getLat() + "," +
                rs.getLong() + ",'" +
                user.getName() + "');";
        return executeInsert(statement);
    }

    private int getNextRatKey() {
        String statement = "SELECT COUNT(`key`) AS numOfRats FROM `cs2340db`.`rat_sighting`";
        ResultSet result = executeRetrieval(statement);
        if (result == null) {
            return 0;
        }
        try {
            result.beforeFirst();
            result.next();
            return result.getInt(1) + 1;
        } catch(Exception e) {
            Log.d("ERROR:", "Failed GetNextRatKey");
            Log.d("ERROR:", "MSG: " + e.getMessage());
        }
        return 0;
    }

    /**
     * Clears the rat data table
     * @return boolean if worked correctly
     */
    public boolean clearRatTable() {
        String safeStatement = "SET sql_safe_updates=0";
        executeInsert(safeStatement);
        String statement = "DELETE FROM `cs2340db`.`rat_sighting`";
        return executeInsert(statement);
    }

    /**
     * Updates the specific RatSighting associated with the passed in key given the newSighting information
     * @param key unique key associated with specific RatSighting
     * @param newSighting new information we want to replace to the specific RatSighting
     * associated with the key
     * @return boolean if updating the RatSighting was successful or not
     */
    public boolean updateRatSighting(int key, RatSighting newSighting) {
        return false;
    }

    /**
     * Removes RatSighting from the database
     * @param key The rat sighting key that you want removed
     * @return boolean if removing a rat sighting was successful or not
     */
    public boolean removeRatSighting(int key) {
        String statement = "DELETE FROM `cs2340db`.`rat_sighting`" +
                "WHERE `key` = " + key + ";";
        return executeInsert(statement);
    }

    /**
     * Gets user information associated with the RatSighting given the key
     * @param userName unique UserName associated with that user account
     * @return the User associated with creating the RatSighting instance given the key
     */
    public User getUser(String userName) {
        String statement = "SELECT * FROM `cs2340db`.`user` WHERE `name` = '" + userName + "';";
        ResultSet result = executeRetrieval(statement);
        if (result == null) {
            return null;
        }
        try {
            result.beforeFirst();
            if (!result.next()) return null;
            return new User(result.getString(1),
                    result.getString(2), result.getBoolean(3));
        } catch (Exception e) {
            Log.d("ERROR:", "Failed GetUser for Name-" + userName);
            Log.d("ERROR:", "MSG: " + e.getMessage());
            return null;
        }
    }

    /**
     * Gets user information associated with the RatSighting given the key
     * @param userName unique UserName associated with that user account
     * @param password password with account
     * @return the User associated with creating the RatSighting instance given the key
     */
    public User getUser(String userName, String password) {
        String statement = "SELECT * FROM `cs2340db`.`user` WHERE `name` = '" + userName + "' AND `password` = '" + password + "';";
        ResultSet result = executeRetrieval(statement);
        if (result == null) {
            return null;
        }
        try {
            result.beforeFirst();
            result.next();
            return new User(result.getString(1),
                    result.getString(2), result.getBoolean(3));
        } catch (Exception e) {
            Log.d("ERROR:", "Failed GetUser for Name-" + userName);
            Log.d("ERROR:", "MSG: " + e.getMessage());
            return null;
        }
    }

    /**
     * Registers a new username with the database
     * @param user User data to add to the database
     * @return was the insertion completed
     */
    public boolean addUser(User user) {
        String statement = "INSERT INTO `cs2340db`.`user` VALUES('" +
                user.getName() + "', '" +
                user.getPass() + "', " +
                user.getIsAdmin() + ");";
        return executeInsert(statement);
    }

    /**
     * Updates User profile- user can change username, password, or name
     * updates the username associated with the RatSightings created by the User
     * @param key unique key associated with the RatSighting instance created by the User
     * @param newUser new information we want to replace with old user information
     * @return boolean updating the User profile was successful or not
     */
    public boolean updateUser(int key, User newUser) {
        return false;
    }

    /**
     * removes User from database
     * ?? if we remove a user do we remove the RatSightings associated with them as well ??
     * @return boolean if removing user was successful or not
     */
    public boolean removeUser(String username) {
        String statement = "DELETE FROM `cs2340db`.`user` WHERE name = '" + username + "'";
        return executeInsert(statement);
    }

}
