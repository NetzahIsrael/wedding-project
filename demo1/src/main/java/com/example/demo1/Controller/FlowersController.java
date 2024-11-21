package com.example.demo1.Controller;

import com.example.demo1.model.Flowers;
import com.example.demo1.services.FlowersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/flowers")
public class FlowersController {
    @Autowired
    private FlowersRepository flowersRepository;


    public FlowersController(FlowersRepository flowersRepository) {
        this.flowersRepository = flowersRepository;
    }


    @GetMapping("/getFlowers")
    public ResponseEntity<List<Flowers>> getFlowers(){
        return new ResponseEntity<>(flowersRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/getFlower/{id}")
    public ResponseEntity <Flowers> getFlower(@PathVariable Long id) {
        Flowers f = flowersRepository.findById(id).orElse(null);
        if (f == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(f, HttpStatus.OK);
    }


    @PostMapping("/addFlower")
    public ResponseEntity<Flowers> addFlower(@RequestBody Flowers flowers){
        Flowers newFlower=flowersRepository.save(flowers);
        return new ResponseEntity<>(newFlower, HttpStatus.CREATED);
    }
//    @PutMapping("/updateFlower/{id}")
//    public ResponseEntity<Flowers> updateFlower(@PathVariable Long id, @RequestBody Flowers flowers) {
//        if (!flowersRepository.existsById(id)) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        if (!id.equals(flowers.getId())) {
//            return new ResponseEntity<>(HttpStatus.CONFLICT);
//        }
//        Flowers updatedFlower = flowersRepository.save(flowers);
//        return new ResponseEntity<>(updatedFlower, HttpStatus.OK);
//    }


    @PutMapping("/updateFlower/{id}")
    public ResponseEntity updateFlowers(@RequestBody Flowers flowers, @PathVariable Long id) {
        if (flowersRepository.findById(id).isPresent()) {
            Flowers f = flowersRepository.findById(id).get();
            f.setName(flowers.getName());
            f.setDescription(flowers.getDescription());
            f.setDate(flowers.getDate());
            f.setPhone(flowers.getPhone());
            f.setImageUrl(flowers.getImageUrl());
            f.setPosition(flowers.getPosition());
            flowersRepository.save(f);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteFlower/{id}")
    public ResponseEntity<Void> deleteFlower(@PathVariable Long id){
        flowersRepository.deleteById(id);
        return ResponseEntity.noContent().build();  // מחזיר 204
    }
//    //פונקצית חיפוש פרח לפי שם או מספר הרייטינג או שניהם
//    @GetMapping("/searchFlower")
//    public List<Flowers> searchFlowers(@RequestParam(required = false) String name,
//                                       @RequestParam(required = false) String position) {
//        if (name != null && position != null) {
//            return flowersRepository.findByNameContainingAndPositionGreaterThanEqual(name, position);
//        } else if (name != null) {
//            return flowersRepository.findByNameContaining(name);
//        } else if (position != null) {
//            return flowersRepository.findByPositionGreaterThanEqual(position);
//        } else {
//            return flowersRepository.findAll();
//        }
//    }
}