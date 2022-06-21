package com.userservice.controller;

import com.userservice.entity.User;
import com.userservice.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Log4j2
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (!users.isEmpty()) {
            log.info("Found {} users", users.size());
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        log.error("Unable to find users");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(params = "name")
    public ResponseEntity<User> getUserByName(@RequestParam("name") String name) {
        User user = userService.getUserByName(name);
        if (user != null) {
            log.info("Found user by name: {}", name);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        log.error("Unable to find user by name: {}", name);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            log.info("Found user by id: {}", id);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }
        log.error("Unable to find user by id: {}", id);
        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<User> addUser(@RequestBody User user) {
        if (user != null)
            try {
                userService.saveUser(user);
                log.info("Successfully saved user");
                return new ResponseEntity<User>(user, HttpStatus.CREATED);
            } catch (Exception e) {
                log.error("Error while saving user with name: {}", user.getUserName(), e);
                e.printStackTrace();
                return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        log.error("Requesting user should not be null");
        return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
    }
}
