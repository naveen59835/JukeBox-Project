/*
 * Author : Naveen Kumar
 * Date : 30-11-2022
 * Created With : IntelliJ IDEA Community Edition
 */

package com.niit.jdp.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionService {
    private static final String URL = "jdbc:mysql://localhost:3306/jukebox";
    private static final String USER = "root";
    private static final String PASS = "585858";
    Connection connection;

    public DatabaseConnectionService() {
        connection = null;
    }

    /**
     * @return load the driver
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(URL, USER, PASS);
    }

    /**
     * @return connection
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Connection getConnectionToDatabase() throws SQLException, ClassNotFoundException {
        getConnection();
        return connection;
    }

    public static void main(String[] args) {
        DatabaseConnectionService databaseConnectionService = new DatabaseConnectionService();
        try {
            databaseConnectionService.getConnectionToDatabase();
            databaseConnectionService.printConnectionStatus();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return print connection status
     */
    public Object printConnectionStatus() {
        if (connection != null) {
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("\u001B[32m Connection is Successful\u001B[0m");
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        } else {
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.err.println("Connection failed");
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        }
        return connection == null;
    }
}
