/*
 * Author : Naveen Kumar
 * Date : 30-11-2022
 * Created With : IntelliJ IDEA Community Edition
 */

package com.niit.jdp.service;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class MusicPlayerService {
    public void getSongById(int songId){
        Scanner scanner=new Scanner(System.in);
        try {
            DatabaseConnectionService databaseConnectionService=new DatabaseConnectionService();
            Connection connection= databaseConnectionService.connection;
            String playQuery="Select*from`jukebox`.`songs` where songId=?;";
            PreparedStatement preparedStatement= connection.prepareStatement(playQuery);
            preparedStatement.setInt(1,songId);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                File file=new File(resultSet.getString(1));

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
