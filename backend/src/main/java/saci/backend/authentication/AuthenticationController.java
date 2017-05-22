package saci.backend.authentication;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import saci.backend.user.User;

import java.util.UUID;

/**
 * @author Stănilă Ioan, 5/21/2017.
 */
@RestController
@RequestMapping(value = "api/")
public class AuthenticationController {

    @RequestMapping(value = "login")
    public ResponseEntity<String> login(@RequestBody User user) {
        return new ResponseEntity<>(UUID.randomUUID().toString(), HttpStatus.OK);
    }
}
