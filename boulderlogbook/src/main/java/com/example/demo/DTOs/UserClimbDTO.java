package com.example.demo.DTOs;

import java.time.LocalDate;

public class UserClimbDTO {

    private int climbId;
    private String climbName;
    private String grade;
    private LocalDate loggedAt;
    private Integer attempts;
    private String notes;
    private String ascentType;

    public UserClimbDTO(
        int climbId,
        String climbName,
        String grade,
        LocalDate loggedAt,
        Integer attempts,
        String notes,
        String ascentType
    ) {
        this.climbId = climbId;
        this.climbName = climbName;
        this.grade = grade;
        this.loggedAt = loggedAt;
        this.attempts = attempts;
        this.notes = notes;
        this.ascentType = ascentType;
    }

    public int getClimbId() {
        return climbId;
    }

    public String getClimbName() {
        return climbName;
    }

    public String getGrade() {
        return grade;
    }

    public LocalDate getLoggedAt() {
        return loggedAt;
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
