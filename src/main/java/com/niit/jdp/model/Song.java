/*
 * Author : Naveen Kumar
 * Date : 30-11-2022
 * Created With : IntelliJ IDEA Community Edition
 */

package com.niit.jdp.model;

import java.util.Objects;

public class Song {
    private int songId;
    private String songName;
    private String artistName;
    private String genre;
    private String duration;
    private String songPath;

    public Song() {
    }

    public Song(int songId, String songName, String artistName, String genre, String duration, String songPath) {
        this.songId = songId;
        this.songName = songName;
        this.artistName = artistName;
        this.genre = genre;
        this.duration = duration;
        this.songPath = songPath;
    }

    public Song(int songId, String songName, String artistName, String genre, String duration) {
        this.songId = songId;
        this.songName = songName;
        this.artistName = artistName;
        this.genre = genre;
        this.duration = duration;
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

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSongPath() {
        return songPath;
    }

    public void setSongPath(String songPath) {
        this.songPath = songPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song)) return false;
        Song song = (Song) o;
        return getSongId() == song.getSongId() && Objects.equals(getSongName(), song.getSongName()) && Objects.equals(getArtistName(), song.getArtistName()) && Objects.equals(getGenre(), song.getGenre()) && Objects.equals(getDuration(), song.getDuration()) && Objects.equals(getSongPath(), song.getSongPath());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSongId(), getSongName(), getArtistName(), getGenre(), getDuration(), getSongPath());
    }

    @Override
    public String toString() {
        return String.format("%-10s %-30s %-20s %-20s %-30s\n", songId, songName, artistName, genre, duration);
    }
}

