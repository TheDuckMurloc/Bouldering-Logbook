package com.example.demo.Models;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "StyleTag")
public class StyleTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TagID")
    private int id;

    @Column(name = "Name")
    private String name;

    @ManyToMany(mappedBy = "styleTags")
    @JsonIgnore
    private List<Climb> climbs;

    public StyleTag() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Climb> getClimbs() { return climbs; }
    public void setClimbs(List<Climb> climbs) { this.climbs = climbs; }
}

