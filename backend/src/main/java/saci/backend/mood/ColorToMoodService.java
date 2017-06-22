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
            new Mood("00CC00", "energized"),
            new Mood("99FF33", "proud"),
            new Mood("351423", "angry"),
            new Mood("9999FF", "sad"),
            new Mood("870000", "furious"),
            new Mood("834404", "pessimistic"),
            new Mood("666600", "hurt"),
            new Mood("FF9999", "optimistic"),
            new Mood("FF0000", "excited"),
            new Mood("CE3DFF", "loved"),
            new Mood("CCCC00", "exhausted"),
            new Mood("6666FF", "fabulous"),
            new Mood("66B2FF", "happy"),
            new Mood("6976B7", "lost"),
            new Mood("330066", "emotional"),
            new Mood("FFFF09", "strong"),
            new Mood("99FF00", "joyful"),
            new Mood("FF9900", "crazy"),
            new Mood("FFFF99", "lazy"),
            new Mood("99FFFF", "chill")
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
