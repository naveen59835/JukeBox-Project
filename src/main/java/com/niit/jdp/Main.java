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
import java.util.Random;
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
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println("Press 1 to Search in Playlist by Genre or by Name and Play It");
                    System.out.println("Press 2 to Play a Song");
                    System.out.println("Press 3 to Create PlayList");
                    System.out.println("Press 4 to Insert Song into PlayList");
                    System.out.println("Press 5 to Display PlayList");
                    System.out.println("Press 6 to get song from SongList by Id");
                    System.out.println("Press 7 to Display SongList");
                    System.out.println("Press 8 to Shuffle Songs & Play A Random Song");
                    System.out.println("Press 9 to Sort Songs By Song Name");
                    System.out.println("Press 10 to Exit the Application");
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    task = scanner.nextInt();
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
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
                                Song getGenre = songRepository.songSearchByGenre(displayplaylist, genre);
                                if (!genre.equals(getGenre.getGenre())) {
                                    System.err.println("The Genre Not Found Please Try Again");
                                } else {
                                    System.out.println(getGenre);
                                }

                                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                                System.out.println("If you want to play any Song please press 1 or press 2 to exit");
                                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                                String option = scanner.next();
                                if (option.equals("1")) {
                                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                                    System.out.println("please enter the song ID which you want play");
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
                            System.out.println("If you want to Play a Song press 1 or O to Exit");
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            int choice3 = scanner.nextInt();
                            scanner.nextLine();
                            switch (choice3) {
                                case 1:
                                    System.out.println("Choose From the Menu");
                                    int Id = scanner.nextInt();
                                    musicPlayerService.playSong(Id);
                                    break;
                                case 0:
                                    break;
                                default:
                                    System.out.println("Invalid Option Please Try Again");
                                    break;
                            }
                            break;
                        case 3:
                            List<Song> songList = playlistRepository.displayPlaylist();
                            for (Song song : songList) {
                                System.out.println(song);
                            }
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("Please Note the playlist id is Auto Generated ");
                            System.out.println("Insert other values and SongID you want to add (DO NOT REPEAT THE SONG ID)");
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("Enter the playlist name you want to add");
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            String playlistname = scanner.next();
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("Enter the SONG ID you want to add");
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            int songid = scanner.nextInt();
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("Enter the name of the song you want to add");
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            String songname = scanner.next();
                            playlistRepository.createPlaylist(playlistname, songid, songname);
                            break;
                        case 4:
                            List<Song> songs = songRepository.displaySongList();
                            for (Song song : songs) {
                                System.out.println(song);
                            }
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("Please Note the SONG ID is Auto Generated ");
                            System.out.println("Insert only Other Details");
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
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
                            playlistRepository.insertSongIntoPlaylist(songName, artist, genre, duration, link);
                            break;
                        case 5:
                            List<Song> songList1 = playlistRepository.displayPlaylist();
                            for (Song song : songList1) {
                                System.out.println(song);
                            }
                            break;
                        case 6:
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("Enter the Song ID");
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            int id = scanner.nextInt();
                            List<Song> getSongFromList = playlistRepository.getSongFromplaylist(id, displayplaylist);
                            songRepository.display(getSongFromList);
                            break;
                        case 7:
                            List<Song> songs1 = songRepository.displaySongList();
                            for (Song song : songs1) {
                                System.out.println(song);
                            }
                            break;
                        case 8:
                            //   songRepository.display(displayplaylist);
                            System.out.println("++++++++++++++++++++++++++++++++");
                            System.out.println("Randomly playing a song");
                            System.out.println("++++++++++++++++++++++++++++++++");
                            Random random = new Random();
                            int rand = random.nextInt(10);
                            System.out.println("Now Playing SONG ID =" + rand);
                            System.out.println("Now Playing :-");
                            List<Song> songFromplaylist = playlistRepository.getSongFromplaylist(rand, displayplaylist);
                            songRepository.display(songFromplaylist);
                            musicPlayerService.playSong(rand);

                            break;
                        case 9:
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("Sorted list of Song Name :");
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            List<Song> songs12 = songRepository.displaySongList();
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("The Sorted List of Songs are");
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            List<Song> songs2 = songRepository.sortSongsBySongName(songs12);
                            for (Song song : songs2) {
                                System.out.println(song);
                            }
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

                            break;
                        case 10:
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("Exited the application");
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("**************THANK YOU VISIT AGAIN***************");
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            break;
                        default:
                            System.out.println("Wrong Command Try again");
                            break;
                    }

                } while (task < 10);


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
