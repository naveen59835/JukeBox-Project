/*
 * Author : Naveen Kumar
 * Date : 30-11-2022
 * Created With : IntelliJ IDEA Community Edition
 */

package com.niit.jdp.repository;

import com.niit.jdp.exception.PlaylistNotFound;
import com.niit.jdp.model.Playlist;
import com.niit.jdp.service.DatabaseConnectionService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistRepository implements Repository {
    Connection connection;
    DatabaseConnectionService databaseConnectionService;
    public PlaylistRepository () throws SQLException, ClassNotFoundException {
        databaseConnectionService=new DatabaseConnectionService();
        connection=databaseConnectionService.getConnectionToDatabase();

    }
    public boolean createPlaylist(int playlistId,String playlistName,int songId){
        String insertQuery="Insert into`jukebox`.`playlist`(playlistId,playlistName,songId) Values=(? ? ?);";
        int numberOfRowsAffected ;
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1,playlistId);
            preparedStatement.setString(2,playlistName);
            preparedStatement.setInt(3,songId);
            numberOfRowsAffected=preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return numberOfRowsAffected>0;

    }


    @Override
    public List<Playlist> getAll() throws SQLException,PlaylistNotFound {
        List<Playlist> playlists = new ArrayList<>();
        String getQuery="Select*from `jukebox`.`playlist`;";
        try {
            Statement statement= connection.createStatement();
            ResultSet resultSet= statement.executeQuery(getQuery);
            while (resultSet.next()){
                int playlistId=resultSet.getInt("playlistId");
                String playlistName=resultSet.getString("playlistName");
                int songId=resultSet.getInt("songId");
                Playlist playlist=new Playlist(playlistId,playlistName,songId);
                playlists.add(playlist);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return playlists;
    }

    @Override
    public Playlist getBySongId(int songId) throws SQLException,PlaylistNotFound {
        String selectQuery="select*from `jukebox`.`"
        return null;
    }

    @Override
    public boolean deleteBySongId(int songId) throws SQLException,PlaylistNotFound {
        return false;
    }
}
