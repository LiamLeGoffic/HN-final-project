package com.HNformation.FinalProject.controller;

import com.HNformation.FinalProject.entity.UserType;
import com.HNformation.FinalProject.exception.DuplicateUserTypeException;
import com.HNformation.FinalProject.exception.NoChangesDetectedException;
import com.HNformation.FinalProject.service.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4500")
@RequestMapping("user-types")
public class UserTypeController {

    private final UserTypeService userTypeService;

    @Autowired
    public UserTypeController(UserTypeService userTypeService) {
        this.userTypeService = userTypeService;
    }

    @GetMapping
    public ResponseEntity<List<UserType>> getAllUserTypes() {
        return ResponseEntity.ok(userTypeService.findAllUserTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserType> getUserTypeById(@PathVariable Long id) {
        return ResponseEntity.ok(userTypeService.findUserTypeById(id));
    }

    @PostMapping("/new-user-type")
    public ResponseEntity<String> addNewUserType(@RequestBody UserType userType) {
        System.out.println("Received User Type: " + userType);
        try {
            userTypeService.saveNewUserType(userType);
            return ResponseEntity.ok("User type successfully created");
        } catch (DuplicateUserTypeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping("/user-type-modification")
    public ResponseEntity<String> modifyUserType(@RequestBody UserType userType) {
        try {
            userTypeService.saveUserTypeModifications(userType);
            return ResponseEntity.ok("User type successfully updated");
        } catch (NoChangesDetectedException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (DuplicateUserTypeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @DeleteMapping("/user-type-deleting/{id}")
    public ResponseEntity<String> deleteUserTypeById(@PathVariable Long id) {
        userTypeService.deleteUserType(id);
        return ResponseEntity.ok("User type successfully deleted");
    }
}
