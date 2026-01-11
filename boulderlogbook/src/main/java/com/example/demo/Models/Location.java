package com.example.demo.Models;
import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int locationID;

    @Column(nullable = false, length = 100)
    private String name;
    private String region;
    private String address;
    private String mapURL;

    @OneToMany(mappedBy = "location")
    @JsonIgnore
    private List<Climb> climbs;

    @OneToMany(mappedBy = "location")
    private List<Session> sessions;
    
    public String getName() {
        return name;
    }

    public int getLocationID() {
    return locationID;
}

public String getRegion() {
    return region;
}

public String getAddress() {
    return address;
}

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
