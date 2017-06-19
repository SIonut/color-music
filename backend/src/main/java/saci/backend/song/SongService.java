package saci.backend.song;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saci.backend.eight.tracks.EightTracksService;
import saci.backend.mood.Mood;
import saci.backend.mood.MoodDto;
import saci.backend.mood.MoodRepository;
import saci.backend.mood.MoodService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Stănilă Ioan, 5/22/2017.
 */
@Service
public class SongService {

    private final MoodService moodService;
    private final SongRepository songRepository;
    private final EightTracksService eightTracksService;

    @Autowired
    public SongService(MoodService moodService,
                       SongRepository songRepository,
                       EightTracksService eightTracksService) {
        this.moodService = moodService;
        this.songRepository = songRepository;
        this.eightTracksService = eightTracksService;
    }

    public List<SongDto> findOnline(List<String> colors) {
        List<String> moods = colors.stream()
                .map(moodService::findByColor)
                .filter(Objects::nonNull)
                .map(MoodDto::getMood)
                .collect(Collectors.toList());
        List<String> songLinks = eightTracksService.findByMoods(moods);
        return songLinks.stream()
                .map(link -> {
                    SongDto dto = new SongDto();
                    dto.setLink(link);
                    dto.setColor(colors);
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
