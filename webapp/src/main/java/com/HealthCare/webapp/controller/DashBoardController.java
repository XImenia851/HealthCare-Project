package com.HealthCare.webapp.controller;

import com.HealthCare.webapp.client.PatientFeignClient;
import com.HealthCare.webapp.dto.PatientDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class DashBoardController {

    private final PatientFeignClient patientFeignClient;

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model) {
        String email = authentication.getName();
        PatientDTO patient = patientFeignClient.getPatientByEmail(email);
        model.addAttribute("patient", patient);
        return "dashboard";
    }
}