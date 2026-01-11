package com.example.demo.Services;

import com.example.demo.DTOs.LocationDTO;
import com.example.demo.Models.Location;
import com.example.demo.Repositories.LocationRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LocationServiceTests {

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private LocationService locationService;

    @Test
    void getAllLocations_mapsToDTOs() {
        Location l1 = new Location();
        l1.setLocationID(1);
        l1.setName("Gym A");
        l1.setRegion("North");
        l1.setAddress("Street 1");

        Location l2 = new Location();
        l2.setLocationID(2);
        l2.setName("Gym B");
        l2.setRegion("South");
        l2.setAddress("Street 2");

        when(locationRepository.findAll())
                .thenReturn(List.of(l1, l2));

        List<LocationDTO> result =
                locationService.getAllLocations();

        assertEquals(2, result.size());
        assertEquals("Gym A", result.get(0).getName());
        assertEquals("North", result.get(0).getRegion());
        assertEquals("Street 1", result.get(0).getAddress());
    }

    @Test
    void getLocationById_existingLocation_returnsDTO() {
        Location location = new Location();
        location.setLocationID(1);
        location.setName("Gym A");
        location.setRegion("North");
        location.setAddress("Street 1");

        when(locationRepository.findById(1))
                .thenReturn(Optional.of(location));

        LocationDTO result =
                locationService.getLocationById(1);

        assertNotNull(result);
        assertEquals("Gym A", result.getName());
        assertEquals("North", result.getRegion());
    }

    @Test
    void getLocationById_nonExistingLocation_returnsNull() {
        when(locationRepository.findById(1))
                .thenReturn(Optional.empty());

        LocationDTO result =
                locationService.getLocationById(1);

        assertNull(result);
    }

    @Test
    void create_createsAndSavesLocation() {
        LocationDTO dto =
                new LocationDTO(0, "New Gym", "East", "Street 9");

        when(locationRepository.save(any(Location.class)))
                .thenAnswer(i -> i.getArgument(0));

        Location result = locationService.create(dto);

        assertEquals("New Gym", result.getName());
        assertEquals("East", result.getRegion());
        assertEquals("Street 9", result.getAddress());

        verify(locationRepository).save(any(Location.class));
    }
}
