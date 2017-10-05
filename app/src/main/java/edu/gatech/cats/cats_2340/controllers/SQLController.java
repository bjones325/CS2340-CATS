package edu.gatech.cats.cats_2340.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import android.content.Intent;
import android.util.Log;

/**
 * Created by acer_ on 10/5/2017.
 */

public class SQLController {

    private static String username = "acer";
    private static String password = "cs2340SQL";
    private static String dbName = "cs2340";
    private static String serverName = "127.0.0.1";
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
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://" +
                            serverName +
                            ":" + portNumber +
                            "/" + dbName + "?autoReconnect=true&useSSL=false",
                    connectionProps);

            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            SQLconnection = conn;
            Log.d("INFO", "Initialized Connection to Database");
        } catch (SQLException e){
            Log.d("ERROR", "Failed to Connect to Database!");
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
}
