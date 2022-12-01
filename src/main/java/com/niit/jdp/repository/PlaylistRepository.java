/*
 * Author : Naveen Kumar
 * Date : 30-11-2022
 * Created With : IntelliJ IDEA Community Edition
 */

package com.niit.jdp.repository;

import com.niit.jdp.model.Playlist;
import com.niit.jdp.service.DatabaseConnectionService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PlaylistRepository implements Repository {
    Connection connection;
    DatabaseConnectionService databaseConnectionService;
    public PlaylistRepository () throws SQLException, ClassNotFoundException {
        databaseConnectionService=new DatabaseConnectionService();
        connection=databaseConnectionService.getConnectionToDatabase();

    }


    @Override
    public List<Playlist> getAll(Connection connection) throws SQLException {
        return null;
    }

    @Override
    public Playlist getBySongId(Connection connection, int songId) throws SQLException {
        return null;
    }

    @Override
    public boolean deleteBySongId(Connection connection, int songId) throws SQLException {
        return false;
    }
}
