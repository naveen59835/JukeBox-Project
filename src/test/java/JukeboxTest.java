import com.niit.jdp.exception.PlaylistNotFound;
import com.niit.jdp.exception.SongNotFound;
import com.niit.jdp.repository.PlaylistRepository;
import com.niit.jdp.repository.SongRepository;
import com.niit.jdp.service.DatabaseConnectionService;
import com.niit.jdp.service.MusicPlayerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

class JukeboxTest {
    PlaylistRepository playlistRepository;
    SongRepository songRepository;
    DatabaseConnectionService databaseConnectionService;
    MusicPlayerService musicPlayerService;

    @BeforeEach
    void setUp() throws Exception, SongNotFound, PlaylistNotFound {


    }

    @AfterEach
    void tearDown() throws Exception, SongNotFound, PlaylistNotFound {

    }

}