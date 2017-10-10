package edu.gatech.cats.cats_2340.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import com.mysql.jdbc.Driver;

import android.content.Intent;
import android.util.Log;

import edu.gatech.cats.cats_2340.model.RatSighting;

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
    public static void initializeConnection(){
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
        } catch (Exception e){
            Log.d("ERROR", "Failed to Connect to Database!");
            Log.d("ERROR", "MSG: " + e.getMessage());
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

    public static void executeInsert(String statementString) {
        if (!isSQLInitialized()) return;
        Statement statement = null;
        try {
            statement = SQLconnection.createStatement();
            statement.execute(statementString);

        } catch(SQLException e) {
            Log.d("ERROR:", "Error executing statement: " + statementString);
        } finally {
            closeStatement(statement);
        }
    }

    private static boolean isSQLInitialized() {
        if (SQLconnection == null) {
            Log.d("ERROR", "Not connected to Database! Attempted to reinitialize");
            initializeConnection();
            return false;
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

    public List<RatSighting> getSightings() {
        return null;
    }
}
