package com.example.demo.Repositories;

import com.example.demo.Models.Climb;
import com.example.demo.Models.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GoalRepository extends JpaRepository<Goal, Integer> {
    List<Climb> findByUser_UserID(int userID);
}
