package com.example.demo.Controllers;

import com.example.demo.Security.TestSecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Import(TestSecurityConfig.class)
public class UserClimbControllerTests {

    @Autowired
    private MockMvc mockMvc;

    /* ---------- POST /api/user-climbs/log ---------- */
    @Test
    void logBoulder_requiresAuthentication() throws Exception {
        mockMvc.perform(post("/api/user-climbs/log")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                      "climbId": 10,
                      "attempts": 3,
                      "notes": "Nice climb",
                      "ascentType": "Flash"
                    }
                """))
            .andExpect(status().isUnauthorized());
    }

    /* ---------- GET /api/user-climbs/user/{userId} ---------- */
    @Test
    void getUserClimbs_returnsOk() throws Exception {
        mockMvc.perform(get("/api/user-climbs/user/1"))
            .andExpect(status().isOk());
    }
}
