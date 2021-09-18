package com.rest.restApi.exceptions;

@SuppressWarnings("serial")
//@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BookNotExistsException extends Exception {

	public BookNotExistsException(String message) {
		super(message);
			}
}
