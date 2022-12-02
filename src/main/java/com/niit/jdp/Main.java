package com.niit.jdp;

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
    public static void main(String[] args){

        try {
            DatabaseConnectionService databaseConnectionService = new DatabaseConnectionService();
            PlaylistRepository playlistRepository = new PlaylistRepository();
            MusicPlayerService musicPlayerService = new MusicPlayerService();
            SongRepository songRepository = new SongRepository();

            Scanner scanner = new Scanner(System.in);
            System.out.println("Hello Welcome please enter your credentials to continue");
            System.out.println("============================================");
            System.out.println("Please enter your username");
            String username = scanner.next();
            System.out.println("please enter your password");
            String password = scanner.next();
            List<Song> displayplaylist = songRepository.displaySongList();
            songRepository.display(displayplaylist);
            if (username.equals("naveen") && password.equals("1234")) {
                System.out.println("===========================================");
                System.out.println("Welcome to the JukeBox Music Player");
                System.out.println("===========================================");
                databaseConnectionService.getConnectionToDatabase();
                databaseConnectionService.printConnectionStatus();
                System.out.println();
                int task = 0;
                do {

                    System.out.println("Press 1 to search in playlist by genre or name");
                    System.out.println("Press 2 to Play a Song");
                    System.out.println("Press 3 to create play list");
                    System.out.println("Press 4 to insert song into play List");
                    System.out.println("Press 5 to display play list");
                    System.out.println("Press 6 to get song from list by id");
                    System.out.println("Press 7 to display song list including path");
                    System.out.println("Press 8 to Exit");
                    task = scanner.nextInt();
                    System.out.println("============================================");
                    System.out.println();
                    switch (task) {
                        case 1:
                            System.out.println("Press 1 to search song by Genre");
                            System.out.println("Press 2 to search song by name");
                            int choice = scanner.nextInt();
                            if (choice == 1) {
                                System.out.println("Enter Genre name ::");
                                String genre = scanner.next();
                                List<Song> getGenre1 = songRepository.songSearchByGenre(displayplaylist, genre);
                                if (genre.equals(getGenre1)){
                                    System.out.println("The genre is not there in the playlist");
                                }else{
                                    System.out.println("The genre is in the playlist");
                                }

                            } else if (choice == 2) {
                                System.out.println("Enter the song name");
                                String getSong = scanner.next();
                                List<Song> songList = songRepository.songSearchBySongName(displayplaylist, getSong);
                                System.out.println("The specific song is the list "+getSong);
                            }
                            break;
                        case 2:
                            songRepository.display(displayplaylist);
                            System.out.println("Enter the choice of Songs you want to play");
                            int choice1 = scanner.nextInt();
                            musicPlayerService.getSongById(choice1);
                            break;
                        case 3:
                            System.out.println("Enter the playlist id you want to add");
                            int playlistid = scanner.nextInt();
                            System.out.println("Enter the playlist name you want to add");
                            String playlistname = scanner.next();
                            System.out.println("Enter the song id you want to add");
                            int songid = scanner.nextInt();
                            System.out.println("Enter the name of the song you want to add");
                            String songname = scanner.next();
                            playlistRepository.createPlaylist(playlistid, playlistname, songid, songname);
                            break;
                        case 4:
                            System.out.println("Enter song id ");
                            int songid1 = scanner.nextInt();
                            System.out.println("Enter the song name");
                            String songName = scanner.next();
                            System.out.println("Enter the artist name");
                            String artist= scanner.next();
                            System.out.println("Enter the genre");
                            String genre=scanner.next();
                            System.out.println("Enter the duration");
                            String duration=scanner.next();
                            System.out.println("Enter the song path (Link)");
                            String link= scanner.next();
                            playlistRepository.insertSongIntoList(songid1,songName,artist,genre,duration,link);
                            break;
                        case 5:
                            List<Song> songList = playlistRepository.displayPlaylist();
                            System.out.println(songList);
                            break;
                        case 6:
                            System.out.println("Enter the id");
                            int id=scanner.nextInt();
                            List<Song> getSongFromList = playlistRepository.getSongFromList(id, displayplaylist);
                            songRepository.display(getSongFromList);
                            break;

                        case 7:
                            List<Song> songs12=songRepository.displaySongList();
                            System.out.println(songs12);
                            break;
                        case 8:
                            System.out.println("Exited the application");
                    }

                }
                while (task < 8);

            } else {
                System.out.println("Wrong user name or password please try again");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException exception) {
            throw new RuntimeException(exception);
        } catch (SongNotFound exc) {
            throw new RuntimeException(exc);
        }


    }
}
