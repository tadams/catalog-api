package com.example.api.catalog.domain;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.Year;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

class BookTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void shouldFindAllFieldsCorrect() {
        Book book = new Book("1234", "Good Book", "Fred Boggs", Year.of(1985), 1.25, "Polar");

        assertThat(validator.validate(book)).isEmpty();
    }

    @Test
    public void shouldFindIsbnTitleAuthorPubYearPriceErrors() {
        Book book = new Book("", "", null, Year.of(3000), -1.0, "Polar");

        Set<ConstraintViolation<Book>> violations = validator.validate(book);

        assertThat(violations).hasSize(5);
        for (ConstraintViolation<Book> violation : violations) {
            switch (violation.getPropertyPath().toString()) {
                case "author" : assertThat(violation.getMessage()).contains("author must be defined - test failure");
                    break;
                case "isbn" : assertThat(violation.getMessage()).contains("ISBN must be defined");
                    break;
                case "publishingYear" : assertThat(violation.getMessage()).contains("future published");
                    break;
                case "price" : assertThat(violation.getMessage()).contains("price must be greater than zero");
                    break;
                case "title" : assertThat(violation.getMessage()).contains("title must be defined");
                    break;
                default: fail("Unknown property error");
            }
        }
    }

}