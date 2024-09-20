package com.upeu.degree.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "degrees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Degree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;   // Nombre del grado (por ejemplo, Bachillerato, Maestría)
    private String description;  // Descripción del grado
    private String level;   // Nivel del grado (por ejemplo, Licenciatura, Maestría, Doctorado)
    private String status;  // Estado del grado (por ejemplo, en progreso, finalizado)
}
