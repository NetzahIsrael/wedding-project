package com.example.demo1.services;

import com.example.demo1.model.ProducerRecommend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducerRecommendRepository extends JpaRepository<ProducerRecommend, Long> {
}
