package com.example.demo1.Controller;

import com.example.demo1.model.Singers;
import com.example.demo1.services.SingersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/singers")
public class SingersController {
    @Autowired
    private SingersRepository singersRepository;

    @GetMapping("/getSingers")
    public ResponseEntity<List<Singers>> getAllSingers() {
        return new ResponseEntity<>(singersRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/getSinger/{id}")
    public ResponseEntity<Singers> getSingerById(@PathVariable Long id) {
        Singers singer = singersRepository.findById(id).orElse(null);
        if (singer == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(singer, HttpStatus.OK);
    }

    @PostMapping("/addSinger")
    public ResponseEntity<Singers> addSinger(@RequestBody Singers singer) {
        Singers newSinger = singersRepository.save(singer);
        return new ResponseEntity<>(newSinger, HttpStatus.CREATED);
    }

    @PutMapping("/updateSinger/{id}")
    public ResponseEntity<Singers> updateSinger(@RequestBody Singers singer, @PathVariable Long id) {
        Singers existingSinger = singersRepository.findById(id).orElse(null);
        if (existingSinger == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        existingSinger.setName(singer.getName());
        existingSinger.setDescription(singer.getDescription());
        existingSinger.setPhone(singer.getPhone());
        existingSinger.setImageUrl(singer.getImageUrl());
        singersRepository.save(existingSinger);
        return new ResponseEntity<>(existingSinger, HttpStatus.OK);
    }

    @DeleteMapping("/deleteSinger/{id}")
    public ResponseEntity<Void> deleteSinger(@PathVariable Long id) {
        if (singersRepository.existsById(id)) {
            singersRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}


