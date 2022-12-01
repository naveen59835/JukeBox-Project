package com.niit.jdp;

import com.niit.jdp.service.DatabaseConnectionService;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        DatabaseConnectionService databaseConnectionService=new DatabaseConnectionService();
        try {
            databaseConnectionService.getConnection();
            databaseConnectionService.printConnectionStatus();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}