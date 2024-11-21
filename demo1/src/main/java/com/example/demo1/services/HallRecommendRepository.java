package com.example.demo1.services;

import com.example.demo1.model.Hall;
import com.example.demo1.model.HallRecommend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HallRecommendRepository extends JpaRepository<HallRecommend,Long> {
    List<HallRecommend> findByHall(Hall hall);
}
