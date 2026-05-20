package org.ximenia851.patient.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Patient {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank (message = "can't be blank")
    private String firstName;
    @NotBlank (message = "can't be blank")
    private String lastName;
    @NotBlank (message = "can't be blank")
    @Email
    @Column(unique = true)
    private String email;
    @NotBlank
    private String password;
    @NotBlank (message = "can't be blank")
    @Pattern(regexp = "[0-9]{10}", message = "can't be empty")
    private String phoneNumber;
    @NotBlank (message = "can't be blank")
    private String address;
    @NotBlank (message = "can't be blank")
    private String gender;
    @NotNull(message = "can't be empty")
    @Past(message = "you are not in the futur.")
    private LocalDate birthDate;
}
