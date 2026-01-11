package com.example.demo.Controllers;

import com.example.demo.DTOs.LogUserClimbDTO;
import com.example.demo.Models.UserClimb;
import com.example.demo.Services.UserClimbService;
import com.example.demo.Services.GoalService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-climbs")
@CrossOrigin(origins = {
    "https://bouldering-logbook-user-frontend.onrender.com",
    "http://localhost:5173"
})
public class UserClimbController {

    private final UserClimbService userClimbService;
    private final GoalService goalService;

    public UserClimbController(
            UserClimbService userClimbService,
            GoalService goalService
    ) {
        this.userClimbService = userClimbService;
        this.goalService = goalService;
    }

    

    @PostMapping("/log")
    public ResponseEntity<UserClimb> logBoulder(
            Authentication authentication,
            @RequestBody LogUserClimbDTO dto
    ) {
        if (authentication == null || authentication.getPrincipal() == null) {
            return ResponseEntity.status(401).build();
        }

        int userId = Integer.parseInt(authentication.getPrincipal().toString());

        UserClimb userClimb =
            userClimbService.logBoulder(
                userId,
                dto.getClimbId(),
                dto.getAttempts(),
                dto.getNotes(),
                dto.getAscentType()
            );

        goalService.handleClimbLogged(
            userId,
            userClimb.getClimb().getGrade()
        );

        return ResponseEntity.ok(userClimb);
    }
    
    @GetMapping("/me")
    public ResponseEntity<List<UserClimb>> getMyClimbs(Authentication authentication) {
        if (authentication == null || authentication.getPrincipal() == null) {
            return ResponseEntity.status(401).build();
        }

        int userId = Integer.parseInt(authentication.getPrincipal().toString());

        return ResponseEntity.ok(
            userClimbService.getUserClimbsByUserId(userId)
        );
    }
}
