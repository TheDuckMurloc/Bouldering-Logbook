package com.example.demo.Services;

import com.example.demo.Models.StyleTag;
import com.example.demo.Repositories.StyleTagRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StyleTagServiceTests {

    @Mock
    private StyleTagRepository styleTagRepository;

    @InjectMocks
    private StyleTagService styleTagService;

    @Test
    void getAllTags_returnsAllTags() {
        List<StyleTag> tags =
            List.of(new StyleTag(), new StyleTag(), new StyleTag());

        when(styleTagRepository.findAll()).thenReturn(tags);

        List<StyleTag> result = styleTagService.getAllTags();

        assertEquals(3, result.size());
        verify(styleTagRepository).findAll();
    }
}
