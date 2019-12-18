package com.udacity.course3.reviews.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.List;

@Document
public class Reviews {
    @Id
    private String id;

    private String reviewContent;

    private int productId;

    private List<Comments> comments;

    public Reviews() {
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }


    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public Reviews(String id, String reviewContent, int productId, List<Comments> comments) {
        this.id = id;
        this.reviewContent = reviewContent;
        this.productId = productId;
        this.comments = comments;
    }
}
