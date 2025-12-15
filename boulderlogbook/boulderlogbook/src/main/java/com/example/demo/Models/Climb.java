package com.example.demo.Models;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "climbs")
public class Climb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int climbID;

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "locationID")
    private Location location;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String grade;
    private String ascentType;
    private int attempts;
    private String notes;
    private int duration;
    private String photo;

    @ManyToMany
    @JoinTable(
        name = "climb_style_tags",
        joinColumns = @JoinColumn(name = "climbID"),
        inverseJoinColumns = @JoinColumn(name = "tagID")
    )
    private List<StyleTag> styleTags;

    public int getId() {
        return climbID;
    }

    
}
