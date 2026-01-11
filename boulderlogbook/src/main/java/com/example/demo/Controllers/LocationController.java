package com.example.demo.Controllers;

import com.example.demo.Models.Location;
import com.example.demo.DTOs.LocationDTO;
import com.example.demo.Services.LocationService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
@CrossOrigin(origins = {"https://bouldering-logbook-user-frontend.onrender.com", "http://localhost:5173"})
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public ResponseEntity<List<LocationDTO>> getAllLocations() {
        return ResponseEntity.ok(locationService.getAllLocations());
    }

    @GetMapping("/{id}")
public ResponseEntity<LocationDTO> getLocationById(@PathVariable int id) {
    LocationDTO locationDTO = locationService.getLocationById(id);
    if (locationDTO != null) {
        return ResponseEntity.ok(locationDTO);
    } else {
        return ResponseEntity.notFound().build();
    }
}


    @PostMapping("/create")
    public ResponseEntity<LocationDTO> create(@RequestBody LocationDTO dto) {
        Location created = locationService.create(dto);

        LocationDTO response = new LocationDTO(
                created.getLocationID(),
                created.getName(),
                created.getRegion(),
                created.getAddress()
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
