package com.HealthCare.webapp.controller;

import com.HealthCare.webapp.client.PatientFeignClient;
import com.HealthCare.webapp.dto.PatientDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class DashBoardController {

    private final PatientFeignClient patientFeignClient;

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model) {
        String email = authentication.getName();
        PatientDTO patient = patientFeignClient.getPatientByEmail(email);

        model.addAttribute("patient", patient);
        model.addAttribute("patients", patientFeignClient.getAllPatients());
        model.addAttribute("activePage", "dashboard");
        return "dashboard";
    }

    @GetMapping("/patients/edit/{id}")
    public String editForm(@PathVariable String id, Model model) {
        model.addAttribute("patientEdit", patientFeignClient.getPatientById(id));
        model.addAttribute("activePage", "dashboard");
        return "patient-edit";
    }

    @PostMapping("/patients/edit/{id}")
    public String updatePatient(@PathVariable String id,
                                @ModelAttribute("patientEdit") PatientDTO patientEdit) {
        patientFeignClient.updatePatient(id, patientEdit);
        return "redirect:/dashboard";
    }

    @PostMapping("/patients/delete/{id}")
    public String deletePatient(@PathVariable String id) {
        patientFeignClient.deletePatient(id);
        return "redirect:/dashboard";
    }
}