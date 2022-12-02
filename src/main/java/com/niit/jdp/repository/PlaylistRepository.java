/*
 * Author : Naveen Kumar
 * Date : 30-11-2022
 * Created With : IntelliJ IDEA Community Edition
 */

package com.niit.jdp.repository;

import com.niit.jdp.exception.SongNotFound;
import com.niit.jdp.model.Playlist;
import com.niit.jdp.model.Song;
import com.niit.jdp.service.DatabaseConnectionService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlaylistRepository {
    Connection connection;
    DatabaseConnectionService databaseConnectionService;

    public PlaylistRepository() throws SQLException, ClassNotFoundException {
        databaseConnectionService = new DatabaseConnectionService();
        connection = databaseConnectionService.getConnectionToDatabase();

    }

    public void createPlaylist(int playlistId, String playlistName, int songId, String songName) {
        String insertQuery = "Insert into`jukebox`.`playlist`(playlistId,playlistName,songId,songName) Values (?, ?, ?,?);";
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
    public List<Song> getSongFromList(int songId, List<Song> songList) {
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


    public void insertSongIntoList(int songId, String songName, String artistName, String genre, String duration, String songPath) {
        String insertQuery = "insert into `jukebox`.`song`(`songId`,`songName`,`artistName`,`genre`,`duration`,`songPath`)" + "values(?,?,?,?,?,?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, songId);
            preparedStatement.setString(2, songName);
            preparedStatement.setString(3, artistName);
            preparedStatement.setString(4, genre);
            preparedStatement.setString(5, duration);
            preparedStatement.setString(6, songPath);

            int prepare = preparedStatement.executeUpdate();
            if (prepare == 1) {
                System.out.println("song successfully added to the playlist");

            } else {
                System.out.println("Adding song failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, RuntimeException, SongNotFound,NullPointerException{
        SongRepository songRepository=new SongRepository();
        PlaylistRepository playlistRepository=new PlaylistRepository();
        List<Song> displayplaylist = songRepository.displaySongList();
        songRepository.display(displayplaylist);

  //      List<Song> getPlayListName = playlistRepository.displayPlaylist();
//        for (Song playList : getPlayListName) {
//            System.out.println(playList.getSongId() + " " + playList.getSongName());
//        }
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the id");
        int id=scanner.nextInt();
        List<Song> getSongFromList = playlistRepository.getSongFromList(id, displayplaylist);
        songRepository.display(getSongFromList);
    }
    }

