package com.rest.restApi.reposotiry;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.rest.restApi.entities.Book;


public interface BookRepository extends JpaRepository<Book, Long>{

    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
}
