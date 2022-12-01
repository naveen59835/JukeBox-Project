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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    public List<Song> displayPlaylist() throws SongNotFound {
        List<Song> songList=new ArrayList<>();
        String displayQuery="Select*from `jukebox`.`song`;";
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(displayQuery);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next());
            int songId=resultSet.getInt(1);
            String songName=resultSet.getString(2);
            String artistName=resultSet.getString(3);
            String genre=resultSet.getString(4);
            String duration=resultSet.getString(5);
            songList.add(new Song(songId,songName,artistName,genre,duration));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return songList;
    }

    @Override
    public List<Song> songSearchBySongName(List<Song> songList, String songName) {
        List<Song> songList1 = new ArrayList<>();
        for(Song song:songList){
            if(song.getSongName().equals(songName)){
                songList1.add(song);
            }
        }
        return songList1;
    }

    @Override
    public List<Song> songSearchByGenre(List<Song> songList, String genre) {
        List<Song> songList2=new ArrayList<>();
        for (Song song:songList){
            if(song.getGenre().equals(genre)){
                songList2.add(song);
            }

        }
        return songList2;
    }
    public void display(List<Song> songList){
        System.out.println("SongID,Song Name,ArtistName,Genre,Duration");
        for (Song song:songList){
            System.out.println(song.getSongId());
            System.out.println(song.getSongName());
            System.out.println(song.getArtistName());
            System.out.println(song.getGenre());
            System.out.println(song.getDuration());
        }
    }
}
