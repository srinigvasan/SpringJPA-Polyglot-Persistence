package com.udacity.course3.reviews.service;

import com.udacity.course3.reviews.entity.Comments;
import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Reviews;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.repository.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ReviewsRepository reviewsRepository;

   @Override
    public Reviews saveReviewForProduct(int productId,Reviews reviews){
        Optional<Product> optionalProduct =productRepository.findById(productId);
        if(optionalProduct.isPresent()){
            Product product=optionalProduct.get();
            reviews.setProductId(productId);
            return reviewsRepository.save(reviews);
        }
       else
           throw new ProductNotFoundException("Product Not Found for "+productId);
    }

    @Override
    public List<Reviews> getReviewsForProduct(int productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent()) {
            throw new ProductNotFoundException("Product Not Found for " + productId);
        }
        return reviewsRepository.findAllByProductId(productId);
   }

    @Override
    public Reviews addCommentsForReview(String reviewId, Comments comments) {
        Optional<Reviews> optionalReview=reviewsRepository.findById(reviewId);
        if(optionalReview.isPresent()){
                    Reviews reviews=optionalReview.get();
                    List<Comments> commentsList =reviews.getComments();
                    if(commentsList==null) {
                        commentsList = new ArrayList<Comments>();
                    }
                    comments.setId("comment"+(commentsList.size()+1));
                    commentsList.add(comments);
                    reviews.setComments(commentsList);
                    return reviewsRepository.save(reviews);

        }
        else{
           throw new ReviewNotFoundException("Review does not exist for "+reviewId);
        }

    }

    @Override
    public List<Comments> getAllCommentForReview(String reviewId) {
        Optional<Reviews> optionalReview=reviewsRepository.findById(reviewId);
        if(optionalReview.isPresent()){
           return optionalReview.get().getComments();

        } else{
            throw new ReviewNotFoundException("Review does not exist for "+reviewId);
        }

    }
}
