package com.example.demo.Controllers;

import java.util.List;

import com.example.demo.Models.StyleTag;
import com.example.demo.Services.StyleTagService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/style-tags")
@CrossOrigin(origins = {
    "http://localhost:5173",
    "https://bouldering-logbook-user-frontend.onrender.com"
})
public class StyleTagController {

    private final StyleTagService styleTagService;

    public StyleTagController(StyleTagService styleTagService) {
        this.styleTagService = styleTagService;
    }

    @GetMapping
    public List<StyleTag> getAllTags() {
        return styleTagService.getAllTags();
    }
}
