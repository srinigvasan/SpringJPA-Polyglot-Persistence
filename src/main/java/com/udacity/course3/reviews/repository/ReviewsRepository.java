package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.entity.Comments;
import com.udacity.course3.reviews.entity.Reviews;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewsRepository extends MongoRepository<Reviews,String> {

    List<Reviews> findAllByProductId(int productId);

    @Override
    Optional<Reviews> findById(String reviewId);

}
