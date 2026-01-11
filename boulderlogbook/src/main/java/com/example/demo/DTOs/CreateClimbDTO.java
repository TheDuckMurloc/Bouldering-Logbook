package com.example.demo.DTOs;

import java.util.List;

public class CreateClimbDTO {

    private String grade;
    private int locationId;
    private List<Integer> styleTagIds;


    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
    public List<Integer> getStyleTagIds() {
        return styleTagIds;
    }

    public void setStyleTagIds(List<Integer> styleTagIds) {
        this.styleTagIds = styleTagIds;
    }
}
