package com.example.demo1.model;


import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
public class SingersRecommend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reviewerName;
    private String comment;
    private int rating; // דירוג מ-1 עד 5

    @ManyToOne
    @JoinColumn(name = "singers_id")
    private Singers singers; // קשר בין המלצה לצלם

    private LocalDate date; // תאריך ההמלצה

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

    public Singers getSingers() {
        return singers;
    }

    public void setSingers(Singers singers) {
        this.singers = singers;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}

