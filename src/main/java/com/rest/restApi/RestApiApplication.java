package com.rest.restApi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.rest.restApi.entities.Book;
import com.rest.restApi.services.BookServiceImpl;

@SpringBootApplication
public class RestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiApplication.class, args);
	}

	
	@Bean
	CommandLineRunner initData(BookServiceImpl bookrepo) {
	return args -> {
		bookrepo.saveBook(new Book(1L, "Aristo", "Mitaphisica"));
		bookrepo.saveBook(new Book(2L, "Plato", "Zinon"));
		bookrepo.saveBook(new Book(3L, "Aristo", "Organon"));
		bookrepo.saveBook(new Book(4L, "Talis", "Philosaphia"));
	};
	}
}
