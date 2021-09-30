package com.rest.restApi.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.rest.restApi.entities.Book;
import com.rest.restApi.exceptions.BookAlreadyExistsException;
import com.rest.restApi.exceptions.BookNotExistsException;
import com.rest.restApi.services.BookService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
public class BookController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);
	@Autowired
	private BookService bookService;

	@ApiOperation(value = "save a book")
	@RequestMapping(value = "/books", method = RequestMethod.POST, consumes = { "application/json" })
	@ResponseStatus(value = HttpStatus.CREATED)
	public Book saveBook(@RequestBody @Valid final Book book) throws BookAlreadyExistsException {
		LOGGER.info("Received Request to save {} ", book);
		book.setTitle(book.getTitle().toUpperCase());
		book.setAuthor(book.getAuthor().toUpperCase());
		return bookService.saveBook(book);
	}

	@ApiOperation(value = "Retrieve a list of books .", responseContainer = "List")
	@RequestMapping(value = "/books", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<List<Book>> getAll() {
		LOGGER.info("Received Request to retrieve all books");

		try {
			List<Book> items = new ArrayList<Book>();

			bookService.getAll().forEach(items::add);

			if (items.isEmpty())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			return new ResponseEntity<>(items, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Retrieve a  book By id .")
	@RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
	public Book getBook(@PathVariable Long id) throws BookNotExistsException {
		LOGGER.info("Received request to retrieve a  book  {}", id);
		Book book = bookService.getBook(id);
		return book;
	}

	@ApiOperation(value = "Update a  book .")
	@RequestMapping(value = "/books/{id}", method = RequestMethod.PUT, consumes = { "application/json" })
	@ResponseStatus(value = HttpStatus.CREATED)
	public Book Update(@RequestBody @Valid Book book, @PathVariable Long id) throws BookNotExistsException {
		LOGGER.info("Received request to update a  book  {}", id);
		return bookService.updateBook(book, id);
	}

	@ApiOperation(value = "Delete  a  book .")
	@RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
	public void deleteBook(@PathVariable Long id) throws BookNotExistsException {
		LOGGER.info("Delete a Book {}", id);
		bookService.deleteBook(id);
	}

	@ApiOperation(value = "Get List of Books By a title .")
	@RequestMapping(value = "/books/title", method = RequestMethod.GET, produces = { "application/json" })
	public List<Book> getBooksByTitle(@RequestParam(value = "title") String title) {
		LOGGER.info("Get List of Books By a title= {}", title);
		return bookService.findByTitle(title.toUpperCase());
	}

	@ApiOperation(value = "Get List of Books By a Author .")
	@RequestMapping(value = "/books/author", method = RequestMethod.GET, produces = { "application/json" })
	public List<Book> getBooksByAuthor(@RequestParam(value = "author") String author) {
		LOGGER.info("Get List of Books By a author= {}", author);
		return bookService.findByAuthor(author.toUpperCase());
	}

	@ApiOperation(value = "Get List of Books By a Category name.")
	@RequestMapping(value = "/books/category", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<List<Book>> getBooksByCategory(@RequestParam(value = "category") String category) {
		LOGGER.info("Get List of Books By a category= {}", category);
		List<Book> books= bookService.findByCategory(category);

		try{		if (books.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
	}catch(Exception e){
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	}
}
