package com.example.demo1.services;

import com.example.demo1.model.Photographer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotograperRepository extends JpaRepository<Photographer,Long> {
}
