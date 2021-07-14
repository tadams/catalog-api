package com.example.api.catalog.service;

import com.example.api.catalog.domain.Book;
import com.example.api.catalog.exception.BookAlreadyExistsException;
import com.example.api.catalog.exception.BookNotFoundException;
import com.example.api.catalog.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Collection<Book> viewBookList() {
        return bookRepository.findAll();
    }

    public Book viewBookDetails(String isbn) {
        return bookRepository.findByIsbn(isbn).orElseThrow(() -> new BookNotFoundException(isbn));
    }

    public Book addBookToCatalog(Book book) {
        if (bookRepository.existsByIsbn(book.getIsbn())) {
            throw new BookAlreadyExistsException(book.getIsbn());
        }
        return bookRepository.save(book);
    }

    public void removeBookFromCatalog(String isbn) {
        if (bookRepository.existsByIsbn(isbn)) {
            bookRepository.deleteByIsbn(isbn);
            return;
        }
        throw new BookNotFoundException(isbn);
    }

    public Book editBookDetails(String isbn, Book book) {
        Optional<Book> existingBook = bookRepository.findByIsbn(isbn);
        if (existingBook.isEmpty()) {
            addBookToCatalog(book);
            return book;
        }
        Book bookToUpdate = existingBook.get();
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setPrice(book.getPrice());
        bookToUpdate.setPublishingYear(book.getPublishingYear());
        bookToUpdate.setTitle(book.getTitle());
        return bookRepository.save(bookToUpdate);
    }


}
