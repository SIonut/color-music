package saci.backend.mood;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author Stănilă Ioan, 5/22/2017.
 */
@Service
public class ColorToMoodService {

    private static final List<Mood> COLOR_TO_MOOD_MAP = Arrays.asList(
            new Mood("#FF0000", "angry"),
            new Mood("#00FF00", "sad"),
            new Mood("#0000FF", "happy")
    );

    private final MoodRepository moodRepository;

    public ColorToMoodService(MoodRepository moodRepository) {
        this.moodRepository = moodRepository;
    }

    public void insert() {
        List<Mood> all = moodRepository.findAll();
        if (all.isEmpty()) {
            moodRepository.insert(COLOR_TO_MOOD_MAP);
        }
    }
}
