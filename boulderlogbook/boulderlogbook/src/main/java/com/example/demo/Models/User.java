package com.example.demo.Models;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Temporal(TemporalType.DATE)
    private Date joinDate;

    private String profilePhoto;

    @OneToMany(mappedBy = "user")
    private List<Climb> climbs;

    @OneToMany(mappedBy = "user")
    private List<Goal> goals;

    @OneToMany(mappedBy = "user")
    private List<Statistic> statistics;

    @OneToMany(mappedBy = "user")
    private List<Session> sessions;

    public int getId() {
        return userID;
    }
}
