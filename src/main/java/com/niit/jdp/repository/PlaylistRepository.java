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

public class PlaylistRepository implements Repository {
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


    @Override
    public List<Song> displayAllSong() {
        return null;
    }

    @Override
    public List<Song> songSearchBySongName(List<Song> songList, String name) {
        return null;
    }

    @Override
    public List<Song> songSearchByGenre(List<Song> songList, String genre) {
        return null;
    }
}
