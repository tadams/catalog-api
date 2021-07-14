package com.example.api.catalog.integration;

import com.example.api.catalog.controller.BookController;
import com.example.api.catalog.exception.BookNotFoundException;
import com.example.api.catalog.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class BookControllerMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    public void shouldReturnNotFound() throws Exception {
        String notFoundIsbn = "123";
        when(bookService.viewBookDetails(notFoundIsbn)).thenThrow(new BookNotFoundException(notFoundIsbn));

        mockMvc.perform(get("/books/" + notFoundIsbn))
               .andExpect(status().isNotFound())
               .andExpect(content().json("{'error': 'A book with ISBN 123 was not found'}"));
    }

}
