package com.example.demo.Repositories;

import com.example.demo.Models.Climb;
import com.example.demo.Models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Integer> {
    List<Climb> findByUser_UserID(int userID);
    List<Climb> findByLocation_LocationID(int locationID);
}
