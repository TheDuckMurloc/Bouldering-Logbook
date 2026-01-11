package com.example.demo.Models;


import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "[Users]")
@Access(AccessType.FIELD)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;

    @Column(name = "Name", nullable = false, length = 100)
    private String name;

    @Column(name = "Email", nullable = false, unique = true, length = 255)
    private String email;

    @Column(name = "PasswordHash", nullable = false)
    private String passwordHash;

    @Temporal(TemporalType.DATE)
    @Column(name = "JoinDate", nullable = false)
    private Date joinDate;

    @Column(name = "ProfilePhoto")
    private String profilePhoto;
    
    @Column(name = "Role", nullable = false)
    private String role;

    @OneToMany(mappedBy = "user")
    private List<Goal> goals;

    @OneToMany(mappedBy = "user")
    private List<Statistic> statistics;

    @OneToMany(mappedBy = "user")
    private List<Session> sessions;

    public int getUserId() { return userID; }
    public String getName() { return name; }
    public String getEmail() { return email; }  
    public String getRole() { return role; }
    public String getPasswordHash() { return passwordHash; }
    public Date getJoinDate() { return joinDate; }
    public String getProfilePhoto() { return profilePhoto; }
    public void setName(String name) {
    this.name = name;
}

public void setUserId(int userID) {
    this.userID = userID;
}

public void setEmail(String email) {
    this.email = email;
}

public void setPasswordHash(String passwordHash) {
    this.passwordHash = passwordHash;
}

public void setJoinDate(Date joinDate) {
    this.joinDate = joinDate;
}

public void setProfilePhoto(String profilePhoto) {
    this.profilePhoto = profilePhoto;
}

public void setRole(String role) {
    this.role = role;
}

    
}
