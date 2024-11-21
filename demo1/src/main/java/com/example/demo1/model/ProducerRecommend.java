package com.example.demo1.model;


import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
public class ProducerRecommend {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reviewerName;
    private String comment;
    private int rating; // דירוג מ-1 עד 5

    @ManyToOne
    @JoinColumn(name = "producer_id")
    private com.example.demo1.model.Producer producer; // קשר בין המלצה לצלם

    private LocalDate date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }


    public com.example.demo1.model.Producer getProducer() {
        return producer;
    }

    public void setProducer(com.example.demo1.model.Producer producer) {
        this.producer = producer;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
// תאריך ההמלצה
}
