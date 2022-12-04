import com.niit.jdp.exception.PlaylistNotFound;
import com.niit.jdp.exception.SongNotFound;
import com.niit.jdp.model.Song;
import com.niit.jdp.repository.PlaylistRepository;
import com.niit.jdp.repository.SongRepository;
import com.niit.jdp.service.DatabaseConnectionService;
import com.niit.jdp.service.MusicPlayerService;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

class JukeboxTest {
    PlaylistRepository playlistRepository;
    SongRepository songRepository;
    DatabaseConnectionService databaseConnectionService;
    MusicPlayerService musicPlayerService;
    List<Song> songList;

    @BeforeEach
    void setUp() throws Exception, SongNotFound, PlaylistNotFound {
        playlistRepository = new PlaylistRepository();
        songRepository = new SongRepository();
        databaseConnectionService = new DatabaseConnectionService();
        musicPlayerService = new MusicPlayerService();
        songList = songRepository.displaySongList();
    }

    @Before
    public void init() {
        databaseConnectionService.printConnectionStatus();
    }

    @AfterEach
    void tearDown() throws Exception, SongNotFound, PlaylistNotFound {
        databaseConnectionService = null;

    }

    @Test
    void getSongFromPlaylist() throws PlaylistNotFound {
        List<Song> songList1 = playlistRepository.getSongFromplaylist(9, songList);
        Assertions.assertEquals(1, songList1.size());
    }

    @Test
    void getSongFromPlaylistFail() throws PlaylistNotFound {
        List<Song> songs = playlistRepository.getSongFromplaylist(9, songList);
        Assertions.assertNotEquals(2, songs.size());
    }

    @Test
    void displayPlaylist() {
        List<Song> songList2 = playlistRepository.displayPlaylist();
        Assertions.assertEquals(9, songList2.size());
    }

    @Test
    void displayPlaylistFail() {
        List<Song> songs1 = playlistRepository.displayPlaylist();
        Assertions.assertNotEquals(1, songs1.size());
    }

    @Test
    void displaySongList() throws SongNotFound {
        List<Song> songList1 = songRepository.displaySongList();
        Assertions.assertEquals(10, songList1.size());
    }

    @Test
    void displaySongListFail() throws SongNotFound {
        List<Song> songList1 = songRepository.displaySongList();
        Assertions.assertNotEquals(1, songList1.size());
    }

    @Test
    void songSearchByGenre() throws SongNotFound {
        List<Song> songs = songRepository.songSearchByGenre(songList, "pop");
        assertEquals(2, songs.size());
    }

    @Test
    void songSearchByGenreFail() throws SongNotFound {
        List<Song> songs = songRepository.songSearchByGenre(songList, "bass");
        assertNotEquals(2, songs.size());
    }

    @Test
    void songSearchBySongName() throws SongNotFound {
        List<Song> songs = songRepository.songSearchBySongName(songList, "rolex1");
        assertEquals(1, songs.size());
    }

    @Test
    void songSearchBySongNameFail() throws SongNotFound {
        List<Song> songs = songRepository.songSearchBySongName(songList, "rolex1");
        assertNotEquals(2, songs.size());
    }

    @Test
    void display() throws SongNotFound {
        List<Song> songs = songRepository.displaySongList();
        assertEquals(10, songs.size());
    }

    @Test
    void displayFail() throws SongNotFound {
        List<Song> songs = songRepository.displaySongList();
        assertNotEquals(8, songs.size());
    }

    @Test
    void printConnection() {
        assertEquals(true, databaseConnectionService.printConnectionStatus());
    }

    @Test
    void printConnectionFail() {
        assertNotEquals(false, databaseConnectionService.printConnectionStatus());
    }


}