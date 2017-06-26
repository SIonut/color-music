package saci.backend.song;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import saci.backend.playlist.PlaylistDto;

import java.util.List;

/**
 * @author Stănilă Ioan, 5/22/2017.
 */
@RestController
@RequestMapping(value = "/api/songs")
public class SongController {

    private final SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }

    @RequestMapping(value = "/{colors}")
    public ResponseEntity<List<SongDto>> searchByColors(@PathVariable List<String> colors) {
        return new ResponseEntity<>(songService.findByColors(colors), HttpStatus.OK);
    }

    @RequestMapping(value = "/id/{id}")
    public ResponseEntity<SongDto> searchById(@PathVariable String id) {
        SongDto songDto = songService.findById(id);
        if (songDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(songDto, HttpStatus.OK);
    }
}
