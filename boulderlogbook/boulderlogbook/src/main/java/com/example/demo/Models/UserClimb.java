package com.example.demo.Models;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_climb")
public class UserClimb {

    @EmbeddedId
    private UserClimbId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("climbId")
    @JoinColumn(name = "climb_id")
    private Climb climb;

    private LocalDate loggedAt;
    private Integer attempts;

    @Column(length = 1000)
    private String notes;

    public UserClimb() {}

    public UserClimb(User user, Climb climb, LocalDate loggedAt, Integer attempts, String notes) {
        this.id = new UserClimbId(user.getId(), climb.getId());
        this.user = user;
        this.climb = climb;
        this.loggedAt = loggedAt;
        this.attempts = attempts;
        this.notes = notes;
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

    public LocalDate getLoggedAt() {
        return loggedAt;
    }

    public Integer getAttempts() {
        return attempts;
    }

    public String getNotes() {
        return notes;
    }
}
