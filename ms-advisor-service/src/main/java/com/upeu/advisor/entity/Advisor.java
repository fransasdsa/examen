package com.upeu.advisor.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "advisors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Advisor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String firstName;

    @NotBlank(message = "El apellido es obligatorio")
    private String lastName;

    @Email(message = "El correo electrónico debe ser válido")
    @NotBlank(message = "El correo electrónico es obligatorio")
    private String email;

    private String phoneNumber;

    @NotBlank(message = "La especialización es obligatoria")
    private String specialization; // Línea de investigación

    private double rating; // Calificación promedio (1 a 5)

    @OneToMany(mappedBy = "advisor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Project> projects;

    @OneToMany(mappedBy = "advisor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<AdvisorRating> ratings;
}
