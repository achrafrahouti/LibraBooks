package com.rest.restApi.exceptions;

@SuppressWarnings("serial")
public class BookNotExistsException extends Exception {

	public BookNotExistsException(String message) {
		super(message);
			}
}
