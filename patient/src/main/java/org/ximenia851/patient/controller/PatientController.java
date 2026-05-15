package org.ximenia851.patient.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.ximenia851.patient.model.Patient;
import org.ximenia851.patient.service.PatientService;

@RestController
@AllArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @PostMapping("/add")
    public ResponseEntity<Patient> addPatient(@Valid @RequestBody Patient patient) {
        try {
            Patient savePatient = patientService.save(patient);
            return ResponseEntity.status(HttpStatus.CREATED).body(savePatient);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }
}
