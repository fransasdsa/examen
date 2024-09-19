package com.upeu.student.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;  // Importando desde Jakarta
import jakarta.validation.constraints.NotNull;  // Validaciones
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 100) // Valida que el nombre no esté vacío y tenga un tamaño adecuado
    private String firstName;

    @NotNull
    @Size(min = 1, max = 100) // Valida que el apellido no esté vacío
    private String lastName;

    @NotNull
    @Email // Valida que sea un formato de email válido
    private String email;

    @NotNull
    private String degree;

    @Column(unique = true)
    @NotNull
    private String studentNumber;

    @NotNull
    private String status; // Activo o inactivo
}
