package com.example.demo1.model;



import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
public class PhotoRecommend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reviewerName;
    private String comment;
    private int rating; // דירוג מ-1 עד 5

    @ManyToOne
    @JoinColumn(name = "photographer_id")
    private Photographer photographer; // קשר בין המלצה לצלם

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

    public Photographer getPhotographer() {
        return photographer;
    }

    public void setPhotographer(Photographer photographer) {
        this.photographer = photographer;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}

