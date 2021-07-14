package com.example.api.catalog.config;

import com.example.api.catalog.domain.Book;
import com.example.api.catalog.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.Year;

@Component
@Profile("test-data")
public class BookDataLoader {

    @Autowired
    private BookRepository bookRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void loadBookTestData() {
        Book book1 = new Book("1234567891", "Northern Lights", "Lyra Silvertongue", Year.of(2011), 9.90, "Polar");
        Book book2 = new Book("1234567892", "Polar Journey", "Iorek Polarson", Year.of(1993), 12.90, "Polar");
        bookRepository.save(book1);
        bookRepository.save(book2);
    }
}
