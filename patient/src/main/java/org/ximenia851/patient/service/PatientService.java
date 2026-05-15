package org.ximenia851.patient.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.ximenia851.patient.model.Patient;
import org.ximenia851.patient.repository.PatientRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    private List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public Patient update(Patient newPatientData) {
        return patientRepository.save(newPatientData);
    }

    public void delete(Long id) {
        patientRepository.deleteById(id);
    }

    public Patient findById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

}
