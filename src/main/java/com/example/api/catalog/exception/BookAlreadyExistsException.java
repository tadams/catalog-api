package com.example.api.catalog.exception;

public class BookAlreadyExistsException extends RuntimeException {
    public BookAlreadyExistsException(String isbn) {
        super(String.format("A book with ISBN %s already exists", isbn));
    }
}
