package main.java.com.example.demo.Services;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.Repositories.UserRepository;
import com.example.demo.Auth.LoginRequest;
import com.example.demo.Auth.LoginResponse;
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
            .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(user.getId());

        return new LoginResponse(token, user.getId());
    }
}
