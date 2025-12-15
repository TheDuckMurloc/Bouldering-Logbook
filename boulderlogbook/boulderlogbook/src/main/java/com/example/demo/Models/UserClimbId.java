package com.example.demo.Models;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserClimbId implements Serializable {

    private Integer userId;
    private Integer climbId;

    public UserClimbId() {}

    public UserClimbId(Integer userId, Integer climbId) {
        this.userId = userId;
        this.climbId = climbId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getClimbId() {
        return climbId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserClimbId)) return false;
        UserClimbId that = (UserClimbId) o;
        return Objects.equals(userId, that.userId) &&
               Objects.equals(climbId, that.climbId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, climbId);
    }
}
