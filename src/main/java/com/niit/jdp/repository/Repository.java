package com.niit.jdp.repository;

import com.niit.jdp.exception.PlaylistNotFound;
import com.niit.jdp.exception.SongNotFound;
import com.niit.jdp.model.Song;

import java.util.List;

public interface Repository {
    /**
     * @return displays song list
     * @throws SongNotFound
     * @throws PlaylistNotFound
     */
    List<Song> displaySongList() throws SongNotFound, PlaylistNotFound;

    /**
     * @param songList
     * @param name
     * @return search by song name and return song detiails
     * @throws SongNotFound
     */

    List<Song> songSearchBySongName(List<Song> songList, String name) throws SongNotFound;

    /**
     * @param songList
     * @param genre
     * @return search song by genre and return details
     * @throws SongNotFound
     */

    List<Song> songSearchByGenre(List<Song> songList, String genre) throws SongNotFound;
}
