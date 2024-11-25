package com.java.springBoot.app.RestController;
import com.java.springBoot.app.Model.Patron;
import com.java.springBoot.app.Service.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/patrons")
public class PatronController {
    @Autowired
    private  PatronService patronService;



    @GetMapping
    public List<Patron> getAllPatrons() {
        return patronService.getAllPatrons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPatronById(@PathVariable Long id) {
        try {
            Patron patron = patronService.getPatronById(id)
                    .orElseThrow(() -> new RuntimeException("Patron not found"));
            return ResponseEntity.ok(patron);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Patron not found");
        }
    }

    @PostMapping
    public Patron addPatron(@Valid @RequestBody Patron patron) {
        return patronService.addPatron(patron);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePatron( @PathVariable Long id, @Valid @RequestBody Patron patronDetails) {
        try {
            Patron updatedPatron = patronService.updatePatron(id, patronDetails);
            return ResponseEntity.ok(updatedPatron);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Patron u trying to edit does not exist");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatron(@PathVariable Long id) {
        try {
            patronService.deletePatron(id);
            return ResponseEntity.ok("Patron was deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("patron not found");
        }
    }
}
