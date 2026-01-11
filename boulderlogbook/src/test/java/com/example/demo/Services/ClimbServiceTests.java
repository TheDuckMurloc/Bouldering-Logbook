package com.example.demo.Services;

import com.example.demo.DTOs.ClimbDTO;
import com.example.demo.Models.Climb;
import com.example.demo.Models.Location;
import com.example.demo.Models.StyleTag;
import com.example.demo.Models.UserClimb;
import com.example.demo.Repositories.ClimbRepository;
import com.example.demo.Repositories.LocationRepository;
import com.example.demo.Repositories.StyleTagRepository;
import com.example.demo.Repositories.UserClimbRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClimbServiceTests {

    @Mock
    private ClimbRepository climbRepository;

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private UserClimbRepository userClimbRepository;

    @Mock
    private StyleTagRepository styleTagRepository;

    @InjectMocks
    private ClimbService climbService;

    @Test
    public void getAllClimbs_returnsDtos() {
        Climb climb = new Climb();
        climb.setGrade("6A");
        climb.setDate(new Date());

        when(climbRepository.findAll()).thenReturn(List.of(climb));

        List<ClimbDTO> result = climbService.getAllClimbs();

        assertEquals(1, result.size());
        verify(climbRepository).findAll();
    }

    @Test
    public void getClimbById_existing_returnsDto() {
        Climb climb = new Climb();
        climb.setGrade("6B");

        when(climbRepository.findById(1)).thenReturn(Optional.of(climb));

        ClimbDTO dto = climbService.getClimbById(1);

        assertNotNull(dto);
        verify(climbRepository).findById(1);
    }

    @Test
    public void getClimbById_nonExisting_returnsNull() {
        when(climbRepository.findById(1)).thenReturn(Optional.empty());

        ClimbDTO dto = climbService.getClimbById(1);

        assertNull(dto);
    }

    @Test
    public void createClimb_createsAndSaves() {
        Location location = new Location();
        location.setLocationID(1);

        when(locationRepository.findById(1)).thenReturn(Optional.of(location));
        when(styleTagRepository.findAllById(List.of(1, 2)))
            .thenReturn(List.of(new StyleTag(), new StyleTag()));
        when(climbRepository.save(any(Climb.class)))
            .thenAnswer(i -> i.getArgument(0));

        Climb result =
            climbService.createClimb("7A", 1, List.of(1, 2));

        assertEquals("7A", result.getGrade());
        assertEquals(location, result.getLocation());
        verify(climbRepository).save(any(Climb.class));
    }

    @Test
    public void getClimbsByUser_returnsDtos() {
        Climb climb = new Climb();
        climb.setGrade("6C");

        UserClimb uc = new UserClimb();
        uc.setClimb(climb);

        when(userClimbRepository.findByUser_UserID(1))
            .thenReturn(List.of(uc));

        List<ClimbDTO> result = climbService.getClimbsByUser(1);

        assertEquals(1, result.size());
        verify(userClimbRepository).findByUser_UserID(1);
    }

    @Test
    public void getActiveClimbsByLocationId_returnsClimbs() {
        when(climbRepository.findByLocation_LocationIDAndIsActiveTrue(1))
            .thenReturn(List.of(new Climb(), new Climb()));

        List<Climb> result =
            climbService.getActiveClimbsByLocationId(1);

        assertEquals(2, result.size());
        verify(climbRepository)
            .findByLocation_LocationIDAndIsActiveTrue(1);
    }

    @Test
    public void deactivateClimb_active_setsInactive() {
        Climb climb = new Climb();
        climb.setActive(true);

        when(climbRepository.findById(1))
            .thenReturn(Optional.of(climb));

        climbService.deactivateClimb(1);

        assertFalse(climb.isActive());
        verify(climbRepository).save(climb);
    }

    @Test
    public void deactivateClimb_alreadyInactive_doesNothing() {
        Climb climb = new Climb();
        climb.setActive(false);

        when(climbRepository.findById(1))
            .thenReturn(Optional.of(climb));

        climbService.deactivateClimb(1);

        verify(climbRepository, never()).save(any());
    }
}
