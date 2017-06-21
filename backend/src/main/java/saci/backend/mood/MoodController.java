package saci.backend.mood;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saci.backend.song.SongDto;

import java.util.Arrays;
import java.util.List;

/**
 * @author Stănilă Ioan, 5/22/2017.
 */
@RestController
@RequestMapping("api/moods")
public class MoodController {

    private final MoodService moodService;

    @Autowired
    public MoodController(MoodService moodService) {
        this.moodService = moodService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<MoodDto>> findAll() {
        return new ResponseEntity<>(moodService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<MoodDto> findByColor(@PathVariable String id) {
        MoodDto moodDto = moodService.findByColor(id);
        if (moodDto == null) {
            return new ResponseEntity<>(new MoodDto(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(moodDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/8tracks/{colors}")
    public ResponseEntity<List<SongDto>> searchByColors(@PathVariable List<String> colors) {
        SongDto songDto = new SongDto();
        songDto.setId("0");
        songDto.setLink("https://www.youtube.com/watch?v=06H_6oI4EK4");
        songDto.setColor(colors);
        SongDto songDto1 = new SongDto();
        songDto1.setId("0");
        songDto1.setLink("https://www.youtube.com/watch?v=IRY93xEjSfw");
        songDto1.setColor(colors);
        return new ResponseEntity<>(Arrays.asList(songDto, songDto1), HttpStatus.OK);
    }
}
