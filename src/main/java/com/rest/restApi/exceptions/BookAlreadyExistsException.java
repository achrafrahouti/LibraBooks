package com.rest.restApi.exceptions;

@SuppressWarnings("serial")
public class BookAlreadyExistsException extends Exception {

	public BookAlreadyExistsException() {
	}

	public BookAlreadyExistsException(String message) {
		super(message);
		
	}

}
