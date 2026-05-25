package org.ximenia851.patient.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ximenia851.patient.model.Patient;
import org.ximenia851.patient.service.PatientService;

import java.util.List;

@RestController
@AllArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping("/add")
    public ResponseEntity<Patient> addPatient(@Valid @RequestBody Patient patient) {
        try {
            Patient savedPatient = patientService.save(patient);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPatient);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @GetMapping("/patients")
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(patientService.findAll());
    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        Patient patient = patientService.findById(id);
        if (patient == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(patient);
    }

    @GetMapping("/patients/find/{email}")
    public ResponseEntity<Patient> getPatientByEmail(@PathVariable String email) {
        Patient patient = patientService.findByEmail(email);
        if (patient == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(patient);
    }

    @PutMapping("/patients/{id}")
    public ResponseEntity<Patient> updatePatient(
            @PathVariable Long id,
            @RequestBody Patient patient) {  // pas de @Valid — le password n'est pas dans le form
        Patient existing = patientService.findById(id);
        if (existing == null) return ResponseEntity.notFound().build();

        patient.setId(id);

        // On conserve le mot de passe existant si non fourni
        if (patient.getPassword() == null || patient.getPassword().isBlank()) {
            patient.setPassword(existing.getPassword());
        }

        return ResponseEntity.ok(patientService.save(patient));
    }

    @DeleteMapping("/patients/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        Patient existing = patientService.findById(id);
        if (existing == null) return ResponseEntity.notFound().build();
        patientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}