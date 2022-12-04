/*
 * Author : Naveen Kumar
 * Date : 30-11-2022
 * Created With : IntelliJ IDEA Community Edition
 */

package com.niit.jdp.repository;

import com.niit.jdp.exception.SongNotFound;
import com.niit.jdp.model.Song;
import com.niit.jdp.service.DatabaseConnectionService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class SongRepository implements Repository {
    Connection connection;
    DatabaseConnectionService databaseConnectionService;
    /**
     * @return get connection from the database connection service
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public SongRepository() throws SQLException, ClassNotFoundException {
        databaseConnectionService = new DatabaseConnectionService();
        connection = databaseConnectionService.getConnectionToDatabase();
    }
    /**
     * @return display song list
     * @throws SongNotFound
     */
    @Override
    public List<Song> displaySongList() throws SongNotFound {
        List<Song> songList = new ArrayList<>();
        String displayQuery = "Select * from `jukebox`.`song`;";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(displayQuery)) {
            while (resultSet.next()) {
                int songId = resultSet.getInt("songId");
                String songName = resultSet.getString("songName");
                String artistName = resultSet.getString("artistName");
                String genre = resultSet.getString("genre");
                String duration = resultSet.getString("duration");
                String songPath = resultSet.getString("songPath");
                songList.add(new Song(songId, songName, artistName, genre, duration, songPath));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songList;
    }
    /**
     * @param songList
     * @param songName search song by songName
     * @return return song details by getting songName as input
     * @throws SongNotFound
     */
    @Override
    public List<Song> songSearchBySongName(List<Song> songList, String songName) throws SongNotFound {
        if (songList == null) {
            throw new SongNotFound("The song not found");
        }
        List<Song> songList1 = new ArrayList<>();
        for (Song song : songList) {
            if (song.getSongName().equals(songName)) {
                songList1.add(song);
            }
        }
        return songList1;

    }
    /**
     * @param songList
     * @param genre    parameter which gives song details
     * @return return song details by getting genre as input
     * @throws SongNotFound
     */

    @Override
    public List<Song> songSearchByGenre(List<Song> songList, String genre) throws SongNotFound {
        if (songList == null) {
            throw new SongNotFound("Song not found");
        }
        List<Song> songList2 = new ArrayList<>();
        for (Song song : songList) {
            if (song.getGenre().equals(genre)) {
                songList.add(song);
            }

        }
        return songList2;
    }

    /**
     * @param songList
     * @return displays the song list in the below specified format (tabular format)
     */
    public void display(List<Song> songList) {
        System.out.println("The details of the songs are");
        for (Song song : songList) {
            System.out.format("%-10d %-30s %-20s %-20s %-30s\n", song.getSongId(), song.getSongName(), song.getArtistName(), song.getDuration(), song.getGenre());
        }
    }
}
