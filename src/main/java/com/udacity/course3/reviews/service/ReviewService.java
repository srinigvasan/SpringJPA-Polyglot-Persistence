package com.udacity.course3.reviews.service;

import com.udacity.course3.reviews.entity.Comments;
import com.udacity.course3.reviews.entity.Reviews;

import java.util.List;

public interface ReviewService {
    Reviews saveReviewForProduct(int productId, Reviews reviews);
    List<Reviews> getReviewsForProduct(int productId);
    Reviews addCommentsForReview(String reviewId, Comments comments);
    List<Comments> getAllCommentForReview(String reviewId);
}
