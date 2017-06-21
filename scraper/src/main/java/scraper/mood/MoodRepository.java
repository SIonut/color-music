package scraper.mood;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoodRepository extends MongoRepository<Mood, String> {
}
