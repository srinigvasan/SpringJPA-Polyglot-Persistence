package com.udacity.course3.reviews.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

@Document
public class Comments {
    @Id
    private String id;

    private String reviewComments;

    public Comments() {
    }


    public String getReviewComments() {
        return reviewComments;
    }

    public void setReviewComments(String reviewComments) {
        this.reviewComments = reviewComments;
    }

    public Comments(String id, String reviewComments) {
        this.id = id;
        this.reviewComments = reviewComments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
