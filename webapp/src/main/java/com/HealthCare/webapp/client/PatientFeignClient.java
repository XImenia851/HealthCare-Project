package com.HealthCare.webapp.client;

import com.HealthCare.webapp.dto.PatientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "patient")
public interface PatientFeignClient {

    @PostMapping("/add")
    PatientDTO addPatient(@RequestBody PatientDTO patient);

    @GetMapping("/patients")
    List<PatientDTO> getAllPatients();

    @GetMapping("/patients/{id}")
    PatientDTO getPatientById(@PathVariable("id") String id);

    @GetMapping("/patients/find/{email}")
    PatientDTO getPatientByEmail(@PathVariable("email") String email);

    @PutMapping("/patients/{id}")
    PatientDTO updatePatient(@PathVariable("id") String id, @RequestBody PatientDTO patient);

    @DeleteMapping("/patients/{id}")
    void deletePatient(@PathVariable("id") String id);
}