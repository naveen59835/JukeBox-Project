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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class MusicPlayerService {
    public void playSong(int songId){
        Scanner scanner=new Scanner(System.in);
        try {
            DatabaseConnectionService databaseConnectionService=new DatabaseConnectionService();
            Connection connection= databaseConnectionService.getConnectionToDatabase();
            String playQuery="Select*from`jukebox`.`song` where songId=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(playQuery);
            preparedStatement.setInt(1,songId);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                File file=new File(resultSet.getString("songPath"));
                AudioInputStream audioInputStream= AudioSystem.getAudioInputStream(file);
                Clip clip=AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                int userInput=0;
                do{
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println("press 1 to start|| 2 to pause ||3 to resume||0 to close music player");
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    userInput=scanner.nextInt();
                    scanner.nextLine();
                    switch (userInput){
                        case (1):
                            clip.start();
                            break;
                        case (2):
                            clip.stop();
                            break;
                        case(3):
                            clip.loop(1);
                            break;
                        case (0):
                            clip.close();
                            clip.stop();
                            break;
                        default:
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("Incorrect Input please try again");
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    }
                    }while (userInput>0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
