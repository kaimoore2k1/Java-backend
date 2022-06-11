package com.senshop.backend;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.context.annotation.PropertySource;

// import com.senshop.backend.*;


@PropertySource(value = {"classpath:graphql/graphql.properties"})
@SpringBootApplication
@EnableMongoRepositories
public class Application {

	//@Autowired AccountRepository accountRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/* public void showAllGroceryItems() {
         
		accountRepository.findByUsername("kaimoore2001").forEach(item -> System.out.println((item)));
	} */

}
