package saci.backend.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

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

    @RequestMapping("/register")
    public ResponseEntity<Boolean> register(@RequestBody UserDto dto, Principal principal) {
        userService.save(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
