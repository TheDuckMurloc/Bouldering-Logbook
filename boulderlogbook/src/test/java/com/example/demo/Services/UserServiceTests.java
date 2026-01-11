package com.example.demo.Services;

import com.example.demo.DTOs.UserDTO;
import com.example.demo.Models.User;
import com.example.demo.Models.UserClimb;
import com.example.demo.Repositories.UserClimbRepository;
import com.example.demo.Repositories.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserClimbRepository userClimbRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void getAllUsers_returnsUsers() {
        List<User> users = List.of(new User(), new User());
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertEquals(2, result.size());
        verify(userRepository).findAll();
    }

    @Test
    void getUserById_existingUser_returnsUser() {
        User user = new User();
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserById(1);

        assertTrue(result.isPresent());
        verify(userRepository).findById(1);
    }

    @Test
    void getUserById_nonExistingUser_returnsEmpty() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        Optional<User> result = userService.getUserById(1);

        assertTrue(result.isEmpty());
    }

    @Test
    void createUser_encodesPassword_setsDefaults_andSaves() {
        User user = new User();
        user.setPasswordHash("plain");

        when(passwordEncoder.encode("plain")).thenReturn("hashed");
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArgument(0));

        User result = userService.createUser(user);

        assertEquals("hashed", result.getPasswordHash());
        assertNotNull(result.getJoinDate());
        assertEquals("CLIMBER", result.getRole());
        verify(passwordEncoder).encode("plain");
        verify(userRepository).save(user);
    }

    @Test
    void createUser_preservesExistingJoinDateAndRole() {
        User user = new User();
        Date date = new Date();
        user.setJoinDate(date);
        user.setRole("ADMIN");
        user.setPasswordHash("plain");

        when(passwordEncoder.encode("plain")).thenReturn("hashed");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.createUser(user);

        assertEquals(date, result.getJoinDate());
        assertEquals("ADMIN", result.getRole());
    }

    @Test
    void deleteUser_deletesById() {
        userService.deleteUser(1);

        verify(userRepository).deleteById(1);
    }

    @Test
    void getUserByName_returnsUser() {
        User user = new User();
        when(userRepository.findByName("Jan")).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserByName("Jan");

        assertTrue(result.isPresent());
        verify(userRepository).findByName("Jan");
    }

    @Test
    void getUserClimbs_returnsClimbs() {
        List<UserClimb> climbs = List.of(new UserClimb(), new UserClimb());
        when(userClimbRepository.findByUser_UserID(1)).thenReturn(climbs);

        List<UserClimb> result = userService.getUserClimbs(1);

        assertEquals(2, result.size());
        verify(userClimbRepository).findByUser_UserID(1);
    }

    @Test
    void getUserDtoById_existingUser_returnsDto() {
        User user = new User();
        user.setUserId(1);
        user.setName("Jan");
        user.setEmail("jan@test.com");
        user.setProfilePhoto("photo.png");
        Date date = new Date();
        user.setJoinDate(date);

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        Optional<UserDTO> result = userService.getUserDtoById(1);

        assertTrue(result.isPresent());
        assertEquals("Jan", result.get().getName());
        assertEquals("jan@test.com", result.get().getEmail());
    }

    @Test
    void getUserDtoById_nonExistingUser_returnsEmpty() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        Optional<UserDTO> result = userService.getUserDtoById(1);

        assertTrue(result.isEmpty());
    }
}
