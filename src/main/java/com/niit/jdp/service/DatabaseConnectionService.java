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
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(URL, USER, PASS);
    }

    /**
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Connection getConnectionToDatabase() throws SQLException, ClassNotFoundException {
        getConnection();
        return connection;
    }

    /**
     *
     */
    public void printConnectionStatus() {
        if (connection != null) {
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("Connection is Successful");
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        } else {
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("Connection failed");
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        }
    }
}
