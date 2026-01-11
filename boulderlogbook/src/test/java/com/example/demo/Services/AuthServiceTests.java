package com.example.demo.Services;

import com.example.demo.DTOs.LoginRequest;
import com.example.demo.DTOs.LoginResponse;
import com.example.demo.Models.User;
import com.example.demo.Repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private JwtService jwtService;
    private AuthService authService;

    // 🔐 32+ chars → HS256 veilig
    private static final String TEST_SECRET =
        "this-is-a-very-long-test-secret-key-for-hs256";

    @BeforeEach
    void setUp() {
        jwtService = new JwtService(TEST_SECRET);
        authService = new AuthService(userRepository, passwordEncoder, jwtService);
    }

    @Test
    void login_validCredentials_returnsTokenAndUserId() {
        User user = new User();
        user.setUserId(1);
        user.setEmail("test@test.com");
        user.setPasswordHash("hashed");
        user.setRole("CLIMBER");

        LoginRequest request = new LoginRequest();
        request.setEmail("test@test.com");
        request.setPassword("password");

        when(userRepository.findByEmail("test@test.com"))
            .thenReturn(Optional.of(user));

        when(passwordEncoder.matches("password", "hashed"))
            .thenReturn(true);

        LoginResponse response = authService.login(request);

        assertNotNull(response.getToken());
        assertEquals(1L, response.getUserId());
    }

    @Test
    void login_invalidEmail_throwsException() {
        LoginRequest request = new LoginRequest();
        request.setEmail("test@test.com");
        request.setPassword("password");

        when(userRepository.findByEmail("test@test.com"))
            .thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(
            RuntimeException.class,
            () -> authService.login(request)
        );

        assertEquals("Invalid Email", ex.getMessage());
    }

    @Test
    void login_invalidPassword_throwsException() {
        User user = new User();
        user.setUserId(1);
        user.setEmail("test@test.com");
        user.setPasswordHash("hashed");
        user.setRole("CLIMBER");

        LoginRequest request = new LoginRequest();
        request.setEmail("test@test.com");
        request.setPassword("password");

        when(userRepository.findByEmail("test@test.com"))
            .thenReturn(Optional.of(user));

        when(passwordEncoder.matches("password", "hashed"))
            .thenReturn(false);

        RuntimeException ex = assertThrows(
            RuntimeException.class,
            () -> authService.login(request)
        );

        assertEquals("Invalid Password", ex.getMessage());
    }

    @Test
    void login_roleIsUppercasedBeforeTokenGeneration() {
        User user = new User();
        user.setUserId(1);
        user.setEmail("test@test.com");
        user.setPasswordHash("hashed");
        user.setRole("admin"); // lowercase

        LoginRequest request = new LoginRequest();
        request.setEmail("test@test.com");
        request.setPassword("password");

        when(userRepository.findByEmail("test@test.com"))
            .thenReturn(Optional.of(user));

        when(passwordEncoder.matches("password", "hashed"))
            .thenReturn(true);

        LoginResponse response = authService.login(request);

        String roleInToken = jwtService.extractRole(response.getToken());

        assertEquals("ADMIN", roleInToken);
    }
}
