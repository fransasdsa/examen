package com.upeu.student.repository;

import com.upeu.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    // Métodos de consulta personalizados si es necesario
}
