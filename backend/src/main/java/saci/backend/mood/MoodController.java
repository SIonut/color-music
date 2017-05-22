package saci.backend.mood;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Stănilă Ioan, 5/22/2017.
 */
@RestController
@RequestMapping(value = "api/moods")
public class MoodController {

    private final MoodService moodService;

    @Autowired
    public MoodController(MoodService moodService) {
        this.moodService = moodService;
    }

    @RequestMapping(value = "")
    public ResponseEntity<List<MoodDto>> findAll() {
        return new ResponseEntity<>(moodService.findAll(), HttpStatus.FOUND);
    }
}
