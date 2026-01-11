package com.example.demo.DTOs;

public class LogUserClimbDTO {

    private int climbId;
    private Integer attempts;
    private String notes;
    private String ascentType;

    public int getClimbId() {
        return climbId;
    }

    public Integer getAttempts() {
        return attempts;
    }

    public String getNotes() {
        return notes;
    }

    public String getAscentType() {
        return ascentType;
    }
}
