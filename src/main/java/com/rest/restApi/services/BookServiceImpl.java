package com.rest.restApi.services;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.rest.restApi.entities.Book;
import com.rest.restApi.exceptions.BookAlreadyExistsException;
import com.rest.restApi.exceptions.BookNotExistsException;
import com.rest.restApi.reposotiry.BookRepository;

@Service
@Validated
public class BookServiceImpl implements BookService{

	private static final Logger LOGGER=LoggerFactory.getLogger(BookServiceImpl.class);
	
	@Autowired
	private BookRepository bookrepository;
	
	@Override
	@Transactional
	public Book saveBook(@NotNull @Valid Book book) throws BookAlreadyExistsException {
		LOGGER.debug("Creating {}",book);
		if(bookrepository.existsById(book.getId())) {
			throw new BookAlreadyExistsException(String.format("there already exists a book with id =%s", book.getId()));
		}
		return bookrepository.save(book);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Book> getAll() {
		LOGGER.debug("Retrieving all Books");
		return bookrepository.findAll();
	}

	@Override
	public Book getBook(Long bookId) {
		LOGGER.debug("Retrieving  {}", bookId);
		return bookrepository.getById(bookId);
	}

	@Override
	public void deleteBook(final Long bookId) throws  BookNotExistsException{
		LOGGER.debug("Deleting {} ",bookId);
		if(!bookrepository.existsById(bookId)) {
			throw new BookNotExistsException(String.format("there no book with id =%s", bookId));
		}
		bookrepository.deleteById(bookId);
		
	}

}
