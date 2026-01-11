package com.example.demo.Controllers;

import com.example.demo.Models.User;
import com.example.demo.Repositories.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Date;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class AuthControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
void setup() {
    User user = new User();
    user.setName("test");
    user.setEmail("test@test.com");
    user.setPasswordHash(passwordEncoder.encode("password"));
    user.setJoinDate(new Date()); // 👈 DIT IS DE FIX
    user.setRole("Climber");
    userRepository.save(user);
}


    @Test
    void login_returnsOk() throws Exception {
        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                      "email": "test@test.com",
                      "password": "password"
                    }
                """))
            .andExpect(status().isOk());
    }
}
