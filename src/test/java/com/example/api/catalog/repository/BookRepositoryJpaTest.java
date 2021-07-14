package com.example.api.catalog.repository;

import com.example.api.catalog.config.JpaConfig;
import com.example.api.catalog.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.time.Year;
import java.util.Collection;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(JpaConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryJpaTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager entityManager;

    private final String bookIsbn = "1234561235";
    private final Book saveBook = new Book(bookIsbn, "Title", "Author", Year.of(2000), 12.90, "Polar");

    @Test
    void findBookByIsbnWhenExisting() {
        Book expectedBook = saveBook();

        Optional<Book> actualBook = bookRepository.findByIsbn(bookIsbn);

        assertThat(actualBook).isPresent();
        assertThat(actualBook.get().getIsbn()).isEqualTo(expectedBook.getIsbn());
        assertThat(actualBook.get()).isEqualTo(expectedBook);
    }

    @Test
    void shouldFindAllBooks() {
        Book expectedBook = saveBook();

        Collection<Book> allBooks = bookRepository.findAll();

        assertThat(allBooks).hasSize(1);
        assertThat(allBooks).containsExactly(expectedBook);
    }

    private Book saveBook() {
        entityManager.persist(saveBook);
        entityManager.detach(saveBook);
        return saveBook;
    }
}
