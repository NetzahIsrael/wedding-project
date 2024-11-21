package com.example.demo1.services;

import com.example.demo1.model.PhotoRecommend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotograperRecommendRepository extends JpaRepository<PhotoRecommend,Long> {
}
