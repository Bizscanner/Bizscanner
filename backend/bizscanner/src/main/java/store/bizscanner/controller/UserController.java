package store.bizscanner.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.bizscanner.entity.User;
import store.bizscanner.service.UserService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("user/{userid}")
    public ResponseEntity<User> getUser(@PathVariable("userid") Long userId) {
        return new ResponseEntity<>(userService.findById(userId), HttpStatus.OK);
    }


}
