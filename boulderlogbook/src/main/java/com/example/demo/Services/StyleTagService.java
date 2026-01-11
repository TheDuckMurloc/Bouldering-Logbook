package com.example.demo.Services;

import java.util.List;

import com.example.demo.Models.StyleTag;
import com.example.demo.Repositories.StyleTagRepository;
import org.springframework.stereotype.Service;

@Service
public class StyleTagService {

    private final StyleTagRepository styleTagRepository;

    public StyleTagService(StyleTagRepository styleTagRepository) {
        this.styleTagRepository = styleTagRepository;
    }

    public List<StyleTag> getAllTags() {
        return styleTagRepository.findAll();
    }
}
