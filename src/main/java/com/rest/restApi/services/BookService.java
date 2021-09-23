package com.rest.restApi.services;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.rest.restApi.entities.Book;
import com.rest.restApi.exceptions.BookAlreadyExistsException;
import com.rest.restApi.exceptions.BookNotExistsException;


public interface BookService {

	Book saveBook(@NotNull @Valid final Book book) throws BookAlreadyExistsException;
	List<Book> getAll();
	Book getBook(Long bookId) throws BookNotExistsException;
	Book updateBook(Book book,Long id) throws BookNotExistsException;
	void deleteBook(final Long bookId) throws BookNotExistsException;

	List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
}
