package com.example.demo.DTOs;

public class CreateGoalRequest {
    private String description;
    private String targetGrade;
    private float progress;
    private int userId;

    public String getDescription() { return description; }
    public String getTargetGrade() { return targetGrade; }
    public float getProgress() { return progress; }
    public int getUserId() { return userId; }

    public void setDescription(String description) { this.description = description; }
    public void setTargetGrade(String targetGrade) { this.targetGrade = targetGrade; }
    public void setProgress(float progress) { this.progress = progress; }
    public void setUserId(int userId) { this.userId = userId; }
}
