package com.example.demo.Controllers;

import com.example.demo.DTOs.CreateGoalRequest;
import com.example.demo.Models.Goal;
import com.example.demo.Services.GoalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/goals")
public class GoalController {

    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @GetMapping
    public List<Goal> getAllGoals() {
        return goalService.getAllGoals();
    }

    @GetMapping("/user/{userId}")
    public List<Goal> getGoalsByUser(@PathVariable int userId) {
        return goalService.getGoalsByUserId(userId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Goal> getGoalById(@PathVariable int id) {
        return goalService.getGoalById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
public ResponseEntity<Goal> createGoal(@RequestBody CreateGoalRequest request) {
    try {
        return ResponseEntity.ok(goalService.createGoal(request));
    } catch (IllegalArgumentException ex) {
        return ResponseEntity.badRequest().build();
    }
}


    @PutMapping("/{id}")
    public ResponseEntity<Goal> updateGoal(@PathVariable int id, @RequestBody Goal goal) {
        return goalService.getGoalById(id)
                .map(existing -> {
                    goal.setGoalID(id);
                    return ResponseEntity.ok(goalService.updateGoal(goal));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGoal(@PathVariable int id) {
        goalService.deleteGoal(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/progress")
    public ResponseEntity<Void> updateProgress(
            @PathVariable int id,
            @RequestParam float progress) {
        goalService.updateProgress(id, progress);
        return ResponseEntity.ok().build();
    }
}
