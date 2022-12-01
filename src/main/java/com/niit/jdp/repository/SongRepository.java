/*
 * Author : Naveen Kumar
 * Date : 30-11-2022
 * Created With : IntelliJ IDEA Community Edition
 */

package com.niit.jdp.repository;

import com.niit.jdp.model.Song;
import com.niit.jdp.service.DatabaseConnectionService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SongRepository implements Repository {
    Connection connection;
    DatabaseConnectionService databaseConnectionService;

    public SongRepository() throws SQLException, ClassNotFoundException {
        databaseConnectionService = new DatabaseConnectionService();
        connection = databaseConnectionService.getConnectionToDatabase();

    }
    @Override
    public List<Song> displayAllSong() {
        List<Song> songList=new ArrayList<>();

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
