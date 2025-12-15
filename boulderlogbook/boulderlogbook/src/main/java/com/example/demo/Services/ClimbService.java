package com.example.demo.Services;

import com.example.demo.Models.Climb;
import com.example.demo.Repositories.ClimbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClimbService {

    @Autowired
    private ClimbRepository climbRepository;

    public List<Climb> getAllClimbs() {
        return climbRepository.findAll();
    }

    public Climb getClimbById(int id) {
        return climbRepository.findById(id).orElse(null);
    }

    public Climb createClimb(Climb climb) {
        return climbRepository.save(climb);
    }

    public List<Climb> getClimbsByUser(int userId) {
        return climbRepository.findByUser_UserID(userId);
    }

    public void deleteClimb(int id) {
        climbRepository.deleteById(id);
    }
}
