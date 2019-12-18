package com.udacity.course3.reviews;

import com.mongodb.MongoClient;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@TestConfiguration
public class ReviewAPITestConfiguration {
    @Bean(name="mongoTemplate")
    public MongoTemplate createTemplate(){
        String ip = "localhost";
        int port = 27017;
        return new MongoTemplate(new MongoClient(ip, port), "test");
    }




}
