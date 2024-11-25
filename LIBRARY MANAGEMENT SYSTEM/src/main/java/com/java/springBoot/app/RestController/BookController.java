package com.java.springBoot.app.RestController;

import com.java.springBoot.app.Model.Book;
import com.java.springBoot.app.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById( @PathVariable Long id) {
        try {
            Book book = bookService.getBookById(id)
                    .orElseThrow(() -> new RuntimeException("Book not found"));
            return ResponseEntity.ok(book);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Book not found");
        }
    }

    @PostMapping
    public Book addBook(@Valid @RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@Valid @PathVariable Long id,@Valid  @RequestBody Book bookDetails) {
        try {
            return ResponseEntity.ok(bookService.updateBook(id, bookDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Book u trying to edit does not exist");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook( @PathVariable Long id) {
        try {
            bookService.deleteBook(id);
            return ResponseEntity.ok("Book was deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Book with this id does not exist");
        }
    }
}
