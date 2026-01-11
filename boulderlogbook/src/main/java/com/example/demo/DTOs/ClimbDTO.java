package com.example.demo.DTOs;

import java.util.Date;

public class ClimbDTO {

    private int id;
    private String grade;
    private Date date;
    private String photo;
    private String location; // naam van de locatie
    private boolean active;


    public ClimbDTO(int id, String grade, Date date, String photo, String location, boolean active) {
        this.id = id;
        this.grade = grade;
        this.date = date;
        this.photo = photo;
        this.location = location;
        this.active = active;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public String getPhoto() { return photo; }
    public void setPhoto(String photo) { this.photo = photo; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
}
