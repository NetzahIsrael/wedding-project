package com.example.demo1.Controller;

import com.example.demo1.model.Hall;
import com.example.demo1.model.HallRecommend;
import com.example.demo1.services.HallRecommendRepository;
import com.example.demo1.services.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class HallRecommendController {

    @RestController
    @CrossOrigin
    @RequestMapping("api/flowers/recommendations")
    public class FlowersRecommendController {

        @Autowired
        private HallRecommendRepository hallRecommendRepository;

        @Autowired
        private HallRepository hallRepository;
        public FlowersRecommendController(HallRecommendRepository hallRecommendRepository, HallRepository hallRepository) {
            this.hallRecommendRepository = hallRecommendRepository;
            this.hallRepository = hallRepository;
        }



        @GetMapping("/getRecommendations/{hallId}")
        public ResponseEntity<List<HallRecommend>> getRecommendations(@PathVariable Long hallId) {
            Hall hall = hallRepository.findById(hallId).orElse(null);
            if (hall == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            List<HallRecommend> recommendations = hallRecommendRepository.findByHall(hall);
            return new ResponseEntity<>(recommendations, HttpStatus.OK);
        }


        // Get a specific recommendation by ID
        @GetMapping("/getRecommendation/{id}")
        public ResponseEntity<HallRecommend> getRecommendation(@PathVariable Long id) {
            HallRecommend recommendation = hallRecommendRepository.findById(id).orElse(null);
            if (recommendation == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(recommendation, HttpStatus.OK);
        }

        // Add a new recommendation to a specific flower
        @PostMapping("/addRecommendation/{hallId}")
        public ResponseEntity<HallRecommend> addRecommendation(@PathVariable Long hallId, @RequestBody HallRecommend recommendation) {
            Hall hall = hallRepository.findById(hallId).orElse(null);

            if (hall == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            recommendation.setHall(hall); // קישור ההמלצה לפרח
            HallRecommend newRecommendation = hallRecommendRepository.save(recommendation); // שמירת ההמלצה
            return new ResponseEntity<>(newRecommendation, HttpStatus.CREATED);
        }


        @PutMapping("/updateRecommendation/{id}")
        public ResponseEntity<Void> updateRecommendation(@PathVariable Long id, @RequestBody HallRecommend recommendationDetails) {
            HallRecommend recommendation = hallRecommendRepository.findById(id).orElse(null);

            if (recommendation == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            recommendation.setReviewerName(recommendationDetails.getReviewerName());
            recommendation.setComment(recommendationDetails.getComment());
            recommendation.setRating(recommendationDetails.getRating());
            recommendation.setDate(recommendationDetails.getDate());

            hallRecommendRepository.save(recommendation);
            return new ResponseEntity<>(HttpStatus.OK);
        }



        // Delete a recommendation by ID
        @DeleteMapping("/deleteRecommendation/{id}")
        public ResponseEntity<Void> deleteRecommendation(@PathVariable Long id) {
            if (hallRecommendRepository.existsById(id)) {
                hallRecommendRepository.deleteById(id);
                return ResponseEntity.noContent().build(); // מחזיר 204 אם המחיקה הצליחה
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    }
}
