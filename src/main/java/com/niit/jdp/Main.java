package com.niit.jdp;

import com.niit.jdp.exception.PlaylistNotFound;
import com.niit.jdp.exception.SongNotFound;
import com.niit.jdp.model.Song;
import com.niit.jdp.repository.PlaylistRepository;
import com.niit.jdp.repository.SongRepository;
import com.niit.jdp.service.DatabaseConnectionService;
import com.niit.jdp.service.MusicPlayerService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {
            DatabaseConnectionService databaseConnectionService = new DatabaseConnectionService();
            PlaylistRepository playlistRepository = new PlaylistRepository();
            MusicPlayerService musicPlayerService = new MusicPlayerService();
            SongRepository songRepository = new SongRepository();

            Scanner scanner = new Scanner(System.in);
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("Hello Welcome please enter your credentials to continue");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("-----------------------------");
            System.out.println("Please enter your username");
            System.out.println("-----------------------------");
            String username = scanner.next();
            System.out.println("----------------------------");
            System.out.println("please enter your password");
            System.out.println("----------------------------");
            String password = scanner.next();
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            List<Song> displayplaylist = songRepository.displaySongList();
            songRepository.display(displayplaylist);
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            if (username.equals("naveen") && password.equals("1234")) {
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("Welcome to the JukeBox Music Player");
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                databaseConnectionService.getConnectionToDatabase();
                databaseConnectionService.printConnectionStatus();
                System.out.println();
                int task = 0;
                do {
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println("Press 1 to search in playlist by genre or by name and Play It");
                    System.out.println("Press 2 to Play a Song");
                    System.out.println("Press 3 to create play list");
                    System.out.println("Press 4 to insert song into play List");
                    System.out.println("Press 5 to display playlist");
                    System.out.println("Press 6 to get song from SongList by id");
                    System.out.println("Press 7 to display song list including path");
                    System.out.println("Press 8 to Exit the Application");
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
                    task = scanner.nextInt();
                    System.out.println("=================================================");
                    System.out.println();
                    switch (task) {
                        case 1:
                            System.out.println("Press 1 to search song by genre or 2 to search song by name");
                            int choice = scanner.nextInt();
                            System.out.println("------------------------------------------------------------------------");
                            if (choice == 1) {
                                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
                                System.out.println("Enter the Genre");
                                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
                                String genre = scanner.next();
                                List<Song> getGenre = songRepository.songSearchByGenre(displayplaylist, genre);
                                songRepository.display(getGenre);
                                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                                System.out.println("If you want to play any song please press 1 or press 2 to exit");
                                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                                String option = scanner.next();
                                if (option.equals("1")) {
                                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                                    System.out.println("please enter the song id which you want play");
                                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                                    int id = scanner.nextInt();
                                    musicPlayerService.playSong(id);
                                    System.out.println();
                                } else {
                                    break;
                                }
                            } else if (choice == 2) {
                                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                                System.out.println("Enter the song name");
                                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                                String getSong = scanner.next();
                                List<Song> songList = songRepository.songSearchBySongName(displayplaylist, getSong);
                                songRepository.display(songList);
                                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                                System.out.println("If you want to play any song please press 1 or press 2 to exit");
                                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                                String option = scanner.next();
                                if (option.equals("1")) {
                                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                                    System.out.println("please enter the song id you want to play");
                                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                                    int id = scanner.nextInt();
                                    musicPlayerService.playSong(id);
                                    System.out.println();
                                } else {
                                    break;
                                }
                            }
                            break;

                        case 2:
                            songRepository.display(displayplaylist);
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("Enter the choice of Songs you want to play 0 to Exit");
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            int choice1 = scanner.nextInt();
                            musicPlayerService.playSong(choice1);
                            break;
                        case 3:
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("Enter the playlist id you want to add");
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            int playlistid = scanner.nextInt();
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("Enter the playlist name you want to add");
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            String playlistname = scanner.next();
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("Enter the song id you want to add");
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            int songid = scanner.nextInt();
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("Enter the name of the song you want to add");
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            String songname = scanner.next();
                            playlistRepository.createPlaylist(playlistid, playlistname, songid, songname);
                            break;
                        case 4:
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("Enter song id ");
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            int songid1 = scanner.nextInt();
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("Enter the song name");
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            String songName = scanner.next();
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("Enter the artist name");
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            String artist = scanner.next();
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("Enter the genre");
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            String genre = scanner.next();
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("Enter the duration");
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            String duration = scanner.next();
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("Enter the song path (Link)");
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            String link = scanner.next();
                            playlistRepository.insertSongIntoPlaylist(songid1, songName, artist, genre, duration, link);
                            break;
                        case 5:
                            List<Song> songList = playlistRepository.displayPlaylist();
                            System.out.println(songList);
                            break;
                        case 6:
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("Enter the id");
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            int id = scanner.nextInt();
                            List<Song> getSongFromList = playlistRepository.getSongFromplaylist(id, displayplaylist);
                            songRepository.display(getSongFromList);
                            break;
                        case 7:
                            List<Song> songs = songRepository.displaySongList();
                            System.out.println(songs);
                            break;
                        case 8:
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("Exited the application");
                            System.out.println("************THANK YOU VISIT AGAIN**************");
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    }

                } while (task < 8);

            } else {
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("Wrong user name or password please try again");
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            }
        } catch (SQLException | ClassNotFoundException | SongNotFound | PlaylistNotFound e) {
            throw new RuntimeException(e);
        }


    }
}
