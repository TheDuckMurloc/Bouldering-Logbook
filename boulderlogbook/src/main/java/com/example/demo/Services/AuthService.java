package com.example.demo.Services;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.Repositories.UserRepository;
import com.example.demo.Models.User;
import com.example.demo.DTOs.LoginRequest;
import com.example.demo.DTOs.LoginResponse;
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder,
        JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

   public LoginResponse login(LoginRequest request) {

    User user = userRepository.findByEmail(request.getEmail())
        .orElseThrow(() -> new RuntimeException("Invalid Email"));

    if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
        throw new RuntimeException("Invalid Password");
    }

    Long userId = Long.valueOf(user.getUserId());
    String role = user.getRole().toUpperCase();

    String token = jwtService.generateToken(userId, role);
    return new LoginResponse(token, userId);
}

}


