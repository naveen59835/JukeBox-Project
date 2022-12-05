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
import java.util.Collections;
import java.util.Comparator;
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
    public static void main(String[] args) throws SQLException, ClassNotFoundException, SongNotFound {
        SongRepository songRepository = new SongRepository();
        List<Song> songs = songRepository.displaySongList();
        for (Song song : songs) {
            System.out.println(song);
        }
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
     * @return display song list
     * @throws SongNotFound
     */
    @Override
    public List<Song> displaySongList() throws SongNotFound {
        List<Song> songList = new ArrayList<>();
        if (songList == null) {
            throw new SongNotFound("The values is null");
        }
        String displayQuery = "Select `songId`,`songName`,`artistName`,`genre`,`duration` from `jukebox`.`song`;";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(displayQuery)) {
            while (resultSet.next()) {
                int songId = resultSet.getInt(1);
                String songName = resultSet.getString(2);
                String artistName = resultSet.getString(3);
                String genre = resultSet.getString(4);
                String duration = resultSet.getString(5);
                Song song = new Song(songId, songName, artistName, genre, duration);
                songList.add(song);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songList;
    }

    /**
     * @param songList get song list as parameter type
     * @return returns song list by sorted order by name
     */

    public List<Song> sortSongsBySongName(List<Song> songList) {
        Comparator<Song> comparator = (o1, o2) -> String.CASE_INSENSITIVE_ORDER.compare(o1.getSongName(), o2.getSongName());
        Collections.sort(songList, comparator);
        return songList;
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
                songList2.add(song);
            }

        }
        return songList2;
    }

}
