package com.example.demo.Repositories;

import com.example.demo.Models.Climb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClimbRepository extends JpaRepository<Climb, Integer> {

    List<Climb> findByUser_UserID(int userID);

    List<Climb> findByLocation_LocationID(int locationID);
}
