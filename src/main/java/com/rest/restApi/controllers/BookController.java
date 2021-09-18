package com.rest.restApi.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rest.restApi.entities.Book;
import com.rest.restApi.exceptions.BookAlreadyExistsException;
import com.rest.restApi.exceptions.BookNotExistsException;
import com.rest.restApi.services.BookService;

import io.swagger.annotations.ApiOperation;

@RestController
public class BookController {

	private static final Logger LOGGER=LoggerFactory.getLogger(BookController.class);
	@Autowired
	private  BookService bookservice ;
	
	@ApiOperation(value = "save a book")
	@RequestMapping(value = "/books",method = RequestMethod.POST,consumes ={"application/json"})
	@ResponseStatus(value = HttpStatus.CREATED)
	public Book saveBook(@RequestBody @Valid final Book book ) throws BookAlreadyExistsException {
		LOGGER.info("Received Request to save {} ",book);
		return bookservice.saveBook(book);
	}
	
	@ApiOperation(value = "Retrieve a list of books ." ,responseContainer = "List")
	@RequestMapping(value = "/books" , method = RequestMethod.GET,produces = {"application/json"})
	public List<Book> getAll(){
		LOGGER.info("Received Request to retrieve all books");
		return  bookservice.getAll();
	}
	
	@ApiOperation(value = "Retrieve a  book with id ." )
	@RequestMapping(value = "/books/{id}" ,method = RequestMethod.GET)
	public Book getBook(@PathVariable Long id) throws BookNotExistsException  {
		LOGGER.info("Received request to retreive a  book  {}",id);
		Book book = bookservice.getBook(id);
		return book;
	}
	
	@ApiOperation(value = "Update a  book ." )
	@RequestMapping(value = "/books/{id}" ,method = RequestMethod.PUT,consumes = {"application/json"})
	@ResponseStatus(value=HttpStatus.CREATED)
	public Book Update(@RequestBody @Valid Book book,@PathVariable Long id) throws BookNotExistsException {
		LOGGER.info("Received request to update a  book  {}",id);
		return bookservice.updateBook(book, id);
	}
	
	@RequestMapping(value = "/books/{id}",method = RequestMethod.DELETE)
	public void deleteBook(@PathVariable Long id) throws BookNotExistsException {
		LOGGER.info("Delete a Book {}",id);
		bookservice.deleteBook(id);
	}

}