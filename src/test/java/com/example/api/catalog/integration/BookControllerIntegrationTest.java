package com.example.api.catalog.integration;

import com.example.api.catalog.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldCreateBook() {
        Book newBook = new Book("123", "The Title", "Tom Adams", Year.of(2000), 1.25, "Polar");
        ResponseEntity<Book> postResponse = restTemplate.postForEntity("/books", newBook, Book.class);

        assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(postResponse.getBody().getIsbn()).isEqualTo(newBook.getIsbn());
        assertThat(postResponse.getBody().getPrice()).isEqualTo(newBook.getPrice());

        ResponseEntity<Book> getResponse = restTemplate.getForEntity("/books/" + newBook.getIsbn(), Book.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getResponse.getBody().getIsbn()).isEqualTo(newBook.getIsbn());
        assertThat(getResponse.getBody().getPrice()).isEqualTo(newBook.getPrice());
    }
}
