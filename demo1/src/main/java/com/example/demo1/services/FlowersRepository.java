package com.example.demo1.services;

import com.example.demo1.model.Flowers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlowersRepository extends JpaRepository<Flowers,Long> {
}
