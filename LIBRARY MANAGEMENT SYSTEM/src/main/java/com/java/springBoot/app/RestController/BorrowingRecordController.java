package com.java.springBoot.app.RestController;

import com.java.springBoot.app.Model.BorrowingRecord;
import com.java.springBoot.app.Service.BookService;
import com.java.springBoot.app.Service.BorrowingRecordService;
import com.java.springBoot.app.Service.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/borrow")
public class BorrowingRecordController {

    @Autowired
    private  BorrowingRecordService borrowingRecordService;
    @Autowired
    private  BookService bookService;
    @Autowired
    private  PatronService patronService;



    @PostMapping("/{bookId}/patron/{patronId}")
    public ResponseEntity<String> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        try {
            var book = bookService.getBookById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
            var patron = patronService.getPatronById(patronId).orElseThrow(() -> new RuntimeException("Patron not found"));

            boolean alreadyBorrowed = borrowingRecordService.getAllRecords().stream()
                    .anyMatch(record -> record.getBook().getId().equals(bookId) &&
                            record.getPatron().getId().equals(patronId) &&
                            record.getReturnDate() == null);

            if (alreadyBorrowed) {
                return ResponseEntity.badRequest().body("The book is already borrowed by this patron and not returned yet.");
            }

            BorrowingRecord record = new BorrowingRecord();
            record.setBook(book);
            record.setPatron(patron);
            record.setBorrowDate(java.time.LocalDate.now());
            ResponseEntity.ok(borrowingRecordService.addRecord(record));
            return ResponseEntity.ok("Borrowing record created successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping("/{bookId}/patron/{patronId}")
    public ResponseEntity<String> returnBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        try {
            BorrowingRecord record = borrowingRecordService.getAllRecords().stream()
                    .filter(r -> r.getBook().getId().equals(bookId) &&
                            r.getPatron().getId().equals(patronId) &&
                            r.getReturnDate() == null)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No active borrowing record found for this book and patron."));

            record.setReturnDate(java.time.LocalDate.now());
            borrowingRecordService.updateRecord(record.getId(), record);

            return ResponseEntity.ok("Book returned successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
