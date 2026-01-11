package com.example.demo.Models;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Climb")
public class Climb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ClimbID")
    private int id;

    @ManyToOne
    @JoinColumn(name = "LocationID")
    private Location location;

    @Column(name = "Date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "Grade")
    private String grade;

    @Column(name = "Photo")
    private String photo;

    @Column(name = "is_active")
    private boolean isActive = true;

    @ManyToMany
    @JoinTable(
        name = "ClimbStyleTag",
        joinColumns = @JoinColumn(name = "ClimbID"),
        inverseJoinColumns = @JoinColumn(name = "TagID")
    )


    private List<StyleTag> styleTags;

    public boolean isActive() {
        return isActive;
    }
    public Climb() {}

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Location getLocation() { return location; }
    public void setLocation(Location location) { this.location = location; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    public String getPhoto() { return photo; }
    public void setPhoto(String photo) { this.photo = photo; }

    public List<StyleTag> getStyleTags() { return styleTags; }
    public void setStyleTags(List<StyleTag> styleTags) { this.styleTags = styleTags; }

    public void setActive(boolean active) {
    isActive = active;
}
}
