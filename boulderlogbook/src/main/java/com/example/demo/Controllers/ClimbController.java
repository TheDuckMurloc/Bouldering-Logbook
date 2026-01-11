package com.example.demo.Controllers;

import com.example.demo.DTOs.CreateClimbDTO;
import com.example.demo.DTOs.ClimbDTO;
import com.example.demo.Models.Climb;
import com.example.demo.Services.ClimbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"https://bouldering-logbook-user-frontend.onrender.com", "http://localhost:5173"})
@RestController
@RequestMapping("/api/climbs")
public class ClimbController {

    private final ClimbService climbService;

    @Autowired
    public ClimbController(ClimbService climbService) {
        this.climbService = climbService;
    }

    @GetMapping
    public List<ClimbDTO> getAllClimbs() {
        return climbService.getAllClimbs();
    }

    @GetMapping("/{id}")
    public ClimbDTO getClimbById(@PathVariable int id) {
        return climbService.getClimbById(id);
    }

    @PostMapping("/create")
public ClimbDTO createClimb(@RequestBody CreateClimbDTO request) {
    Climb climb = climbService.createClimb(
        request.getGrade(),
        request.getLocationId(),
        request.getStyleTagIds()
    );

    return new ClimbDTO(
        climb.getId(),
        climb.getGrade(),
        climb.getDate(),
        climb.getPhoto(),
        climb.getLocation().getName(),
        climb.isActive()
    );
}


    @GetMapping("/user/{userId}")
    public List<ClimbDTO> getClimbsByUser(@PathVariable int userId) {
        return climbService.getClimbsByUser(userId);
    }

    @GetMapping("/location/{locationId}")
public ResponseEntity<List<Climb>> getClimbsByLocation(
        @PathVariable int locationId
) {
    return ResponseEntity.ok(
        climbService.getActiveClimbsByLocationId(locationId)
    );
}

@PatchMapping("/{id}/deactivate")
public ResponseEntity<Void> deactivateClimb(@PathVariable int id) {
    climbService.deactivateClimb(id);
    return ResponseEntity.noContent().build();
}


}
