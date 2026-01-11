package com.example.demo.Controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ComponentScan(
    excludeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = com.example.demo.Security.JwtAuthFilter.class
    )
)
class ClimbControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllClimbs_returnsOk() throws Exception {
        mockMvc.perform(get("/api/climbs"))
            .andExpect(status().isOk());
    }

    @Test
    void getClimbById_returnsOk() throws Exception {
        mockMvc.perform(get("/api/climbs/1"))
            .andExpect(status().isOk());
    }

    @Test
    void createClimb_returnsCreated() throws Exception {
        mockMvc.perform(post("/api/climbs/create")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                      "grade": "6A",
                      "locationId": 1,
                      "styleTagIds": [1, 2]
                    }
                """))
            .andExpect(status().isOk());
    }
}
