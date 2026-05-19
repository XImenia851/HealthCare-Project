package com.HealthCare.webapp.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PatientDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String gender;
    private LocalDate birthDate;

    // même si le microservice patient ne le stocke pas encore, on anticipe.
    private String password;
}