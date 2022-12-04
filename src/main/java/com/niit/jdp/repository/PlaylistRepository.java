/*
 * Author : Naveen Kumar
 * Date : 30-11-2022
 * Created With : IntelliJ IDEA Community Edition
 */

package com.niit.jdp.repository;

import com.niit.jdp.exception.PlaylistNotFound;
import com.niit.jdp.exception.SongNotFound;
import com.niit.jdp.model.Playlist;
import com.niit.jdp.model.Song;
import com.niit.jdp.service.DatabaseConnectionService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlaylistRepository {
    Connection connection;
    DatabaseConnectionService databaseConnectionService;

    /**
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public PlaylistRepository() throws SQLException, ClassNotFoundException {
        databaseConnectionService = new DatabaseConnectionService();
        connection = databaseConnectionService.getConnectionToDatabase();
    }

    /**
     * @param args main methods
     * @return for checking the get song by id .I used the main method here
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws RuntimeException
     * @throws SongNotFound
     * @throws NullPointerException
     * @throws PlaylistNotFound
     */

//    public static void main(String[] args) throws SQLException, ClassNotFoundException, RuntimeException, SongNotFound, NullPointerException, PlaylistNotFound {
//        SongRepository songRepository = new SongRepository();
//        PlaylistRepository playlistRepository = new PlaylistRepository();
//        List<Song> displayplaylist = songRepository.displaySongList();
//        songRepository.display(displayplaylist);
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter the id");
//        int id = scanner.nextInt();
//        List<Song> getSongFromList = playlistRepository.getSongFromplaylist(id, displayplaylist);
//        songRepository.display(getSongFromList);
//    }

    public static void main(String[] args) {
        Random random = new Random();
        int randomIndex = random.nextInt(10);
        System.out.println(randomIndex);
    }

    /**
     * @return displays playlist from the playlist table
     */
    public List<Song> displayPlaylist() {
        List<Song> playList = new ArrayList<>();
        String displayQuery = "SELECT * FROM `jukebox`.`playlist`;";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(displayQuery);
            while (resultSet.next()) {
                int playlistId = resultSet.getInt("playlistId");
                String playlistName = resultSet.getString("playlistName");
                int songId = resultSet.getInt("songId");
                String songName = resultSet.getString("songName");

                playList.add(new Playlist(playlistId, playlistName, songId, songName));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return playList;
    }

    /**
     * @param playlistId   creating playlist id by getting it
     * @param playlistName creating playlist name
     * @param songId       creating songid
     * @param songName     creating songname
     * @return if number of rows affected=1 then the playlist is created else not created
     * @throws PlaylistNotFound throws custom exception
     */
    public boolean createPlaylist(int playlistId, String playlistName, int songId, String songName) throws PlaylistNotFound {
        if (playlistName == null && songId == 0 && songName == null) {
            throw new PlaylistNotFound("Playlist not found");
        }
        String insertQuery = "Insert into`jukebox`.`playlist`(playlistId,playlistName,songId,songName) Values (?, ?, ?,?);";
        int numberOfRowsAffected;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, playlistId);
            preparedStatement.setString(2, playlistName);
            preparedStatement.setInt(3, songId);
            preparedStatement.setString(4, songName);
            numberOfRowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return numberOfRowsAffected > 0;
    }

    /**
     * @param songId        get song by songid
     * @param songList-from the song list
     * @return return song details by using only songID
     * @throws PlaylistNotFound
     */
    public List<Song> getSongFromplaylist(int songId, List<Song> songList) throws PlaylistNotFound {
        if (songId == 0 && songList == null) {
            throw new PlaylistNotFound("Playlist not found");
        }
        List<Song> getSong = new ArrayList<>();
        String query = "Select * from song where songId = " + songId;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int songId1 = resultSet.getInt("songId");
                String songName = resultSet.getString("songName");
                for (Song song : songList) {
                    if (songId1 == song.getSongId()) {
                        getSong.add(song);
                    }
                }
            }
            if (getSong == null) {
                System.out.println("The following playlist is empty");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return getSong;
    }

    /**
     * @param songId     insert song by using song id
     * @param songName   insert song by mnetioning song name
     * @param artistName insert song by mentioning artistname
     * @param genre      insert song using genre name
     * @param duration   insert song using duration
     * @param songPath   insert song path by mentioning durationb
     * @return song successfully added if number of rows affected=1
     * @throws PlaylistNotFound
     */
    public boolean insertSongIntoPlaylist(int songId, String songName, String artistName, String genre, String duration, String songPath) throws PlaylistNotFound {
        if (songId == 0 && songName == null && artistName == null && genre == null && duration == null && songPath == null) {
            throw new PlaylistNotFound("Playlist not found");
        }
        String insertQuery = "insert into `jukebox`.`song`(`songId`,`songName`,`artistName`,`genre`,`duration`,`songPath`) values (?,?,?,?,?,?);";
        int numberOfRowsAffected;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, songId);
            preparedStatement.setString(2, songName);
            preparedStatement.setString(3, artistName);
            preparedStatement.setString(4, genre);
            preparedStatement.setString(5, duration);
            preparedStatement.setString(6, songPath);

            numberOfRowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return numberOfRowsAffected > 0;
    }
}

