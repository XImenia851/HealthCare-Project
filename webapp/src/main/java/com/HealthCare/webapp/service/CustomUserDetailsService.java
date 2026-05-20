package com.HealthCare.webapp.service;

import com.HealthCare.webapp.client.PatientFeignClient;
import com.HealthCare.webapp.dto.PatientDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final PatientFeignClient patientFeignClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            // 1. On appelle le microservice via Feign pour récupérer le patient
            PatientDTO patient = patientFeignClient.getPatientByEmail(username);

            if (patient == null) {
                throw new UsernameNotFoundException("Utilisateur non trouvé avec l'email : " + username);
            }

            // 2. On convertit notre PatientDTO en un objet UserDetails que Spring Security comprend
            return User.builder()
                    .username(patient.getEmail())
                    .password(patient.getPassword()) // Ce sera le mot de passe haché de la BDD
                    .roles("USER") // Tu pourras gérer des vrais rôles plus tard
                    .build();

        } catch (Exception e) {
            throw new UsernameNotFoundException("Erreur lors de la récupération de l'utilisateur", e);
        }
    }
}