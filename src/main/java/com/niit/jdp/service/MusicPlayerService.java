/*
 * Author : Naveen Kumar
 * Date : 30-11-2022
 * Created With : IntelliJ IDEA Community Edition
 */

package com.niit.jdp.service;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.NotActiveException;
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
                AudioInputStream audioInputStream= AudioSystem.getAudioInputStream(file);
                Clip clip=AudioSystem.getClip();
                clip.open(audioInputStream);
                String response=" ";
                while (response!="Z"){
                    System.out.println("P for play,S for stop,E for End");
                    response=scanner.next();
                    switch (response){
                        case ("P"):
                            clip.start();
                            break;
                        case ("S"):
                            clip.stop();
                            break;
                        case ("E"):
                            clip.stop();
                            break;
                        default:
                            System.out.println("Incorrect Input");


                        }
                    }
                }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
