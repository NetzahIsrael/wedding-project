package com.example.demo1.Controller;


import com.example.demo1.model.Producer;
import com.example.demo1.services.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/producer")
public class ProducerController {
    @Autowired
    private ProducerRepository producerRepository;

    @GetMapping("/getProducers")
    public ResponseEntity<List<Producer>> getAllProducers() {
        return new ResponseEntity<>(producerRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/getProducer/{id}")
    public ResponseEntity<Producer> getProducerById(@PathVariable Long id) {
        Producer producer = producerRepository.findById(id).orElse(null);
        if (producer == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(producer, HttpStatus.OK);
    }

    @PostMapping("/addProducer")
    public ResponseEntity<Producer> addProducer(@RequestBody Producer producer) {
        Producer newProducer = producerRepository.save(producer);
        return new ResponseEntity<>(newProducer, HttpStatus.CREATED);
    }

    @PutMapping("/updateProducer/{id}")
    public ResponseEntity<Producer> updateProducer(@RequestBody Producer producer, @PathVariable Long id) {
        Producer existingProducer = producerRepository.findById(id).orElse(null);
        if (existingProducer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        existingProducer.setName(producer.getName());
        existingProducer.setDescription(producer.getDescription());
        existingProducer.setPhone(producer.getPhone());
        existingProducer.setImageUrl(producer.getImageUrl());
        producerRepository.save(existingProducer);
        return new ResponseEntity<>(existingProducer, HttpStatus.OK);
    }

    @DeleteMapping("/deleteProducer/{id}")
    public ResponseEntity<Void> deleteProducer(@PathVariable Long id) {
        if (producerRepository.existsById(id)) {
            producerRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

