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

    @BeforeEach
    void setUp() throws Exception, SongNotFound, PlaylistNotFound {
        playlistRepository = new PlaylistRepository();
        songRepository = new SongRepository();
        databaseConnectionService = new DatabaseConnectionService();
        musicPlayerService = new MusicPlayerService();
    }

    @AfterEach
    void tearDown() throws Exception, SongNotFound, PlaylistNotFound {

    }

    @Test
    void createPlaylist() throws Exception, PlaylistNotFound, SongNotFound {
        List<Song> playlistList = playlistRepository.displayPlaylist();
        Assertions.assertEquals(9, playlistList.size());

    }
}