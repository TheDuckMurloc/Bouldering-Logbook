package com.example.demo.Services;

import com.example.demo.Models.Climb;
import com.example.demo.Models.User;
import com.example.demo.Models.UserClimb;
import com.example.demo.Models.UserClimbId;
import com.example.demo.Repositories.ClimbRepository;
import com.example.demo.Repositories.UserClimbRepository;
import com.example.demo.Repositories.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserClimbServiceTests {

    @Mock
    private UserClimbRepository userClimbRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ClimbRepository climbRepository;

    @InjectMocks
    private UserClimbService userClimbService;

    @Test
    void logBoulder_newClimb_createsUserClimb() {
        User user = new User();
        user.setUserId(1);

        Climb climb = new Climb();
        climb.setId(2);

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(climbRepository.findById(2)).thenReturn(Optional.of(climb));
        when(userClimbRepository.findById(any(UserClimbId.class)))
                .thenReturn(Optional.empty());
        when(userClimbRepository.save(any(UserClimb.class)))
                .thenAnswer(i -> i.getArgument(0));

        UserClimb result = userClimbService.logBoulder(
                1, 2, 3, "Nice climb", "Flash"
        );

        assertNotNull(result);
        assertEquals(3, result.getAttempts());
        assertEquals("Nice climb", result.getNotes());
        assertEquals("Flash", result.getAscentType());
        assertEquals(LocalDate.now(), result.getLoggedAt());

        verify(userClimbRepository).save(any(UserClimb.class));
    }

    @Test
    void logBoulder_existingClimb_updatesUserClimb() {
        UserClimb existing = new UserClimb();
        existing.setAttempts(1);

        when(userRepository.findById(1))
                .thenReturn(Optional.of(new User()));
        when(climbRepository.findById(2))
                .thenReturn(Optional.of(new Climb()));
        when(userClimbRepository.findById(any(UserClimbId.class)))
                .thenReturn(Optional.of(existing));
        when(userClimbRepository.save(existing))
                .thenReturn(existing);

        UserClimb result = userClimbService.logBoulder(
                1, 2, 5, "Updated", "Top"
        );

        assertEquals(5, result.getAttempts());
        assertEquals("Updated", result.getNotes());
        assertEquals("Top", result.getAscentType());

        verify(userClimbRepository).save(existing);
    }

    @Test
    void logBoulder_userNotFound_throwsException() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> userClimbService.logBoulder(1, 2, 1, null, null)
        );

        assertEquals("User not found", ex.getMessage());
    }

    @Test
    void logBoulder_climbNotFound_throwsException() {
        when(userRepository.findById(1))
                .thenReturn(Optional.of(new User()));
        when(climbRepository.findById(2))
                .thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> userClimbService.logBoulder(1, 2, 1, null, null)
        );

        assertEquals("Climb not found", ex.getMessage());
    }

    @Test
    void getUserClimbsByUserId_returnsClimbs() {
        List<UserClimb> climbs =
                List.of(new UserClimb(), new UserClimb());

        when(userClimbRepository.findByUser_UserID(1))
                .thenReturn(climbs);

        List<UserClimb> result =
                userClimbService.getUserClimbsByUserId(1);

        assertEquals(2, result.size());
        verify(userClimbRepository).findByUser_UserID(1);
    }
}
