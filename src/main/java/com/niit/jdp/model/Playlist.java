/*
 * Author : Naveen Kumar
 * Date : 30-11-2022
 * Created With : IntelliJ IDEA Community Edition
 */

package com.niit.jdp.model;

import java.util.Objects;

public class Playlist extends Song {
    int playlistId;
    String playlistName;
    int songId;
    String songName;

    public Playlist() {
    }

    public Playlist(int playlistId, String playlistName, int songId, String songName) {
        this.playlistId = playlistId;
        this.playlistName = playlistName;
        this.songId = songId;
        this.songName = songName;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Playlist)) return false;
        Playlist playlist = (Playlist) o;
        return getPlaylistId() == playlist.getPlaylistId() && getSongId() == playlist.getSongId() && Objects.equals(getPlaylistName(), playlist.getPlaylistName()) && Objects.equals(getSongName(), playlist.getSongName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlaylistId(), getPlaylistName(), getSongId(), getSongName());
    }

    @Override
    public String toString() {
        return String.format("%-10s %-30s %-20s %-10s\n",playlistId,playlistName,songId,songName);
    }



}
