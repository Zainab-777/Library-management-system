package com.java.springBoot.app.Repository;

import com.java.springBoot.app.Model.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatronRepository extends JpaRepository<Patron, Long> {
    // يمكن إضافة استعلامات مخصصة هنا إذا لزم الأمر
}