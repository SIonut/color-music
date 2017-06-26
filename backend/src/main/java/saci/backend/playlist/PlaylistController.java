package saci.backend.playlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Stănilă Ioan, 5/22/2017.
 */
@RestController
@RequestMapping(value = "/api/playlists")
public class PlaylistController {

    private final PlaylistService playlistService;

    @Autowired
    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<PlaylistDto>> getEntities() {
        return new ResponseEntity<>(playlistService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<PlaylistDto> getById(@PathVariable String id) {
        PlaylistDto dto = playlistService.findById(id);
        if (dto == null) {
            return new ResponseEntity<>(new PlaylistDto(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<PlaylistDto> create(@RequestBody PlaylistDto dto) {
        PlaylistDto playlistDto = playlistService.create(dto);
        if (playlistDto == null) {
            return new ResponseEntity<>(new PlaylistDto(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(playlistDto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<PlaylistDto> update(@RequestBody PlaylistDto dto) {
        PlaylistDto playlistDto = playlistService.update(dto);
        if (playlistDto == null) {
            return new ResponseEntity<>(new PlaylistDto(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(playlistDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        playlistService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}/playlists")
    public ResponseEntity<List<PlaylistDto>> getPlaylists(@PathVariable String userId) {
        return new ResponseEntity<>(playlistService.findByUserId(userId), HttpStatus.OK);
    }
}
