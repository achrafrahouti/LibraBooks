package com.rest.restApi.exceptions;

public class CategoryAlreadyExistException extends Exception {

    /**
     * 
     */
    public CategoryAlreadyExistException() {
    }

    /**
     * @param message
     */
    public CategoryAlreadyExistException(String message) {
        super(message);
    }

    
}
