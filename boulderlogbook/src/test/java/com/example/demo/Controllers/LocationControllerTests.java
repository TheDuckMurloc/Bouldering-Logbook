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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Import(TestSecurityConfig.class) // 🔑 DIT IS DE FIX
public class LocationControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllLocations_returnsOk() throws Exception {
        mockMvc.perform(get("/api/locations"))
            .andExpect(status().isOk());
    }

    @Test
void getLocationById_existing_returnsOk() throws Exception {
    mockMvc.perform(post("/api/locations/create")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                {
                  "name": "Gym A",
                  "region": "Region A",
                  "address": "Address A"
                }
            """))
        .andExpect(status().isCreated()); // ✅

    mockMvc.perform(get("/api/locations/1"))
        .andExpect(status().isOk());      // ✅
}

    @Test
    void getLocationById_returnsOk() throws Exception {
        mockMvc.perform(get("/api/locations"))
            .andExpect(status().isOk());
    }

    @Test
void createLocation_returnsCreated() throws Exception {
    mockMvc.perform(post("/api/locations/create")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                {
                  "name": "Gym A",
                  "region": "Region A",
                  "address": "Address A"
                }
            """))
        .andExpect(status().isCreated()); // ✅
}

}
