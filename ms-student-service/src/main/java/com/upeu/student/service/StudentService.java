package com.upeu.student.service;

import com.upeu.student.entity.Student;
import com.upeu.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Método para crear o actualizar un estudiante
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    // Obtener todos los estudiantes
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Obtener estudiante por ID
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    // Eliminar un estudiante
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
