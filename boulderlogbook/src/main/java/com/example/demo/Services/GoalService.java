package com.example.demo.Services;

import com.example.demo.DTOs.CreateGoalRequest;
import com.example.demo.Models.Goal;
import com.example.demo.Models.User;
import com.example.demo.Repositories.GoalRepository;
import com.example.demo.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GoalService {

    private final GoalRepository goalRepository;
    private final UserRepository userRepository;

    public GoalService(GoalRepository goalRepository, UserRepository userRepository) {
        this.goalRepository = goalRepository;
        this.userRepository = userRepository;
    }

    public List<Goal> getAllGoals() {
        return goalRepository.findAll();
    }

    public List<Goal> getGoalsByUserId(int userId) {
        return goalRepository.findByUser_UserID(userId);
    }

    public Optional<Goal> getGoalById(int id) {
        return goalRepository.findById(id);
    }

    public Goal createGoal(CreateGoalRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow();

        Goal goal = new Goal();
        goal.setDescription(request.getDescription());
        goal.setTargetGrade(request.getTargetGrade());
        goal.setProgress(request.getProgress());
        goal.setUser(user);
        goal.setCreatedDate(new Date());
        goal.setCompleted(false);

        return goalRepository.save(goal);
    }

    public Goal updateGoal(Goal goal) {
        return goalRepository.save(goal);
    }

    public void deleteGoal(int id) {
        goalRepository.deleteById(id);
    }

    public void updateProgress(int goalId, float progress) {
        Goal goal = goalRepository.findById(goalId).orElseThrow();
        goal.setProgress(progress);
        if (progress >= 100) {
            goal.setCompleted(true);
        }
        goalRepository.save(goal);
    }

    public void handleClimbLogged(int userId, String climbGrade) {
        List<Goal> goals = goalRepository.findByUser_UserID(userId);

        for (Goal goal : goals) {
            if (goal.isCompleted()) {
                continue;
            }

            if (goal.getTargetGrade().equals(climbGrade)) {
                goal.setProgress(100);
                goal.setCompleted(true);
                goalRepository.save(goal);
            }
        }
    }
}
