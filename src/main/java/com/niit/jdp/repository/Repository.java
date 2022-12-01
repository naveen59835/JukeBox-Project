package com.niit.jdp.repository;

import com.niit.jdp.exception.PlaylistNotFound;
import com.niit.jdp.exception.SongNotFound;
import com.niit.jdp.model.Song;
import com.sun.source.doctree.ThrowsTree;

import java.util.List;

public interface Repository {
    List<Song> displayPlaylist() throws SongNotFound, PlaylistNotFound;

    List<Song> songSearchBySongName( List<Song> songList, String name);

    List<Song> songSearchByGenre(List<Song> songList, String genre);
}
