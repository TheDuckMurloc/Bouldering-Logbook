package com.example.demo.Controllers;

import com.example.demo.DTOs.UserDTO;
import com.example.demo.Models.User;
import com.example.demo.Models.UserClimb;
import com.example.demo.Services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {
    "https://bouldering-logbook-user-frontend.onrender.com",
    "http://localhost:5173"
})
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        return userService.getUserById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/name")
    public ResponseEntity<User> getUserByName(@RequestParam String name) {
        return userService.getUserByName(name)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{userId}/climbs")
    public ResponseEntity<List<UserClimb>> getUserClimbs(@PathVariable int userId) {
        return ResponseEntity.ok(userService.getUserClimbs(userId));
    }

    @GetMapping("/{userId}/dto")
    public ResponseEntity<UserDTO> getUserDto(@PathVariable int userId) {
        return userService.getUserDtoById(userId)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}
