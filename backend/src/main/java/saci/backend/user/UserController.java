package saci.backend.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saci.backend.playlist.PlaylistDto;

/**
 * @author Stănilă Ioan, 5/24/2017.
 */
@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<UserDto> findByCredentials(@RequestBody UserDto dto) {
        UserDto userDto = userService.find(dto);
        if (userDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @RequestMapping("/register")
    public ResponseEntity<Boolean> register(@RequestBody UserDto dto) {
        userService.save(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{userId}/likes/{songId}")
    public ResponseEntity<Boolean> isSongLiked(@PathVariable String userId, @PathVariable String songId) {
        return new ResponseEntity<>(userService.isSongLiked(userId, songId), HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}/likes/add/{songId}", method = RequestMethod.POST)
    public ResponseEntity<Boolean> addToLikes(@PathVariable String userId, @PathVariable String songId) {
        return new ResponseEntity<>(userService.addToLikes(userId, songId), HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}/likes/remove/{songId}", method = RequestMethod.POST)
    public ResponseEntity<Boolean> removeFromLikes(@PathVariable String userId, @PathVariable String songId) {
        return new ResponseEntity<>(userService.removeFromLikes(userId, songId), HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}/likes")
    public ResponseEntity<PlaylistDto> getLikes(@PathVariable String userId) {
        return new ResponseEntity<>(userService.getLikes(userId), HttpStatus.OK);
    }
}
