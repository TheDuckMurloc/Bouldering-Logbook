package com.example.demo.Models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "UserClimb")
public class UserClimb {

    @EmbeddedId
    private UserClimbId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "UserID")
    private User user;

    @ManyToOne
    @MapsId("climbId")
    @JoinColumn(name = "ClimbID")
    private Climb climb;

    private Integer attempts;

    @Column(length = 1000)
    private String notes;

    private LocalDate loggedAt;

    @Column(name = "AscentType")
    private String ascentType;

    public UserClimb() {}

    public UserClimb(
        User user,
        Climb climb,
        LocalDate loggedAt,
        Integer attempts,
        String notes,
        String ascentType
    ) {
        this.id = new UserClimbId(user.getUserId(), climb.getId());
        this.user = user;
        this.climb = climb;
        this.loggedAt = LocalDate.now();
        this.attempts = attempts;
        this.notes = notes;
        this.ascentType = ascentType;
    }

    public UserClimbId getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Climb getClimb() {
        return climb;
    }

    public Integer getAttempts() {
        return attempts;
    }

    public void setAttempts(Integer attempts) {
        this.attempts = attempts;
    }

    public LocalDate getLoggedAt() {
        return loggedAt;
    }

    public void setLoggedAt(LocalDate loggedAt) {
        this.loggedAt = loggedAt;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getAscentType() {
        return ascentType;
    }

    public void setAscentType(String ascentType) {
        this.ascentType = ascentType;
    }

    public void setClimb(Climb climb) {
        this.climb = climb;
    }
}
