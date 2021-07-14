package com.example.api.catalog.repository;

import com.example.api.catalog.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {
    Collection<Book> findAll();
    Optional<Book> findByIsbn(String isbn);
    boolean existsByIsbn(String isbn);
    Book save(Book book);

    @Transactional
    void deleteByIsbn(String isbn);
}
