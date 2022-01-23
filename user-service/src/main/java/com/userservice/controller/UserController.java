package com.userservice.controller;

import com.userservice.entity.User;
import com.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/users")
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
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(params = "name")
    public ResponseEntity<User> getUserByName(@RequestParam("name") String name) {
        User user = userService.getUserByName(name);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }
        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<User> addUser(@RequestBody User user, HttpServletRequest request){
        if(user != null)
            try {
                userService.saveUser(user);
                return new ResponseEntity<User>(user, HttpStatus.CREATED);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
    }
}
