package com.java.springBoot.app.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.java.springBoot.app.Model.Patron;
import com.java.springBoot.app.Repository.PatronRepository;
import com.java.springBoot.app.Service.PatronService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

public class PatronServiceTest {

    @Mock
    private PatronRepository patronRepository;

    @InjectMocks
    private PatronService patronService;

    private Patron patron;

    @BeforeEach
    public void setUp() {
        patron = new Patron();
        patron.setId(1L);
        patron.setName("Alice Brown");
        patron.setEmail("alice.brown@example.com");
        patron.setPhoneNumber("+112233445544");

        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllPatrons() {
        when(patronRepository.findAll()).thenReturn(List.of(patron));

        var patrons = patronService.getAllPatrons();
        assertNotNull(patrons);
        assertEquals(1, patrons.size());
        assertEquals("Alice Brown", patrons.get(0).getName());
    }

    @Test
    public void testGetPatronById() {
        when(patronRepository.findById(1L)).thenReturn(Optional.of(patron));

        var result = patronService.getPatronById(1L);
        assertTrue(result.isPresent());
        assertEquals("Alice Brown", result.get().getName());
    }

    @Test
    public void testGetPatronByIdNotFound() {
        when(patronRepository.findById(1L)).thenReturn(Optional.empty());

        var result = patronService.getPatronById(1L);
        assertFalse(result.isPresent());
    }

    @Test
    public void testAddPatron() {
        when(patronRepository.save(any(Patron.class))).thenReturn(patron);

        var result = patronService.addPatron(patron);
        assertNotNull(result);
        assertEquals("Alice Brown", result.getName());
    }

    @Test
    public void testUpdatePatron() {
        when(patronRepository.findById(1L)).thenReturn(Optional.of(patron));
        when(patronRepository.save(any(Patron.class))).thenReturn(patron);

        patron.setName("Alice Brownies");
        var updatedPatron = patronService.updatePatron(1L, patron);
        assertEquals("Alice Brownies", updatedPatron.getName());
    }

    @Test
    public void testUpdatePatronNotFound() {
        when(patronRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            patronService.updatePatron(1L, patron);
        });

        assertEquals("Patron not found with id: 1", exception.getMessage());
    }

    @Test
    public void testDeletePatron() {
        doNothing().when(patronRepository).deleteById(1L);

        patronService.deletePatron(1L);

        verify(patronRepository, times(1)).deleteById(1L);
    }
}
