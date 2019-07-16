package com.qrqs.spring.reactor;

import com.qrqs.spring.reactor.database.model.Event;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoOperations;

@SuppressWarnings({"unused"})
@SpringBootApplication
public class SpringReactorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringReactorApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(MongoOperations mongo) {
		return (String... args) -> {
			mongo.dropCollection(Event.class);
			mongo.createCollection(Event.class, CollectionOptions.empty().maxDocuments(200).size(100000).capped());
		};
	}
}
