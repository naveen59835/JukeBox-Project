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
            Connection connection= databaseConnectionService.getConnectionToDatabase();
            String playQuery="Select*from`jukebox`.`song` where songId=?;";
            PreparedStatement preparedStatement= connection.prepareStatement(playQuery);
            preparedStatement.setInt(1,songId);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                File file=new File(resultSet.getString("songPath"));
                AudioInputStream audioInputStream= AudioSystem.getAudioInputStream(file);
                Clip clip=AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                String userInput=null;
                while (userInput!="Z"){
                    System.out.println(" ||1 for play ||2 for pause ||3 for resume ||4 for Close|| ");
                    userInput=scanner.next();
                    switch (userInput){
                        case ("1"):
                            clip.start();
                            break;
                        case ("2"):
                            clip.stop();
                            break;
                        case("3"):
                            clip.loop(1);
                            break;
                        case ("4"):
                            clip.close();
                            break;
                        default:
                            System.out.println("Incorrect Input please try again");
                    }
                    }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
