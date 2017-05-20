package saci.backend.mood;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Corina on 5/20/2017.
 */
@Repository
public interface MoodRepository extends MongoRepository<Mood, String> {
}
