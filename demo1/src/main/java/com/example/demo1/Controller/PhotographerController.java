package com.example.demo1.Controller;

import com.example.demo1.model.Photographer;
import com.example.demo1.services.PhotograperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/photographer")
public class PhotographerController {
    @Autowired
    private PhotograperRepository photographerRepository;

    @GetMapping("/getPhotographers")
    public ResponseEntity<List<Photographer>> getAllPhotographers() {
        return new ResponseEntity<>(photographerRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/getPhotographer/{id}")
    public ResponseEntity<Photographer> getPhotographerById(@PathVariable Long id) {
        Photographer photographer = photographerRepository.findById(id).orElse(null);
        if (photographer == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(photographer, HttpStatus.OK);
    }

    @PostMapping("/addPhotographer")
    public ResponseEntity<Photographer> addPhotographer(@RequestBody Photographer photographer) {
        Photographer newPhotographer = photographerRepository.save(photographer);
        return new ResponseEntity<>(newPhotographer, HttpStatus.CREATED);
    }

    @PutMapping("/updatePhotographer/{id}")
    public ResponseEntity<Photographer> updatePhotographer(@RequestBody Photographer photographer, @PathVariable Long id) {
        Photographer existingPhotographer = photographerRepository.findById(id).orElse(null);
        if (existingPhotographer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        existingPhotographer.setName(photographer.getName());
        existingPhotographer.setLocation(photographer.getLocation());
        existingPhotographer.setDescription(photographer.getDescription());
        existingPhotographer.setYearsOfExperience(photographer.getYearsOfExperience());
        existingPhotographer.setPhone(photographer.getPhone());
        existingPhotographer.setImageUrl(photographer.getImageUrl());
        photographerRepository.save(existingPhotographer);
        return new ResponseEntity<>(existingPhotographer, HttpStatus.OK);
    }

    @DeleteMapping("/deletePhotographer/{id}")
    public ResponseEntity<Void> deletePhotographer(@PathVariable Long id) {
        if (photographerRepository.existsById(id)) {
            photographerRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
