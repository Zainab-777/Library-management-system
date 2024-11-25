package com.java.springBoot.app.Service;

import com.java.springBoot.app.Model.Patron;
import com.java.springBoot.app.Repository.PatronRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatronService {

    private final PatronRepository patronRepository;

    public PatronService(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }

    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }

    public Optional<Patron> getPatronById(Long id) {
        return patronRepository.findById(id);
    }

    public Patron addPatron(Patron patron) {
        return patronRepository.save(patron);
    }

    public Patron updatePatron(Long id, Patron patronDetails) {
        return patronRepository.findById(id)
                .map(patron -> {
                    patron.setName(patronDetails.getName());
                    patron.setEmail(patronDetails.getEmail());
                    patron.setPhoneNumber(patronDetails.getPhoneNumber());
                    return patronRepository.save(patron);
                })
                .orElseThrow(() -> new RuntimeException("Patron not found with id: " + id));
    }

    public void deletePatron(Long id) {
        patronRepository.deleteById(id);
    }
}
