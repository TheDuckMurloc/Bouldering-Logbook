package com.example.demo.Services;

import com.example.demo.DTOs.LocationDTO;
import com.example.demo.Models.Location;
import com.example.demo.Repositories.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<LocationDTO> getAllLocations() {
        return locationRepository.findAll()
                .stream()
                .map(location -> new LocationDTO(
                        location.getLocationID(),
                        location.getName(),
                        location.getRegion(),
                        location.getAddress()
                ))
                .collect(Collectors.toList());
    }

    public LocationDTO getLocationById(int id) {
        return locationRepository.findById(id)
                .map(location -> new LocationDTO(
                        location.getLocationID(),
                        location.getName(),
                        location.getRegion(),
                        location.getAddress()
                ))
                .orElse(null);
    }

    public Location create(LocationDTO dto) {
        Location location = new Location();
        location.setName(dto.getName());
        location.setRegion(dto.getRegion());
        location.setAddress(dto.getAddress());

        return locationRepository.save(location);
    }
}
