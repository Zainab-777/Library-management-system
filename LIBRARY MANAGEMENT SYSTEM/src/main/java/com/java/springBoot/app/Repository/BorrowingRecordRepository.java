package com.java.springBoot.app.Repository;

import com.java.springBoot.app.Model.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {
    // يمكن إضافة استعلامات مخصصة هنا إذا لزم الأمر
}