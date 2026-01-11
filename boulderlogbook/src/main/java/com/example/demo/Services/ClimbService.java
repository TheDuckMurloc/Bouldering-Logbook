package com.example.demo.Services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.DTOs.ClimbDTO;
import com.example.demo.Models.Climb;
import com.example.demo.Models.Location;
import com.example.demo.Models.StyleTag;
import com.example.demo.Models.UserClimb;
import com.example.demo.Repositories.ClimbRepository;
import com.example.demo.Repositories.LocationRepository;
import com.example.demo.Repositories.StyleTagRepository;
import com.example.demo.Repositories.UserClimbRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClimbService {

    private final ClimbRepository climbRepository;
    private final LocationRepository locationRepository;
    private final UserClimbRepository userClimbRepository;
    private final StyleTagRepository styleTagRepository;

    @Autowired
    public ClimbService(
        ClimbRepository climbRepository,
        LocationRepository locationRepository,
        UserClimbRepository userClimbRepository,
        StyleTagRepository styleTagRepository
    ) {
        this.climbRepository = climbRepository;
        this.locationRepository = locationRepository;
        this.userClimbRepository = userClimbRepository;
        this.styleTagRepository = styleTagRepository;
    }

    public List<ClimbDTO> getAllClimbs() {
        return climbRepository.findAll()
            .stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    public ClimbDTO getClimbById(int id) {
        return climbRepository.findById(id)
            .map(this::toDTO)
            .orElse(null);
    }

    public Climb createClimb(String grade, int locationId, List<Integer> tagIds) {

        Location location = locationRepository.findById(locationId)
            .orElseThrow(() -> new RuntimeException("Location not found"));

        List<StyleTag> tags =
            (tagIds == null || tagIds.isEmpty())
                ? List.of()
                : styleTagRepository.findAllById(tagIds);

        Climb climb = new Climb();
        climb.setGrade(grade);
        climb.setDate(new Date());
        climb.setLocation(location);
        climb.setStyleTags(tags); 

        return climbRepository.save(climb);
    }

    public List<ClimbDTO> getClimbsByUser(int userId) {
        List<UserClimb> userClimbs =
            userClimbRepository.findByUser_UserID(userId);

        return userClimbs.stream()
            .map(UserClimb::getClimb)
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    public List<Climb> getActiveClimbsByLocationId(int locationId) {
    return climbRepository.findByLocation_LocationIDAndIsActiveTrue(locationId);
    }

    private ClimbDTO toDTO(Climb climb) {
        return new ClimbDTO(
            climb.getId(),
            climb.getGrade(),
            climb.getDate(),
            climb.getPhoto(),
            climb.getLocation() != null
                ? climb.getLocation().getName()
                : null,
            climb.isActive()
        );

    }


    public void deactivateClimb(int id) {
    Climb climb = climbRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Climb not found"));

    if (!climb.isActive()) {
        return;
    }

    climb.setActive(false);
    climbRepository.save(climb);
}

}
