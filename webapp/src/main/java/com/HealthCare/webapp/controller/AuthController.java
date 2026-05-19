package com.HealthCare.webapp.controller;

import com.HealthCare.webapp.client.PatientFeignClient;
import com.HealthCare.webapp.dto.PatientDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class AuthController {

    private final PatientFeignClient patientFeignClient;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Affiche la page d'inscription et prépare un objet PatientDTO vide pour le formulaire Thymeleaf
    @GetMapping("/signUp")
    public String signUpForm(Model model) {
        model.addAttribute("patient", new PatientDTO());
        return "signup";
    }

    @PostMapping("/signUp")
    public String registerPatient(@ModelAttribute("patient") PatientDTO patient, Model model) {
        try {
            // Appel du microservice 'patient' via OpenFeign
            patientFeignClient.addPatient(patient);

            return "redirect:/login?success";
        } catch (Exception e) {
            model.addAttribute("error", "Une erreur est survenue lors de l'inscription. L'email est peut-être déjà utilisé.");
            return "signup";
        }
    }
}