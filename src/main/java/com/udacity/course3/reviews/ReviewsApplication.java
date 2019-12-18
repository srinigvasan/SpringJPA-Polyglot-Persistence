package com.udacity.course3.reviews;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableMongoRepositories
public class ReviewsApplication {
	@Value("${spring.datasource.url}")
	private  String jdbcurl;
	@Value("${spring.datasource.username}")
	private  String mysqluser;
	@Value("${spring.datasource.password}")
	private  String mysqlpass;

	public static void main(String[] args) {
		SpringApplication.run(ReviewsApplication.class, args);
	}

    @Bean
	public CommandLineRunner createTables(){
     return args -> {
     	Flyway flyway = Flyway.configure().dataSource(jdbcurl,mysqluser,mysqlpass).load();
		 	flyway.migrate();


     };
	}


}