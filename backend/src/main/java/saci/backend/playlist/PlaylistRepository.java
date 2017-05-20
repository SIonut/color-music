package saci.backend.playlist;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Corina on 5/20/2017.
 */
@Repository
public interface PlaylistRepository extends MongoRepository<Playlist, String> {
}
