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

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Import(TestSecurityConfig.class)   // ✅ DIT WAS DE MISSENDE STAP
class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllUsers_returnsOk() throws Exception {
        mockMvc.perform(get("/api/users"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void getUserById_nonExistingUser_returns404() throws Exception {
        mockMvc.perform(get("/api/users/999999"))
            .andExpect(status().isNotFound());
    }

    @Test
    void registerUser_createsUser() throws Exception {
        mockMvc.perform(post("/api/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                      "name": "Jan",
                      "email": "jan_%d@test.com",
                      "passwordHash": "password"
                    }
                """.formatted(System.currentTimeMillis())))
            .andExpect(status().isOk());
    }

    @Test
    void getUserByName_nonExisting_returns404() throws Exception {
        mockMvc.perform(get("/api/users/name")
                .param("name", "BestaatNiet"))
            .andExpect(status().isNotFound());
    }

    @Test
    void getUserDto_nonExistingUser_returns404() throws Exception {
        mockMvc.perform(get("/api/users/999999/dto"))
            .andExpect(status().isNotFound());
    }
}
