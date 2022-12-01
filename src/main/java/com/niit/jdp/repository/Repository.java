package com.niit.jdp.repository;

import com.niit.jdp.exception.PlaylistNotFound;
import com.niit.jdp.model.Playlist;
import com.niit.jdp.model.Song;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Repository {
    List<Song> displayAllSong();

    List<Song> songSearchBySongName(List<Song> songList, String name);

    List<Song> songSearchByGenre(List<Song> songList, String genre);
}
