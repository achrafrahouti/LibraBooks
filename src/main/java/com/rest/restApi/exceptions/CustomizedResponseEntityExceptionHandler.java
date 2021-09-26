package com.rest.restApi.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(BookAlreadyExistsException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public ExceptionResponse handleBookAlreadyExistsException(BookAlreadyExistsException e,WebRequest request) {
	return new ExceptionResponse(new Date(), e.getMessage(),"Book Already Exists   " + request.getDescription(false));
	}
	
	@ExceptionHandler(BookNotExistsException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ExceptionResponse handleBookNotExistsException(BookNotExistsException e,WebRequest request) {
		return new ExceptionResponse(new Date(), e.getMessage(),"Book Not Exists   " + request.getDescription(false));
	}

	@ExceptionHandler(EmailAlreadyExistsException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public ExceptionResponse handleEmailAlreadyExistsException(EmailAlreadyExistsException e,WebRequest request) {
	return new ExceptionResponse(new Date(), e.getMessage(),"Email Already Used   " + request.getDescription(false));
	}

	@ExceptionHandler(CustomUserNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ExceptionResponse handleUserNotExistsException(CustomUserNotFoundException e,WebRequest request) {
		return new ExceptionResponse(new Date(), e.getMessage(),"User Not Exists   " + request.getDescription(false));
	}


	@ExceptionHandler(OfferNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ExceptionResponse handleOfferNotExistsException(OfferNotFoundException e,WebRequest request) {
		return new ExceptionResponse(new Date(), e.getMessage(),"Offer Not Exists   " + request.getDescription(false));
	}
}
