/*
 * Author : Naveen Kumar
 * Date : 30-11-2022
 * Created With : IntelliJ IDEA Community Edition
 */

package com.niit.jdp.repository;

import com.niit.jdp.exception.PlaylistNotFound;
import com.niit.jdp.model.Playlist;
import com.niit.jdp.model.Song;
import com.niit.jdp.service.DatabaseConnectionService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistRepository {
    Connection connection;
    DatabaseConnectionService databaseConnectionService;

    public PlaylistRepository() throws SQLException, ClassNotFoundException {
        databaseConnectionService = new DatabaseConnectionService();
        connection = databaseConnectionService.getConnectionToDatabase();

    }

    public void createPlaylist(int playlistId, String playlistName, int songId, String songName) {
        String insertQuery = "Insert into`jukebox`.`playlist`(playlistId,playlistName,songId) Values=(? ? ?);";
        int numberOfRowsAffected;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, playlistId);
            preparedStatement.setString(2, playlistName);
            preparedStatement.setInt(3, songId);
            preparedStatement.setString(4, songName);
            numberOfRowsAffected = preparedStatement.executeUpdate();
            if (numberOfRowsAffected == 1) {
                System.out.println("Playlist successfully Created");
            } else {
                System.out.println("Playlist creation failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Song> displayPlaylist() {
        List<Song> playList = new ArrayList<>();
        String displayQuery = "SELECT * FROM `jukebox`.`song`;";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(displayQuery);
            while (resultSet.next()) {
                int songId = resultSet.getInt("songId");
                String songName = resultSet.getString("songName");
                String artistName = resultSet.getString("artistName");
                String genre=resultSet.getString("genre");
                String duration=resultSet.getString("duration");
                Song song = new Song(songId,songName,artistName,genre,duration);
                playList.add(song);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return playList;
    }
    public  List<Song>getSongFromList(int playlistId, List<Song> songList){
        List<Song> getSongFromList= new ArrayList<>();
        String query="Select*from `jukebox`.`playlist` where (`playlistId`=?);";
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(query);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                int playListId=resultSet.getInt(1);
                String playlistName=resultSet.getString(2);
                int songId=resultSet.getInt(3);
                String songName=resultSet.getString(4);
                for (Song song:songList){
                    if(song.getSongId()==songId){
                        getSongFromList.add(song);
                    }
                }
            }
            if(getSongFromList==null){
                throw new PlaylistNotFound("The following playlist is empty");
            }
        } catch (SQLException | PlaylistNotFound e) {
            throw new RuntimeException(e);
        }
        return getSongFromList;
    }
    public void insertSongIntoList(int playlistId,int songId){
        String insertQuery="insert into `jukebox`.`playlist`(`playlistId`,`songId`)"+"values(?, ?);";
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1,playlistId);

            preparedStatement.setInt(3,songId);

            int prepare= preparedStatement.executeUpdate();
            if(prepare==1){
                System.out.println("song successfully added to the playlist");

            }else{
                System.out.println("Adding song failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
