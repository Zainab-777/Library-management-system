package com.java.springBoot.app.services;

import com.java.springBoot.app.Model.Book;
import com.java.springBoot.app.Repository.BookRepository;
import com.java.springBoot.app.Service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void testGetAllBooks() {
        List<Book> books = List.of(new Book(1L, "Book1", "Author1", 2020, "1234567890123"));
        Mockito.when(bookRepository.findAll()).thenReturn(books);

        List<Book> result = bookService.getAllBooks();

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Book1", result.get(0).getTitle());
    }

    @Test
    void testGetBookById() {
        Book book = new Book(1L, "Book1", "Author1", 2020, "1234567890123");
        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Optional<Book> result = bookService.getBookById(1L);

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("Book1", result.get().getTitle());
    }

    @Test
    void testSaveBook() {
        Book book = new Book(null, "Book1", "Author1", 2020, "1234567890123");
        Book savedBook = new Book(1L, "Book1", "Author1", 2020, "1234567890123");
        Mockito.when(bookRepository.save(book)).thenReturn(savedBook);

        Book result = bookService.addBook(book);

        Assertions.assertNotNull(result.getId());
        Assertions.assertEquals("Book1", result.getTitle());
    }

    @Test
    void testDeleteBook() {
        Long bookId = 1L;
        bookService.deleteBook(bookId);

        Mockito.verify(bookRepository, Mockito.times(1)).deleteById(bookId);
    }
}
