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
public class CommentRepositoryTest {
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


    }
    @Test
    public void testAddCommentsForReview(){
        String id="testid1";
        String reviewContent="Review for product1";
        String reviewComment="Comment for Review1";
        addReview(id,reviewContent);
        addComment(id,reviewComment);
        Query query = new Query();
        query.addCriteria(Criteria.where("id").in(id));

        Reviews searched=mongoTemplate.findOne(query,Reviews.class,"reviews");
        assertEquals("Review comments details should match",reviewComment,searched.getComments().get(0).getReviewComments());

    }

    @Test
    public void testlistAllCommentsForReview(){
        String id="testid1";
        String reviewContent="Review for product1";
        String reviewComment1="Comment1 for Review1";
        String reviewComment2="Comment2 for Review1";
        addReview(id,reviewContent);
        addComment(id,reviewComment1);
        addComment(id,reviewComment2);
        Query query = new Query();
        query.addCriteria(Criteria.where("id").in(id));
        Reviews searched=mongoTemplate.findOne(query,Reviews.class,"reviews");
        assertEquals("Review comment1 details should match",reviewComment1,searched.getComments().get(0).getReviewComments());
        assertEquals("Review comment2 details should match",reviewComment2,searched.getComments().get(1).getReviewComments());

    }

    public void addReview(String id,String comment){
        Reviews reviews=new Reviews();
        reviews.setId(id);
        reviews.setProductId(1);
        reviews.setReviewContent(comment);
        mongoTemplate.save(reviews, "reviews");
    }



    public void addComment(String reviewid,String reviewcomment){
        Query query = new Query();
        query.addCriteria(Criteria.where("id").in(reviewid));
        Reviews searched=mongoTemplate.findOne(query,Reviews.class,"reviews");
        List<Comments> commentsList=searched.getComments();


        if(commentsList==null)
            commentsList=new ArrayList<Comments>();
        Comments comments=new Comments("comment"+(commentsList.size()+1),reviewcomment);

        commentsList.add(comments);

        mongoTemplate.updateFirst(query,  Update.update("comments",commentsList),Reviews.class,"reviews");


    }


}
