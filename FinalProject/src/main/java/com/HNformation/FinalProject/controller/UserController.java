package com.HNformation.FinalProject.controller;

import com.HNformation.FinalProject.entity.User;
import com.HNformation.FinalProject.exception.NoChangesDetectedException;
import com.HNformation.FinalProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4500")
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @PostMapping("/new-user")
    public ResponseEntity<String> addNewUser(@RequestBody User user) {
        System.out.println("Received User: " + user);
        userService.saveNewUser(user);
        return ResponseEntity.ok("User successfully created");
    }

    @PutMapping("/user-modification")
    public ResponseEntity<String> modifyUser(@RequestBody User user) {
        try {
            userService.saveUserModifications(user);
            return ResponseEntity.ok("user successfully updated");
        } catch (NoChangesDetectedException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @DeleteMapping("/user-deleting/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User successfully deleted");
    }
}
