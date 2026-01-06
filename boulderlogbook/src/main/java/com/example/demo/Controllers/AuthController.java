package main.java.com.example.demo.Controllers;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;

import main.java.com.example.demo.DTOs.LoginRequest;
import main.java.com.example.demo.DTOs.LoginResponse;
import main.java.com.example.demo.Services.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "https://bouldering-logbook-user-frontend.onrender.com")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
  

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}