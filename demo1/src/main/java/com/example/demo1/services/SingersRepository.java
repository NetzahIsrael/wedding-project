package com.example.demo1.services;

import com.example.demo1.model.Singers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SingersRepository extends JpaRepository<Singers, Long> {
}
