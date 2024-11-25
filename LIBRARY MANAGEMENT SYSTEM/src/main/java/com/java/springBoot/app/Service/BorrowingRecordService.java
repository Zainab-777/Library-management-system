package com.java.springBoot.app.Service;

import com.java.springBoot.app.Model.BorrowingRecord;
import com.java.springBoot.app.Repository.BorrowingRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BorrowingRecordService {

    private final BorrowingRecordRepository borrowingRecordRepository;

    public BorrowingRecordService(BorrowingRecordRepository borrowingRecordRepository) {
        this.borrowingRecordRepository = borrowingRecordRepository;
    }

    public List<BorrowingRecord> getAllRecords() {
        return borrowingRecordRepository.findAll();
    }

    public Optional<BorrowingRecord> getRecordById(Long id) {
        return borrowingRecordRepository.findById(id);
    }

    public BorrowingRecord addRecord(BorrowingRecord record) {
        return borrowingRecordRepository.save(record);
    }

    public BorrowingRecord updateRecord(Long id, BorrowingRecord recordDetails) {
        return borrowingRecordRepository.findById(id)
                .map(record -> {
                    record.setBorrowDate(recordDetails.getBorrowDate());
                    record.setReturnDate(recordDetails.getReturnDate());
                    record.setBook(recordDetails.getBook());
                    record.setPatron(recordDetails.getPatron());
                    return borrowingRecordRepository.save(record);
                })
                .orElseThrow(() -> new RuntimeException("Record not found with id: " + id));
    }

    public void deleteRecord(Long id) {
        borrowingRecordRepository.deleteById(id);
    }

}
