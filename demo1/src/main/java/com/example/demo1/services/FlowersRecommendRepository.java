package com.example.demo1.services;

import com.example.demo1.model.Flowers;
import com.example.demo1.model.FlowersRecommend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlowersRecommendRepository extends JpaRepository<FlowersRecommend,Long> {
    List<FlowersRecommend> findByFlower(Flowers flower);
}
