package com.example.demo.Repositories;

import com.example.demo.Models.Climb;
import com.example.demo.Models.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StatisticRepository extends JpaRepository<Statistic, Integer> {
    List<Climb> findByUser_UserID(int userID);
}
