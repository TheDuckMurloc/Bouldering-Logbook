package com.example.demo.Models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Goal")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int goalID;

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private User user;

    private String description;
    private String targetGrade;

    private float progress;

    @Temporal(TemporalType.DATE)
    private Date createdDate;

    private boolean completed;

    public int getGoalID() { return goalID; }
    public User getUser() { return user; }
    public String getDescription() { return description; }
    public String getTargetGrade() { return targetGrade; }
    public float getProgress() { return progress; }
    public Date getCreatedDate() { return createdDate; }
    public boolean isCompleted() { return completed; }

    public void setUser(User user) { this.user = user; }
    public void setGoalID(int goalID) { this.goalID = goalID; }
    public void setDescription(String description) { this.description = description; }
    public void setTargetGrade(String targetGrade) { this.targetGrade = targetGrade; }
    public void setProgress(float progress) { this.progress = progress; }
    public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}

