import com.niit.jdp.exception.PlaylistNotFound;
import com.niit.jdp.exception.SongNotFound;
import com.niit.jdp.model.Song;
import com.niit.jdp.repository.PlaylistRepository;
import com.niit.jdp.repository.SongRepository;
import com.niit.jdp.service.DatabaseConnectionService;
import com.niit.jdp.service.MusicPlayerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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

    @AfterEach
    void tearDown() throws Exception, SongNotFound, PlaylistNotFound {

    }

    @Test
    void createPlaylist() throws Exception, PlaylistNotFound, SongNotFound {
        List<Song> playlistList = playlistRepository.displayPlaylist();
        Assertions.assertEquals(9, playlistList.size());
    }

    @Test
    void createPlaylistFail() throws Exception, PlaylistNotFound, SongNotFound {
        List<Song> playlist = playlistRepository.displayPlaylist();
        Assertions.assertNotEquals(10, playlist.size());

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
}