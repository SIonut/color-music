package saci.backend.song;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author Stănilă Ioan, 5/22/2017.
 */
@RestController
@RequestMapping(value = "/api/songs")
public class SongController {

    @RequestMapping(value = "/{colors}")
    public ResponseEntity<List<SongDto>> testSearchByColors(@PathVariable String[] colors) {
        SongDto songDto = new SongDto();
        songDto.setId("0");
        songDto.setLink("https://www.youtube.com/watch?v=06H_6oI4EK4");
        songDto.setColor(Arrays.asList(colors));
        SongDto songDto1 = new SongDto();
        songDto1.setId("0");
        songDto1.setLink("https://www.youtube.com/watch?v=IRY93xEjSfw");
        songDto1.setColor(Arrays.asList(colors));
        return new ResponseEntity<>(Arrays.asList(songDto, songDto1), HttpStatus.OK);
    }
}
