package com.upeu.student.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;  // Usando jakarta.persistence para estar alineado con Spring Boot 3.x

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String degree;

    @Column(unique = true)
    private String studentNumber;

    private String status; // Activo o inactivo
}
