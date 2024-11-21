package com.example.demo1.Controller;

import com.example.demo1.model.Hall;
import com.example.demo1.services.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/hall")
public class HallController {
    @Autowired
    private HallRepository hallRepository;
    @GetMapping("/getHalls")
    public ResponseEntity <List<Hall>> getAllHalls(){

        return new ResponseEntity<>(hallRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/getHall/{id}")
    public ResponseEntity<Hall>getHallById(@PathVariable Long id){
        Hall h= hallRepository.findById(id).orElse(null);
        if(h==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(h, HttpStatus.OK);
    }
    @GetMapping("/addHall")
    public  ResponseEntity<Hall>addHall(@RequestBody  Hall hall){
        Hall newHall=hallRepository.save(hall);
        return new ResponseEntity<>(newHall, HttpStatus.CREATED);
    }
    @GetMapping("/updateHall")
    public ResponseEntity<Hall>updateHall(@RequestBody  Hall hall,@PathVariable Long id){
        Hall newHall=hallRepository.findById(id).orElse(null);
        if(newHall==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        newHall.setName(hall.getName());
        newHall.setDate(hall.getDate());
        newHall.setDescription(hall.getDescription());
        newHall.setPhone(hall.getPhone());
        newHall.setPosition(hall.getPosition());
        newHall.setImageUrl(hall.getImageUrl());
        hallRepository.save(newHall);
        return new ResponseEntity<>(newHall, HttpStatus.OK);

    }
    @GetMapping("/deleteHall")
    public ResponseEntity<Hall>deleteHall(@PathVariable Long id){
        hallRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
