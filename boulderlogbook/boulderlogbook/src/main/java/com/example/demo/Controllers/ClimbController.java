package com.example.demo.Controllers;

import com.example.demo.Models.Climb;
import com.example.demo.Services.ClimbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/climbs")
public class ClimbController {

    @Autowired
    private ClimbService climbService;

    @GetMapping
    public List<Climb> getAllClimbs() {
        return climbService.getAllClimbs();
    }

    @GetMapping("/{id}")
    public Climb getClimbById(@PathVariable int id) {
        return climbService.getClimbById(id);
    }

    @GetMapping("/user/{userId}")
    public List<Climb> getClimbsByUser(@PathVariable int userId) {
        return climbService.getClimbsByUser(userId);
    }

    @PostMapping
    public Climb createClimb(@RequestBody Climb climb) {
        return climbService.createClimb(climb);
    }

    @DeleteMapping("/{id}")
    public void deleteClimb(@PathVariable int id) {
        climbService.deleteClimb(id);
    }
}
