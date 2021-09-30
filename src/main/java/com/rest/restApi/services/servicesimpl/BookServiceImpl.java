package com.rest.restApi.services.servicesimpl;

import java.util.ArrayList;
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
import com.rest.restApi.services.BookService;

@Service
@Validated
public class BookServiceImpl implements BookService{

	private static final Logger LOGGER=LoggerFactory.getLogger(BookServiceImpl.class);
	
	@Autowired
	private BookRepository bookrepository;
	
	@Override
	@Transactional
	public Book saveBook(@NotNull @Valid Book book) throws BookAlreadyExistsException {
		LOGGER.info("Creating {}",book);
		if(bookrepository.existsById(book.getId())) {
			throw new BookAlreadyExistsException(String.format("there already exists a book with id =%s", book.getId()));
		}
		return bookrepository.save(book);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Book> getAll() {
		LOGGER.info("Retrieving all Books");
		return bookrepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Book getBook(Long bookId) throws BookNotExistsException {
		LOGGER.info("Retrieving  {}", bookId);
		if(!bookrepository.existsById(bookId)) {
			throw new BookNotExistsException(String.format("there no book with id =%s", bookId));
		}
		return 		bookrepository.getById(bookId);
	}

	@Override
	@Transactional
	public void deleteBook(final Long bookId) throws  BookNotExistsException{
		LOGGER.info("Deleting {} ",bookId);
		if(!bookrepository.existsById(bookId)) {
			throw new BookNotExistsException(String.format("Book  With id = %s don't exist", bookId));
		}
		bookrepository.deleteById(bookId);
		
	}

	@Override
	@Transactional
	public Book updateBook(Book book, Long id) throws BookNotExistsException {
		
		LOGGER.info("Updating {}",book);
					return bookrepository.findById(id).map(x->{
				x.setAuthor(book.getAuthor());
				x.setTitle(book.getTitle());
				return bookrepository.save(x);
			}).orElseThrow(()->new BookNotExistsException(String.format("Book  With id = %s don't exist", id)));
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<Book> findByTitle(String title) {

		LOGGER.info("Retrieving Books By Title = {}",title);
		return bookrepository.findByTitle(title);
	}

	@Override
	public List<Book> findByAuthor(String author) {

		LOGGER.info("Retrieving Books By Author = {}",author);
		
		return bookrepository.findByAuthor(author);
	}

	@Override
	public List<Book> findByCategory(String category) {
		LOGGER.info("Retrieving Books By Category = {}",category);
		List<Book> books=new ArrayList<>();
		bookrepository.findAll().forEach((x)->{
			if (x.getCategory().getName().equalsIgnoreCase(category)) {
				books.add(x);
			}
		});
		return books;
	}

	

}
