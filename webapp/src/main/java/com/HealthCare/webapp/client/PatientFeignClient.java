package com.HealthCare.webapp.client;

import com.HealthCare.webapp.dto.PatientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "patient")
public interface PatientFeignClient {

    @PostMapping("/add")
    PatientDTO addPatient(@RequestBody PatientDTO patient);

    @GetMapping("/patients/find/{email}")
    PatientDTO getPatientByEmail(@PathVariable("email") String email);
}