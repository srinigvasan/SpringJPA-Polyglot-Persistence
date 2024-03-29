package com.udacity.course3.reviews;


import com.mongodb.MongoClient;
import com.udacity.course3.reviews.entity.Comments;
import com.udacity.course3.reviews.entity.Reviews;
import com.udacity.course3.reviews.repository.ReviewsRepository;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class ReviewRepositoryTest {
    private MongodExecutable mongodExecutable;

  @Autowired
   private ReviewsRepository repository;

  private MongoTemplate mongoTemplate;

    @After
    public void clean() {
        mongodExecutable.stop();
    }

    @Before
    public void setup() throws Exception {
        String ip = "localhost";
        int port = 27018;

        IMongodConfig mongodConfig = new MongodConfigBuilder().version(Version.Main.PRODUCTION)
                .net(new Net(ip, port, Network.localhostIsIPv6()))
                .build();

        MongodStarter starter = MongodStarter.getDefaultInstance();
        mongodExecutable = starter.prepare(mongodConfig);

        mongodExecutable.start();
        mongoTemplate = new MongoTemplate(new MongoClient(ip, port), "test");
        System.out.println("************************");
        System.out.println("Object created"+mongoTemplate);
        System.out.println("************************");

    }


    @Test
   public  void testAddReviewForProduct(){
        String id="testid1";
        String reviewContent="Review for product1";
        addReview(id,reviewContent);
        Query query = new Query();
        query.addCriteria(Criteria.where("id").in(id));

        Reviews searched=mongoTemplate.findOne(query,Reviews.class,"reviews");
        assertEquals("Review content should match with saved object",reviewContent,searched.getReviewContent());
        assertEquals("Review Id should match with saved object",id,searched.getId());

    }

    @Test
    public void testAllReviewForProduct(){
        String id="testid1";
        String reviewContent1="Review1 for product1";
        addReview(id,reviewContent1);
        String reviewContent2="Review2 for product1";
        id="testid2";

        addReview(id,reviewContent2);
        Query query = new Query();
        query.addCriteria(Criteria.where("productId").in(1));
        List<Reviews> searched=mongoTemplate.find(query,Reviews.class,"reviews");

        assertEquals("There should be 2 reviews for product 1",searched.size(),2);
    }

    public void addReview(String id,String comment){
        Reviews reviews=new Reviews();
        reviews.setId(id);
        reviews.setProductId(1);
        reviews.setReviewContent(comment);
        mongoTemplate.save(reviews, "reviews");
    }


}
